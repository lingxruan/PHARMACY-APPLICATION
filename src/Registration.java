// Importing the Swing library for GUI components
import javax.swing.*;
// Importing the AWT (Abstract Window Toolkit) package for basic GUI functionalities
import java.awt.*;
// Importing classes and interfaces for handling GUI events
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Registration extends JPanel {
    private JTextField usernameField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JComboBox<String> genderDropdown;
    private JTextField addressField;
    private JTextField emailField;
    private JPasswordField passwordField;

    public Registration(DatabaseHandler dbHandler, Main main) {
        // Initialize and set up the registration panel components
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        setBackground(Color.ORANGE);

        JLabel registerLabel = new JLabel("REGISTER");
        registerLabel.setFont(new Font("Arial", Font.BOLD, 20));

        usernameField = new JTextField(20);
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        genderDropdown = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        addressField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back");

        // Set the gradient background for the "REGISTER" button
        registerButton.setContentAreaFilled(false);
        registerButton.setOpaque(true);
        registerButton.setBorderPainted(false);
        registerButton.setForeground(Color.BLACK);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Set the gradient background for the "BACK" button
        backButton.setContentAreaFilled(false);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setForeground(Color.BLACK);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

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

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setBackground(new Color(79, 172, 254));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(new Color(0, 242, 254));
            }
        });

        // Add an error label to display validation errors
        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add a success label to display the success message
        JLabel successLabel = new JLabel("");
        successLabel.setForeground(Color.GREEN);
        successLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(registerLabel, constraints);

        constraints.gridy = 1;
        add(new JLabel("Username:"), constraints);
        constraints.gridy = 2;
        add(usernameField, constraints);

        constraints.gridy = 3;
        add(new JLabel("First Name:"), constraints);
        constraints.gridy = 4;
        add(firstNameField, constraints);

        constraints.gridy = 5;
        add(new JLabel("Last Name:"), constraints);
        constraints.gridy = 6;
        add(lastNameField, constraints);

        constraints.gridy = 7;
        add(new JLabel("Gender:"), constraints);
        constraints.gridy = 8;
        add(genderDropdown, constraints);

        constraints.gridy = 9;
        add(new JLabel("Address:"), constraints);
        constraints.gridy = 10;
        add(addressField, constraints);

        constraints.gridy = 11;
        add(new JLabel("Email:"), constraints);
        constraints.gridy = 12;
        add(emailField, constraints);

        constraints.gridy = 13;
        add(new JLabel("Password:"), constraints);
        constraints.gridy = 14;
        add(passwordField, constraints);

        constraints.gridy = 15;
        add(registerButton, constraints);
        constraints.gridy = 16;
        add(backButton, constraints);

        constraints.gridy = 17;
        add(errorLabel, constraints);

        constraints.gridy = 18;
        add(successLabel, constraints);

        // Add action listeners for register and back buttons
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement registration logic here
                String username = usernameField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String gender = (String) genderDropdown.getSelectedItem();
                String address = addressField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                if (dbHandler.isUsernameTaken(username)) {
                    // Show an error message using a message dialog
                    JOptionPane.showMessageDialog(null, "Username is already taken. Please choose another.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() ||
                        address.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    // Show an error message using a message dialog
                    JOptionPane.showMessageDialog(null, "Please fill in all information.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Clear any previous error message
                    errorLabel.setText("");

                    // Perform the registration
                    dbHandler.registerUser(username, firstName, lastName, gender, address, email, password);

                    // Show a success message in the label
                    successLabel.setText("Registration successful");

                    // Show the Medicine Catalog panel after successful registration
                    main.showMedicineCatalog();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch back to the login panel
                main.showLogin();
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

