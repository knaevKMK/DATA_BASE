import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException, SQLException {
        System.out.println("Please insert log-in MYSQL server!");
//        System.out.println("user:");
   //     String user = reader.readLine();
   //     System.out.println("password: ");
   //     String password = reader.readLine();

        Homework homework = new Homework
                ("root","c!!2211paKev");
             //   (user, password);

        System.out.println("Connecting...");
        System.out.println();
        System.out.println("Enter number of Exercise: between 2 to 9"
                + System.lineSeparator()
                + " OR 'end' to Stop");

        String read = reader.readLine();

        while (!read.equalsIgnoreCase("end")) {
            switch (read) {
                case "2":
                    homework.ex2();
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "8":
                    break;
                case "9":
                    break;
                default:
                    System.out.println("Wrong choose!");
                    break;
            }

            System.out.println("\nEnter number of Exercise: between 2 to 9"
                    + System.lineSeparator()
                    + " OR 'end' to Stop");

            read = reader.readLine();
        }
    }
}
