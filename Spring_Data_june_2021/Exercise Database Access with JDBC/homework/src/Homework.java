import java.sql.*;
import java.util.Properties;

public class Homework {
    public static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    public static final String MINIONS_TABLE_NAME = "minions_db?useSSL=false";

    private final Connection connection;
    private final Properties properties;

    public Homework(String user, String password) throws SQLException {
        this.properties = setProperties(user,password);
        this.connection = DriverManager.getConnection
                (CONNECTION_STRING + MINIONS_TABLE_NAME, this.properties);
    }

    private Properties setProperties(String user, String password) {

        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);
        return properties;
    }

    public void ex2() throws SQLException {
        String query= "SELECT v.name, COUNT(mv.minion_id) AS `count` " +
                "FROM villains AS v " +
                "JOIN minions_villains mv ON v.id = mv.villain_id " +
                "GROUP BY mv.villain_id " +
                "HAVING `count` > 15 " +
                "ORDER BY `count` DESC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet result=preparedStatement.executeQuery();
        int count=1;
        while (result.next()){
            System.out.printf("%d. %s %s%n", count++, result.getString("name")
                    ,result.getInt(2));
        }
    }
}
