import com.opencsv.CSVReader;
import model.Employee;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Мар'ян on 10.05.2016.
 */
public class EmployeeCSVReader {
    public static Set<Employee> read(String fileName, char separator, boolean isHeader, boolean isId) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(fileName), separator);
        List<String[]> employees = reader.readAll();
        Set<Employee> employeeSet = new HashSet<>();
        for (int i = isHeader ? 1 : 0; i < employees.size(); i++) {
            for (int j = isId ? 1 : 0; j < employees.get(0).length; j++) {
                String firstName = employees.get(0)[j];
                String lastName = employees.get(0)[j+1];
            }
            for (String employe : employees.get(i)) {
                String[] split = employe.split(String.valueOf(separator));
                String firstName = split[1];
                String lastName = split[2];
                int salary = Integer.parseInt(split[3]);
                employeeSet.add(new Employee(firstName, lastName, salary));
            }
        }
        reader.close();
        return employeeSet;
    }
}
