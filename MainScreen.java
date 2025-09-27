import javax.swing.*;
import java.awt.*;
public class MainScreen extends JFrame {
    public MainScreen(String UserName) {
        JLabel title = new JLabel("Welcome " + UserName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        title.setFont(new Font("Times New Roman", Font.BOLD, 36));
        title.setBounds(100, 100, 450, 300);
        add(title);
        setSize(2400, 1000);
        setLayout(null);
        setVisible(true);

    }
}