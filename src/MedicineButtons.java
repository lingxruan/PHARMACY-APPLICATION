// Importing the Swing library for GUI components
import javax.swing.*;
// Importing the AWT library for basic GUI functionalities
import java.awt.*;
// Importing classes for handling action events (e.g., button clicks)
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Importing classes for handling mouse events
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
// Importing classes for working with key-value pair storage
import java.util.HashMap;
import java.util.Map;


public class MedicineButtons extends JPanel {

    public MedicineButtons(MedicineCatalog medicineCatalog) {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        // Create a common gradient color for all buttons
        Color startColor = new Color(79, 172, 254);
        Color endColor = new Color(0, 242, 254);

        // Create a common mouse listener for all buttons
        MouseAdapter buttonMouseListener = new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                ((JButton) e.getSource()).setBackground(startColor);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ((JButton) e.getSource()).setBackground(endColor);
            }
        };

        // Create Home button with a resized icon
        ImageIcon homeIcon = new ImageIcon("home.png");
        Image homeImg = homeIcon.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        ImageIcon homeResizedIcon = new ImageIcon(homeImg);
        JButton homeButton = new JButton("Home", homeResizedIcon);
        homeButton.setOpaque(true);
        homeButton.setBorderPainted(false);
        homeButton.setForeground(Color.BLACK);
        homeButton.setFont(new Font("Arial", Font.BOLD, 10));
        homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        homeButton.addMouseListener(buttonMouseListener);
        homeButton.setBackground(startColor);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear the search results and reset the table
                medicineCatalog.clearSearchResults();
            }
        });
        add(homeButton);

        // Create Health Tips button with a resized icon
        ImageIcon healthTipsIcon = new ImageIcon("tips.png");
        Image healthTipsImg = healthTipsIcon.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        ImageIcon healthTipsResizedIcon = new ImageIcon(healthTipsImg);
        JButton healthTipsButton = new JButton("Health Tips", healthTipsResizedIcon);
        healthTipsButton.setOpaque(true);
        healthTipsButton.setBorderPainted(false);
        healthTipsButton.setForeground(Color.BLACK);
        healthTipsButton.setFont(new Font("Arial", Font.BOLD, 10));
        healthTipsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        healthTipsButton.addMouseListener(buttonMouseListener);
        healthTipsButton.setBackground(startColor);
        add(healthTipsButton);

        // Add an ActionListener to the Health Tips button
        healthTipsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display health tips
                JOptionPane.showMessageDialog(
                        null,
                        "Here are some health tips:\n1. Eat a balanced diet\n2. Exercise regularly\n3. Get enough sleep",
                        "Health Tips",
                        JOptionPane.PLAIN_MESSAGE
                );
            }
        });

        // Create Medicine button with a resized icon
ImageIcon medicineIcon = new ImageIcon("drugs.png");
Image medicineImg = medicineIcon.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
ImageIcon medicineResizedIcon = new ImageIcon(medicineImg);
JButton medicineButton = new JButton("Medicine", medicineResizedIcon);
medicineButton.setOpaque(true);
medicineButton.setBorderPainted(false);
medicineButton.setForeground(Color.BLACK);
medicineButton.setFont(new Font("Arial", Font.BOLD, 10));
medicineButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
medicineButton.addMouseListener(buttonMouseListener);
medicineButton.setBackground(startColor);
add(medicineButton);

// Create a map to store medicine details
Map<String, String> medicineDetails = new HashMap<>();
medicineDetails.put("Albuterol", "Dose: Inhale 1-2 puffs (90 mcg) every 4-6 hours as needed for asthma or bronchospasm.\nSide Effects:  Common side effects include nervousness, tremors, and rapid heart rate. Severe side effects are rare.\nSymptoms:  Albuterol is a bronchodilator that helps relieve shortness of breath and wheezing in asthma or respiratory conditions.\nUsage Guidelines:  Use as prescribed by your doctor. Do not exceed the recommended dosage. It provides quick relief for breathing difficulties.");
medicineDetails.put("Loratadine ", "Dose: Take 1 tablet (10 mg) once a day for allergy symptoms, such as hay fever.\nSide Effects: Side effects are rare but may include headache or dry mouth.\nSymptoms: Loratadine is an antihistamine that helps relieve allergy symptoms like sneezing, runny nose, and itchy eyes.\nUsage Guidelines: Take as directed on the label or by your healthcare provider. It is usually taken once daily.");
medicineDetails.put("Omeprazole", "Dose: Take 1 capsule (20 mg) daily before a meal for acid reflux and heartburn.\nSide Effects: Common side effects may include headache, stomach pain, and nausea. Long-term use can affect bone health.\nSymptoms: Omeprazole is a proton pump inhibitor used to reduce stomach acid and relieve symptoms of acid reflux and heartburn.\nUsage Guidelines: Take it as directed by your healthcare provider. It is usually taken in the morning before eating.");
medicineDetails.put("Simvastatin", "Dose: Typically, the initial dose is 20 mg once daily in the evening. Dosage may be adjusted based on cholesterol levels.\nSide Effects: Common side effects include muscle pain and headache. Rarely, it can cause liver problems.\nSymptoms:Simvastatin is a statin medication used to lower high cholesterol levels and reduce the risk of heart disease.\nUsage Guidelines: Take as prescribed by your doctor, usually in the evening. Follow a low-cholesterol diet for best results.");
medicineDetails.put("Cetirizine", "Dose: Take 1 tablet (10 mg) once daily for allergy symptoms, such as sneezing and itchy eyes.\nSide Effects: Side effects are generally mild and can include drowsiness, dry mouth, and headache.\nSymptoms: Cetirizine is an antihistamine used to relieve allergy symptoms and hives.\nUsage Guidelines: Take as directed on the label or by your healthcare provider. It is typically taken once a day and can be taken with or without food.");

