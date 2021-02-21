import java.sql.*;
import java.util.Properties;

public class Homework {
    public  static final    String CONNECTION_STRING="jdbc:mysql://localhost:3306/";
    public static final String MINIONS_TABLE_NAME= "minions_db";

    Connection connection;

    public void setConnection(String user, String password) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user",user);
        properties.setProperty("password", password);

        connection= DriverManager.getConnection(CONNECTION_STRING+MINIONS_TABLE_NAME,properties);
    }

    public void getVillansNamesEx2() throws SQLException {
        String quety= "SELECT v.name, COUNT(mv.minion_id) AS 'count'\n" +
                "FROM villains AS v\n" +
                "JOIN minions_villains AS mv\n" +
                "ON v.id = mv.villain_id\n" +
                "GROUP BY v.id\n" +
                "HAVING count> 15\n" +
                "ORDER BY count DESC";
        PreparedStatement stm= connection.prepareStatement(quety);
        ResultSet rs= stm.executeQuery();
        while (rs.next()){
            System.out.printf("%s %d%n", rs.getString("name"), rs.getInt(2));
        }
    }
    public void Ex3(int villainId) throws SQLException {
        String villainName = getEntityNameById(villainId, "villains");
        if (villainName == null) {
            System.out.println("Villain does not exist!!! Not valid id:" + villainId);
            return;
        }
        System.out.println("Villain: "+ villainName);

        String query = ("SELECT m.name, m.age FROM minions AS m\n" +
                "JOIN minions_villains AS mv ON m.id = mv.minion_id\n" +
                "WHERE mv.villain_id=?");
        PreparedStatement stm = connection.prepareStatement(query);
        stm.setInt(1,villainId);
        ResultSet resultSet = stm.executeQuery();

        int count=1;
        while (resultSet.next()){
            System.out.printf("%d. %s %d%n"
                    ,count++
                    ,resultSet.getString("name")
                    ,resultSet.getInt("age"));

        }
    }

    private String getEntityNameById(int entityId, String table) throws SQLException {
        String query = String.format("SELECT name FROM %s WHERE id = ?", table);
        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setInt(1, entityId);
        ResultSet resultSet = pstm.executeQuery();

        return resultSet.next() ? resultSet.getString("name") : null;
    }
}
