import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static void Ex6(EntityManager entityManager, String lastName) {

        Address address = entityManager.createQuery(
                "SELECT a FROM Address  AS a WHERE a.text= 'Vitoshka 15'", Address.class)
                .getResultStream().findFirst().orElse(null);
        if (address == null) {
            address = new Address();
            address.setText("Vitoshka 15");
            entityManager.getTransaction().begin();
            entityManager.persist(address);
            entityManager.getTransaction().commit();
        }

        Employee employee = entityManager.createQuery(
                "SELECT e FROM Employee AS e WHERE e.lastName= :lastName", Employee.class)
                .setParameter("lastName", lastName)
                .getResultStream().findFirst().orElse(null);

        if (employee == null) {
            System.out.println("Employee with this last name does not exist!");
            return;
        }
        entityManager.getTransaction().begin();
        employee.setAddress(address);
        entityManager.getTransaction().commit();

        entityManager.createQuery("SELECT e FROM Employee  AS e WHERE e.lastName= :lastName"
                , Employee.class)
                .setParameter("lastName", lastName)
                .getResultStream()
                .forEach(e -> System.out.println("Employee with lsat name: " + e.getLastName()
                        + "associated to address: " + e.getAddress().getText()));
    }


    public static void Ex7(EntityManager entityManager) {
        entityManager.createQuery(
                "SELECT a FROM Address AS a ORDER BY a.employees.size DESC "
                , Address.class)
                .getResultStream()
                .limit(10)
                .forEach(a -> System.out.printf("%s, %s, - %d employee%s%n"
                        , a.getText(), a.getTown().getName(), a.getEmployees().size(),
                        a.getEmployees().size() <= 1 ? "" : "s"));

    }

    public static void Ex8(EntityManager entityManager, Integer employeeId) {
        Employee employee = entityManager.createQuery(
                "SELECT  e FROM Employee  AS e WHERE e.id= : id", Employee.class)
                .setParameter("id", employeeId)
                .getSingleResult();

        if (employee == null) {
            System.out.println("Employee with this id does not exist!");
            return;
        }
        List<Project> projects = entityManager.createQuery(
                "SELECT p FROM Project AS p ", Project.class)
                .getResultStream()
                .filter(p -> p.getEmployees().contains(employee))
                .sorted(Comparator.comparing(Project::getName))
                .collect(Collectors.toList());

        System.out.printf("%s %s - Production Technician%n", employee.getFirstName(), employee.getLastName());
        projects.forEach(project -> System.out.println("    " + project.getName()));
    }

    public static void EX9(EntityManager entityManager) {
        entityManager.createQuery(
                "SELECT p FROM Project AS p ORDER BY p.startDate DESC"
                , Project.class)
                .getResultStream()
                .limit(10)
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.printf(
                        "Project name: %s%n\tProject Description: %s%n\tProject Start Date:%s%n\tProject End Date:%s%n"
                        , p.getName()
                        , p.getDescription()
                        , p.getStartDate()
                        , p.getEndDate()
                ));
    }

    public static void Ex10(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        entityManager.createQuery(
                "UPDATE Employee AS e " +
                        "SET e.salary= e.salary * 0.12 " +
                        "WHERE e.department.id IN :ids")
                .setParameter("ids", Set.of(1, 2, 4, 11))
                .executeUpdate();
        entityManager.getTransaction().commit();

        entityManager.createQuery("SELECT e FROM Employee AS e " +
                        "WHERE e.department.name IN :names"
                , Employee.class)
                .setParameter("names", Set.of("Engineering", "Tool Design", "Marketing", "Information Services"))
                .getResultStream().forEach(e -> System.out.printf(
                "%s %s ($%.2f)%n"
                , e.getFirstName(), e.getLastName(), e.getSalary()));
    }
}
