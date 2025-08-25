package descriptionupdate.view.scenes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import descriptionupdate.view.View;
import descriptionupdate.view.utils.GuiFactory;

/**
 * LogInScene class that extends JPanel to create a login scene for the application.
 */
public class LogInScene extends JPanel {

    private static final String FONT = "Roboto";

    @SuppressWarnings("unused")
    private final View view;

    /**
     * Constructor for LogInScene.
     *
     * @param view the main view of the application
     */
    public LogInScene(View view) {
        this.view = view;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600, 400)); // Pannello più grande

        // Titolo dell'applicazione in alto
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));
        JLabel titleLabel = new JLabel("Description Database");
        titleLabel.setFont(new Font(FONT, Font.BOLD, 32));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        northPanel.add(titleLabel);
        this.add(northPanel, BorderLayout.NORTH);

        // Pulsante centrato
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(60, 20, 60, 20));
        centerPanel.setOpaque(false);

        JButton accediButton = GuiFactory.getButtom(
            "Accedi",
            Color.GRAY,
            Color.BLACK,
            new Font(FONT, Font.BOLD, 22),
            new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    view.getController().initialScene();
                }
            }
        );
        accediButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(accediButton);
        centerPanel.add(Box.createVerticalGlue());

        this.add(centerPanel, BorderLayout.CENTER);
    }
}
