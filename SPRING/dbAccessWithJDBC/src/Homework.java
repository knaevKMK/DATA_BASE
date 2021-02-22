import java.sql.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Homework {
    public static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    public static final String MINIONS_TABLE_NAME = "minions_db";

    Connection connection;

    public Homework() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "c!!2211paKev");
        connection = DriverManager.getConnection(CONNECTION_STRING + MINIONS_TABLE_NAME, properties);

    }


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


    public void EX_4(String[] minionsInfo, String villainName) throws SQLException {
        String town_name = minionsInfo[2];
        int town_id = getEntityIdByName(town_name, "towns");
        if (town_id == -1) {
            town_id = insertEntityDataIfNotExistIntable("towns", town_name);
        }

        String minion_name = minionsInfo[0];
        int minion_age = Integer.parseInt(minionsInfo[1]);
        int minion_id = getEntityIdByName(minion_name, "minions");
        //if minion does NOT exist insert him to the table and get the id;
        if (minion_id == -1) {
            minion_id = insertEntityDataIfNotExistIntable("minions", minion_name, "" + minion_age, "" + town_id);
        }
        //vilaint check
        int villain_id = getEntityIdByName(villainName, "villains");
        if (villain_id == -1) {
            villain_id = insertEntityDataIfNotExistIntable("villains", villainName);
        }
        //update minions data
        insertEntityDataIfNotExistIntable("updateMinion", "" + town_id, "" + minion_id, "" + villain_id);
        System.out.printf("Successfully added %s to be minion of %s.%n", minion_name, villainName);
    }

    private int insertEntityDataIfNotExistIntable(String... args) throws SQLException {
        String name = args[1];
        String table = args[0];
        PreparedStatement stm = null;
        switch (table) {
            case "towns":
                String query = "INSERT INTO towns (name) VALUE (?)";
                stm = connection.prepareStatement(query);
                stm.setString(1, name);
                stm.execute();
                System.out.printf("Town %s was added to the database.%n", name);
                break;
            case "villains":
                query = "INSERT INTO villains (name, evilness_factor) VALUE(?, ?)";
                stm = connection.prepareStatement(query);
                stm.setString(1, name);
                stm.setString(2, "evil");
                stm.execute();
                System.out.printf("Villain %s was added to the database.%n", name);
                break;
            case "minions":
                query = ("INSERT INTO minions(name, age,town_id) VALUE (?,?,?)");
                stm = connection.prepareStatement(query);
                stm.setString(1, name);
                stm.setInt(2, Integer.parseInt(args[2]));
                stm.setInt(3, Integer.parseInt(args[3]));
                stm.execute();
                break;
            case "updateMinion":
                //update town
                query = "UPDATE minions SET town_id=? WHERE id=?";
                stm = connection.prepareStatement(query);
                stm.setInt(1, Integer.parseInt(name));
                stm.setInt(2, Integer.parseInt(args[2]));
                stm.execute();

                //update villans id id min_vill table
                query = "UPDATE minions_villains SET villain_id = ?  WHERE minion_id=?";
                stm = connection.prepareStatement(query);
                stm.setInt(1, Integer.parseInt(args[3]));
                stm.setInt(2, Integer.parseInt(args[2]));
                stm.execute();
                return 0;
            default:
                System.out.println("Not Inserted: " + args);
                return -1;
        }


        return getEntityIdByName(name, table);
    }

    private int getEntityIdByName(String name, String table) throws SQLException {
        String query = String.format("SELECT id FROM %s WHERE name=?", table);
        PreparedStatement stm = connection.prepareStatement(query);
        stm.setString(1, name);
        ResultSet rs = stm.executeQuery();
        return rs.next() ? rs.getInt(1) : -1;
    }

    private String getEntityNameById(int id, String table) throws SQLException {
        String query = String.format("SELECT v.name FROM %s AS v\n" +
                "WHERE id= ?", table);
        PreparedStatement stm = connection.prepareStatement(query);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        return rs.next() ? rs.getString("name") : null;
    }

    public void EX_5(String country) throws SQLException {
        List<String> townsNames = getTownsByCountry(country);
        if (townsNames.size() == 0) {
            System.out.println("No town names were affected.");
            return;
        }
        System.out.printf("%d town names were affected.%n", townsNames.size());
        System.out.println(townsNames.toString());
    }

    private List<String> getTownsByCountry(String country) throws SQLException {
        // to UpperCase
        String query = "UPDATE towns SET name=UPPER (name) WHERE country=?";
        PreparedStatement stm = connection.prepareStatement(query);
        stm.setString(1, country);
        stm.execute();

        //get towns
        query = "SELECT name FROM towns WHERE country=?";
        stm = connection.prepareStatement(query);
        stm.setString(1, country);
        ResultSet rs = stm.executeQuery();
        List<String> result = new ArrayList<>();
        while (rs.next()) {
            result.add(rs.getString("name"));
        }
        return result;
    }

    public void EX_6(int id) throws SQLException {
        String name = getEntityNameById(id, "villains");
        if (name == null) {
            System.out.println("No such villain was found");
            return;
        }

        // get minions count
        String query = "SELECT COUNT(*) AS count FROM minions_villains WHERE villain_id=?";
        PreparedStatement stm = connection.prepareStatement(query);
        stm.setInt(1, id);
        ResultSet result = stm.executeQuery();
        if (result.next()) {
            query = "DELETE  FROM minions_villains WHERE villain_id=?";
            PreparedStatement deletestm1 = connection.prepareStatement(query);
            deletestm1.setInt(1, id);
            deletestm1.execute();
            query = "DELETE  FROM villains WHERE id=?";
            PreparedStatement deletestm = connection.prepareStatement(query);
            deletestm.setInt(1, id);
            deletestm.execute();
        }
        System.out.println(name + " was deleted\n" +
                result.getInt(1) + " minions released");

    }

    public void EX_7() throws SQLException {
        String query = "SELECT name FROM minions";
        PreparedStatement stm = connection.prepareStatement(query);
        ResultSet rs = stm.executeQuery();
        ArrayDeque<String> minions = new ArrayDeque<>();
        while (rs.next()) {
            minions.offer(rs.getString(1));
        }
        while (!minions.isEmpty()){
            System.out.println(minions.pollFirst());
            System.out.println(minions.pollLast());
        }
    }

    public void EX_8(int[] idS) throws SQLException {
        for (int id : idS) {
            String query="UPDATE minions SET name=LOWER(name), age=age+1 WHERE id=?";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1,id);
            stm.execute();


        }
       String query="SELECT name,age FROM minions";
        PreparedStatement stm1 = connection.prepareStatement(query);
        ResultSet res= stm1.executeQuery();
        while (res.next()){
            String temp = res.getString(1)+" "+res.getInt(2);
            System.out.println(temp);
        }

    }
}
