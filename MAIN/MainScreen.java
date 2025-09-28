import javax.swing.*;
import java.awt.*;
public class MainScreen extends JFrame {
    public MainScreen(String UserName) {
        JPanel contentPane = new JPanel();
        JLabel title = new JLabel("Welcome " + UserName);
        JLabel subTitle = new JLabel("Let's Paly a Quiz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        title.setFont(new Font("Times New Roman", Font.BOLD, 36));
        subTitle.setFont(new Font("Arial",Font.BOLD,22));
        ImageIcon icon = new ImageIcon("../images/APP_icon.png");
        JButton playQuiz = new JButton("Play Quiz");
        playQuiz.setPreferredSize(new Dimension(100,60));
        playQuiz.addActionListener(e->{
            new quizScreen();

        });
        SpringLayout Layout = new SpringLayout();
        //Labels Layout
        //title
        Layout.putConstraint(SpringLayout.NORTH, title, 15, SpringLayout.NORTH, contentPane);
        Layout.putConstraint(SpringLayout.WEST, title, 650, SpringLayout.WEST, contentPane);
        //sub title
        Layout.putConstraint(SpringLayout.NORTH, subTitle, 15, SpringLayout.SOUTH, title);
        Layout.putConstraint(SpringLayout.WEST, subTitle, 690, SpringLayout.WEST, contentPane);
        //play button
        Layout.putConstraint(SpringLayout.NORTH, playQuiz, 80, SpringLayout.SOUTH, subTitle);
        Layout.putConstraint(SpringLayout.WEST, playQuiz, 690, SpringLayout.WEST, contentPane);
        contentPane.setLayout(Layout);
        setContentPane(contentPane);
        contentPane.add(playQuiz);
        add(title);
        add(subTitle);
        setTitle("QUIZ APPLICATION");
        setIconImage(icon.getImage());
        setSize(2400, 1000);
        setVisible(true);

    }
}