import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Homework {
    public static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    public static final String MINIONS_TABLE_NAME = "minions_db?useSSL=false";

    private final Connection connection;
    private final Properties properties;

    public Homework(String user, String password) throws SQLException {
        this.properties = setProperties(user, password);
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
        String query = "SELECT v.name, COUNT(mv.minion_id) AS `count` " +
                "FROM villains AS v " +
                "JOIN minions_villains mv ON v.id = mv.villain_id " +
                "GROUP BY mv.villain_id " +
                "HAVING `count` > 15 " +
                "ORDER BY `count` DESC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet result = preparedStatement.executeQuery();
        int count = 1;
        while (result.next()) {
            System.out.printf("%d. %s %s%n", count++, result.getString("name")
                    , result.getInt(2));
        }
    }

    public void ex3(int villainId) throws SQLException {
        String villainName = findEntityNameById("villains", villainId);
        if (villainName == null) {
            System.out.printf("No villain with ID %d exists in the database.", villainId);
            return;
        }
        System.out.printf("Villain: %s", villainName);

        String query = "SELECT m.name,m.age " +
                "FROM minions AS m " +
                "JOIN minions_villains mv on m.id = mv.minion_id\n" +
                "WHERE villain_id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, villainId);
        ResultSet result = statement.executeQuery();
        int count = 1;
        while (result.next()) {
            System.out.printf("%d. %s %d%n", count++, result.getString("name")
                    , result.getInt("age"));
        }
    }

    private String findEntityNameById(String tableName, int villainId) throws SQLException {
        String query = String.format("SELECT name  " +
                "FROM %s " +
                "WHERE id=?", tableName);
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, villainId);
        ResultSet result = statement.executeQuery();

        return result.next()
                ? result.getString("name")
                : null;
    }

    public void ex4(String[] minionData, String villainName) throws SQLException {

        String minionTown = minionData[2];
        Integer townId = getEntityIdByName("towns", minionTown);
        if (townId == null) {
            townId = insertEntityDataIfNotExistInTable("towns", minionTown);
        }

        Integer villainId = getEntityIdByName("villains", villainName);
        if (villainId == null) {
            villainId = insertEntityDataIfNotExistInTable("villains", villainName);
        }


        String minionName = minionData[0];
        Integer minionId = getEntityIdByName("minions", minionName);
        String minionAge = (minionData[1]);

          if (minionId == null) {
            minionId = insertEntityDataIfNotExistInTable("minions", minionName, minionAge, "" + townId);
        }
        insertEntityDataIfNotExistInTable("updateMinion", "" + townId, "" + minionId, "" + villainId);
        System.out.printf("Successfully added %s to be minion of %s.%n", minionName, villainName);

    }

    private int insertEntityDataIfNotExistInTable(String... args) throws SQLException {
        String table = args[0];
        String name = args[1];
        PreparedStatement stm = null;
//        System.out.println(table);
        switch (table) {
            case "towns":
                String query = "INSERT INTO towns (`name`) VALUE (?)";
                stm = connection.prepareStatement(query);
                stm.setString(1, name);
                stm.execute();
                System.out.printf("Town %s was added to the database.%n", name);
                break;
            case "villains":
                query = "INSERT INTO villains (`name`, evilness_factor) VALUE(?, 'evil')";
                stm = connection.prepareStatement(query);
                stm.setString(1, name);
                stm.execute();
                System.out.printf("Villain %s was added to the database.%n", name);
                break;
            case "minions":
                query = "INSERT INTO minions(`name`, age,town_id) VALUE (?, ?, ? )";
                stm = connection.prepareStatement(query);
                 stm.setString(1, name);
                 stm.setInt(2, Integer.parseInt(args[2]));
                 stm.setInt(3, Integer.parseInt(args[3]));

                stm.execute();
                break;
            case "updateMinion":
                //update town
                query = String.format("UPDATE minions SET town_id=%d WHERE id=%d"
                        ,Integer.parseInt(name)
                        ,Integer.parseInt(args[2]) );
                stm = connection.prepareStatement(query);
                stm.execute();

                //update villans id id min_vill table
                query = String.format("UPDATE minions_villains SET villain_id = %d  WHERE minion_id=%d",
                        Integer.parseInt(args[3]),Integer.parseInt(args[2]) );
                stm = connection.prepareStatement(query);
                stm.execute();

            return 0;
            default:
                System.out.println("Not Inserted: " + args);
                return -1;
        }


        return getEntityIdByName(table, name);
    }

    private Integer getEntityIdByName(String tableName, String entityName) throws SQLException {
        String query = String.format("SELECT id FROM %s WHERE name=?", tableName);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, entityName);
        ResultSet result = statement.executeQuery();
        return result.next()
                ? result.getInt(1)
                : null;
    }

    public void ex5(String countryName) throws SQLException {
        List<String> townsNames = getTownsByCountry(countryName);
        if (townsNames.size() == 0) {
            System.out.println("No town names were affected.");
            return;
        }
        System.out.printf("%d town names were affected.%n", townsNames.size());
        System.out.println(townsNames.toString());
    }

    private List<String> getTownsByCountry(String country) throws SQLException {
        String query ="SELECT  COUNT(id) FROM towns WHERE country=?";
        PreparedStatement stm = connection.prepareStatement(query);
        stm.setString(1, country);
        ResultSet rs = stm.executeQuery();
        if (!rs.next()){
            return new ArrayList<>();
        }

        // to UpperCase
         query = "UPDATE towns SET name=UPPER (name) WHERE country=?";
         stm = connection.prepareStatement(query);
        stm.setString(1, country);
        stm.execute();

        //get towns
        query = "SELECT name FROM towns WHERE country=?";
        stm = connection.prepareStatement(query);
        stm.setString(1, country);
         rs = stm.executeQuery();
        List<String> result = new ArrayList<>();
        while (rs.next()) {
            result.add(rs.getString("name"));
        }
        return result;
    }

    public void ex6() throws SQLException {
    }

    public void ex7() throws SQLException {
    }

    public void ex8() throws SQLException {
    }

    public void ex9() throws SQLException {
    }
}
