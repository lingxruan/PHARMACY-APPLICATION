// Importing the Swing library for GUI components
import javax.swing.*;
// Importing the AWT (Abstract Window Toolkit) package for basic GUI functionalities
import java.awt.*;
// Importing ArrayList for dynamic array lists
import java.util.ArrayList;
// Importing HashMap for key-value pair storage
import java.util.HashMap;
// Importing List for working with lists
import java.util.List;
// Importing Map for working with key-value pairs
import java.util.Map;
// Importing DefaultTableModel for managing table data in a Swing JTable
import javax.swing.table.DefaultTableModel;


public class MedicineCatalog extends JPanel {

    private JTable medicineTable;
    private Map<String, Medicine> medicineMap;
    private List<String> originalMedicineList;
    private SearchBar searchBar;

    public MedicineCatalog() {
        setLayout(new BorderLayout());
        setBackground(new Color(79, 172, 254));

        // Create a top panel for search bar and images
        JPanel topPanel = new JPanel(new BorderLayout());

        // Create an image label for the left image
        ImageIcon leftImageIcon = ImageUtils.createResizedImageIcon("man.png", 35, 35);
        if (leftImageIcon != null) {
            JLabel leftImageLabel = new JLabel(leftImageIcon);
            topPanel.add(leftImageLabel, BorderLayout.WEST);
        }

        // Create a SearchBar and add it to the top panel
        searchBar = new SearchBar(this);
        topPanel.add(searchBar, BorderLayout.CENTER);

        // Create an image label for the right image and resize it
        ImageIcon rightImageIcon = ImageUtils.createResizedImageIcon("notify.png", 35, 35);
        if (rightImageIcon != null) {
            JLabel rightImageLabel = new JLabel(rightImageIcon);
            topPanel.add(rightImageLabel, BorderLayout.EAST);
        }

        add(topPanel, BorderLayout.NORTH);

        // Medicine data initialization
        medicineMap = new HashMap<>();
        medicineMap.put("Acetaminophen", new Medicine("Acetaminophen", "500 mg to 1000 mg every 4-6 hours as needed, not exceeding 4000 mg per day.", "Rare at normal doses but can cause liver damage at high doses.", "Pain and fever.", "Follow the dosing instructions on the label or as directed by your healthcare provider"));
        medicineMap.put("Aspirin", new Medicine("Aspirin", "Take 1 tablet (325 mg) every 4 to 6 hours as needed for pain and fever. Do not exceed 4 tablets in a 24-hour period.", "Common side effects may include upset stomach, heartburn, and headache. Rarely, it can cause bleeding or allergic reactions.", "Aspirin is commonly used to relieve pain, reduce inflammation, and lower fever. It can also help prevent blood clots.", "Follow the dosing instructions on the label or as directed by your healthcare provider. Avoid taking on an empty stomach."));
        medicineMap.put("Amoxicillin", new Medicine("Amoxicillin", "The usual dose for adults is 500 mg every 8 hours. The duration of treatment depends on the infection.", "Common side effects include diarrhea, nausea, and skin rash. Severe allergic reactions are rare but possible.", "Amoxicillin is an antibiotic used to treat bacterial infections, such as respiratory and urinary tract infections.", "Take the medication as prescribed by your doctor. Complete the full course of antibiotics, even if you feel better."));
        medicineMap.put("Ibuprofen", new Medicine("Ibuprofen", "Take 1 tablet (200 mg) every 4 to 6 hours for pain and inflammation. Do not exceed 1200 mg in a 24-hour period.", "Common side effects include upset stomach, heartburn, and headache. Prolonged use can lead to stomach ulcers.", "Ibuprofen is used to reduce pain, inflammation, and fever.", "Follow the dosing instructions on the label or as directed by your healthcare provider. Take with food or milk if it upsets your stomach."));

        // Create a table model for the medicine names
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Medicine Name"}, 0);
        originalMedicineList = new ArrayList<>(medicineMap.keySet());
        originalMedicineList.forEach(medicineName -> tableModel.addRow(new Object[]{medicineName}));
        medicineTable = new JTable(tableModel);

        // Set the preferred number of visible rows based on the last row's height
        int lastRowHeight = medicineTable.getPreferredSize().height - (originalMedicineList.size() - 1) * medicineTable.getRowHeight();
        int maxVisibleRows = Math.min(originalMedicineList.size(), 6); // Set to 6 rows for a 420x800 frame
        medicineTable.setPreferredScrollableViewportSize(new Dimension(400, lastRowHeight * maxVisibleRows));

        // Add a selection listener to show details as a message dialog when a row is selected
        medicineTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = medicineTable.getSelectedRow();
            if (selectedRow >= 0) {
                String medicineName = (String) tableModel.getValueAt(selectedRow, 0);
                Medicine medicine = medicineMap.get(medicineName);
                showMedicineDetailsDialog(medicine);
            }
        });

        JScrollPane scrollPane = new JScrollPane(medicineTable);
        scrollPane.setPreferredSize(new Dimension(400, lastRowHeight * maxVisibleRows)); // Set the size of the JScrollPane
        add(scrollPane, BorderLayout.CENTER);

        // Create the buttons panel
        MedicineButtons buttonsPanel = new MedicineButtons(this);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    public void clearSearchResults() {
        // Clear the search results (if any)
        searchBar.clearSearchField();
        // Reset the table to its initial state
        resetMedicineTable();
    }

    private void resetMedicineTable() {
        DefaultTableModel tableModel = (DefaultTableModel) medicineTable.getModel();
        tableModel.setRowCount(0); // Clear the table
        originalMedicineList.forEach(medicineName -> tableModel.addRow(new Object[]{medicineName}));
    }

    private void showMedicineDetailsDialog(Medicine medicine) {
        // Display medicine details as a message dialog
        StringBuilder message = new StringBuilder();
        message.append("Medicine Name: ").append(medicine.getName()).append("\n");
        message.append("Dose: ").append(medicine.getDose()).append("\n");
        message.append("Side Effects: ").append(medicine.getSideEffects()).append("\n");
        message.append("Symptoms: ").append(medicine.getSymptoms()).append("\n");
        message.append("Usage Guidelines: ").append(medicine.getUsageGuidelines()).append("\n");

        JOptionPane.showMessageDialog(this, message.toString(), "Medicine Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public void searchMedicine(String searchKeyword) {
        DefaultTableModel tableModel = (DefaultTableModel) medicineTable.getModel();
        tableModel.setRowCount(0); // Clear the table
        originalMedicineList.stream()
                .filter(medicineName -> medicineName.contains(searchKeyword))
                .forEach(medicineName -> tableModel.addRow(new Object[]{medicineName}));
    }

    public static void main(String[] args) {
        // Create and show the Medicine Catalog GUI
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Medicine Catalog");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MedicineCatalog catalog = new MedicineCatalog();
            frame.getContentPane().add(catalog);
            frame.setSize(420, 800); // Set the size of the frame
            frame.setVisible(true);
        });
    }
}
