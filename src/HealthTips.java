// Importing the Swing library for GUI components
import javax.swing.*;

public class HealthTips extends JPanel {
    public HealthTips() {
        JTextArea healthTipsText = new JTextArea();
        healthTipsText.setText("Here are some health tips:\n1. Eat a balanced diet\n2. Exercise regularly\n3. Get enough sleep");
        healthTipsText.setEditable(false);
        add(healthTipsText);
    }
}
