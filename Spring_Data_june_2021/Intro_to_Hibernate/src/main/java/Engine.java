import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine implements Runnable {
    private final EntityManager entityManager;
    private BufferedReader reader;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() {
        System.out.println("Enter Exercise Number (between 2 to 13) OR END to Stop: ");
        try {
            String input = reader.readLine();
            while (!input.equalsIgnoreCase("END")) {

                switch (input) {
                    case "2":
                        Homework.Ex2(entityManager);
                        break;
                    case "3":
                        System.out.println("Enter name: ");
                        String name = reader.readLine();
                        Homework.Ex3(entityManager, name.replaceAll("\\s+", ""));
                    case "4":
                        Homework.Ex4(entityManager);
                        break;
                    case "5":
                        Homework.Ex5(entityManager);
                        break;
                    case "6":
                        System.out.println("Enter employee last name: ");
                        String lastName = reader.readLine();
                        Homework.Ex6(entityManager, lastName);
                        break;
                    case "7":
                        Homework.Ex7(entityManager);
                        break;
                    case "8":
                        System.out.println("Enter employee id: ");
                        Integer employeeId = Integer.parseInt(reader.readLine());
                        Homework.Ex8(entityManager, employeeId);
                        break;
                    case "9":
                        Homework.EX9(entityManager);
                        break;
                    case "10":
                        Homework.Ex10(entityManager);
                        break;
                    case "11":
                        System.out.println("Enter employee first name's initials: ");
                        String pattern = reader.readLine();
                        Homework.Ex11(entityManager, pattern);
                        break;
                    case "12":
                        Homework.Ex12(entityManager);
                        break;
                    case "13":
                        System.out.println("Enter town name");
                        String townName= reader.readLine();
                        Homework.Ex13(entityManager, townName);
                        break;

                }

                System.out.println("\nEnter Exercise Number (between 2 to 13) OR END to Stop: ");
                input = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
