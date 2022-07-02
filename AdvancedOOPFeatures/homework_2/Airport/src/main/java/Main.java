import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        List<Terminal> tt = airport.getTerminals();
        List<Flight> f = tt.get(0).getFlights();
        System.out.println("getType " + f.get(0).getType());

        List<Flight> getFlight = new ArrayList<>();
        getFlight = findPlanesLeavingInTheNextTwoHours(airport);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy.MM.dd  HH:mm.ss");
        for (Flight fly : getFlight) {
            System.out.println(fly);
        }

    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        //TODO Метод должен вернуть список рейсов вылетающих в ближайшие два часа.
        List<Terminal> terminal = airport.getTerminals();
        List<Flight> sum = new ArrayList<>();//
        //собираем все данные о вылетах с разных терминалов в один список
        for (Terminal value : terminal) {
            sum.addAll(value.getFlights());
        }

        //готовим граничные переменные
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusHours(2);
        //контейнер для сбора результата
        List<Flight> flights = new ArrayList<>();
        for (Flight flt : sum) {
            LocalDateTime flightTime = flt.getDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            if ((flightTime.isEqual(startTime) || flightTime.isEqual(endTime)) ||
                    (flightTime.isBefore(endTime) && flightTime.isAfter(startTime)
                            && flt.getType().toString().equals("DEPARTURE"))) {

                flights.add(flt);
            }
        }
        return flights;
    }

}