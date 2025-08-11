package template.view.scenes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import template.view.View;
import template.view.utils.GuiFactory;

public class WelcomeScene extends JPanel {

    private static final String FONT = "Roboto";
    private final View view;

    public WelcomeScene(View view) {
        this.view = view;
        this.setLayout(new BorderLayout());

        // North: Title panel
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        JLabel titleLabel = new JLabel("Welcome Scene");
        titleLabel.setFont(new Font(FONT, Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        northPanel.add(titleLabel);
        this.add(northPanel, BorderLayout.NORTH);

        // Center: JTable in JScrollPane
        String[] columnNames = {"Column 1", "Column 2", "Column 3"};
        Object[][] data = {
            {"Row1-Col1", "Row1-Col2", "Row1-Col3"},
            {"Row2-Col1", "Row2-Col2", "Row2-Col3"}
        };
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        this.add(scrollPane, BorderLayout.CENTER);

        // South: Panel with 4 buttons
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

            JButton button1 = GuiFactory.getButtom("Button 1", Color.GREEN, Color.BLACK, Font.getFont(FONT), new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    // Add your button logic here
                }
            });
                        JButton button2 = GuiFactory.getButtom("Button 2", Color.GREEN, Color.BLACK, Font.getFont(FONT), new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    // Add your button logic here
                }
            });
                        JButton button3 = GuiFactory.getButtom("Button 3", Color.GREEN, Color.BLACK, Font.getFont(FONT), new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    // Add your button logic here
                }
            });
                        JButton button4 = GuiFactory.getButtom("Button 4", Color.GREEN, Color.BLACK, Font.getFont(FONT), new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    // Add your button logic here
                }
            });
            southPanel.add(Box.createHorizontalStrut(10));
            southPanel.add(button1);
            southPanel.add(button2);
            southPanel.add(button3);
            southPanel.add(button4);
        this.add(southPanel,BorderLayout.SOUTH);
}}
