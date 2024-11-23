import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ComplaintGUI implements ActionListener, WindowListener {
    private JFrame win;
    private compFile cfile;
    private JButton menuBtns[];
    private final String password = "iiita123";
    // Define colors for consistent theme
    private final Color BACKGROUND_COLOR = new Color(240, 248, 255);
    private final Color BUTTON_COLOR = new Color(16, 51, 228);
    private final Color HOVER_COLOR = new Color(225, 104, 219);
    private final Color TEXT_COLOR = Color.WHITE;
    private final Color BORDER_COLOR = new Color(112, 237, 3);

    public ComplaintGUI() {
        win = new JFrame();
        String tmpPath = System.getProperty("java.io.tmpdir");
        cfile = new compFile(tmpPath + "comps.txt");

        // Enhanced window setup
        win.setTitle("Complaint Management System");
        win.setSize(600, 800); // Increased height to accommodate header
        win.addWindowListener(this);
        win.setLocationRelativeTo(null);

        // Create main container with BorderLayout
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setBackground(BACKGROUND_COLOR);

        // Create header panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add logo
        ImageIcon logoIcon = new ImageIcon("abc.png"); // Replace with actual path
        Image scaledImage = logoIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));

        // Add institution name
        JLabel institutionLabel = new JLabel("IIIT ALLAHABAD");
        institutionLabel.setFont(new Font("Arial", Font.BOLD, 28));
        institutionLabel.setForeground(new Color(0, 51, 102)); // Dark blue color for text

        headerPanel.add(logoLabel);
        headerPanel.add(institutionLabel);

        // Create main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 1, 15, 15));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Add title label
        JLabel titleLabel = new JLabel("COMPLAINT MANAGEMENT SYSTEM", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(BORDER_COLOR);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        mainPanel.add(titleLabel);

        // Initialize buttons with enhanced styling
        menuBtns = new JButton[5];
        for (int i = 0; i < menuBtns.length; ++i) {
            final int buttonIndex = i;
            menuBtns[i] = new JButton();

            // Enhanced button styling
            menuBtns[i].setFont(new Font("Arial", Font.BOLD, 16));
            menuBtns[i].setBackground(BUTTON_COLOR);
            menuBtns[i].setForeground(TEXT_COLOR);
            menuBtns[i].setFocusPainted(false);
            menuBtns[i].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(BORDER_COLOR, 2),
                    BorderFactory.createEmptyBorder(10, 15, 10, 15)
            ));

            // Add hover effect
            menuBtns[i].addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    menuBtns[buttonIndex].setBackground(HOVER_COLOR);
                    menuBtns[buttonIndex].setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                public void mouseExited(MouseEvent e) {
                    menuBtns[buttonIndex].setBackground(BUTTON_COLOR);
                }

                public void mousePressed(MouseEvent e) {
                    menuBtns[buttonIndex].setBackground(BORDER_COLOR);
                }

                public void mouseReleased(MouseEvent e) {
                    menuBtns[buttonIndex].setBackground(HOVER_COLOR);
                }
            });

            mainPanel.add(menuBtns[i]);
            menuBtns[i].addActionListener(this);
        }

        // Set button text with icons
        menuBtns[0].setText("📋 MAIN MENU");
        menuBtns[1].setText("➕ Lodge Complaint");
        menuBtns[2].setText("🔍 Check Status");
        menuBtns[3].setText("📑 View Filed Complaints");
        menuBtns[4].setText("📊 Generate Report");
        menuBtns[0].setEnabled(false);
        menuBtns[0].setBackground(new Color(128, 128, 128));

        // Add panels to container
        containerPanel.add(headerPanel, BorderLayout.NORTH);
        containerPanel.add(mainPanel, BorderLayout.CENTER);

        win.add(containerPanel);
        win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        win.setVisible(true);
    }

    // Rest of your existing methods remain the same
    @Override
    public void actionPerformed(ActionEvent e) {
        if (menuBtns[1] == e.getSource()) {
            new compRegister(cfile);
        } else if (menuBtns[2] == e.getSource()) {
            new compStatus(cfile);
        } else if (menuBtns[3] == e.getSource()) {
            String pwdEntered = JOptionPane.showInputDialog(win, "Enter Password: ");
            if (pwdEntered == null) {
                // do nothing
            } else if (pwdEntered.equals(password)) {
                new compCheck(cfile);
            } else {
                JOptionPane.showMessageDialog(win, "Wrong password");
            }
        } else if (menuBtns[4] == e.getSource()) {
            new compReport(cfile);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        win.dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        cfile.exit();
    }

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}