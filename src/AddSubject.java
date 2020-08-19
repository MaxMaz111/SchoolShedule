import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddSubject extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;

    public AddSubject() {

        setContentPane(contentPane);
        setModal(true);
        pack();
        setVisible(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
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
        try {
            String serverName = "jdbc:mysql://localhost/";
            String baseName = "schoolschedule?serverTimezone=UTC";
            String userName = "root";
            String password = "";
            String url = serverName + baseName;
            Connection connection = DriverManager.getConnection(url, userName, password);
            String qInsert = "insert into subjects (Name) values ('"+ ss + "')" ;
            Statement statement = connection.createStatement();
            if(ss.isEmpty()) {
                System.out.println(1);
            }else{
                statement.executeUpdate(qInsert);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
