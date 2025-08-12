package template.view.scenes;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import template.model.api.Description;
import template.view.View;
import template.view.utils.GuiFactory;

import java.awt.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

public class AddDescriptionScene extends JPanel {

    private static final String FONT = "Roboto";

    @SuppressWarnings("unused")
    private final View view;

    public AddDescriptionScene(View view) {
        this.view = view;
        /*
         * this.view = view;
         * this.itaDescription = itaDescription;
         * this.engDescription = engDescription;
         * this.group = group;
         */

        this.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        JLabel titleLabel = new JLabel("Add Description Scene");
        titleLabel.setFont(new Font(FONT, Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        northPanel.add(titleLabel);
        this.add(northPanel, BorderLayout.NORTH);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));
        JLabel itaLabel = new JLabel("ITA Description:");
        JLabel engLabel = new JLabel("ENG Description:");
        JLabel groupLabel = new JLabel("Group:");

        JTextField itaTextField = GuiFactory.getTextField(20);
        JTextField engTextField = GuiFactory.getTextField(20);
        JTextField groupTextField = GuiFactory.getTextField(20);
        mainPanel.add(itaLabel);
        mainPanel.add(itaTextField);

        mainPanel.add(engLabel);
        mainPanel.add(engTextField);

        mainPanel.add(groupLabel);
        mainPanel.add(groupTextField);
        this.add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(
                GuiFactory.getButtom("Aggiungi", Color.GRAY, Color.BLACK, Font.getFont(FONT), new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        view.getController().addDescription(
                                new Description(itaLabel.getText(), engLabel.getText(), groupLabel.getText()));
                    }
                }));
        mainPanel.add(GuiFactory.getButtom("Annulla", Color.RED, Color.WHITE, Font.getFont(FONT), new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                view.getController().initialScene();
            }
        }));

    }

}
