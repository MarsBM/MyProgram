package model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Created by Мар'ян on 05.05.2016.
 */

@Entity
public class Role extends Model {

    private String title;

    private Set<Employee> employees;

    public Role() {
        super();
    }

    public Role(String title) {
        super();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @OneToMany(mappedBy = "role")
    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
