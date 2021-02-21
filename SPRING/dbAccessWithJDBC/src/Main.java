import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        //MysqlxDatatypes.Scalar;
        System.out.println("Please insert log-in MYSQL server!");
        System.out.println("user:");
        String user = scan.nextLine();
        System.out.println("password: ");
        String password = scan.nextLine();


        Homework homework = new Homework();
        homework.setConnection(user, password);
        System.out.print("Connecting.");
        System.out.print(".");
        System.out.print("..");

        System.out.println("Please, choose number of Exercise: between 2 to 9"
                + System.lineSeparator()
                + " OR 'end' to Stop");

        String read = scan.nextLine();

        while (!read.equalsIgnoreCase("end")) {
            switch (read) {
                case "2":
                    homework.EX_2();
                    break;
                case "3":
                    System.out.println("Insert villain's Id");
                    int villainsId= Integer.parseInt(scan.nextLine());
                    homework.EX_3(villainsId);
                    break;
                case "4":
                    System.out.println("Enter minions info: name, age, town_name:");
                    String []minionsInfo = scan.nextLine().split("\\s+");
                    System.out.println("Enter Villain name: ");
                    String villainName= scan.nextLine();
                    homework.EX_4(minionsInfo,villainName);
                    break;
                default:
                    System.out.println("Wrong choose!");
                    break;
            }
            System.out.println("Please, choose number of Exercise: between 2 to 9"
                    + System.lineSeparator()
                    + " OR 'end' to Stop");

            read = scan.nextLine();
        }
    }
}
