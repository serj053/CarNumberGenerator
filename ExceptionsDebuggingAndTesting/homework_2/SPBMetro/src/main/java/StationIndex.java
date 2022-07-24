import core.Line;
import core.Station;

import java.util.*;
import java.util.stream.Collectors;

public class StationIndex {
    private final Map<Integer, Line> number2line;
    private final TreeSet<Station> stations;
    /*коллекция, где ключ-станция значение - дерево станций
    коллекция станций пересадки для пересадочного узла*/
    private final Map<Station, TreeSet<Station>> connections;

    public StationIndex() {
        number2line = new HashMap<>();
        stations = new TreeSet<>();
        connections = new TreeMap<>();
    }

    public void addStation(Station station) {
        stations.add(station);
    }

    public void addLine(Line line) {
        number2line.put(line.getNumber(), line);
    }
    //добавляем в коллекцию список станций к существующему узлу либо создается новый узел в который
    //вносятся станции из передаваемого списка и все это добавляется в коллекцию
    public void addConnection(List<Station> stations) {
        for (Station station : stations) {
            /*если коллекция класса не содержит станцию, то добавить в коллекцию и станцию и пустое дерево*/
            if (!connections.containsKey(station)) {
                connections.put(station, new TreeSet<>());
            }
            /*создаем ссылку TreeSet на TreeSet по ключу station то есть получаем список всех
            станций которые находятся на одной линии с рассматриваемой станцией*/
            TreeSet<Station> connectedStations = connections.get(station);
            /*к полученному списку добавляем все станции из нового списка переданного через
            параметр метода если этих станций нет в исходном списке*/
            connectedStations.addAll(stations.stream()
                    .filter(s -> !s.equals(station)).collect(Collectors.toList()));
        }
    }
    //получение названия линии по номеру
    public Line getLine(int number) {
        return number2line.get(number);
    }
//если переданная станция есть в списке, то вернуть эту станцию
    public Station getStation(String name) {
        for (Station station : stations) {
            if (station.getName().equalsIgnoreCase(name)) {
                return station;
            }
        }
        return null;
    }

    public Station getStation(String name, int lineNumber) {
        Station query = new Station(name, getLine(lineNumber));
        Station station = stations.ceiling(query);
        return station.equals(query) ? station : null;
    }
    //возвращает список (дерево) станций на пересадочном узле
    //или пустой список(дерево) если такой станции в коллекции нет
    public Set<Station> getConnectedStations(Station station) {
        return connections.containsKey(station) ?//если коллекция содержит станцию
                //то, вернуть список станций на которые можно пересесть
                //в противном случае вернуть пустой список
                connections.get(station) : new TreeSet<>();
    }
}
