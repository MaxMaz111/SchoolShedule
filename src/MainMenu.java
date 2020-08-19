import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;

public class MainMenu {
    private JPanel contentPane;
    private JButton showSubjectsButton;
    private JButton addSubjectButton;
    private JButton showTeachersButton;
    private JTextArea textArea1;
    private JTextField textField1;


    public MainMenu() {
        showSubjectsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String serverName = "jdbc:mysql://localhost/";
                    String baseName = "schoolschedule?serverTimezone=UTC";
                    String userName = "root";
                    String password = "";
                    String url = serverName + baseName;
                    Connection connection = DriverManager.getConnection(url, userName, password);
                    Statement statement = connection.createStatement(TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs = statement.executeQuery("SELECT `Name` FROM `lessons` WHERE 1");
                    String ss = "";
                    rs.first();

                     do{
                        ss = ss + rs.getString("Name") + "\n";
                    }while (rs.next());
                    textArea1.setText(ss);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        showTeachersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String serverName = "jdbc:mysql://localhost/";
                    String baseName = "schoolschedule?serverTimezone=UTC";
                    String userName = "root";
                    String password = "";
                    String url = serverName + baseName;
                    Connection connection = DriverManager.getConnection(url, userName, password);
                    Statement statement = connection.createStatement(TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs = statement.executeQuery("SELECT `Name`,`Surname` FROM `teachers` WHERE 1");
                    String ss = "";
                    rs.first();

                     do {
                        ss = ss + rs.getString("Name") +" "+rs.getString("Surname")+ "\n";
                    }while (rs.next());
                    textArea1.setText(ss);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        addSubjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ss = textField1.getText();
                try {
                    String serverName = "jdbc:mysql://localhost/";
                    String baseName = "schoolschedule?serverTimezone=UTC";
                    String userName = "root";
                    String password = "";
                    String url = serverName + baseName;
                    Connection connection = DriverManager.getConnection(url, userName, password);
                    String qInsert = "insert into lessons (Name) values ('"+ ss + "')" ;
                    Statement statement = connection.createStatement();
                    if(ss.equals("")) {
                        System.out.println(1);
                    }else{
                        statement.executeUpdate(qInsert);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                }
        });
    }



}
