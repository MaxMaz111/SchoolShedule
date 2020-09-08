import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddTeacher extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    public AddTeacher(MyFrame frame) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
                frame.setContentPane(new TeacherSubject(frame).getPanel());
                frame.revalidate();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void onOK() {
        // add your code here
        String ss = textField1.getText();
        String ss2 = textField2.getText();
        String ss3 = textField3.getText();
        String qInsert = "insert into teachers (Name, Surname, Midname) values ('"+ ss + "', '"+ ss2+"', '" + ss3 + "')";
        if(ss.isEmpty() || ss2.isEmpty()) {
            System.out.println(1);
        }else{
            new MyBase().executeUpdate(qInsert);
        }
        dispose();
    }
    AddTeacher(String str) {
        System.out.println(str);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        String[] strings = str.split(" ");

        textField1.setText(strings[0]);
        textField2.setText(strings[1]);
        if (strings.length == 3){
            textField3.setText(strings[2]);
        }else{
            textField3.setText(null);
            textField3.setEditable(false);
        }
        System.out.println(strings[1]);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(strings.length == 3)
                new MyBase().executeUpdate("UPDATE `teachers` SET `Name` = '"+textField1.getText()+"', `Surname` = '"+textField2.getText()+"', `Midname` = '"+textField3.getText()+"' WHERE `Name` = '"+strings[0]+"' AND `Surname` = '"+strings[1]+"' AND `Midname` = '"+strings[2]+"'");
                else
                    new MyBase().executeUpdate("UPDATE `teachers` SET `Name` = '"+textField1.getText()+"', `Surname` = '"+textField2.getText()+"' WHERE `Name` = '"+strings[0]+"' AND `Surname` = '"+strings[1]+"'");
                dispose();
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        pack();
        setVisible(true);
    }
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
