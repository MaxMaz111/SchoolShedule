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
        if(Language.lang == "Русский") {
            showSubjectsButton.setText("Показать предметы");
            showTeachersButton.setText("Показать учителей");
            languageButton.setText("Язык");
        }
        if(Language.lang == "English") {
            showSubjectsButton.setText("Show subjects");
            showTeachersButton.setText("Show teachers");
            languageButton.setText("Language");
        }
        languageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Language(frame).getPanel());
                frame.revalidate();
            }
        });
        addTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTeacher addTeacher = new AddTeacher();
                addTeacher.pack();
                addTeacher.setVisible(true);
            }
        });
        addSubjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSubject addSubject = new AddSubject();
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return contentPane;
    }
}
