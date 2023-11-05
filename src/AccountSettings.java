// Importing the Swing library for GUI components
import javax.swing.*;

public class AccountSettings {
    public static void showAccountSettingsDialog() {
        // Create a panel for the account settings
        JPanel accountSettingsPanel = new JPanel();
        accountSettingsPanel.setLayout(new BoxLayout(accountSettingsPanel, BoxLayout.Y_AXIS));

        // Create labels and input fields for the account settings
        JTextField usernameField = new JTextField(20);
        JTextField firstNameField = new JTextField(20);
        JTextField lastNameField = new JTextField(20);
        JTextField genderField = new JTextField(20);
        JTextField addressField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        // Add labels and input fields to the account settings panel
        accountSettingsPanel.add(new JLabel("Change Username:"));
        accountSettingsPanel.add(usernameField);
        accountSettingsPanel.add(new JLabel("Change First Name:"));
        accountSettingsPanel.add(emailField);
        accountSettingsPanel.add(new JLabel("Change Last Name:"));
        accountSettingsPanel.add(firstNameField);
        accountSettingsPanel.add(new JLabel("Change Gender:"));
        accountSettingsPanel.add(lastNameField);
        accountSettingsPanel.add(new JLabel("Change Address:"));
        accountSettingsPanel.add(addressField);
        accountSettingsPanel.add(new JLabel("Change Email:"));
        accountSettingsPanel.add(genderField);
        accountSettingsPanel.add(new JLabel("Change Password:"));
        accountSettingsPanel.add(passwordField);

        // Display a dialog with the account settings panel and "OK" and "Cancel" buttons
        int result = JOptionPane.showConfirmDialog(null, accountSettingsPanel, "Account Settings", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            // Get the new values from the input fields
            String newUsername = usernameField.getText();
            String newFirstName = firstNameField.getText();
            String newLastName = lastNameField.getText();
            String newGender = genderField.getText();
            String newAddress = addressField.getText();
            String newEmail = emailField.getText();
            String newPassword = new String(passwordField.getPassword());

            // Update the user's information in the database
            boolean updateSuccessful = updateUserInfo(newUsername, newFirstName, newLastName, newGender, newAddress, newEmail, newPassword);

            if (updateSuccessful) {
                JOptionPane.showMessageDialog(null, "Successfully changed account.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update account. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Simulate updating the user's information in the database
    private static boolean updateUserInfo(String newUsername, String newFirstName, String newLastName, String newGender, String newAddress, String newEmail, String newPassword) {
        return true; // Replace with actual database update logic
    }
}
