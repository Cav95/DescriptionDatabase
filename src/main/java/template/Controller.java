package template;

import java.util.Objects;
import org.slf4j.Logger;

import template.model.Model;
import template.view.View;

public final class Controller {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Controller.class);

    private final Model model;
    private final View view;

    public Controller(Model model, View view) {
        Objects.requireNonNull(model, "Controller created with null model");
        Objects.requireNonNull(view, "Controller created with null view");
        this.view = view;
        this.model = model;
    }

    public void initialScene() {
        LOGGER.info("Initial scene");
        view.goToInitialScene();


    }

    public void secondScene() {
        LOGGER.info("Second scene");
        view.goToSecondScene();
    }
}


