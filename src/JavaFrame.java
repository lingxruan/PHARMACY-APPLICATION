// Importing the JFrame class from Swing for creating a GUI window
import javax.swing.JFrame;
// Importing the SwingUtilities class for managing GUI components and threads
import javax.swing.SwingUtilities;


public class JavaFrame extends JFrame {
    public JavaFrame() {
        setTitle("EasyPharm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 800);

        // Create an instance of the MedicineCatalog panel and add it to the frame
        MedicineCatalog medicineCatalog = new MedicineCatalog();
        add(medicineCatalog);

        // Optional: Center the frame on the screen
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // Create and show the JavaFrame GUI
        SwingUtilities.invokeLater(() -> {
            JavaFrame frame = new JavaFrame();
            frame.setVisible(true);
        });
    }
}
