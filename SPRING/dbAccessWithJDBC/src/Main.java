import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
//        MysqlxDatatypes.Scalar;
        System.out.println("Please insert log-in MYSQL server!");
        System.out.println("user:");
        String user = scan.nextLine();
        System.out.println("password: ");
        String password = scan.nextLine();


        Homework homework = new Homework();
       homework.setConnection(user, password);
        System.out.println("Connecting....");

        System.out.println("Please, choose number of Exercise: between 2 to 9"
                + System.lineSeparator()
                + " OR 'end' to Stop");

        String read = scan.nextLine();
        while (!"end".equalsIgnoreCase(read)) {
            switch (read) {
                case "2":
                    homework.getVillansNamesEx2();
                    break;
                case "3":
                    homework.Ex3(Integer.parseInt(scan.nextLine()));
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
                    System.out.println("Wrong choose! ");
                    break;
            }
            System.out.println("Please, choose number of Exercise: between 2 to 9"
                    + System.lineSeparator()
                    + " OR 'end' to Stop");

            read = scan.nextLine();
        }
    }
}
