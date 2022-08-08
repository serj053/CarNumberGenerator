import core.Line;
import core.Station;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static Logger logger;
    private static final Marker  INPUT_HISTORY_MARKER = MarkerManager.getMarker("INPUT_HISTORY");
    private static final Marker  INVALID_STATIONS_MARKER = MarkerManager.getMarker("INVALID_STATIONS");
    private static final String DATA_FILE = "ExceptionsDebuggingAndTesting/homework_2/SPBMetro/src/main/resources/map.json";
    private static Scanner scanner;

    private static StationIndex stationIndex;

    public static void main(String[] args) {

        logger = LogManager.getRootLogger();

    /*получаем объект RouteCalculator, который содержит методы подсчет и создаем объект
    StationIndex() и заполняет его данными из jason файла линии метро, станции и пересадки
    */
        RouteCalculator calculator = getRouteCalculator();


        System.out.println("Программа расчёта маршрутов метрополитена Санкт-Петербурга\n");
        scanner = new Scanner(System.in);
        for (; ; ) {
            //вводим имя станции, проверяем есть такая в списке станций и возвращаем имя проверенной
            //станции перемененную или сообщение о том что станция не найдена
            try {
                Station from = takeStation("Введите станцию отправления:");
                Station to = takeStation("Введите станцию назначения:");
                //формируем маршрут до станции назначения
                List<Station> route = calculator.getShortestRoute(from, to);
                System.out.println("Маршрут:");
                printRoute(route);

                System.out.println("Длительность: " +
                        RouteCalculator.calculateDuration(route) + " минут");
            } catch (Exception e) {
                logger.error("Ошибка выполнения программы");
            }
        }
    }

    private static RouteCalculator getRouteCalculator() {
        createStationIndex();
        return new RouteCalculator(stationIndex);
    }

    private static void printRoute(List<Station> route) {
        Station previousStation = null;
        for (Station station : route) {
            if (previousStation != null) {
                Line prevLine = previousStation.getLine();
                Line nextLine = station.getLine();
                if (!prevLine.equals(nextLine)) {
                    System.out.println("\tПереход на станцию " +
                            station.getName() + " (" + nextLine.getName() + " линия)");
                }
            }
            System.out.println("\t" + station.getName());
            previousStation = station;
        }
    }

    //проверка есть ли введенная станция в списке станций
    private static Station takeStation(String message) throws Exception {
        for (; ; ) {
            System.out.println(message);
            String line = scanner.nextLine().trim();//получаем строку с консоли
            if (line.equals("try")) {
                throw new Exception("Вызываем исключения");
            }
            //проверяем есть ли переданная станция в списке станций
            Station station = stationIndex.getStation(line);
            if (station != null) {
                //фиксируем запрашиваемые станции
                logger.info(INPUT_HISTORY_MARKER,message + station.getName());
                return station;//если есть, то вернуть её
            }
            System.out.println("Неверное имя станции.:))");
            //фиксация ошибок при вызове
            logger.warn(INVALID_STATIONS_MARKER, "logger Неверное имя станции  " + line);
        }
    }

    private static void createStationIndex() {
        stationIndex = new StationIndex();
        try {
            JSONParser parser = new JSONParser();//объект для работы с файлом json
            JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());//получили строку из файла

            JSONArray linesArray = (JSONArray) jsonData.get("lines");//создали массив строк
            //передаем данные в объект stationIndex
            parseLines(linesArray);//
            //передаем данные в объект stationIndex
            JSONObject stationsObject = (JSONObject) jsonData.get("stations");
            parseStations(stationsObject);
            //передаем данные в объект stationIndex
            JSONArray connectionsArray = (JSONArray) jsonData.get("connections");
            parseConnections(connectionsArray);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void parseConnections(JSONArray connectionsArray) {
        connectionsArray.forEach(connectionObject ->
        {
            JSONArray connection = (JSONArray) connectionObject;
            List<Station> connectionStations = new ArrayList<>();
            connection.forEach(item ->
            {
                JSONObject itemObject = (JSONObject) item;
                int lineNumber = ((Long) itemObject.get("line")).intValue();
                String stationName = (String) itemObject.get("station");

                Station station = stationIndex.getStation(stationName, lineNumber);
                if (station == null) {
                    throw new IllegalArgumentException("core.Station " +
                            stationName + " on line " + lineNumber + " not found");
                }
                connectionStations.add(station);
            });
            stationIndex.addConnection(connectionStations);
        });
    }

    private static void parseStations(JSONObject stationsObject) {
        stationsObject.keySet().forEach(lineNumberObject ->
        {
            int lineNumber = Integer.parseInt((String) lineNumberObject);
            Line line = stationIndex.getLine(lineNumber);
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            stationsArray.forEach(stationObject ->
            {
                Station station = new Station((String) stationObject, line);
                stationIndex.addStation(station);
                line.addStation(station);
            });
        });
    }

    private static void parseLines(JSONArray linesArray) {
        linesArray.forEach(lineObject -> {
            JSONObject lineJsonObject = (JSONObject) lineObject;
            Line line = new Line(
                    ((Long) lineJsonObject.get("number")).intValue(),
                    (String) lineJsonObject.get("name")
            );
            stationIndex.addLine(line);
        });
    }

    //получаем из файла строку
    private static String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            //readAllLines возвращает строки из файла как список
            List<String> lines = Files.readAllLines(Paths.get(DATA_FILE));
            lines.forEach(line -> builder.append(line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
}

/*
* <plugin>
        <artifactId>maven-assembly-plugin</artifactId>
        <executions>
          <execution>
            <phase>package</phase>
            <goals>
              <goal>single</goal>
            </goals>
          </execution>
        </executions>
        <configuration>
          <archive>
            <manifest>
              <addClasspath>true</addClasspath>
              <mainClass>Main</mainClass>
            </manifest>
          </archive>
          <descriptorRefs>
            <descriptorRef>jar-with-dependencies</descriptorRef>
          </descriptorRefs>
        </configuration>

      </plugin>
* */