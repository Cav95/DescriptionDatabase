package template;

import java.sql.SQLException;

import template.model.Model;
import template.view.View;

public final class App {

    public static void main(String[] args) throws SQLException {

        var model = new Model();
        var view = new View();
        var controller = new Controller(model, view);
        view.setController(controller);
        controller.initialScene();
    }
}
