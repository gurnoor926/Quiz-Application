import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class quizScreen extends JFrame {

    int currentQuestion = 0;
    int score = 0;
    int totalQuestions = 0;
    String url = "jdbc:postgresql://localhost:5432/quizDb";
    String user = "postgres";
    String pass = "Manak6142";
    Connection con;
    ResultSet rs;

    JLabel questionLabel = new JLabel();
    JRadioButton opt1 = new JRadioButton();
    JRadioButton opt2 = new JRadioButton();
    JRadioButton opt3 = new JRadioButton();
    JRadioButton opt4 = new JRadioButton();
    JLabel scoreLabel = new JLabel();
    SpringLayout QuizLayout = new SpringLayout();
    SpringLayout NorthLayout = new SpringLayout();
    SpringLayout ContentPaneLayout = new SpringLayout();
    ButtonGroup group = new ButtonGroup();
    JButton nextButton = new JButton("⎘");
    JButton backButton;
    JPanel contentPane;
    String currentAnswer = "";
    private MainScreen mainScreenRef;
    quizScreen(String quizType, MainScreen mainScreen) {
        this.mainScreenRef = mainScreen;
        setTitle("Quiz");
        setSize(2400, 1000);
        contentPane = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel QuizPanel = new JPanel();
        QuizPanel.setPreferredSize(new Dimension(1090, 500));
        JPanel NorthPanel = new JPanel();
        NorthPanel.setPreferredSize(new Dimension(2400,100));
        NorthPanel.setLayout(NorthLayout);
        QuizPanel.setLayout(QuizLayout);
        questionLabel.setFont(new Font("Roboto", Font.BOLD, 36));
        backButton = new JButton("⮐ BACK");
        backButton.addActionListener(e->{
            mainScreenRef.setVisible(true);
            this.dispose();
        });
        contentPane.setLayout(ContentPaneLayout);
        setContentPane(contentPane);
        // Load questions from database
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, user, pass);
            String sql = "SELECT * FROM questions WHERE quiz_type = ? ORDER BY RANDOM() LIMIT 10";
            PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pstmt.setString(1, quizType);
            rs = pstmt.executeQuery();

            // Count total questions
            rs.last();
            totalQuestions = rs.getRow();
            rs.beforeFirst();

            if (totalQuestions == 0) {
                JOptionPane.showMessageDialog(null, "No questions found!");
                dispose();
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading questions: " + e.getMessage());
            dispose();
            return;
        }
        // options font size
        opt1.setFont(new Font("Roboto", Font.BOLD, 15));
        opt2.setFont(new Font("Roboto", Font.BOLD, 15));
        opt3.setFont(new Font("Roboto", Font.BOLD, 15));
        opt4.setFont(new Font("Roboto", Font.BOLD, 15));

        // score label font
        scoreLabel.setFont(new Font("Roboto", Font.BOLD, 15));

        //back button
        backButton.setFont(new Font("Roboto", Font.BOLD, 15));
        backButton.setPreferredSize(new Dimension(100,90));
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setOpaque(false);
        // next button
        nextButton.setFont(new Font("Roboto", Font.BOLD, 25));
        nextButton.setBorderPainted(false);
        nextButton.setFocusPainted(false);
        nextButton.setBackground(Color.decode("#42E36D"));
        nextButton.setForeground(Color.white);
        QuizPanel.add(questionLabel);
        QuizPanel.add(opt1);
        QuizPanel.add(opt2);
        QuizPanel.add(opt3);
        QuizPanel.add(opt4);
        QuizPanel.add(nextButton);

        group.add(opt1);
        group.add(opt2);
        group.add(opt3);
        group.add(opt4);

        NorthPanel.add(backButton);
        NorthPanel.add(scoreLabel);

        showQuestion();

        nextButton.addActionListener(e -> {
            checkAnswer();
            currentQuestion++;
            if (currentQuestion < totalQuestions) {
                showQuestion();
            } else {
                JOptionPane.showMessageDialog(null, "Quiz Over! Score: " + score + "/" + totalQuestions);
                closeResources();
                mainScreenRef.setVisible(true);
                dispose(); // close quiz window
            }
        });

        // Layout constraints
        // Quiz Layout quiz panel
        // Question
        QuizLayout.putConstraint(SpringLayout.NORTH, questionLabel, 20, SpringLayout.NORTH, QuizPanel);
        QuizLayout.putConstraint(SpringLayout.WEST, questionLabel, 15, SpringLayout.WEST, QuizPanel);

        // Options
        // opt1
        QuizLayout.putConstraint(SpringLayout.WEST, opt1, 55, SpringLayout.WEST, QuizPanel);
        QuizLayout.putConstraint(SpringLayout.NORTH, opt1, 20, SpringLayout.SOUTH, questionLabel);
        // opt2
        QuizLayout.putConstraint(SpringLayout.WEST, opt2, 20, SpringLayout.EAST, opt1);
        QuizLayout.putConstraint(SpringLayout.NORTH, opt2, 20, SpringLayout.SOUTH, questionLabel);
        // opt3
        QuizLayout.putConstraint(SpringLayout.WEST, opt3, 55, SpringLayout.WEST, QuizPanel);
        QuizLayout.putConstraint(SpringLayout.NORTH, opt3, 20, SpringLayout.SOUTH, opt1);
        // opt4
        QuizLayout.putConstraint(SpringLayout.WEST, opt4, 20, SpringLayout.EAST, opt3);
        QuizLayout.putConstraint(SpringLayout.NORTH, opt4, 20, SpringLayout.SOUTH, opt2);
        // Next button
        QuizLayout.putConstraint(SpringLayout.WEST, nextButton, 800, SpringLayout.WEST, QuizPanel);
        QuizLayout.putConstraint(SpringLayout.NORTH, nextButton, 35, SpringLayout.SOUTH, opt3);

        // North Panel
        // score
        NorthLayout.putConstraint(SpringLayout.EAST, scoreLabel, -25, SpringLayout.EAST, NorthPanel);
        NorthLayout.putConstraint(SpringLayout.NORTH, scoreLabel, 15, SpringLayout.NORTH, NorthPanel);
        // back button
        NorthLayout.putConstraint(SpringLayout.WEST, backButton, 2, SpringLayout.WEST, NorthPanel);
        NorthLayout.putConstraint(SpringLayout.NORTH, backButton, 15, SpringLayout.NORTH, NorthPanel);

        // content pane layout
        //quiz panel
        ContentPaneLayout.putConstraint(SpringLayout.WEST, QuizPanel, 335, SpringLayout.WEST,contentPane);
        ContentPaneLayout.putConstraint(SpringLayout.NORTH,  QuizPanel, 100, SpringLayout.SOUTH, NorthPanel);
        // north panel
        ContentPaneLayout.putConstraint(SpringLayout.WEST, NorthPanel, 0, SpringLayout.WEST, contentPane);
        ContentPaneLayout.putConstraint(SpringLayout.NORTH, NorthPanel, 0, SpringLayout.NORTH, contentPane);
        ContentPaneLayout.putConstraint(SpringLayout.EAST, NorthPanel, 0, SpringLayout.EAST, contentPane);

        contentPane.add(QuizPanel);
        contentPane.add(NorthPanel);
        setVisible(true);
    }

    String wrapText(String text, int maxCharsPerLine) {
        StringBuilder wrapped = new StringBuilder("<html>");
        int length = text.length();
        int start = 0;

        while (start < length) {
            int end = Math.min(start + maxCharsPerLine, length);

            // Try to break at a space if possible
            if (end < length) {
                int lastSpace = text.lastIndexOf(' ', end);
                if (lastSpace > start) {
                    end = lastSpace;
                }
            }

            wrapped.append(text.substring(start, end));
            if (end < length) {
                wrapped.append("<br>");
            }
            start = end + 1; // +1 to skip the space
        }

        wrapped.append("</html>");
        return wrapped.toString();
    }

    void showQuestion() {
        try {
            if (rs.next()) {
                String question = rs.getString("question");
                String option1 = rs.getString("option_1");
                String option2 = rs.getString("option_2");
                String option3 = rs.getString("option_3");
                String option4 = rs.getString("option_4");
                currentAnswer = rs.getString("answer");
                questionLabel.setText(wrapText((currentQuestion + 1) + ". " + question, 60));
                opt1.setText(option1);
                opt2.setText(option2);
                opt3.setText(option3);
                opt4.setText(option4);
                group.clearSelection();
                scoreLabel.setText("Score: " + score + "/" + totalQuestions);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error displaying question: " + e.getMessage());
        }
    }

    void checkAnswer() {
        String selected = "";
        if (opt1.isSelected()) selected = opt1.getText();
        else if (opt2.isSelected()) selected = opt2.getText();
        else if (opt3.isSelected()) selected = opt3.getText();
        else if (opt4.isSelected()) selected = opt4.getText();

        if (selected.equals(currentAnswer)) {
            score++;
        }
    }

    void closeResources() {
        try {
            if (rs != null) rs.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }}