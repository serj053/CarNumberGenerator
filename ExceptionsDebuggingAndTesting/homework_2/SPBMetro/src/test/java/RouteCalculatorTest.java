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

    @Override//����� � ������� ���������������� ������ ��� ��������
    protected void setUp() throws Exception {
        super.setUp();
        Line line1 = new Line(1, "������");
        Line line2 = new Line(2, "������");
        Line line3 = new Line(3, "������");
        Line line4 = new Line(4, "���������");

        stationIndex.addConnection(new ArrayList<>() {{
            add(new Station("����������", line1));
            add(new Station("��������", line2));
            add(new Station("������� ������", line3));
        }});

        stationIndex.addConnection(new ArrayList<>() {{
            add(new Station("���������", line3));
            add(new Station("�������������", line4));
        }});

        stationIndex.addStation(new Station("����������", line1));
        stationIndex.addStation(new Station("���������", line1));
        stationIndex.addStation(new Station("��������", line2));
        stationIndex.addStation(new Station("������", line2));
        stationIndex.addStation(new Station("������� ������", line3));
        stationIndex.addStation(new Station("��������� �����", line3));
        stationIndex.addStation(new Station("���������", line3));
        stationIndex.addStation(new Station("�������������", line4));
        stationIndex.addStation(new Station("������", line2));
        stationIndex.addStation(new Station("�������", line4));

        line1.addStation(stationIndex.getStation("����������"));
        line1.addStation(stationIndex.getStation("���������"));
        line2.addStation(stationIndex.getStation("��������"));
        line2.addStation(stationIndex.getStation("������"));
        line3.addStation(stationIndex.getStation("������� ������"));
        line3.addStation(stationIndex.getStation("��������� �����"));
        line3.addStation(stationIndex.getStation("���������"));
        line4.addStation(stationIndex.getStation("������������� "));
        line4.addStation(stationIndex.getStation("������"));
        line4.addStation(stationIndex.getStation("�������"));
        //line4.addStation();

    }

    //�������� ������ getShortestRoute()
    public void testGetShortestRoute() {

    }

    //�������� ������ ������ calculateDuration()
    public void testCalculateDuration() {
        List<Station> route = new ArrayList<>() {{
            add(stationIndex.getStation("����������"));
            add(stationIndex.getStation("���������"));
            add(stationIndex.getStation("��������"));
        }};
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 6;
        assertEquals(expected, actual);
    }

    //�������� ������ getRouteOnTheLine()
    public void testGetRouteOnTheLine() {
        List<Station> actual = calculator.getShortestRoute(stationIndex.getStation("����������"),
                stationIndex.getStation("���������"));
        List<Station> expected = new ArrayList<>() {{
            add(stationIndex.getStation("����������"));
            add(stationIndex.getStation("���������"));
        }};

        assertEquals(expected, actual);
    }

    //�������� ������ getRouteWithOneConnection()
    public void testGetRouteWithOneConnection() {
        List<Station> actual = calculator.getShortestRoute(stationIndex.getStation("����������"),
                stationIndex.getStation("���������"));
        List<Station> expected = new ArrayList<>() {{
            add(stationIndex.getStation("����������"));
            add(stationIndex.getStation("���������"));
        }};

        assertEquals(expected, actual);
    }


    //
    public void testGetRouteWithTwoConnections() {
        Station from = stationIndex.getStation("���������");
        Station to = stationIndex.getStation("������");
        List<Station> actual = new ArrayList<>();
        actual = calculator.getShortestRoute(from, to);
        List<Station> expected = new ArrayList<>() {{

            add(stationIndex.getStation("���������"));
            add(stationIndex.getStation("����������"));
            add(stationIndex.getStation("������� ������"));
            add(stationIndex.getStation("��������"));
            add(stationIndex.getStation("������"));
        }};

        assertEquals(expected, actual);
    }


    @Override//����� ��� �������� ����������� ������
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
