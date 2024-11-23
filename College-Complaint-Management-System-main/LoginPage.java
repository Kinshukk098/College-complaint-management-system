import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class LoginPage implements ActionListener {
    private JFrame frame;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, signupButton;
    private final String USER_FILE = "users.txt";
    private Color primaryColor = new Color(25, 118, 210); // Dark blue
    private Color accentColor = new Color(245, 245, 245); // Light gray

    public LoginPage() {
        frame = new JFrame("IIIT Allahabad - Login");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLayout(null); // Using null layout for precise positioning

        // Center the frame on screen
        frame.setLocationRelativeTo(null);

        // Institute Name Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(primaryColor);
        headerPanel.setBounds(0, 0, 500, 100);
        headerPanel.setLayout(new GridBagLayout());

        JLabel instituteLabel = new JLabel("IIIT ALLAHABAD");
        instituteLabel.setFont(new Font("Arial", Font.BOLD, 28));
        instituteLabel.setForeground(Color.WHITE);
        headerPanel.add(instituteLabel);

        // Main Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBounds(50, 120, 400, 400);
        contentPanel.setLayout(null);

        // Login Label
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 24));
        loginLabel.setForeground(primaryColor);
        loginLabel.setBounds(150, 20, 100, 30);
        contentPanel.add(loginLabel);

        // Email Field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel.setBounds(50, 80, 100, 25);
        contentPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(50, 105, 300, 35);
        emailField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(primaryColor),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPanel.add(emailField);

        // Password Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setBounds(50, 150, 100, 25);
        contentPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(50, 175, 300, 35);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(primaryColor),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPanel.add(passwordField);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(50, 240, 300, 40);
        loginButton.setBackground(primaryColor);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(this);
        contentPanel.add(loginButton);

        // Sign Up Button
        signupButton = new JButton("Don't have an account? Sign Up");
        signupButton.setBounds(50, 290, 300, 40);
        signupButton.setBackground(Color.WHITE);
        signupButton.setForeground(primaryColor);
        signupButton.setFont(new Font("Arial", Font.PLAIN, 12));
        signupButton.setBorder(BorderFactory.createEmptyBorder());
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupButton.addActionListener(this);
        contentPanel.add(signupButton);

        frame.add(headerPanel);
        frame.add(contentPanel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame,
                        "Please fill in all fields",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (validateUser(email, password)) {
                JOptionPane.showMessageDialog(frame,
                        "Login successful!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new ComplaintGUI();
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Invalid email or password",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == signupButton) {
            frame.dispose();
            new SignupPage();
        }
    }

    private boolean validateUser(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] user = line.split(",");
                if (user.length == 3 && user[0].equals(email) && user[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new LoginPage());
    }
}