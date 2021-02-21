import java.sql.*;
import java.util.Arrays;
import java.util.Properties;

public class Homework {
    public static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    public static final String MINIONS_TABLE_NAME = "minions_db";

    Connection connection;


    public void setConnection(String user, String password) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);

        connection = DriverManager.getConnection(CONNECTION_STRING + MINIONS_TABLE_NAME, properties);

    }

    public void EX_2() throws SQLException {
        String query = "SELECT v.name, COUNT(mv.minion_id) AS 'count'\n" +
                "FROM villains AS v\n" +
                "JOIN minions_villains AS mv\n" +
                "ON v.id = mv.villain_id\n" +
                "GROUP BY v.id\n" +
                "HAVING count> 15\n" +
                "ORDER BY count DESC";
        PreparedStatement stm = connection.prepareStatement(query);
        ResultSet rs = stm.executeQuery();
        int count = 1;
        while ((rs.next())) {
            System.out.printf("%d. %s %s%n", count++, rs.getString("name"), rs.getInt(2));
        }
    }

    public void EX_3(int villainId) throws SQLException {
        String villainName = getEntityNameById(villainId, "villains");
        if (villainName == null) {
            System.out.println("Villain does not exist!!! Not valid id:" + villainId);
            return;
        }
        System.out.println("Villain: " + villainName);

        String query = "SELECT m.name, m.age FROM minions AS m\n" + "JOIN minions_villains AS mv\n" +
                "ON m.id = mv.minion_id\n"
                + "WHERE mv.villain_id= ?";
        PreparedStatement stm = connection.prepareStatement(query);
        stm.setInt(1, villainId);
        ResultSet rs = stm.executeQuery();

        int count = 1;
        while (rs.next()) {
            System.out.printf("%d. %s %d%n", count++, rs.getString("name"), rs.getInt(2));
        }
    }

    private String getEntityNameById(int id, String table) throws SQLException {
        String query = String.format("SELECT v.name FROM %s AS v\n" +
                "WHERE id= ?", table);
        PreparedStatement stm = connection.prepareStatement(query);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        return rs.next() ? rs.getString("name") : null;
    }

    public void EX_4(String[] minionsInfo, String villainName) throws SQLException {
        String town_name = minionsInfo[2];
        int town_id = getEntityIdByName(town_name, "towns");
        if (town_id == -1) {
            town_id = insertEntityNameIntable(town_name, "towns");
            System.out.printf("Town %s was added to the database.", town_name);
        }

        String minion_name = minionsInfo[0];
        String minion_age = (minionsInfo[1]);
//        int minion_id = getEntityIdByName(minion_name, "minions");
//        if (minion_id == -1) {
//            insertEntityNameIntable(minion_name, "minions", minion_age, "" + town_id);
//        }
        int villain_id = getEntityIdByName(villainName, "villains");
        if (villain_id == -1) {
            insertEntityNameIntable();
        }


    }

    private int insertEntityNameIntable(String... args) throws SQLException {
        String name = args[0];
        String table = args[1];
        PreparedStatement stm = null;
        if (args.length == 2) {
            String query = "INSERT INTO ?(name) VALUE (?)";
            stm = connection.prepareStatement(query);
            stm.setString(1, table);
            stm.setString(2, name);
        } else if (args.length == 4) {
            int age = Integer.parseInt(args[3]);
            int town_id = Integer.parseInt(args[4]);
            String query = "INSERT INTO ?(name,age,town_id) VALUE(?, ?, ?)";
            stm = connection.prepareStatement(query);
            stm.setString(1, table);
            stm.setString(2, name);
            stm.setInt(3, age);
            stm.setInt(2, town_id);
        } else if (args.length == 3) {

        } else {
            System.out.println("Not Inserted: " + args);
            return -1;
        }

        ResultSet rs = stm.executeQuery();



        return getEntityIdByName(name,  table) ;
    }

    private int getEntityIdByName(String name, String table) throws SQLException {
        String query = "SELECT t.id FROM %? AS t WHERE t.name= ?";
        PreparedStatement stm = connection.prepareStatement(query);
        stm.setString(1, table);
        stm.setString(2, name);
        ResultSet rs = stm.executeQuery();
        return rs.next() ? rs.getInt(1) : -1;
    }
}
