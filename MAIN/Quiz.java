import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Quiz extends JFrame{
    JPanel panel;
    JPanel contentPane;
    JLabel lblQuiz;
    JButton btnPlay, btnLogin, btnRegister;
    SpringLayout springLayout;
    ImageIcon icon;
    String username;
    String Id;
    private Boolean isLoggedIn = false;
    Quiz() {
        setTitle("Quiz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(2400, 1000);
        setLocationRelativeTo(null); // center the window on screen
         springLayout = new SpringLayout();
        icon = new ImageIcon("../images/APP_icon.png");
        // Content pane setup
        contentPane = new JPanel();
        contentPane.setLayout(springLayout);
        setContentPane(contentPane);
        // Center panel setup
        panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // centers components inside it
        panel.setBackground(Color.decode("#D4D4D4")); // light gray background
        contentPane.add(panel);
        panel.setPreferredSize(new Dimension(400, 300));

        // Label and Play button setup
        lblQuiz = new JLabel("Quiz");
        lblQuiz.setFont(new Font("Roboto", Font.BOLD, 28));

        btnPlay = new JButton("Play");
        btnPlay.setFont(new Font("Roboto", Font.PLAIN, 18));
        btnPlay.setBorderPainted(false);
        btnPlay.setFocusPainted(false);
        btnPlay.setBackground(Color.decode("#54FF54"));


        // Add components to panel with spacing
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblQuiz, gbc);

        gbc.gridy = 1;
        panel.add(btnPlay, gbc);
        // Login and Register buttons (top-right)
        btnLogin = new JButton("Login");
        btnRegister = new JButton("Register");
        btnLogin.setBorderPainted(false);
        btnLogin.setFocusPainted(false);
        btnLogin.setFont(new Font("Roboto", Font.PLAIN, 18));
        btnLogin.setBackground(Color.decode("#41FAB0"));
        btnRegister.setBorderPainted(false);
        btnRegister.setFocusPainted(false);
        btnRegister.setFont(new Font("Roboto", Font.PLAIN, 18));
        btnRegister.setBackground(Color.decode("#41FAB0"));
        // layout
        //panel
        springLayout.putConstraint(SpringLayout.NORTH, panel, 150, SpringLayout.NORTH, contentPane);
        springLayout.putConstraint(SpringLayout.WEST, panel, 580, SpringLayout.WEST, contentPane);
        // register
        springLayout.putConstraint(SpringLayout.EAST, btnRegister, -25, SpringLayout.EAST, contentPane);
        springLayout.putConstraint(SpringLayout.NORTH, btnRegister, 15, SpringLayout.NORTH, contentPane);
        //login
        springLayout.putConstraint(SpringLayout.EAST, btnLogin, -130, SpringLayout.EAST, contentPane);
        springLayout.putConstraint(SpringLayout.NORTH, btnLogin, 15, SpringLayout.NORTH, contentPane);
        // actions button
        btnPlay.addActionListener(e ->{
            if(isLoggedIn==true){
                new MainScreen(username);
                setVisible(false);
            }else {
                JOptionPane.showMessageDialog(Quiz.this, "Please Login First");
            }
        });
        btnLogin.addActionListener(e->openLoginWindow());
        btnRegister.addActionListener(e->openRegisterWindow());
        contentPane.add(btnLogin);
        contentPane.add(btnRegister);
        contentPane.setBackground(Color.decode("#C4C4C4"));
        setIconImage(icon.getImage());
        setVisible(true);
    }
    public void openLoginWindow(){
        new Login(this);
    }
    public void openRegisterWindow(){
        new Register(this);
    }
    public void setIsLoggedIn(String username){
        isLoggedIn = true;
        this.username = username;
        contentPane.remove(btnLogin);
        contentPane.remove(btnRegister);
        contentPane.revalidate();
        contentPane.repaint();
    }
    public static void main(String[] args) {
        new Quiz();
    }
}