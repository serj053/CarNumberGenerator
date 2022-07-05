import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.skillbox.airport.Flight.Type.DEPARTURE;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();

        List<Flight> getFlights ;
        getFlights = findPlanesLeavingInTheNextTwoHours(airport);

        for (Flight fly : getFlights) {
            System.out.println(fly);
        }

    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        List<Flight> flights = new ArrayList<>();
        airport.getTerminals().forEach(t -> t.getFlights().stream()
                .filter(f -> f.getType().toString().equals("DEPARTURE"))
                .filter(f -> {
                    LocalDateTime startTime = LocalDateTime.now();
                    LocalDateTime endTime = startTime.plusHours(2);
                    LocalDateTime flightTime = f.getDate().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime();
                    if ((flightTime.isEqual(startTime) || flightTime.isEqual(endTime)) ||
                            (flightTime.isBefore(endTime) && flightTime.isAfter(startTime)
                                    && f.getType().toString().equals("DEPARTURE"))) {
                        flights.add(f);
                    }
                    return false;
                }).forEach(flights::add));
        return flights;
    }
}

/*
*
* */