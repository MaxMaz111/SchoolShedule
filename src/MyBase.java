import java.sql.*;
import java.util.Queue;

import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;

public class MyBase {
    private static MyBase DB = new MyBase();

    public static MyBase getDB(){
        return DB;
    }

    private Statement statement;
    private MyBase(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/schoolschedule?serverTimezone=UTC", "root", "");
            statement = connection.createStatement(TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public ResultSet executeQuery(String query) throws SQLException {
        return statement.executeQuery(query);
    }
    public void executeUpdate(String update){
        try {
            statement.executeUpdate(update);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public String getWhereQueryForTeacher(String[] FIO){
        if(FIO.length  == 3){
            return  "WHERE `Name` = '"+FIO[0]+"' AND `Surname` = '"+FIO[1]+"' AND `Midname` = '"+FIO[2]+"'";
        }else{
            return  "WHERE `Name` = '"+FIO[0]+"' AND `Surname` = '"+FIO[1]+"'";
        }
    }
}
