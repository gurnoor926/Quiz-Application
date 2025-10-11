import javax.swing.*;
import java.awt.*;
import java.sql.*;
public class Login extends JFrame {
    String Name;
    String Password;
    int id;
    private Quiz quizRef;
    String url = "jdbc:postgresql://localhost:5432/quizDb";
    String user = "postgres";
    String pass = "Manak6142";
    Connection con;
    Statement stmt;
    ResultSet rs;
    Login(Quiz quiz){
        this.quizRef = quiz;
        setTitle("QUIZ APPLICATION");
        JPanel contentPane = new JPanel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                Image bg = new ImageIcon("../images/login_bg.jpg").getImage();
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        JPanel panel = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        ImageIcon icon = new ImageIcon("../images/APP_icon.png");
        SpringLayout Layout = new SpringLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel Login = new JLabel("Login");
        Login.setFont(new Font("Times New Roman", Font.BOLD, 30));
        JLabel Email = new JLabel("Email:");
        Email.setFont(new Font("Times New Roman", Font.BOLD, 18));
        JLabel password = new JLabel("Password:");
        password.setFont(new Font("Times New Roman", Font.BOLD, 18));
        JTextField EmailField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setEchoChar('*');
        //Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        loginButton.setBackground(Color.decode("#5ECC0A"));
        loginButton.addActionListener(e->{
            String userEmail = EmailField.getText();
            String Password = String.valueOf(passwordField.getPassword());
            try{
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection(url,user,pass);
                String sql = "SELECT * FROM users WHERE email=? AND password=?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, userEmail);
                pstmt.setString(2, Password);
                rs = pstmt.executeQuery();
                if(rs.next()){
                    id = rs.getInt("id");
                    Name = rs.getString("username");
                    quizRef.setIsLoggedIn(Name);
                    con.close();
                    this.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Wrong Username or password");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
        panel.setBounds(300,100,400,300);
        panel.setBackground(Color.decode("#8DABBA"));
        panel.add(Login);
        panel.add(Email);
        panel.add(EmailField);
        panel.add(password);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.setLayout(Layout);
        //labels
        //Heading
        Layout.putConstraint(SpringLayout.NORTH,Login,20,SpringLayout.NORTH,panel);
        Layout.putConstraint(SpringLayout.WEST,Login,160,SpringLayout.WEST,panel);
        //name
        Layout.putConstraint(SpringLayout.NORTH,Email,50,SpringLayout.SOUTH,Login);
        Layout.putConstraint(SpringLayout.WEST,Email,100,SpringLayout.WEST,panel);
        //password
        Layout.putConstraint(SpringLayout.NORTH,password,30,SpringLayout.SOUTH,Email);
        Layout.putConstraint(SpringLayout.WEST,password,70,SpringLayout.WEST,panel);
        //Namefield
        Layout.putConstraint(SpringLayout.NORTH,EmailField,50,SpringLayout.SOUTH,Login);
        Layout.putConstraint(SpringLayout.WEST,EmailField,20,SpringLayout.EAST,Email);
        //password field
        Layout.putConstraint(SpringLayout.NORTH,passwordField,30,SpringLayout.SOUTH,EmailField);
        Layout.putConstraint(SpringLayout.WEST,passwordField,20,SpringLayout.EAST,password);
        //Login Button
        Layout.putConstraint(SpringLayout.NORTH,loginButton,55,SpringLayout.SOUTH,passwordField);
        Layout.putConstraint(SpringLayout.WEST,loginButton,160,SpringLayout.WEST,panel);
        contentPane.add(panel);
        setIconImage(icon.getImage());
        setSize(1000,600);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }
    //public static void main(String[] args) {
      //  new Login();

    //}
}