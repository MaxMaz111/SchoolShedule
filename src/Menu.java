import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;

public class Menu implements Panel{
    private JPanel contentPane;
    private JButton showTeachersButton;
    private JButton showSubjectsButton;
    private JButton languageButton;
    private JButton addSubjectButton;
    private JButton addTeacherButton;

    public Menu(MyFrame frame) {
        showTeachersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Teachers(frame).getPanel());
                frame.revalidate();
            }
        });
        showSubjectsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Subjects(frame).getPanel());
                frame.revalidate();

            }
        });
        
        addTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTeacher addTeacher = new AddTeacher(frame);
                addTeacher.pack();
                addTeacher.setVisible(true);
            }
        });
        addSubjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSubject addSubject = new AddSubject();
                addSubject.pack();
                addSubject.setVisible(true);
            }
        });

    }

    @Override
    public JPanel getPanel() {
        return contentPane;
    }
}
