import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame();
        myFrame.setContentPane(new Login(myFrame).getPanel());
        myFrame.revalidate();

    }
}
