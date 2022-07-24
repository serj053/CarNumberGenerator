import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RouteCalculatorTest extends TestCase {

    StationIndex stationIndex = new StationIndex();
    RouteCalculator calculator = getRouteCalculator();

    private RouteCalculator getRouteCalculator() {
        return new RouteCalculator(stationIndex);
    }

    public StationIndex getStationIndex() {
        return stationIndex;
    }

    @Override//метод в котором инициализируются данные для проверки
    protected void setUp() throws Exception {
        super.setUp();
        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");
        Line line4 = new Line(4, "Четвертая");

        stationIndex.addConnection(new ArrayList<>() {{
            add(new Station("Капитализм", line1));
            add(new Station("Путинизм", line2));
            add(new Station("Честные выборы", line3));
        }});

        stationIndex.addConnection(new ArrayList<>() {{
            add(new Station("Коммунизм", line3));
            add(new Station("Мировоззрение", line4));
        }});

        stationIndex.addStation(new Station("Капитализм", line1));
        stationIndex.addStation(new Station("Социализм", line1));
        stationIndex.addStation(new Station("Путинизм", line2));
        stationIndex.addStation(new Station("Фашизм", line2));
        stationIndex.addStation(new Station("Честные выборы", line3));
        stationIndex.addStation(new Station("Достойная жизнь", line3));
        stationIndex.addStation(new Station("Коммунизм", line3));
        stationIndex.addStation(new Station("Мировоззрение", line4));
        stationIndex.addStation(new Station("Мораль", line2));
        stationIndex.addStation(new Station("История", line4));

        line1.addStation(stationIndex.getStation("Капитализм"));
        line1.addStation(stationIndex.getStation("Социализм"));
        line2.addStation(stationIndex.getStation("Путинизм"));
        line2.addStation(stationIndex.getStation("Фашизм"));
        line3.addStation(stationIndex.getStation("Честные выборы"));
        line3.addStation(stationIndex.getStation("Достойная жизнь"));
        line3.addStation(stationIndex.getStation("Коммунизм"));
        line4.addStation(stationIndex.getStation("Мировоззрение "));
        line4.addStation(stationIndex.getStation("Мораль"));
        line4.addStation(stationIndex.getStation("История"));
        //line4.addStation();

    }

    //проверка метода getShortestRoute()
    public void testGetShortestRoute() {

    }

    //проверка работы метода calculateDuration()
    public void testCalculateDuration() {
        List<Station> route = new ArrayList<>() {{
            add(stationIndex.getStation("Капитализм"));
            add(stationIndex.getStation("Социализм"));
            add(stationIndex.getStation("Путинизм"));
        }};
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 6;
        assertEquals(expected, actual);
    }

    //проверка метода getRouteOnTheLine()
    public void testGetRouteOnTheLine() {
        List<Station> actual = calculator.getShortestRoute(stationIndex.getStation("Капитализм"),
                stationIndex.getStation("Социализм"));
        List<Station> expected = new ArrayList<>() {{
            add(stationIndex.getStation("Капитализм"));
            add(stationIndex.getStation("Социализм"));
        }};

        assertEquals(expected, actual);
    }

    //проверка метода getRouteWithOneConnection()
    public void testGetRouteWithOneConnection() {
        List<Station> actual = calculator.getShortestRoute(stationIndex.getStation("Капитализм"),
                stationIndex.getStation("Социализм"));
        List<Station> expected = new ArrayList<>() {{
            add(stationIndex.getStation("Капитализм"));
            add(stationIndex.getStation("Социализм"));
        }};

        assertEquals(expected, actual);
    }


    //
    public void testGetRouteWithTwoConnections() {
        Station from = stationIndex.getStation("Социализм");
        Station to = stationIndex.getStation("Мораль");
        List<Station> actual = new ArrayList<>();
        actual = calculator.getShortestRoute(from, to);
        List<Station> expected = new ArrayList<>() {{

            add(stationIndex.getStation("Социализм"));
            add(stationIndex.getStation("Капитализм"));
            add(stationIndex.getStation("Честные выборы"));
            add(stationIndex.getStation("Путинизм"));
            add(stationIndex.getStation("Мораль"));
        }};

        assertEquals(expected, actual);
    }


    @Override//метод для удаления проверяемых данных
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
