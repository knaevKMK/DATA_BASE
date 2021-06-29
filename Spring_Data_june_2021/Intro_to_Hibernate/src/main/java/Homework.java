import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
}