// Add an ActionListener to the Medicine button
medicineButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Display a dialog to choose a medicine
        String[] medicineOptions = medicineDetails.keySet().toArray(new String[0]);
        String selectedMedicine = (String) JOptionPane.showInputDialog(
                null,
                "Select a Medicine:",
                "Medicine Selection",
                JOptionPane.PLAIN_MESSAGE,
                null,
                medicineOptions,
                medicineOptions[0]
        );

        if (selectedMedicine != null) {
            // Display medicine details in a dialog
            String details = medicineDetails.get(selectedMedicine);
            JOptionPane.showMessageDialog(
                    null,
                    "Medicine: " + selectedMedicine + "\n" + details,
                    "Medicine Details",
                    JOptionPane.PLAIN_MESSAGE
            );
        }
    }
});


        // Create Profile button with a resized icon
        ImageIcon profileIcon = new ImageIcon("user.png");
        Image profileImg = profileIcon.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        ImageIcon profileResizedIcon = new ImageIcon(profileImg);
        JButton profileButton = new JButton("Profile", profileResizedIcon);
        profileButton.setOpaque(true);
        profileButton.setBorderPainted(false);
        profileButton.setForeground(Color.BLACK);
        profileButton.setFont(new Font("Arial", Font.BOLD, 10));
        profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        profileButton.addMouseListener(buttonMouseListener);
        profileButton.setBackground(startColor);

        // Create an ActionListener for the Profile button
profileButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Create a user interface for the profile settings
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));

        String username = "PieceOfCake";

        ImageIcon userIcon = new ImageIcon("man.png");
        Image userImg = userIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon userResizedIcon = new ImageIcon(userImg);

        JLabel usernameLabel = new JLabel("" + username, userResizedIcon, JLabel.LEFT);
        usernameLabel.setVerticalTextPosition(JLabel.CENTER);
        usernameLabel.setHorizontalTextPosition(JLabel.RIGHT);
        profilePanel.add(usernameLabel);

        // Create View Profile button
JButton viewProfileButton = new JButton("View Profile");
viewProfileButton.setOpaque(true);
viewProfileButton.setBorderPainted(false);
viewProfileButton.setForeground(Color.BLACK);
viewProfileButton.setFont(new Font("Arial", Font.BOLD, 10));
viewProfileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
viewProfileButton.addMouseListener(buttonMouseListener);
viewProfileButton.setBackground(startColor); // Set the initial background color
add(viewProfileButton);

viewProfileButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Your action for View Profile button
    }
});

        profilePanel.add(viewProfileButton);

        // Create a spacer component for adding space between buttons
        profilePanel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Create Account Settings button
JButton accountSettingsButton = new JButton("Account Settings");
accountSettingsButton.setOpaque(true);
accountSettingsButton.setBorderPainted(false);
accountSettingsButton.setForeground(Color.BLACK);
accountSettingsButton.setFont(new Font("Arial", Font.BOLD, 10));
accountSettingsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
accountSettingsButton.addMouseListener(buttonMouseListener);
accountSettingsButton.setBackground(startColor); // Set the initial background color
add(accountSettingsButton);

accountSettingsButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        AccountSettings.showAccountSettingsDialog();
    }
});
                profilePanel.add(accountSettingsButton);

                // Create a spacer component for adding space between buttons
                profilePanel.add(Box.createRigidArea(new Dimension(0, 5)));

                // Create Sign Out button
JButton signOutButton = new JButton("Sign Out");
signOutButton.setOpaque(true);
signOutButton.setBorderPainted(false);
signOutButton.setForeground(Color.BLACK);
signOutButton.setFont(new Font("Arial", Font.BOLD, 10));
signOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
signOutButton.addMouseListener(buttonMouseListener);
signOutButton.setBackground(startColor); // Set the initial background color
add(signOutButton);

signOutButton.addActionListener(new ActionListener() {
    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Hide the profile dialog when signing out
                        JOptionPane optionPane = (JOptionPane) SwingUtilities.getAncestorOfClass(JOptionPane.class, signOutButton);
                        if (optionPane != null) {
                            optionPane.setValue(JOptionPane.CLOSED_OPTION);
                        }

                        SwingUtilities.getWindowAncestor(MedicineButtons.this).dispose();
                        SwingUtilities.invokeLater(() -> {
                            new Main();
                        });
                    }
                });
                profilePanel.add(signOutButton);

                int option = JOptionPane.showOptionDialog(
                        null,
                        profilePanel,
                        "Profile",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        new String[]{"Back"},
                        "Back"
                );

                if (option == 0) {

                }
            }
        });

add(profileButton);
    }
}

