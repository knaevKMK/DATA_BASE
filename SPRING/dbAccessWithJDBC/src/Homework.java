import java.sql.*;
import java.util.Properties;

public class Homework {
    public static final String CONNECTION_STRING =
            "jdbc:mysql://localhost:3306/";
    public static final String MINIONS_TABLE_NAME =
            "minions_db";
    Connection connection;

    public void setConnection(String user, String password) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);

        connection = DriverManager
                .getConnection(CONNECTION_STRING + MINIONS_TABLE_NAME, properties);

    }

    public void getVillansNamesEx2() throws SQLException {
        String query = "SELECT v.name, COUNT(mv.minion_id) AS 'count'\n" +
                "FROM villains AS v\n" +
                "JOIN minions_villains AS mv\n" +
                "ON v.id = mv.villain_id\n" +
                "GROUP BY v.id\n" +
                "HAVING count> 15\n" +
                "ORDER BY count DESC";


        PreparedStatement stm = connection.prepareStatement(query);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {

            System.out.printf("%s %d%n"
                    , rs.getString("name")
                    , rs.getInt(2));
        }
    }
}
