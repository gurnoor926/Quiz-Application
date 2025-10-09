import javax.swing.*;
import java.awt.*;
public class MainScreen extends JFrame {
    private String quizType;
    public MainScreen(String UserName, int id) {
        JPanel contentPane = new JPanel();
        JLabel title = new JLabel("Welcome " + UserName+" "+id);
        JLabel subTitle = new JLabel("Let's Paly a Quiz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        title.setFont(new Font("Times New Roman", Font.BOLD, 36));
        subTitle.setFont(new Font("Arial",Font.BOLD,22));
        ImageIcon icon = new ImageIcon("../images/APP_icon.png");
        JButton GeographyBtn = new JButton("GEOGRAPHY QUIZ");
        GeographyBtn.setPreferredSize(new Dimension(100,60));
        GeographyBtn.addActionListener(e->{
            quizType = "geography";
            new quizScreen(quizType);

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
        Layout.putConstraint(SpringLayout.NORTH, GeographyBtn, 80, SpringLayout.SOUTH, subTitle);
        Layout.putConstraint(SpringLayout.WEST, GeographyBtn, 690, SpringLayout.WEST, contentPane);
        contentPane.setLayout(Layout);
        setContentPane(contentPane);
        contentPane.add(GeographyBtn);
        add(title);
        add(subTitle);
        setTitle("QUIZ APPLICATION");
        setIconImage(icon.getImage());
        setSize(2400, 1000);
        setVisible(true);

    }
}