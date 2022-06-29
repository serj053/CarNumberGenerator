import org.checkerframework.checker.units.qual.C;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Main {

    private static final String STAFF_TXT = "AdvancedOOPFeatures/homework_1/data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        Employee employeeMaxSalary = findEmployeeWithHighestSalary(staff, 2017);
        System.out.println(employeeMaxSalary);


    }

    // Метод должен вернуть сотрудника с максимальной зарплатой среди тех,
    // кто пришёл в году, указанном в переменной year
    public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {
        return staff.stream().map(e -> {
                    if (toCalendar(e.getWorkStart()).get(Calendar.YEAR) == year) {
                        return e;
                    }
                    return null;
                }).filter(Objects::nonNull)
                //.max((e1, e2) -> Integer.compare(e1.getSalary(), e2.getSalary()))
                .max(Comparator.comparingInt(Employee::getSalary)).get();
    }

    public static Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // cal.get(Calendar.YEAR);
        return cal;
    }
}
/*

 List<Date> list =  staff.stream().map(Employee::getWorkStart).filter(d -> {
                    Calendar calendar = toCalendar(d);
                    return calendar.get(Calendar.YEAR) == 2017;
                }).collect(Collectors.toList());

        for (Date dt:list ) {
            System.out.println("dt "+ dt);
        }



 Calendar call = toCalendar(dft);
                    call.get(Calendar.YEAR);


* staff.stream().map(Employee::getWorkStart)//получили список значений
                    .filter(date -> {
                        try {
                            return Objects.equals(date, dt.parse("12.07.2016"));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }).forEach(System.out::println);
                    *
                    * date -> {
                        try {
                            return date.equals(dt.parse("12.07.2016"));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }

                     //Calendar call;
        //cal.setTime(dft);
        //Date date = dt.parse("12.07.2016");
* */