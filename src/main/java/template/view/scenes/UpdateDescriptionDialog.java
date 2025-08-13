package template.view.scenes;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import template.model.api.Description;
import template.view.View;
import template.view.utils.GuiFactory;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ResultPane class that extends JDialog to display the results of the tubular
 * calculations.
 */
public class UpdateDescriptionDialog extends JDialog {

        private static final long serialVersionUID = 1L;
        private static final String FONT = "Roboto";

        private final String exIta;
        private final String exEng;
        private final String exGroup;

        /**
         * Constructor for ResultPane.
         * 
         * @param view       the main view of the application
         * @param title      the title of the dialog
         * @param removeMode whether to remove the dialog after displaying results
         * @param result     the result string to be displayed
         */
        public UpdateDescriptionDialog(final View view, final String title, String ita, String eng, String group) {

                super(view.getMainFrame(), title, ModalityType.APPLICATION_MODAL);
                this.exIta = ita;
                this.exEng = eng;
                this.exGroup = group;

                this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                this.setSize(800, 500);
                this.setMaximumSize(this.getSize());
                this.setLocationRelativeTo(view.getMainFrame());
                this.setResizable(true);

                this.setLayout(new BorderLayout());

                final JPanel titlePannel = new JPanel();
                titlePannel.setLayout(new BorderLayout());
                titlePannel.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));
                this.add(titlePannel, BorderLayout.NORTH);

                JLabel titleLabel = new JLabel("Stai aggiornando la descrizione: " + group + " - " + ita + " - " + eng);
                titleLabel.setFont(new Font(FONT, Font.BOLD, 15));
                titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                titlePannel.add(titleLabel, BorderLayout.CENTER);

                final JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
                mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));
                JLabel itaLabel = new JLabel("ITA Description:");
                JLabel engLabel = new JLabel("ENG Description:");
                // JLabel groupLabel = new JLabel("Group:");

                JTextField itaTextField = GuiFactory.getTextField(20);
                JTextField engTextField = GuiFactory.getTextField(20);
                // JTextField groupTextField = GuiFactory.getTextField(20);
                mainPanel.add(itaLabel);
                mainPanel.add(itaTextField);

                mainPanel.add(engLabel);
                mainPanel.add(engTextField);

                // mainPanel.add(groupLabel);
                // mainPanel.add(groupTextField);
                this.add(mainPanel, BorderLayout.CENTER);

                // Correggi il nome del pannello
                JPanel bottomPanel = new JPanel();
                bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
                mainPanel.add(bottomPanel, BorderLayout.EAST);

                // Pulsante "Stampa"
                JButton update = new JButton("Aggiorna");
                update.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(final ActionEvent e) {
                                try {
                                        view.getController().updateDescription(new Description(exIta, exEng, exGroup),
                                                        new Description(itaTextField.getText().toUpperCase(),
                                                                        engTextField.getText().toUpperCase(), exGroup));
                                        JOptionPane.showMessageDialog(UpdateDescriptionDialog.this,
                                                        "Description updated successfully!\n"
                                                                        + itaTextField.getText().toUpperCase() + " - "
                                                                        + engTextField.getText().toUpperCase() + " - "
                                                                        + exGroup);
                                        view.getController().initialScene();
                                        UpdateDescriptionDialog.this.dispose();

                                } catch (Exception ex) {
                                        JOptionPane.showMessageDialog(UpdateDescriptionDialog.this,
                                                        "Errore " + ex.getMessage());
                                }
                        }

                });
                bottomPanel.add(update);
        };

}
