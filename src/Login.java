import com.sun.media.sound.SF2SoundbankReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;

public class Login {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton registerButton;
    private JButton logInButton;
    private JPanel contentPane;

    public Login(MyFrame frame) {
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String myLogin = textField1.getText();
                String myPass=String.valueOf(passwordField1.getPassword());
                if(checkAccess(myLogin, myPass)){
                    frame.setContentPane(new Menu(frame).getPanel());
                    frame.revalidate();
                }else{
                    new WrongPassword(frame);
                    frame.setContentPane(new Login(frame).getPanel());
                    frame.revalidate();
                }
            }
        });
    }
    public JPanel getPanel() {
        return contentPane;
    }
    public boolean checkAccess(String login, String password){
        try {
            if(new MyBase().executeQuery("SELECT `Login`,`Password` FROM `authorisation` WHERE `Login` = '" + login + "' AND `Password` = '"+ password +"'").next()) {
                return true;
            }else{
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
