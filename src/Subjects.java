import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;

public class Subjects {
    private JButton mainMenuButton;
    private JList list1;
    private JPanel contentPane;
    private JButton editButton;
    private JButton deleteButton;

    public Subjects(MyFrame frame) {
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
            ResultSet rs = statement.executeQuery("SELECT `Name` FROM `lessons` WHERE 1");
            String ss = "";
            rs.first();
            int i = 1;
            DefaultListModel<String> subjects = new DefaultListModel();
            do{
                subjects.addElement(rs.getString("Name"));
            }while(rs.next());
            list1.setModel(subjects);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = (String) list1.getModel().getElementAt(list1.getSelectedIndex());
                System.out.println(str);
                AddSubject addSubject = new AddSubject(str);
                frame.setContentPane(new Menu(frame).getPanel());
                frame.setContentPane(new Subjects(frame).getPanel());
                frame.revalidate();
            }
        });
    }
    public JPanel getPanel() {
        return contentPane;
    }
}
