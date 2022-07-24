import core.Station;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RouteCalculator {
    private final StationIndex stationIndex;

    private static final double INTER_STATION_DURATION = 2.5;
    private static final double INTER_CONNECTION_DURATION = 3.5;

    public RouteCalculator(StationIndex stationIndex) {
        this.stationIndex = stationIndex;
    }

    public List<Station> getShortestRoute(Station from, Station to) {
        /*собрать все станции по выбранному маршруту*/
        //если разные линии, то возвращается ноль и дальше по коду
        List<Station> route = getRouteOnTheLine(from, to);
        //если станции находятся на одной линии, то вернуть маршрут между станциями
        if (route != null) {
            return route;
        }
        //если на одной линии, то возвращается ноль и пустой список route
        route = getRouteWithOneConnection(from, to);
        //если разные линии, то вернуть маршрут с одной пересадкой
        if (route != null) {
            return route;
        }

        route = getRouteWithTwoConnections(from, to);
        return route;
    }

    public static double calculateDuration(List<Station> route) {
        double duration = 0;
        Station previousStation = null;
        for (int i = 0; i < route.size(); i++) {
            Station station = route.get(i);
            if (i > 0) {
                duration += previousStation.getLine().equals(station.getLine()) ?
                        INTER_STATION_DURATION : INTER_CONNECTION_DURATION;
            }
            previousStation = station;
        }
        return duration;
    }

    /*собираем все станции по маршруту от станции отправления до станции прибытия на одной линии*/
    private List<Station> getRouteOnTheLine(Station from, Station to) {
        /*если станции находятся на разных линиях, то вернуть null*/
        if (!from.getLine().equals(to.getLine())) {
            return null;
        }
        List<Station> route = new ArrayList<>();//пустой список станций
        //собираем все станции в спиcок stations по линии, где находится станция "from"
        List<Station> stations = from.getLine().getStations();
        int direction = 0;
        //проходим все станции по списку
        for (Station station : stations) {
            //пока нет совпадения direction = 0 и таким образом доходим первого совпадение
            if (direction == 0) {//
        //если станция совпала со станцией отправления, то присваиваем 1
                if (station.equals(from)) {//
                    direction = 1;
        //если станция совпала со станцией прибытия, то присваиваем -1
                } else if (station.equals(to)) {//
                    direction = -1;
                }
            }
            //добавляем станцию в список маршрут route если direction = -1 то добавляются
            //все станции с начала списка и до станции "from"
            if (direction != 0) {
                route.add(station);
            }
            /* при 1 добавляем станции в лист пока не достигнем станции прибытия или
            * при - 1 добавляем станции пока не достигнем станции отправления*/
            if ((direction == 1 && station.equals(to)) ||
                    (direction == -1 && station.equals(from))) {
                break;
            }
        }
        if (direction == -1) {
            Collections.reverse(route);
        }
        return route;
    }

    //получить маршрут с одной пересадкой
    private List<Station> getRouteWithOneConnection(Station from, Station to) {
        /*если станции находятся на одной линии, то вернуть null*/
        if (!from.getLine().equals(to.getLine())) {
            return null;
        }

        List<Station> route = new ArrayList<>();
        //ссылка на список станций по линии from
        List<Station> fromLineStations = from.getLine().getStations();
        //ссылка на список станций по линии to
        List<Station> toLineStations = to.getLine().getStations();
        //ищем станцию пересечение двух линий
        //перебираем станции по линии начала маршрута
        for (Station srcStation : fromLineStations) {
            //перебираем станции по линии конца маршрута
            for (Station dstStation : toLineStations) {
                //проверка принадлежат ли станции src b dst одному пересадочному узлу
                if (isConnected(srcStation, dstStation)) {//если принадлежат то
                    ArrayList<Station> way = new ArrayList<>();
                    //getRouteOnTheLine() список станций находящихся по маршруту на одной линии
                    //собираем все станции до места пересадки на другую станцию
                    way.addAll(getRouteOnTheLine(from, srcStation));
                    //собираем все станции после места пересадки и до станции назначения
                    way.addAll(getRouteOnTheLine(dstStation, to));
                    if (route.isEmpty() || route.size() > way.size()) {
                        route.clear();
                        route.addAll(way);
                    }
                }
            }
        }
        return route;
    }

    /*проверка находятся ли станции на одном пересадочном узле*/
    private boolean isConnected(Station station1, Station station2) {
        //получаем станции одного пересадочного узла
        Set<Station> connected = stationIndex.getConnectedStations(station1);
        //проверяем находится ли вторая станция на этом же пересадочном узле
        return connected.contains(station2);
    }

    //найти станции пересадки через которые надо добраться до станции назначения
    //если  станции расположены на одной линии то вернуть маршрут между станциями
    private List<Station> getRouteViaConnectedLine(Station from, Station to) {
        //получаем список станций узла пересадки на станции отправления
        Set<Station> fromConnected = stationIndex.getConnectedStations(from);
        //получаем список станций узла пересадки на станции назначения
        Set<Station> toConnected = stationIndex.getConnectedStations(to);
        //изучаем находятся ли какие-либо две рассматриваемые станции на одной линии
        //если находятся то вернуть маршрут
        for (Station srcStation : fromConnected) {
            for (Station dstStation : toConnected) {
                //если станции находятся на одной линии, то вернуть все станции по этой линии
                //которые находятся между стацией отправления и станцией назначения
                if (srcStation.getLine().equals(dstStation.getLine())) {
                    return getRouteOnTheLine(srcStation, dstStation);
                }
            }
        }
        return null;
    }

    private List<Station> getRouteWithTwoConnections(Station from, Station to) {
        //если станции находятся на одной линии, то вернуть null
        if (from.getLine().equals(to.getLine())) {
            return null;
        }

        ArrayList<Station> route = new ArrayList<>();
        // собираем все станции находящиеся на линии станции отправления
        List<Station> fromLineStations = from.getLine().getStations();
        //собираем все станции находящиеся по линии прибытия (назначения)
        List<Station> toLineStations = to.getLine().getStations();

        for (Station srcStation : fromLineStations) {
            //находим те станции которые расположены на одной линии
            for (Station dstStation : toLineStations) {
                List<Station> connectedLineRoute =
                        //если узловые станции расположены на одной линии, то вернуть маршрут между станциями
                        getRouteViaConnectedLine(srcStation, dstStation);
                //поиск узловых станций по маршруту
                //если между узловыми станциями нет линии, то вернуться к началу кода
                if (connectedLineRoute == null) {
                    continue;
                }
                List<Station> way = new ArrayList<>();
                //собираем все станции по маршруту от станции отправления до станции пересадки на одной линии
                way.addAll(getRouteOnTheLine(from, srcStation));
                //добавляем станции которые расположены между узловыми станциями
                // от станции пересадки и до станции второй пересадки
                way.addAll(connectedLineRoute);
                //добавляем станции которые находятся от станции второй пересадки и станции назначения
                way.addAll(getRouteOnTheLine(dstStation, to));
                if (route.isEmpty() || route.size() > way.size()) {
                    route.clear();
                    route.addAll(way);
                }
            }
        }

        return route;
    }
}