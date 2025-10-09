import javax.swing.*;
import java.awt.*;
import java.sql.*;
public class Login extends JFrame {
    String Name;
    String Password;
    int id;
    String url = "jdbc:postgresql://localhost:5432/quizDb";
    String user = "postgres";
    String pass = "Manak6142";
    Connection con;
    Statement stmt;
    ResultSet rs;
    Login(){
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
        JLabel name = new JLabel("Name:");
        name.setFont(new Font("Times New Roman", Font.BOLD, 18));
        JLabel password = new JLabel("Password:");
        password.setFont(new Font("Times New Roman", Font.BOLD, 18));
        JTextField nameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setEchoChar('*');
        //Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        loginButton.setBackground(Color.decode("#5ECC0A"));
        loginButton.addActionListener(e->{
            String UserName = nameField.getText();
            String Password = String.valueOf(passwordField.getPassword());
            try{
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection(url,user,pass);
                String sql = "SELECT * FROM users WHERE username=? AND password=?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, UserName);
                pstmt.setString(2, Password);
                rs = pstmt.executeQuery();
                if(rs.next()){
                    id = rs.getInt("id");
                    new MainScreen(UserName,id);
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
        panel.setBounds(600,200,400,300);
        panel.setBackground(Color.decode("#8DABBA"));
        panel.add(Login);
        panel.add(name);
        panel.add(nameField);
        panel.add(password);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.setLayout(Layout);
        //labels
        //Heading
        Layout.putConstraint(SpringLayout.NORTH,Login,20,SpringLayout.NORTH,panel);
        Layout.putConstraint(SpringLayout.WEST,Login,160,SpringLayout.WEST,panel);
        //name
        Layout.putConstraint(SpringLayout.NORTH,name,50,SpringLayout.SOUTH,Login);
        Layout.putConstraint(SpringLayout.WEST,name,100,SpringLayout.WEST,panel);
        //password
        Layout.putConstraint(SpringLayout.NORTH,password,30,SpringLayout.SOUTH,name);
        Layout.putConstraint(SpringLayout.WEST,password,70,SpringLayout.WEST,panel);
        //Namefield
        Layout.putConstraint(SpringLayout.NORTH,nameField,50,SpringLayout.SOUTH,Login);
        Layout.putConstraint(SpringLayout.WEST,nameField,20,SpringLayout.EAST,name);
        //password field
        Layout.putConstraint(SpringLayout.NORTH,passwordField,30,SpringLayout.SOUTH,nameField);
        Layout.putConstraint(SpringLayout.WEST,passwordField,20,SpringLayout.EAST,password);
        //Login Button
        Layout.putConstraint(SpringLayout.NORTH,loginButton,55,SpringLayout.SOUTH,passwordField);
        Layout.putConstraint(SpringLayout.WEST,loginButton,160,SpringLayout.WEST,panel);
        contentPane.add(panel);
        setIconImage(icon.getImage());
        setSize(2400,1000);
        setLayout(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Login();

    }
}