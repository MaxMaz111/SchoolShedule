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
    public ResultSet executeQuery(String query)  {
        try {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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
    public String[] select(String table, String[] fields, String condition){
        String fieldNames = "";
        for (String field:fields){
            fieldNames += "`" + field + "` ";
        }
        ResultSet rs = executeQuery("SELECT" + fieldNames + "FROM" + "'" + table + "' WHERE"+ condition);
        String temp = "";
        try {
            while (rs.next()) {
                for (int i = 0; i < fields.length; i++) {
                    if (rs.getString(i) != null)
                        temp += rs.getString(i) + "#&";
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return temp.split("#&");
    }
}
