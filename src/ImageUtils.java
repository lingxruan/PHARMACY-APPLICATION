// Importing the ImageIcon class from Swing for working with image icons in GUI
import javax.swing.ImageIcon;
// Importing the Image class from AWT for handling and manipulating images
import java.awt.Image;


public class ImageUtils {
    public static ImageIcon createResizedImageIcon(String imagePath, int width, int height) {
        try {
            ImageIcon originalIcon = new ImageIcon(imagePath);
            Image image = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(image);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
