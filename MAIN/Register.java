import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Register extends JFrame {
    String url = "jdbc:postgresql://localhost:5432/quizDb";
    String user = "postgres";
    String pass = "Manak6142";
    Quiz quizRef;

    Register(Quiz quiz) {
        setTitle("Register");
        this.quizRef = quiz;

        // Background panel
        JPanel contentPane = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image bg = new ImageIcon("../images/login_bg.jpg").getImage();
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.setLayout(null);
        setContentPane(contentPane);

        ImageIcon icon = new ImageIcon("../images/APP_icon.png");

        // Inner panel for registration form
        JPanel panel = new JPanel();
        panel.setBounds(300, 120, 400, 300);
        panel.setBackground(Color.decode("#8DABBA"));
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);

        // Labels and fields
        JLabel lblRegister = new JLabel("Register");
        lblRegister.setFont(new Font("Times New Roman", Font.BOLD, 28));

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 18));

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 18));

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 18));

        JTextField txtUsername = new JTextField(15);
        JTextField txtEmail = new JTextField(15);
        JPasswordField txtPassword = new JPasswordField(15);
        txtPassword.setEchoChar('*');

        // Register button
        JButton btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnRegister.setBackground(Color.decode("#4DA6FF"));

        btnRegister.addActionListener(e -> {
            String username = txtUsername.getText();
            String emailText = txtEmail.getText();
            String passwordText = String.valueOf(txtPassword.getPassword());

            // Check if fields are empty
            if (username.isEmpty() || emailText.isEmpty() || passwordText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

// Username validation
            if (Character.isDigit(username.charAt(0))) {
                JOptionPane.showMessageDialog(this, "Username cannot start with a number!");
                return;
            }

            if (username.contains(" ")) {
                JOptionPane.showMessageDialog(this, "Username cannot contain spaces!");
                return;
            }

// Email validation
            if (!emailText.contains("@") || !emailText.contains(".")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid email address!");
                return;
            }

// More strict email validation
            if (!emailText.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                JOptionPane.showMessageDialog(this, "Invalid email format!");
                return;
            }

// Password validation
            if (passwordText.length() < 6) {
                JOptionPane.showMessageDialog(this, "Password must be at least 6 characters long!");
                return;
            }

            try (Connection con = DriverManager.getConnection(url, user, pass)) {
                // Check if email already exists
                String checkSql = "SELECT * FROM users WHERE email=?";
                try (PreparedStatement checkStmt = con.prepareStatement(checkSql)) {
                    checkStmt.setString(1, emailText);
                    try (ResultSet rs = checkStmt.executeQuery()) {
                        if (rs.next()) {
                            JOptionPane.showMessageDialog(this, "Email already registered!");
                            return;
                        }
                    }
                }

                // Insert new user
                String insertSql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
                try (PreparedStatement insertStmt = con.prepareStatement(insertSql)) {
                    insertStmt.setString(1, username);
                    insertStmt.setString(2, emailText);
                    insertStmt.setString(3, passwordText);

                    int rows = insertStmt.executeUpdate(); // execute insert
                    if (rows > 0) {
                        // Update Quiz screen
                        if (quizRef != null) {
                            quizRef.setIsLoggedIn(username); // remove login/register buttons
                        }
                        JOptionPane.showMessageDialog(this, "Registration successful!");
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Registration failed!");
                    }
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        // Add components to panel
        panel.add(lblRegister);
        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnRegister);

        // Layout constraints
        layout.putConstraint(SpringLayout.NORTH, lblRegister, 20, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblRegister, 0, SpringLayout.HORIZONTAL_CENTER, panel);

        layout.putConstraint(SpringLayout.NORTH, lblUsername, 50, SpringLayout.SOUTH, lblRegister);
        layout.putConstraint(SpringLayout.WEST, lblUsername, 50, SpringLayout.WEST, panel);

        layout.putConstraint(SpringLayout.NORTH, txtUsername, 50, SpringLayout.SOUTH, lblRegister);
        layout.putConstraint(SpringLayout.WEST, txtUsername, 20, SpringLayout.EAST, lblUsername);

        layout.putConstraint(SpringLayout.NORTH, lblEmail, 30, SpringLayout.SOUTH, txtUsername);
        layout.putConstraint(SpringLayout.WEST, lblEmail, 50, SpringLayout.WEST, panel);

        layout.putConstraint(SpringLayout.NORTH, txtEmail, 30, SpringLayout.SOUTH, txtUsername);
        layout.putConstraint(SpringLayout.WEST, txtEmail, 20, SpringLayout.EAST, lblEmail);

        layout.putConstraint(SpringLayout.NORTH, lblPassword, 30, SpringLayout.SOUTH, txtEmail);
        layout.putConstraint(SpringLayout.WEST, lblPassword, 50, SpringLayout.WEST, panel);

        layout.putConstraint(SpringLayout.NORTH, txtPassword, 30, SpringLayout.SOUTH, txtEmail);
        layout.putConstraint(SpringLayout.WEST, txtPassword, 20, SpringLayout.EAST, lblPassword);

        layout.putConstraint(SpringLayout.NORTH, btnRegister, 40, SpringLayout.SOUTH, txtPassword);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnRegister, 0, SpringLayout.HORIZONTAL_CENTER, panel);

        contentPane.add(panel);

        setIconImage(icon.getImage());
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
