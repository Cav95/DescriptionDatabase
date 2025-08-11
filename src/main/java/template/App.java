package template;

import java.sql.Connection;
import java.sql.SQLException;

import template.data.utils.DAOException;
import template.data.utils.DAOUtils;
import template.model.Model;
import template.view.View;
import template.view.utils.ConnectionFailureView;

public final class App {

    public static void main(String[] args) throws SQLException {

        Connection connection;
        try {
            connection = DAOUtils.localMySQLConnection("DesFusion", "root", "");
        } catch (DAOException e) {
            new ConnectionFailureView();
            return;
        }

        var model = new Model(connection);
                var view = new View(() -> {
            try {
                connection.close();
            } catch (Exception ignored) {}
        });
        var controller = new Controller(model, view);
        view.setController(controller);
        view.goToInitialScene();
        //controller.initialScene();
    }
}

