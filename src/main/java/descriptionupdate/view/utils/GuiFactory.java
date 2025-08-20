package descriptionupdate.view.utils;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Factory class for creating GUI components with consistent styling.
 * Provides methods to create buttons, text fields, and combo boxes.
 */
public class GuiFactory {
    public static final String FONT = "Roboto";

    /**
     * Creates a JButton with the specified properties.
     *
     * @param name       the text to display on the button
     * @param backgroud  the background color of the button
     * @param foreGround the foreground color of the button
     * @param font       the font of the button text
     * @param action     the action listener to be added to the button
     * @return a JButton with the specified properties
     */
    static public JButton getButtom(final String name, final Color backgroud, final Color foreGround,
            final Font font, final ActionListener action) {

        final JButton button = new JButton(name);
        button.setBackground(backgroud);
        button.setForeground(foreGround);
        button.setFont(font);
        button.addActionListener(action);
        button.setEnabled(true);
        button.setFocusPainted(false);
        return button;

    }

    /**
     * Creates a JTextField with the specified number of columns.
     */
    public static JTextField getTextField(int i) {
        JTextField textField = new JTextField(i);
        return textField;
    }

    /**
     * Creates a JComboBox with the specified list of strings.
     */
    public static JComboBox<String> getComboBox(List<String> allGroupTypeString) {
        return new JComboBox<>(allGroupTypeString.toArray(new String[0]));
    }

}
