import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;

public class TeacherSubject {
    private JList list1;
    private JList list2;
    private JButton insertButton;
    private JPanel contentPane;
    private JButton mainMenuButton;
    int i = 0;
    String t;
    public TeacherSubject(MyFrame frame) {

        DefaultListModel<String> list2model = new DefaultListModel<>();
        DefaultListModel<String> list1model = new DefaultListModel<>();
        insertButton.addActionListener(new ActionListener() {
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
                    ResultSet rs = statement.executeQuery("SELECT `id` FROM `teachers` WHERE 1");
                    while(rs.next()){
                        t = rs.getString("id");
                    }
                    for(int i = 0; i < list1model.getSize(); i++){
                        ResultSet rs2 = statement.executeQuery("SELECT `id` FROM `lessons` WHERE `Name` = '"+ list1model.get(i) +"'");
                        rs2.first();
                        String qInsert = "insert into lessons_teachers (teahcerId,lessonId) values ("+ t + ", "+ rs2.getString(1) +")";

                        statement.executeUpdate(qInsert);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
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


            do{
                list2model.addElement(rs.getString("Name"));
                System.out.println();
            }while(rs.next());
            list2.setModel(list2model);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        list2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String t = list2model.getElementAt(list2.getSelectedIndex());
                list2model.remove(list2.getSelectedIndex());
                list1model.add(i,t);
                i++;
                list1.setModel(list1model);

            }

        });

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Menu(frame).getPanel());
                frame.revalidate();
            }
        });
    }
    public JPanel getPanel() {
        return contentPane;
    }
}
