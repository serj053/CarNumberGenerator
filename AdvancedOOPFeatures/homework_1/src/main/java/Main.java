import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static final String STAFF_TXT = "AdvancedOOPFeatures/homework_1/data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);

        sortBySalaryAndAlphabet(staff);

        for (Employee eml: staff ) {
            System.out.println(eml.getName() + " " + eml.getSalary());
        }
        //System.out.println(staff);
    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        Comparator<Employee> compare = Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName);
        staff.sort(compare);
    }
}