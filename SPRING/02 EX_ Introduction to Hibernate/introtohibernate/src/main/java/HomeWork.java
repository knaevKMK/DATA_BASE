import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Locale;

public class HomeWork {

    public static void EX_2(EntityManager entityManager) {
        List<Town> towns = entityManager
                .createQuery("SELECT t FROM Town t WHERE length(t .name)<=5", Town.class)
                .getResultList();

        entityManager.getTransaction().begin();
        towns.forEach(entityManager::detach);
        towns.forEach(t -> t.setName(t.getName().toLowerCase()));
        towns.forEach(entityManager::merge);
        entityManager.getTransaction().commit();
    }

    public static void EX_3(EntityManager entityManager, String fullName) {

        List<Employee> employees = entityManager
                .createQuery("SELECT e FROM Employee e " +
                        "WHERE concat(e.firstName, ' ', e.lastName) =:name ", Employee.class)
                .setParameter("name", fullName)
                .getResultList();
        System.out.println(employees.isEmpty() ? "No" : "Yes");

    }

    public static void EX_4(EntityManager entityManager, double salary) {
        entityManager.createQuery("SELECT e FROM Employee e " +
                "WHERE e.salary>50000", Employee.class)
                .getResultStream()
                .forEach(e -> System.out.println(e.getFirstName()));
    }

    public static void EX_5(EntityManager entityManager, String departName) {
        entityManager.createQuery("SELECT e FROM Employee e " +
                "WHERE e.department.name= :name " +
                "ORDER BY e.salary , e.id", Employee.class)
                .setParameter("name", departName)
                .getResultStream()
                .forEach(e -> System.out.printf("%s %s from %s - $%.2f%n"
                        , e.getFirstName(), e.getLastName(), departName, e.getSalary()));
    }

    public static void EX_6(EntityManager entityManager, String addressNew, String lastName) {
        Address address = new Address();
        address.setText(addressNew);
        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.getTransaction().commit();

        //t1:
        Employee employee = entityManager
                //t1.1
                //.find(Employee.class,291);
                .createQuery("SELECT   e FROM Employee e " +
                        "WHERE e.lastName=:name", Employee.class)
                .setParameter("name", lastName)
                .getSingleResult();

        if (employee == null) {
            System.out.println("The name does NOT exist!");
            return;
        }
        entityManager.getTransaction().begin();
        employee.setAddress(address);
        entityManager.getTransaction().commit();
        //t2: not work
//               entityManager. createQuery("UPDATE Employee e " +
//                        "SET e.address=:address " +
//                        "WHERE e.lastName=:name ", Employee.class)
//                .setParameter("address", address)
//                .setParameter("name", lastName)
//                .executeUpdate();
    }
}
