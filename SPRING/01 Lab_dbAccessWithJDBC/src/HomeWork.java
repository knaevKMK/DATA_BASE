import java.sql.*;
import java.util.Properties;

public class HomeWork {
    public static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    public static final String TABLE_NAME_SOFTUNI = "soft_uni";
    public static final String TABLE_NAME_DIABLO = "diablo";
    public static final String TABLE_NAME_MINIONS = "minions_db";

    Connection connection;

    public HomeWork() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "c!!2211paKev");
        connection = DriverManager.getConnection(CONNECTION_STRING + TABLE_NAME_DIABLO, properties);

    }


    public void setConnection(String user, String password) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);

        connection = DriverManager.getConnection(CONNECTION_STRING + TABLE_NAME_DIABLO, properties);

    }


    public void EX_2(double salary) throws SQLException {
        String query = "SELECT (CONCAT(first_name,' ',last_name)) AS full_name FROM soft_uni.employees WHERE  salary>?";
        PreparedStatement stm = connection.prepareStatement(query);
        stm.setDouble(1, salary);
        ResultSet res = stm.executeQuery();
        while (res.next()) {
            System.out.println(res.getString(1));
        }
    }

    public void EX_3(String user) throws SQLException {
        String[] user_data = getEntityIdbyName(user, "user");
        int id_user = Integer.parseInt(user_data[2]);
        if (id_user < 0) {
            System.out.println("No such user exists");
            return;
        }
        String query = "SELECT COUNT(*) FROM diablo.users_games WHERE user_id=?";
        PreparedStatement stm = connection.prepareStatement(query);
        stm.setInt(1, id_user);
        ResultSet res = stm.executeQuery();
        if (res.next()) {
            System.out.printf("%s %s has played %d games%n", user_data[0], user_data[1], res.getInt(1));
        }
    }

    private String[] getEntityIdbyName(String user, String table) throws SQLException {
        String query = "SELECT first_name, last_name, id FROM diablo.users WHERE LOWER(first_name)=? OR LOWER(last_name)=?";
        PreparedStatement stm = connection.prepareStatement(query);
        stm.setString(1, user);
        stm.setString(2, user);
        ResultSet res = stm.executeQuery();

        if (res.next()) {
            return new String[]{res.getString(1), res.getString(2), "" + res.getInt(3)};
        }
        return new String[]{null, null, "-1"};
    }
}
