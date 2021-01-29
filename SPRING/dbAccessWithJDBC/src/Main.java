import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        //MysqlxDatatypes.Scalar
            Homework homework = new Homework();
            homework.setConnection("root", "c!!2211paKev");
            homework.getVillansNamesEx2();

    }
}
