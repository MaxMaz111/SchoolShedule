import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;

public class Teachers implements Panel{
    private JPanel contentPane;
    private JButton mainMenuButton;
    private JList list1;
    private JButton editButton;
    private JButton deleteButton;

    public Teachers(MyFrame frame) {
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Menu(frame).getPanel());
                frame.revalidate();

            }
        });
        try {
            String serverName = "jdbc:mysql://localhost/";
            String baseName = "schoolschedule?serverTimezone=UTC";
            String userName = "root";
            String password = "";
            String url = serverName + baseName;
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement(TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery("SELECT `Name`,`Surname`,`Midname` FROM `teachers` WHERE 1");
            String ss = "";
            rs.first();
            int i = 1;
            DefaultListModel<String> teachers = new DefaultListModel();
            do{
                if(rs.getString("Midname") != null)
                teachers.addElement(rs.getString("Name") + " " + rs.getString("Surname")+ " " + rs.getString("Midname"));
                else
                    teachers.addElement(rs.getString("Name") + " " + rs.getString("Surname"));
                System.out.println();
            }while(rs.next());
            list1.setModel(teachers);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = (String) list1.getModel().getElementAt(list1.getSelectedIndex());
                System.out.println(str);
                AddTeacher addTeacher = new AddTeacher(str);
                frame.setContentPane(new Menu(frame).getPanel());
                frame.setContentPane(new Teachers(frame).getPanel());
                frame.revalidate();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = (String) list1.getModel().getElementAt(list1.getSelectedIndex());
                String[] strings = str.split(" ");

                new MyBase().executeUpdate("DELETE FROM `teachers` WHERE `Name` = "+strings[0]+" AND `Surname` = "+strings[1]+"");
                frame.setContentPane(new Menu(frame).getPanel());
                frame.setContentPane(new Teachers(frame).getPanel());
                frame.revalidate();
            }
        });
    }



    @Override
    public JPanel getPanel() {
        return contentPane;
    }
}
