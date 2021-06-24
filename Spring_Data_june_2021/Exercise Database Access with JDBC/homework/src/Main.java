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
                ("root", "c!!2211paKev");
        //   (user, password);

        System.out.println("Connecting...");
        System.out.println();
        System.out.println("Enter number of Exercise: between 2 to 9"
                + System.lineSeparator()
                + " OR 'end' to Stop");

        String read = reader.readLine();

            while (!read.equalsIgnoreCase("end")) {
                try {
                switch (read) {
                    case "2":
                        homework.ex2();
                        break;
                    case "3":
                        System.out.println("Enter villain ID:");
                        int villainId = Integer.parseInt(reader.readLine());
                        homework.ex3(villainId);
                        break;
                    case "4":
                        System.out.println("Insert data(name,age,town) for minion:");
                        String minionData = reader.readLine();
                        if (minionData.contains("Minion:")){
                            minionData=minionData.substring(minionData.indexOf(':')+2);
                        }
                        System.out.println("Insert villain's name:");
                        String villainData = reader.readLine();
                        if (villainData.contains("Villain:")){
                            villainData=villainData.substring(villainData.indexOf(':')+2);
                        }
                        homework.ex4(minionData.split(" "), villainData);
                        break;
                    case "5":
                        System.out.println("Enter country:");
                        String countryName= reader.readLine();
                        homework.ex5(countryName);
                        break;
                    case "6":
                        System.out.println("Enter villain ID: ");
                        int id = Integer.parseInt(reader.readLine());
                        homework.ex6(id);
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
                }}catch (Exception e){
                    System.out.println(e);
                    System.out.println("Your input is invalid!!!");
                }
                        System.out.println("\nEnter number of Exercise: between 2 to 9"
                    + System.lineSeparator()
                    + " OR 'end' to Stop");

            read = reader.readLine();
        }
    }
}
