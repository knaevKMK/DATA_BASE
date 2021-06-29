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
                        break;
                    case "7":
                        break;
                    case "8":
                        break;
                    case "9":
                        break;
                    case "10":
                        break;

                }

                System.out.println("Enter Exercise Number (between 2 to 13) OR END to Stop: ");
                input = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
