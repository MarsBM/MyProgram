package model;

import com.opencsv.CSVReader;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Мар'ян on 05.05.2016.
 */
@Entity
public class Employee extends Model {

    private String firstName;
    private String lastName;
    private int salary;

    private Role role;

    public Employee() {
        super();
    }

    public Employee(String firstName, String lastName, int salary) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @ManyToOne
    @JoinColumn(name = "role_id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static Set<Employee> openFromCSV(String fileName, char separator, boolean isHeader, boolean isId) throws IOException {
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
