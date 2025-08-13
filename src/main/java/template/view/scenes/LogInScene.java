package template.view.scenes;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import template.view.View;
import template.view.utils.GuiFactory;
import template.view.utils.UserAdmit;

import java.awt.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LogInScene extends JPanel {

    private static final String FONT = "Roboto";

    @SuppressWarnings("unused")
    private final View view;

    public LogInScene(View view) {
        this.view = view;
        this.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        JLabel titleLabel = new JLabel("Welcome Scene");
        titleLabel.setFont(new Font(FONT, Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        northPanel.add(titleLabel);
        this.add(northPanel, BorderLayout.NORTH);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));
        this.add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(GuiFactory.getButtom("Accedi", Color.GRAY, Color.BLACK, Font.getFont(FONT), new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (Arrays.asList(UserAdmit.values()).stream()
                        .filter(t -> t.getName().equals(System.getProperty("user.name")))
                        .findFirst().isPresent()) {
                    view.getController().initialScene();
                } else {
                    JOptionPane.showMessageDialog(view.getMainFrame(), "Accesso negato", "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
                view.getController().initialScene();
            }
        }));

    }

}
