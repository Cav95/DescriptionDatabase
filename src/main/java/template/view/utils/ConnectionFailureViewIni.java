package template.view.utils;

import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ConnectionFailureViewIni extends JFrame {
    private static final String CONFIG_DB_CONNECTION_INI = "configDBConnection.ini";

    public ConnectionFailureViewIni() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);

        Properties properties = new Properties();
        String iniFilePath = System.getProperty("user.dir") + System.getProperty("file.separator")
                + CONFIG_DB_CONNECTION_INI;
        try (FileInputStream fileInputStream = new FileInputStream(iniFilePath)) {
            properties.load(fileInputStream);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Impossibile leggere il file di configurazione.\n" +
                            "Assicurarsi che il file " + CONFIG_DB_CONNECTION_INI + " esista nella cartella di lavoro.",
                    "Errore di configurazione",
                    JOptionPane.ERROR_MESSAGE);
            this.dispose();
            return;
        }
        JOptionPane.showMessageDialog(this,
                "Impossibile connettersi al database.\n" +
                        "Assicurarsi che il server selezionato sia in esecuzione e che esista" +
                        "\n" + "Connection String: " + properties.getProperty("key1") +
                        "\n" + "User: " + properties.getProperty("user") +
                        "\n" + "Password: " + properties.getProperty("psw"),
                "Errore di connessione al database",
                JOptionPane.ERROR_MESSAGE);
        this.dispose();
    }

}
