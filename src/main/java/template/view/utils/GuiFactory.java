package template.view.utils;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;

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

}
