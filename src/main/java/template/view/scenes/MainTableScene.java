package template.view.scenes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import template.model.api.Description;
import template.view.View;
import template.view.utils.GuiFactory;
import template.view.utils.SelectionTable;

public class MainTableScene extends JPanel {
    // private static final int TIME_TO_LAMP = 6; // Example value, adjust as needed
    private static final String FONT = "Roboto";

    private static final long serialVersionUID = 1L;

    private boolean isSaved;


    @SuppressWarnings("unused")
    private final View view;

    public MainTableScene(View view , boolean isSaved) {
        this.view = view;
        this.isSaved = isSaved; // Initialize as saved
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

        final List<Description> des = view.getController().getListDescription();

        final JTable table = new SelectionTable(
                des.stream()
                        .map(desc -> new Object[] {
                                desc.group(),
                                desc.itaDescripion(),
                                desc.engDescription()
                        })
                        .toArray(Object[][]::new),
                new String[] {
                        "GROUP", "ITA", "ING"
                });
        table.setFont(new Font(FONT, Font.PLAIN, 12));
        table.getColumnModel().getColumn(0).setPreferredWidth(170);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        this.add(scrollPane, BorderLayout.CENTER);

        // South: Panel with 4 buttons
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JButton AddButtom = GuiFactory.getButtom("Aggiungi", Color.GREEN, Color.BLACK, Font.getFont(FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            view.getController().addScene();
                           // isSaved = false; // Mark as not saved after adding
                        } catch (Exception ex) {

                        }

                    }
                });
        JButton DeleteButtom = GuiFactory.getButtom("Elimina", Color.GREEN, Color.BLACK, Font.getFont(FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow >= 0) {
                            String group = (String) table.getValueAt(selectedRow, 0);
                            String ita = (String) table.getValueAt(selectedRow, 1);
                            String eng = (String) table.getValueAt(selectedRow, 2);
                            view.getController().deleteDescription(new Description(ita, eng, group));
                            view.getController().initialScene(false);
                          //  isSaved = false; // Mark as not saved after deletion
                        } else {
                            throw new IllegalStateException("No request selected for management");
                        }
                    }
                });
        JButton UpdateButtom = GuiFactory.getButtom("Aggiorna", Color.GREEN, Color.BLACK, Font.getFont(FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow >= 0) {
                            String group = (String) table.getValueAt(selectedRow, 0);
                            String ita = (String) table.getValueAt(selectedRow, 1);
                            String eng = (String) table.getValueAt(selectedRow, 2);
                            UpdateDescriptionDialog dialog = new UpdateDescriptionDialog(view, "Update description",
                                    ita, eng, group);
                            dialog.setVisible(true);

                        } else {
                            throw new IllegalStateException("No request selected for management");
                        }
                    }
                });
        JButton Save = GuiFactory.getButtom("Save", Color.GREEN, Color.BLACK, Font.getFont(FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            view.getController().save();
                            JOptionPane.showMessageDialog(MainTableScene.this, "Changes saved successfully!");
                           // isSaved = true; // Mark as saved after successful save
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(MainTableScene.this,
                                    "Error saving changes: " + ex.getMessage(),
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
        JButton exit = GuiFactory.getButtom("Exit", Color.GREEN, Color.BLACK, Font.getFont(FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (isSaved) {
                            view.exitApplication();
                        } else {

                            int response = JOptionPane.showConfirmDialog(
                                    null,
                                    "Do you want to save?",
                                    "Save Confirmation",
                                    JOptionPane.YES_NO_OPTION);
                            if (response == JOptionPane.YES_OPTION) {
                                // User chose to save
                                view.getController().save();
                                JOptionPane.showMessageDialog(MainTableScene.this, "Changes saved successfully!");
                            } else {
                                // User chose not to save
                                view.exitApplication();
                                JOptionPane.showMessageDialog(MainTableScene.this,
                                        "Changes discarded. Exiting application.");
                            }
                            view.exitApplication();

                        }
                    }
                });
        southPanel.add(Box.createHorizontalStrut(10));
        southPanel.add(AddButtom);
        southPanel.add(DeleteButtom);
        southPanel.add(UpdateButtom);
        southPanel.add(Save);
        southPanel.add(exit);
        this.add(southPanel, BorderLayout.SOUTH);
    }
}
