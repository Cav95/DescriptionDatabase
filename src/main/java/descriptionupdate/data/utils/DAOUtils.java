package descriptionupdate.data.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public final class DAOUtils {

    private static final String CONFIG_DB_CONNECTION_INI = "configDBConnection.ini";

    // Establishes a connection to a MySQL daemon running locally at port 3306.
    //
    public static Connection localMySQLConnection(String database, String username, String password) {
        try {
            var host = "localhost";
            var port = "3306";
            var connectionString = "jdbc:mysql://" + host + ":" + port + "/" + database;
            return DriverManager.getConnection(connectionString, username, password);
        } catch (Exception e) {
            throw new DAOException(e);
        }

    }

    // Establishes a connection to a Microsoft SQL Server database.
    public static Connection localSqlServerConnection(String database, String username, String password) {
        // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl = "jdbc:sqlserver://DBSRV02:1433;"
                + "databaseName=" + database + ";"
                + "user=" + username + ";"
                + "password=" + password + ";"
                + "encrypt=false;"
                + "trustServerCertificate=true;" // <-- importante se encrypt è false
                + "loginTimeout=30;";
        try {
            return DriverManager.getConnection(connectionUrl);
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    /*
     * Establishes a connection to a database using an INI file.
     * The INI file should be located in the current working directory
     * and named "configDBConnection.ini".
     * It should contain the following properties:
     * key1=jdbc:mysql://localhost:3306/DesFusion
     * user=root
     * psw=password
     * This method reads the INI file, retrieves the connection string, username,
     * and password,
     * and establishes a connection to the database.
     * 
     * If the connection string does not include a username and password,
     * it attempts to connect without them.
     * If the connection fails, it throws a DAOException with an error message.
     * 
     * @return a Connection object to the database
     * 
     * @throws DAOException if there is an error reading the INI file or
     * establishing the
     */
    public static Connection localIniStringConnection() {
        Properties properties = new Properties();
        String iniFilePath = System.getProperty("user.dir") + System.getProperty("file.separator")
                + CONFIG_DB_CONNECTION_INI;

    try (FileInputStream fileInputStream = new FileInputStream(iniFilePath)) {
        properties.load(fileInputStream);
    } catch (IOException e) {
    }

            // Leggi una singola chiave
            String connectionString = properties.getProperty("key1");
            String username = properties.getProperty("user");
            String password = properties.getProperty("psw");
            try {
                return DriverManager.getConnection(connectionString, username, password);
            } catch (Exception e) {

                try {
                    return DriverManager.getConnection(connectionString);
                } catch (SQLException t) {
                    throw new DAOException("Errore durante la connessione al database", t);
                }
                // throw new DAOException(e);
            }

            // System.out.println("Valore di chiave1: " + valore);
    }

    // We must always prepare a statement to make sure we do not fall victim to SQL
    // injection:
    // https://owasp.org/www-community/attacks/SQL_Injection
    //
    // This is a helper that prepares the statement with all the values we give it:
    //
    // prepare(connection, MY_QUERY, query_arg1, query_arg2, ...)
    //
    public static PreparedStatement prepare(Connection connection, String query, Object... values) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            return statement;
        } catch (Exception e) {
            if (statement != null) {
                statement.close();
            }
            throw e;
        }
    }
}
