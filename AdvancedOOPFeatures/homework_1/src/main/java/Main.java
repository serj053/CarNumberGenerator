import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static final String STAFF_TXT = "AdvancedOOPFeatures/homework_1/data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);

        sortBySalaryAndAlphabet(staff);
        //System.out.println(staff);
    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        //сортируем по зарплатам сотрудников
        staff.sort((o1, o2) -> Integer.compare(o1.getSalary(), o2.getSalary()));

        //сортируем по фамилиям сотрудников если у них одинаковая зарплата
        ArrayList<Employee> sorted = new ArrayList<>();
        int i , j;
        for (i = 0; i < staff.size(); i = i + j) {
            for (j = 0; j < staff.size(); ) {
                if ((i + j) > staff.size() - 1) {
                    break;
                }
                if (staff.get(i).getSalary().intValue() == staff.get(i + j).getSalary().intValue()) {
                    //staff.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
                    sorted.add(staff.get(j + i));
                    j++;
                } else {
                    /**/
                    sorted.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
                    for (int ins = 0; ins < j; ins++) {
                        staff.set((ins + i), sorted.get(ins));
                    }
                    /*обнулить временный контейнер*/
                    sorted.removeAll(sorted);
                    break;
                }
            }
        }
    }
}