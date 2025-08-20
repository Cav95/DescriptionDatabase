package descriptionupdate.view.utils;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

public class GuiFactory {
    public static final String FONT = "Roboto";

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

    public static JTextField getTextField(int i) {
        JTextField textField = new JTextField(i);
        return textField;
    }

    public static JComboBox<String> getComboBox(List<String> allGroupTypeString) {
        return new JComboBox<>(allGroupTypeString.toArray(new String[0]));
    }

}
