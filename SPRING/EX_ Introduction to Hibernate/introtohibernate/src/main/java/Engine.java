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
        System.out.println("Enter Exercise Number OR END to Stop: ");
        try {
            String input = reader.readLine();
            while (!input.equalsIgnoreCase("END")) {

                switch (input) {
                    case "2":
                        HomeWork.EX_2(entityManager);
                        break;
                    case "3":
                        System.out.println("Enter full name:");
                        String fullName = reader.readLine();
                        HomeWork.EX_3(entityManager, fullName);
                        break;
                    case   "4":
                        HomeWork.EX_4(entityManager,50000.00);
                        break;
                    case "5":
                        HomeWork.EX_5(entityManager, "Research and Development");
                        break;
                    case "6":
                        System.out.println("Enter employee last name:");
                        String lastName= reader.readLine();
                        HomeWork.EX_6(entityManager, "Vitoshka 15", lastName);
                        break;
                    case "7":
                        HomeWork.EX_7()
                        break;
                }
                System.out.println("\nEnter Exercise Number OR END to Stop: ");
                input = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
