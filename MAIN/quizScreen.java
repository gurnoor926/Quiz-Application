import javax.swing.*;
import java.awt.*;
import java.sql.*;
public class quizScreen extends JFrame {

    int currentQuestion = 0;
    int score = 0;

    String questions[] = {"what is java?", "which is used to style a web page?"};
    String options[][] = {
            {"programming language","just another cs","myth","don't know"},
            {"css","html","javascript","java"}
    };
    String answers[] = {"programming language","css"};

    JLabel questionLabel = new JLabel();
    JRadioButton opt1 = new JRadioButton();
    JRadioButton opt2 = new JRadioButton();
    JRadioButton opt3 = new JRadioButton();
    JRadioButton opt4 = new JRadioButton();
    JLabel scoreLabel = new JLabel();
    SpringLayout layout = new SpringLayout();

    ButtonGroup group = new ButtonGroup();
    JButton nextButton = new JButton("Next");

    quizScreen() {
        setTitle("Quiz");
        setSize(2400, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setBounds(690,100,400,300);
        panel.setLayout(layout);

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

        showQuestion(currentQuestion);

        nextButton.addActionListener(e-> {
                checkAnswer();
                currentQuestion++;
                if (currentQuestion < questions.length) {
                    showQuestion(currentQuestion);
                } else {
                    JOptionPane.showMessageDialog(null, "Quiz Over! Score: " + score);
                    dispose();// close quiz window
                }
            }
        );
        //lay out
        //question
        layout.putConstraint(SpringLayout.NORTH, questionLabel, 20, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, questionLabel, 30, SpringLayout.WEST, panel);
        //options
        //opt1
        layout.putConstraint(SpringLayout.WEST, opt1 , 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, opt1 , 20, SpringLayout.SOUTH, questionLabel);
        //opt2
        layout.putConstraint(SpringLayout.WEST, opt2 , 20, SpringLayout.EAST,opt1 );
        layout.putConstraint(SpringLayout.NORTH, opt2 , 20, SpringLayout.SOUTH, questionLabel);
        //opt3
        layout.putConstraint(SpringLayout.WEST, opt3 , 20, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, opt3 , 20, SpringLayout.SOUTH, opt1);
        //opt4
        layout.putConstraint(SpringLayout.WEST, opt4 , 20, SpringLayout.EAST,opt3 );
        layout.putConstraint(SpringLayout.NORTH, opt4 , 20, SpringLayout.SOUTH, opt2);
        //next button
        layout.putConstraint(SpringLayout.WEST , nextButton , 35, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, nextButton , 35, SpringLayout.SOUTH, opt3);

        add(panel);
        setLayout(null);
        setVisible(true);

    }

    void showQuestion(int index) {
        questionLabel.setText(questions[index]);
        opt1.setText(options[index][0]);
        opt2.setText(options[index][1]);
        opt3.setText(options[index][2]);
        opt4.setText(options[index][3]);
        group.clearSelection();
        scoreLabel.setText("Score: " + score);
    }

    void checkAnswer() {
        String selected = "";
        if (opt1.isSelected()) selected = opt1.getText();
        else if (opt2.isSelected()) selected = opt2.getText();
        else if (opt3.isSelected()) selected = opt3.getText();
        else if (opt4.isSelected()) selected = opt4.getText();

        if (selected.equals(answers[currentQuestion])) {
            score++;
        }
    }


}