import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MainScreen extends JFrame implements ActionListener {
    private String quizType;
    private JButton GeographyBtn;
    private JButton computerScienceBtn;
    private JButton ProgrammingBtn;
    private JButton RiddlesBtn;
    private JButton EnglishBtn;
    private JButton HistoryBtn;
    private JButton ScienceBtn;
    private JButton SocialScienceBtn;
    private JButton GKBtn;
    public MainScreen(String UserName, int id) {
        JPanel contentPane = new JPanel();
        JPanel quizPanel = new JPanel();
        JLabel title = new JLabel("Welcome " + UserName+" "+id);
        JLabel subTitle = new JLabel("Let's Paly a Quiz");
        ImageIcon geo =  new ImageIcon("../images/geography.png");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        title.setFont(new Font("Times New Roman", Font.BOLD, 36));
        subTitle.setFont(new Font("Arial",Font.BOLD,22));
        ImageIcon icon = new ImageIcon("../images/APP_icon.png");
        GeographyBtn = new JButton("GEOGRAPHY QUIZ",geo);
        computerScienceBtn = new JButton("Computer Science QUIZ");
        ProgrammingBtn = new JButton("Programming QUIZ");
        RiddlesBtn = new JButton("Riddles QUIZ");
        EnglishBtn = new JButton("English QUIZ");
        HistoryBtn = new JButton("History QUIZ");
        ScienceBtn = new JButton("Science QUIZ");
        SocialScienceBtn = new JButton("Social Science QUIZ");
        GKBtn = new JButton("General Knoowledge QUIZ");
        SpringLayout Layout = new SpringLayout();
        //Labels Layout
        //title
        Layout.putConstraint(SpringLayout.NORTH, title, 15, SpringLayout.NORTH, contentPane);
        Layout.putConstraint(SpringLayout.WEST, title, 650, SpringLayout.WEST, contentPane);
        //sub title
        Layout.putConstraint(SpringLayout.NORTH, subTitle, 15, SpringLayout.SOUTH, title);
        Layout.putConstraint(SpringLayout.WEST, subTitle, 690, SpringLayout.WEST, contentPane);
        // quiz panel
        Layout.putConstraint(SpringLayout.NORTH, quizPanel, 80, SpringLayout.SOUTH, subTitle);
        Layout.putConstraint(SpringLayout.WEST, quizPanel, 350, SpringLayout.WEST, contentPane);
        // btn dimensions
        GeographyBtn.setPreferredSize(new Dimension(200,95));
        HistoryBtn.setPreferredSize(new Dimension(200,95));
        ScienceBtn.setPreferredSize(new Dimension(200,95));
        computerScienceBtn.setPreferredSize(new Dimension(200,95));
        ProgrammingBtn.setPreferredSize(new Dimension(200,95));
        RiddlesBtn.setPreferredSize(new Dimension(200,95));
        GKBtn.setPreferredSize(new Dimension(200,95));
        EnglishBtn.setPreferredSize(new Dimension(200,95));
        SocialScienceBtn.setPreferredSize(new Dimension(200,95));
        // btn Font/image
        // geo
        GeographyBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        GeographyBtn.setVerticalTextPosition(SwingConstants.CENTER);
        GeographyBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        GeographyBtn.setForeground(Color.WHITE); // text color
        GeographyBtn.setBorderPainted(false);
        GeographyBtn.setContentAreaFilled(false);
        //his
        HistoryBtn.setFont(new Font("Times New Roman",Font.BOLD,15));
        //cs
        computerScienceBtn.setFont(new Font("Times New Roman",Font.BOLD,15));
        //pro
        ProgrammingBtn.setFont(new Font("Times New Roman",Font.BOLD,15));
        //sci
        ScienceBtn.setFont(new Font("Times New Roman",Font.BOLD,15));
        //gk
        GKBtn.setFont(new Font("Times New Roman",Font.BOLD,15));
        //riddles
        RiddlesBtn.setFont(new Font("Times New Roman",Font.BOLD,15));
        //eng
        EnglishBtn.setFont(new Font("Times New Roman",Font.BOLD,15));
        //social
        SocialScienceBtn.setFont(new Font("Times New Roman",Font.BOLD,15));
        // btn events
        GeographyBtn.addActionListener(this);
        ProgrammingBtn.addActionListener(this);
        RiddlesBtn.addActionListener(this);
        GKBtn.addActionListener(this);
        ScienceBtn.addActionListener(this);
        HistoryBtn.addActionListener(this);
        computerScienceBtn.addActionListener(this);
        EnglishBtn.addActionListener(this);
        SocialScienceBtn.addActionListener(this);
        quizPanel.setLayout(new GridLayout(0,4,10,10));
        contentPane.setLayout(Layout);
        setContentPane(contentPane);
        contentPane.add(quizPanel);
        quizPanel.add(computerScienceBtn);
        quizPanel.add(ProgrammingBtn);
        quizPanel.add(EnglishBtn);
        quizPanel.add(RiddlesBtn);
        quizPanel.add(ScienceBtn);
        quizPanel.add(SocialScienceBtn);
        quizPanel.add(GKBtn);
        quizPanel.add(HistoryBtn);
        quizPanel.add(GeographyBtn);
        add(title);
        add(subTitle);
        setTitle("QUIZ APPLICATION");
        setIconImage(icon.getImage());
        setSize(2400, 1000);
        setVisible(true);
    }
    // override action performed
    @Override
    public void actionPerformed(ActionEvent e){
        Object src = e.getSource();
        if(src == GeographyBtn){
            quizType = "geography";
        } else if (src == ProgrammingBtn) {
            quizType = "programming";
        } else if (src == ScienceBtn) {
            quizType = "science";
        } else if (src == RiddlesBtn) {
            quizType = "riddles";
        } else if (src == EnglishBtn) {
            quizType = "english";
        } else if (src == HistoryBtn) {
            quizType = "history";
        } else if (src == GKBtn) {
            quizType = "general knowledge";
        } else if (src == computerScienceBtn) {
            quizType = "computer science";
            
        } else if (src == SocialScienceBtn) {
            quizType = "social science";
        }
        new quizScreen(quizType);
    }
}