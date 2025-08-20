package descriptionupdate;

import java.sql.Connection;
import java.sql.SQLException;

import descriptionupdate.data.utils.DAOException;
import descriptionupdate.data.utils.DAOUtils;
import descriptionupdate.model.Model;
import descriptionupdate.view.View;
import descriptionupdate.view.utils.ConnectionFailureViewIni;

public final class App {

    public static void main(String[] args) throws SQLException {

        Connection connection;
        try {
            //connection = DAOUtils.localMySQLConnection("DesFusion", "root", "");
            //connection = DAOUtils.localSqlServerConnection("EdmDb_2008_001", "edm2008", "edm2008");
            connection = DAOUtils.localIniStringConnection();
        } catch (DAOException e) {
            new ConnectionFailureViewIni();
            return;
        }
        connection.setAutoCommit(false);

        var model = new Model(connection);
        var view = new View(() -> {
            try {
                connection.close();
            } catch (Exception ignored) {
            }
        });
        var controller = new Controller(model, view);
        view.setController(controller);
       // view.goToInitialScene();
        // controller.initialScene();
    }
}
