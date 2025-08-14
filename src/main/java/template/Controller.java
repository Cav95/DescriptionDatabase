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

    public void initialScene(Boolean isSaved) {
        LOGGER.info("Initial scene");
        view.goToInitialScene(isSaved);

    }

    public void secondScene() {
        LOGGER.info("Second scene");
        view.goToSecondScene();
    }

    public void addScene() {
        LOGGER.info("Add scene");
        view.goToAddScene();
    }

    public Description getDescription(String itaDescription, String engDescription, String group) {
        LOGGER.info("Getting description for: {}, {}, {}", itaDescription, engDescription, group);
        return model.getDescription(itaDescription, engDescription, group)
                .orElseThrow(() -> new IllegalArgumentException("No description found for the provided parameters"));
    }

    public List<Description> getListDescription( String itaDescription, String engDescription, String group) {
        LOGGER.info("Getting list of descriptions");
        return model.getListDescription(itaDescription, engDescription, group  );

    }

    public void addDescription(Description description) {
        LOGGER.info("Adding description: {}", description);
        model.addDescription(description);
    }

    public void deleteDescription(Description description) {
        LOGGER.info("Delete description: {}", description);
        model.deleteDescription(description);
    }

    public void updateDescription(Description oldDescription, Description newDescription) {
        LOGGER.info("Updating description from: {}, {}, {} to: {}, {}, {}", oldDescription, newDescription);
        model.updateDescription(oldDescription, newDescription);
    }

    public void save() {
        LOGGER.info("Saving changes to the database");
        model.save();
    }

    public List<String> getAllGroupTypeString() {
        LOGGER.info("Getting all group types");
        return model.getAllGroupTypeString();
    }
}
