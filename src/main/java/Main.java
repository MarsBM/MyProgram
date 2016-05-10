import model.Employee;
import model.Role;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Мар'ян on 04.05.2016.
 */
public class Main {
    private static boolean openFromFile = false;
    public static void main(String[] args) throws IOException {

        SessionFactory sessionFactory = HibernateUnit.getSessionFactory();
        Session session = sessionFactory.openSession();
        if (openFromFile) {
            List<String[]> roles = new CSVToList("E://role.csv").getFileBody();
            List<String[]> employees = new CSVToList("E://employees.csv").getFileBody();
            Set<Role> roleSet = new HashSet<>();
            Set<Employee> employeeSet = new HashSet<>();
            for (int i = 1; i < roles.size(); i++) {
                for (String role : roles.get(i)) {
                    roleSet.add(new Role(role.split(";")[1]));
                }
            }
            for (int i = 1; i < employees.size(); i++) {
                for (String employe : employees.get(i)) {
                    String fn = employe.split(";")[1];
                    String ln = employe.split(";")[2];
                    int slr = Integer.parseInt(employe.split(";")[3]);
                    int rn = Integer.parseInt(employe.split(";")[4]);
                    Object[] rls = roleSet.toArray();
                    Role rl = (Role) rls[rn-1];
                    Employee e = new Employee(fn, ln, slr);
                    e.setRole(rl);
                    employeeSet.add(e);
                }
            }
        }

        try {
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.add(Restrictions.eq("id", 2L));
            Employee employee = (Employee) criteria.uniqueResult();
            Role role = (Role) session.createCriteria(Role.class).add(Restrictions.eq("id", 4L)).uniqueResult();
            System.out.println(role.getTitle());
            employee.setRole(role);
            session.update(employee);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }


    }
}
