// Importing the Swing library for GUI components
import javax.swing.*;
// Importing the AWT (Abstract Window Toolkit) package for basic GUI functionalities
import java.awt.*;

// Importing classes and interfaces for handling GUI events
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SearchBar extends JPanel {
    private JTextField searchField;
    private MedicineCatalog medicineCatalog;

    public SearchBar(MedicineCatalog catalog) {
        medicineCatalog = catalog;

        setLayout(new FlowLayout(FlowLayout.CENTER));

        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        searchButton.setContentAreaFilled(false);
        searchButton.setOpaque(true);
        searchButton.setBorderPainted(false);
        searchButton.setForeground(Color.BLACK);
        searchButton.setFont(new Font("Arial", Font.BOLD, 12));
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                searchButton.setBackground(new Color(79, 172, 254));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                searchButton.setBackground(new Color(0, 242, 254));
            }
        });

        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchMedicine();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchMedicine();
            }
        });

        add(searchField);
        add(searchButton);
    }

    public void clearSearchField() {
        searchField.setText(""); // Clear the search field
    }

    private void searchMedicine() {
        String searchKeyword = searchField.getText();
        medicineCatalog.searchMedicine(searchKeyword);
    }
}
