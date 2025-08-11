package template;

import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;

import template.model.Model;
import template.model.api.Description;
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

    public Description getDescription(String itaDescription, String engDescription, String group) {
        LOGGER.info("Getting description for: {}, {}, {}", itaDescription, engDescription, group);
        return model.getDescription(itaDescription, engDescription, group)
                .orElseThrow(() -> new IllegalArgumentException("No description found for the provided parameters"));
    }

    public List<Description> getListDescription() {
        LOGGER.info("Getting list of descriptions");
        return model.getListDescription();

    }
}


