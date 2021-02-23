//import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Main {
    private static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws SQLException, IOException {
//        System.out.println("Enter user: ");
//        String user= read.readLine();
//        System.out.println("Enter password");
//        String password = read.readLine();

        HomeWork homeWork = new HomeWork();
//      homeWork.setConnection(user,password);
        System.out.println("Connecting...");
        System.out.println("Enter number of Exercise: between 2 to 9"
                + System.lineSeparator()
                + " OR 'end' to Stop");
        String input = read.readLine();

        while (!input.equalsIgnoreCase("end")) {
            switch (input) {
                case "2":
                    System.out.println("Enter salay:");
                    double salary = Double.parseDouble(read.readLine());
                    homeWork.EX_2(salary);
                    break;
                case "3":
                    System.out.println("Enter user:");
                    String user= read.readLine();
                    homeWork.EX_3(user);                    break;
                default:
                    System.out.println("Wrong choose!");
                    break;
            }

            System.out.println("\nEnter number of Exercise: between 2 to 9"
                    + System.lineSeparator()
                    + " OR 'end' to Stop");

            input = read.readLine();
        }
    }
}
