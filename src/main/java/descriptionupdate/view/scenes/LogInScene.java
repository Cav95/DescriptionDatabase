package descriptionupdate.view.scenes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import descriptionupdate.Controller;
import descriptionupdate.data.utils.DAOException;
import descriptionupdate.data.utils.DAOUtils;
import descriptionupdate.model.Model;
import descriptionupdate.view.View;
import descriptionupdate.view.utils.ConnectionFailureViewIni;
import descriptionupdate.view.utils.GuiFactory;

/**
 * LogInScene class that extends JPanel to create a login scene for the
 * application.
 */
public class LogInScene extends JPanel {

    private static final String FONT = "Roboto";

    @SuppressWarnings("unused")
    private final View view;
    private Connection connection;

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

        // Pulsante e campi centrati
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(60, 20, 60, 20));
        centerPanel.setOpaque(false);

        // Label e campo utente
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font(FONT, Font.PLAIN, 18));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField userField = new JTextField(20);
        userField.setMaximumSize(new Dimension(300, 30));
        userField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Label e campo password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font(FONT, Font.PLAIN, 18));
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPasswordField passField = new JPasswordField(20);
        passField.setMaximumSize(new Dimension(300, 30));
        passField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton accediButton = GuiFactory.getButtom(
                "Accedi",
                Color.GRAY,
                Color.BLACK,
                new Font(FONT, Font.BOLD, 22),
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        // Effettua il login

                        try {
                            //connection = DAOUtils.localMySQLConnection("DesFusion",
                             //       userField.getText(), new String(passField.getPassword()));
                            // connection = DAOUtils.localSqlServerConnection("EdmDb_2008_001",
                            // userField.getText(), new String(passField.getPassword()));
                            connection = DAOUtils.localIniStringConnection();

                        } catch (DAOException ex) {
                            new ConnectionFailureViewIni();
                            return;
                        }
                        try {
                            connection.setAutoCommit(false);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }

                        var controller = new Controller(new Model(connection), view);
                        view.setController(controller);
                        view.getController().initialScene();
                    }
                });
        accediButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(userLabel);
        centerPanel.add(userField);
        centerPanel.add(passLabel);
        centerPanel.add(passField);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(accediButton);
        centerPanel.add(Box.createVerticalGlue());

        this.add(centerPanel, BorderLayout.CENTER);
    }
}
