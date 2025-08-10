package template.view.scenes;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import template.view.View;
import template.view.utils.GuiFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

public class WelcomeScene extends JPanel {

    private static final String FONT = "Roboto";

    private final View view;

    public WelcomeScene(View view) {
        this.view = view;
        this.setLayout(new BorderLayout());

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));
        this.add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(GuiFactory.getButtom("Pippo", Color.GREEN, Color.BLACK, Font.getFont(FONT), new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                view.getController().secondScene();
            }
        }));
        
    }

}
