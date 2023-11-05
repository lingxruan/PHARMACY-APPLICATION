// Importing the SwingUtilities class from javax.swing for managing Swing components and threads
import javax.swing.SwingUtilities;

public class Main {
    private JavaFrame frame;
    private Login login;
    private Registration registration;
    private MedicineCatalog medicineCatalog;
    private DatabaseHandler dbHandler;

    public Main() {
        frame = new JavaFrame();
        dbHandler = new DatabaseHandler();
        login = new Login(dbHandler, this);
        registration = new Registration(dbHandler, this);
        medicineCatalog = new MedicineCatalog();
        frame.add(login);
        frame.setVisible(true);
    }

    public void showLogin() {
        frame.getContentPane().remove(registration);
        frame.getContentPane().remove(medicineCatalog);
        frame.getContentPane().add(login);
        frame.revalidate();
        frame.repaint();
    }

    public void showMedicineCatalog() {
        frame.getContentPane().remove(login);
        frame.getContentPane().remove(registration);
        frame.getContentPane().add(medicineCatalog);
        frame.revalidate();
        frame.repaint();
    }

    public void showRegistration() {
        frame.getContentPane().remove(login);
        frame.getContentPane().add(registration);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main();
        });
    }
}
