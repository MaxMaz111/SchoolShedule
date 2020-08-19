import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Language {
    private JButton englishButton;
    private JButton русскийButton;
    private JPanel contentPane;
    private JButton mainMenuButton;
    public static String lang = "English";
    public Language(MyFrame frame) {
        русскийButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lang = "Русский";
                frame.revalidate();
            }

        });
        englishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lang = "English";
                frame.revalidate();
            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Menu(frame).getPanel());
                frame.revalidate();
            }
        });
        if(lang == "Русский")
        mainMenuButton.setText("главное меню");
    }
    public JPanel getPanel() {
        return contentPane;
    }
}
