import java.sql.*;
import java.util.Queue;

import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;

public class MyBase {
    MyBase(String serverName, String baseName, String userName, String passwordSQL){

        String url = serverName + baseName;
        try {
            Connection connection = DriverManager.getConnection(url, userName, passwordSQL);
            statement = connection.createStatement(TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    Statement statement;
    MyBase(){
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
}
