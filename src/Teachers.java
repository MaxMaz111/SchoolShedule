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
//            ResultSet rs = MyBase.getDB().executeQuery("SELECT `Name`,`Surname`,`Midname` FROM `teachers` WHERE 1");
//            String ss = "";
//            rs.first();
//            int i = 1;
            DefaultListModel<String> teachers = new DefaultListModel();
//            do{
//                if(rs.getString("Midname") != null)
//                teachers.addElement(rs.getString("Name") + " " + rs.getString("Surname")+ " " + rs.getString("Midname"));
//                else
//                    teachers.addElement(rs.getString("Name") + " " + rs.getString("Surname"));
//                System.out.println();
//            }while(rs.next());
            for(String val: MyBase.getDB().select("teachers", new String[]{"Name","Surname", "Midname"},"123")){
                teachers.addElement(val);
            }
            list1.setModel(teachers);


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

                MyBase.getDB().executeUpdate("DELETE FROM `teachers` WHERE `Name` = "+strings[0]+" AND `Surname` = "+strings[1]+"");
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
