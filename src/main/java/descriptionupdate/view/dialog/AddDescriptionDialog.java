package descriptionupdate.view.dialog;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import descriptionupdate.model.api.Description;
import descriptionupdate.view.View;
import descriptionupdate.view.utils.ControllUtilies;
import descriptionupdate.view.utils.GuiFactory;
import descriptionupdate.view.utils.OptionalPaneFactory;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * AddDescriptionScene class that extends JDialog to allow users to add a new
 * description.
 */
public class AddDescriptionDialog extends JDialog {

    private static final int SIZE_FONT = 18;

    private static final String FONT = "Roboto";

    final JPanel mainPanel = new JPanel();
    private JPanel northPanel = new JPanel();
    private JLabel titleLabel = new JLabel("Add Description Scene");
    private JLabel itaLabel = GuiFactory.getLabel("ITA Description:", GuiFactory.getFont(Font.PLAIN, SIZE_FONT));
    private JLabel engLabel = GuiFactory.getLabel("ENG Description:", GuiFactory.getFont(Font.PLAIN, SIZE_FONT));
    private JLabel groupLabel = GuiFactory.getLabel("Group", GuiFactory.getFont(Font.PLAIN, SIZE_FONT));
    protected JTextField itaTextField = GuiFactory.getTextField(20);
    protected JTextField engTextField = GuiFactory.getTextField(20);
    protected JComboBox<String> groupTextField;

    @SuppressWarnings("unused")
    private final View view;

    /**
     * Constructor for AddDescriptionScene.
     *
     * @param view the main view of the application
     */
    public AddDescriptionDialog(View view) {
        this.view = view;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1200, 500);
        this.setMaximumSize(this.getSize());
        this.setLocationRelativeTo(view.getMainFrame());
        this.setResizable(true);

        this.setLayout(new BorderLayout());

        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        titleLabel.setFont(new Font(FONT, Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        northPanel.add(titleLabel);
        this.add(northPanel, BorderLayout.NORTH);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));

        groupTextField = GuiFactory.getComboBox(view.getController().getAllGroupTypeString());
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
                        try {
                            var newDescription = new Description(itaTextField.getText().toUpperCase(),
                                    engTextField.getText().toUpperCase(),
                                    groupTextField.getSelectedItem().toString().toUpperCase());
                            if (ControllUtilies.isProhibitedCharacter(newDescription.itaDescripion())
                                    || ControllUtilies.isProhibitedCharacter(newDescription.engDescription())) {
                                throw new IllegalArgumentException();
                            }
                            view.getController().addDescription(newDescription);
                            OptionalPaneFactory.successfullyAddedDescription(AddDescriptionDialog.this, newDescription);
                            view.getController().setSaved(false);
                            view.goToInitialSceneFiltered(view.getController().getItaFilterTemp(),
                                    view.getController().getEngFilterTemp(), view.getController().getGroupFilterTemp());
                            AddDescriptionDialog.this.dispose();
                        } catch (IllegalArgumentException t) {
                            OptionalPaneFactory.caractherInvalid(AddDescriptionDialog.this);

                        } catch (Exception ex) {
                            OptionalPaneFactory.existedDescription(AddDescriptionDialog.this);
                        }

                    }
                }));
        mainPanel.add(GuiFactory.getButtom("Annulla", Color.RED, Color.WHITE, Font.getFont(FONT), new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                view.goToInitialSceneFiltered(view.getController().getItaFilterTemp(),
                                    view.getController().getEngFilterTemp(), view.getController().getGroupFilterTemp());
                AddDescriptionDialog.this.dispose();
            }
        }));

    }
}
