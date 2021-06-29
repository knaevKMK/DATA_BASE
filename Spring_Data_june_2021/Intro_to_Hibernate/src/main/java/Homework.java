import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class Homework {
    public static void Ex2(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        int update = entityManager.createQuery("UPDATE Town  t " +
                "SET t.name = upper(t.name) " +
                "WHERE length(t.name)>=5 ")
                .executeUpdate();
        System.out.println(update);
        entityManager.getTransaction().commit();
    }

    public static void Ex3(EntityManager entityManager, String fullName) {
        int result = entityManager.createQuery(
                "SELECT e FROM Employee AS e " +
                        " WHERE concat(e.firstName, e.lastName)=  :fullName "
                , Employee.class)
                .setParameter("fullName", fullName)
                .getResultList().size();

        System.out.println(result == 0 ? "No" : "Yes");
    }

    public static void Ex4(EntityManager entityManager) {
        entityManager.createQuery(
                "SELECT e FROM Employee AS e WHERE e.salary>50000", Employee.class
        ).getResultStream()
                .forEach(e -> System.out.println(e.getFirstName()));
    }

    public static void Ex5(EntityManager entityManager) {
        entityManager.createQuery(
                "SELECT e FROM Employee AS e " +
                        "WHERE e.department.name = 'Research and Development' " +
                        "ORDER BY e.salary, e.id", Employee.class
        ).getResultStream()
                .forEach(e -> System.out.printf("%s %s from Research and Development - $%.2f%n",
                        e.getFirstName(), e.getLastName(), e.getSalary()));
    }
}
