import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginGUI implements ActionListener {
    private JFrame win;
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JButton loginButton;
    private final String correctUsername = "admin";
    private final String correctPassword = "password"; // You can change this as per your needs

    public LoginGUI() {
        win = new JFrame();
        win.setTitle("Login");
        win.setSize(300, 200);
        win.setLayout(new GridLayout(3, 2));

        // Username
        win.add(new JLabel("Username:"));
        tfUsername = new JTextField(15);
        win.add(tfUsername);

        // Password
        win.add(new JLabel("Password:"));
        pfPassword = new JPasswordField(15);
        win.add(pfPassword);

        // Login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        win.add(loginButton);

        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = tfUsername.getText();
        String password = new String(pfPassword.getPassword());

        if (username.equals(correctUsername) && password.equals(correctPassword)) {
            win.dispose();
            new ComplaintGUI(); // Launch the main complaint system GUI after successful login
        } else {
            JOptionPane.showMessageDialog(win, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new LoginGUI(); // Show the login page when the application starts
    }
}
