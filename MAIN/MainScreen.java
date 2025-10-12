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
    private JButton goBackBtn;
    private Quiz quizRef;
    public MainScreen(String UserName, Quiz quiz) {
        this.quizRef = quiz;
        JPanel contentPane = new JPanel();
        JPanel quizPanel = new JPanel();
        JLabel title = new JLabel("Welcome " + UserName);
        JLabel subTitle = new JLabel("Let's Play a Quiz");
        //bt images
        ImageIcon geo =  new ImageIcon("../images/geography.png");
        ImageIcon history = new ImageIcon("../images/history.jpeg");
        ImageIcon social = new ImageIcon("../images/socialSci.jpg");
        ImageIcon english = new ImageIcon("../images/eng.jpeg");
        ImageIcon science = new ImageIcon("../images/sci.jpg");
        ImageIcon CS = new ImageIcon("../images/cs.jpg");
        ImageIcon GenK = new ImageIcon("../images/gk.jpeg");
        ImageIcon code = new ImageIcon("../images/programming.jpg");
        ImageIcon riddles = new ImageIcon("../images/riddles.jpeg");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        title.setFont(new Font("Roboto", Font.BOLD, 36));
        subTitle.setFont(new Font("Arial",Font.BOLD,22));
        ImageIcon icon = new ImageIcon("../images/APP_icon.png");
        GeographyBtn = new JButton("GEOGRAPHY",geo);
        computerScienceBtn = new JButton("Computer Science",CS);
        ProgrammingBtn = new JButton("Programming",code);
        RiddlesBtn = new JButton("Riddles", riddles);
        EnglishBtn = new JButton("English",english);
        HistoryBtn = new JButton("History",history);
        ScienceBtn = new JButton("Science",science);
        SocialScienceBtn = new JButton("Social Science",social);
        GKBtn = new JButton("General Knowledge",GenK);
        goBackBtn = new JButton("⮐ BACK");
        SpringLayout Layout = new SpringLayout();
        //Labels Layout
        //title
        Layout.putConstraint(SpringLayout.NORTH, title, 15, SpringLayout.NORTH, contentPane);
        Layout.putConstraint(SpringLayout.WEST, title, 650, SpringLayout.WEST, contentPane);
        //sub title
        Layout.putConstraint(SpringLayout.NORTH, subTitle, 15, SpringLayout.SOUTH, title);
        Layout.putConstraint(SpringLayout.WEST, subTitle, 690, SpringLayout.WEST, contentPane);
        // quiz panel
        Layout.putConstraint(SpringLayout.NORTH, quizPanel, 100, SpringLayout.SOUTH, subTitle);
        Layout.putConstraint(SpringLayout.WEST, quizPanel, 290, SpringLayout.WEST, contentPane);
        // go back button
        Layout.putConstraint(SpringLayout.NORTH,goBackBtn,15, SpringLayout.NORTH, contentPane);
        Layout.putConstraint(SpringLayout.WEST, goBackBtn, 15, SpringLayout.WEST, contentPane);
        // btn dimensions
        GeographyBtn.setPreferredSize(new Dimension(220,100));
        HistoryBtn.setPreferredSize(new Dimension(220,100));
        ScienceBtn.setPreferredSize(new Dimension(220,100));
        computerScienceBtn.setPreferredSize(new Dimension(220,100));
        ProgrammingBtn.setPreferredSize(new Dimension(220,100));
        RiddlesBtn.setPreferredSize(new Dimension(220,100));
        GKBtn.setPreferredSize(new Dimension(220,100));
        EnglishBtn.setPreferredSize(new Dimension(220,100));
        SocialScienceBtn.setPreferredSize(new Dimension(220,100));
        goBackBtn.setPreferredSize(new Dimension(100,90));
        // btn Font/image
        // geo
        GeographyBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        GeographyBtn.setVerticalTextPosition(SwingConstants.CENTER);
        GeographyBtn.setFont(new Font("Roboto", Font.BOLD, 20));
        GeographyBtn.setForeground(Color.WHITE); // text color
        GeographyBtn.setBorderPainted(false);
        GeographyBtn.setContentAreaFilled(false);
        //his
        HistoryBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        HistoryBtn.setVerticalTextPosition(SwingConstants.CENTER);
        HistoryBtn.setFont(new Font("Roboto",Font.BOLD,20));
        HistoryBtn.setForeground(Color.WHITE);
        HistoryBtn.setBorderPainted(false);
        HistoryBtn.setContentAreaFilled(false);
        //cs
        computerScienceBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        computerScienceBtn.setVerticalTextPosition(SwingConstants.CENTER);
        computerScienceBtn.setFont(new Font("Roboto",Font.BOLD,20));
        computerScienceBtn.setForeground(Color.WHITE);
        computerScienceBtn.setBorderPainted(false);
        computerScienceBtn.setContentAreaFilled(false);
        //pro
        ProgrammingBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        ProgrammingBtn.setVerticalTextPosition(SwingConstants.CENTER);
        ProgrammingBtn.setFont(new Font("Roboto",Font.BOLD,20));
        ProgrammingBtn.setForeground(Color.WHITE);
        ProgrammingBtn.setBorderPainted(false);
        ProgrammingBtn.setContentAreaFilled(false);
        //sci
        ScienceBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        ScienceBtn.setVerticalTextPosition(SwingConstants.CENTER);
        ScienceBtn.setFont(new Font("Roboto",Font.BOLD,20));
        ScienceBtn.setForeground(Color.WHITE);
        ScienceBtn.setBorderPainted(false);
        ScienceBtn.setContentAreaFilled(false);
        //gk
        GKBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        GKBtn.setVerticalTextPosition(SwingConstants.CENTER);
        GKBtn.setFont(new Font("Roboto",Font.BOLD,20));
        GKBtn.setForeground(Color.WHITE);
        GKBtn.setBorderPainted(false);
        GKBtn.setContentAreaFilled(false);
        //riddles
        RiddlesBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        RiddlesBtn.setVerticalTextPosition(SwingConstants.CENTER);
        RiddlesBtn.setFont(new Font("Roboto",Font.BOLD,20));
        RiddlesBtn.setForeground(Color.WHITE);
        RiddlesBtn.setBorderPainted(false);
        RiddlesBtn.setContentAreaFilled(false);
        //eng
        EnglishBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        EnglishBtn.setVerticalTextPosition(SwingConstants.CENTER);
        EnglishBtn.setFont(new Font("Roboto",Font.BOLD,20));
        EnglishBtn.setForeground(Color.WHITE);
        EnglishBtn.setBorderPainted(false);
        EnglishBtn.setContentAreaFilled(false);
        //social
        SocialScienceBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        SocialScienceBtn.setVerticalTextPosition(SwingConstants.CENTER);
        SocialScienceBtn.setFont(new Font("Roboto",Font.BOLD,20));
        SocialScienceBtn.setForeground(Color.WHITE);
        SocialScienceBtn.setBorderPainted(false);
        SocialScienceBtn.setContentAreaFilled(false);
        //go back
        goBackBtn.setFont(new Font("Roboto", Font.BOLD, 15));
        goBackBtn.setBorderPainted(false);
        goBackBtn.setFocusPainted(false);
        goBackBtn.setContentAreaFilled(false);
        goBackBtn.setOpaque(false);
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
        goBackBtn.addActionListener(e->{
            quizRef.setVisible(true);
            this.dispose();
        });
        // content adding
        quizPanel.setLayout(new GridLayout(0,4,35,55));
        contentPane.setLayout(Layout);
        setContentPane(contentPane);
        contentPane.add(goBackBtn);
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
        new quizScreen(quizType,this);
        this.setVisible(false);
    }
}