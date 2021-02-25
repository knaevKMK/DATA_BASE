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
        System.out.println("Enter Exercise Number: ");
        try {
            String input = reader.readLine();

            switch (input) {
                case "2":
                    HomeWork.EX_2(entityManager);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
