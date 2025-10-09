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
    SpringLayout layout = new SpringLayout();
    ButtonGroup group = new ButtonGroup();
    JButton nextButton = new JButton("Next");

    String currentAnswer = "";

    quizScreen(String quizType) {
        setTitle("Quiz");
        setSize(2400, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setBounds(250, 100, 1000, 300);
        panel.setLayout(layout);
        questionLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
        panel.setBackground(Color.BLUE);

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

        panel.add(questionLabel);
        panel.add(opt1);
        panel.add(opt2);
        panel.add(opt3);
        panel.add(opt4);
        panel.add(scoreLabel);
        panel.add(nextButton);

        group.add(opt1);
        group.add(opt2);
        group.add(opt3);
        group.add(opt4);

        showQuestion();

        nextButton.addActionListener(e -> {
            checkAnswer();
            currentQuestion++;
            if (currentQuestion < totalQuestions) {
                showQuestion();
            } else {
                JOptionPane.showMessageDialog(null, "Quiz Over! Score: " + score + "/" + totalQuestions);
                closeResources();
                dispose(); // close quiz window
            }
        });

        // Layout constraints
        // Question
        layout.putConstraint(SpringLayout.NORTH, questionLabel, 20, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, questionLabel, 15, SpringLayout.WEST, panel);

        // Options
        // opt1
        layout.putConstraint(SpringLayout.WEST, opt1, 180, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, opt1, 20, SpringLayout.SOUTH, questionLabel);
        // opt2
        layout.putConstraint(SpringLayout.WEST, opt2, 20, SpringLayout.EAST, opt1);
        layout.putConstraint(SpringLayout.NORTH, opt2, 20, SpringLayout.SOUTH, questionLabel);
        // opt3
        layout.putConstraint(SpringLayout.WEST, opt3, 180, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, opt3, 20, SpringLayout.SOUTH, opt1);
        // opt4
        layout.putConstraint(SpringLayout.WEST, opt4, 20, SpringLayout.EAST, opt3);
        layout.putConstraint(SpringLayout.NORTH, opt4, 20, SpringLayout.SOUTH, opt2);
        // Score label
        layout.putConstraint(SpringLayout.WEST, scoreLabel, 15, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, scoreLabel, 20, SpringLayout.SOUTH, opt3);
        // Next button
        layout.putConstraint(SpringLayout.WEST, nextButton, 250, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nextButton, 35, SpringLayout.SOUTH, opt3);

        add(panel);
        setLayout(null);
        setVisible(true);
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

                questionLabel.setText((currentQuestion + 1) + ". " + question);
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