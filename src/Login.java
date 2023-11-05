// Importing the Swing library for GUI components
import javax.swing.*;
// Importing the AWT (Abstract Window Toolkit) package for basic GUI functionalities
import java.awt.*;
// Importing classes and interfaces for handling GUI events
import java.awt.event.*;


public class Login extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login(DatabaseHandler dbHandler, Main main) {
        // Initialize and set up the login panel components
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // Load the image
        ImageIcon originalIcon = new ImageIcon("boy.png");

        // Resize the image
        Image scaledImage = originalIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon logoIcon = new ImageIcon(scaledImage);

        // Create a JLabel to display the image
        JLabel logoLabel = new JLabel(logoIcon);

        JLabel titleLabel = new JLabel("LOGIN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Log In");
        JButton registerButton = new JButton("Register");

        JLabel forgotPasswordLabel = new JLabel("Forgot Password?");
        forgotPasswordLabel.setForeground(Color.BLUE);
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(logoLabel, constraints); // Add the resized logo image

        constraints.gridy = 1;
        add(titleLabel, constraints);

        constraints.gridy = 2;
        add(new JLabel("Username:"), constraints);

        constraints.gridy = 3;
        add(usernameField, constraints);

        constraints.gridy = 4;
        add(new JLabel("Password:"), constraints);

        constraints.gridy = 5;
        add(passwordField, constraints);

        // Center the "Forgot Password" label between the password field and the login button
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        add(forgotPasswordLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 1;

        // Set the gradient background for the "LOGIN" button
        loginButton.setContentAreaFilled(false);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(new Color(79, 172, 254));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(new Color(0, 242, 254));
            }
        });

        // Set the gradient background for the "REGISTER" button
        registerButton.setContentAreaFilled(false);
        registerButton.setOpaque(true);
        registerButton.setBorderPainted(false);
        registerButton.setForeground(Color.BLACK);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                registerButton.setBackground(new Color(79, 172, 254));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                registerButton.setBackground(new Color(0, 242, 254));
            }
        });

        add(loginButton, constraints);

        constraints.gridy = 8;
        add(registerButton, constraints);

        // Add a MouseListener to the label to trigger the "Forgot Password" action
        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = usernameField.getText();
                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter your username to reset the password.", "Forgot Password?", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "A password reset link has been sent to your email.", "Forgot Password", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in your username and password.", "Login Failed.", JOptionPane.ERROR_MESSAGE);
                } else if (dbHandler.login(username, password)) {
                    // Show the Medicine Catalog after a successful login
                    main.showMedicineCatalog();
                } else {
                    JOptionPane.showMessageDialog(null, "Login failed", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to the registration panel
                main.showRegistration();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Define the gradient colors
        Color color1 = new Color(79, 172, 254);
        Color color2 = new Color(0, 242, 254);

        // Create a GradientPaint for the background
        GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}