import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SignupPage implements ActionListener {
    private JFrame frame;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JRadioButton staffRadioButton, studentRadioButton, facultyRadioButton;
    private ButtonGroup group;
    private JButton submitButton, loginButton;
    private final String USER_FILE = "users.txt";
    private Color primaryColor = new Color(25, 118, 210); // Dark blue
    private Color accentColor = new Color(245, 245, 245); // Light gray

    public SignupPage() {
        frame = new JFrame("IIIT Allahabad - Sign Up");
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

        // Sign Up Label
        JLabel signupLabel = new JLabel("Sign Up");
        signupLabel.setFont(new Font("Arial", Font.BOLD, 24));
        signupLabel.setForeground(primaryColor);
        signupLabel.setBounds(150, 20, 100, 30);
        contentPanel.add(signupLabel);

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

        JPanel rolePanel = new JPanel();
        rolePanel.setBackground(Color.WHITE);
        rolePanel.setBounds(50, 230, 300, 60); // Increased height from 35 to 60
        rolePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(primaryColor, 1),
                "Select Role",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.PLAIN, 12),
                primaryColor
        ));
        rolePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10)); // Added proper layout with spacing

        staffRadioButton = new JRadioButton("Staff");
        studentRadioButton = new JRadioButton("Student");
        facultyRadioButton = new JRadioButton("Faculty");

        // Style radio buttons
        for (JRadioButton rb : new JRadioButton[]{staffRadioButton, studentRadioButton, facultyRadioButton}) {
            rb.setBackground(Color.WHITE);
            rb.setFont(new Font("Arial", Font.PLAIN, 12));
            rb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        group = new ButtonGroup();
        group.add(staffRadioButton);
        group.add(studentRadioButton);
        group.add(facultyRadioButton);

        rolePanel.add(staffRadioButton);
        rolePanel.add(studentRadioButton);
        rolePanel.add(facultyRadioButton);
        contentPanel.add(rolePanel);

        // Submit Button - Adjusted position
        submitButton = new JButton("Sign Up");
        submitButton.setBounds(50, 310, 300, 40); // Moved down by 20 pixels
        submitButton.setBackground(primaryColor);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setFocusPainted(false);
        submitButton.setBorder(BorderFactory.createEmptyBorder());
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.addActionListener(this);
        contentPanel.add(submitButton);

        // Login Button - Adjusted position
        loginButton = new JButton("Already have an account? Log In");
        loginButton.setBounds(50, 360, 300, 40); // Moved down by 20 pixels
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(primaryColor);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 12));
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> {
            frame.dispose();
            new LoginPage();
        });
        contentPanel.add(loginButton);

        frame.add(headerPanel);
        frame.add(contentPanel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String role = "";

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "Please fill in all fields",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (staffRadioButton.isSelected()) {
            role = "Staff";
        } else if (studentRadioButton.isSelected()) {
            role = "Student";
        } else if (facultyRadioButton.isSelected()) {
            role = "Faculty";
        } else {
            JOptionPane.showMessageDialog(frame,
                    "Please select a role",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            writer.write(email + "," + password + "," + role);
            writer.newLine();
            JOptionPane.showMessageDialog(frame,
                    "Registration successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            new LoginPage();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame,
                    "Error registering user",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new SignupPage());
    }
}