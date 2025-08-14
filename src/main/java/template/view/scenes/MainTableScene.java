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
    private static final String ALL = "%";

    // private static final int TIME_TO_LAMP = 6; // Example value, adjust as needed
    private static final String FONT = "Roboto";

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private boolean isSaved;

    private String itaDescription;
    private String engDescription;
    private String group;

    JLabel desFilter = new JLabel("Filtro Descrizione:");
    JTextField itaTextField = GuiFactory.getTextField(20);
    JLabel engFilter = new JLabel("Filtro Inglese:");
    JTextField engTextField = GuiFactory.getTextField(20);
    JLabel groupFilter = new JLabel("Filtro Gruppo:");
    List<String> listGroup;
    JComboBox<String> groupTextField;

    @SuppressWarnings("unused")
    private final View view;

    public MainTableScene(View view, boolean isSaved) {
        this.view = view;
        this.isSaved = isSaved; // Initialize as saved
        this.itaDescription = ALL;
        this.engDescription = ALL;
        this.group = ALL;

        listGroup = view.getController().getAllGroupTypeString();
        listGroup.add(0, "");
        this.groupTextField = GuiFactory.getComboBox(listGroup);
        initial(view, isSaved);

    }

    public MainTableScene(View view, boolean isSaved, String itaDescription, String engDescription, String group) {
        this.view = view;
        this.isSaved = true; // Initialize as saved
        this.itaDescription = itaDescription;
        this.engDescription = engDescription;
        this.group = group;

        this.itaTextField.setText(reversBlankReturn(itaDescription));
        this.engTextField.setText(reversBlankReturn(engDescription));

        listGroup = view.getController().getAllGroupTypeString();
        listGroup.add(0, "");
        this.groupTextField = GuiFactory.getComboBox(listGroup);

        this.groupTextField.setSelectedItem(reversBlankReturn(group));
        initial(view, true);
    }

    private void initial(View view, boolean isSaved) {
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

        final List<Description> des = view.getController().getListDescription(itaDescription, engDescription, group);

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
                            // isSaved = false; // Mark as not saved after deletion
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

        // listGroup.add(0, "");
        // this.groupTextField = GuiFactory.getComboBox(listGroup);
        JButton filterButton = GuiFactory.getButtom("Filtra", Color.GRAY, Color.BLACK, Font.getFont(FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String ita = blankReturn(itaTextField);
                        String eng = blankReturn(engTextField);

                        String group = groupTextField.getSelectedItem().toString().toUpperCase();
                        if (group.isBlank()) {
                            group = ALL;
                        }
                        view.goToInitialSceneFiltered(isSaved, ita, eng, group);
                    }
                });
        JButton resetButton = GuiFactory.getButtom("ResetFilter", Color.GRAY, Color.BLACK, Font.getFont(FONT),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        itaTextField.setText("");
                        engTextField.setText("");
                        groupTextField.setSelectedIndex(0);
                        view.goToInitialScene(isSaved);
                    }
                });
        southPanel.add(Box.createHorizontalStrut(10));
        southPanel.add(desFilter);
        southPanel.add(itaTextField);
        southPanel.add(engFilter);
        southPanel.add(engTextField);
        southPanel.add(groupFilter);
        southPanel.add(groupTextField);
        southPanel.add(filterButton);
        southPanel.add(Box.createHorizontalStrut(10));
        southPanel.add(resetButton);
        southPanel.add(Box.createHorizontalGlue());
        southPanel.add(Box.createHorizontalStrut(10));
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        this.setBackground(Color.WHITE);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    private String blankReturn(JTextField textField) {
        return textField.getText().isBlank() ? ALL : textField.getText().toUpperCase();
    }

    private String reversBlankReturn(String text) {
        return text.equals(ALL) ? "" : text.toUpperCase();
    }
}
