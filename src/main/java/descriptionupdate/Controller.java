package descriptionupdate;

import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;

import descriptionupdate.model.Model;
import descriptionupdate.model.api.Description;
import descriptionupdate.view.View;

/**
 * Controller class that manages the interaction between the model and the view.
 * It handles user actions and updates the view accordingly.
 * This class is responsible for coordinating the flow of data and actions
 * between the model and the view, ensuring that the application behaves as expected.
 * It provides methods to initialize scenes, retrieve and manipulate descriptions,
 * and manage the application's state.
 */
public final class Controller {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Controller.class);

    private final Model model;
    private final View view;

    /**
     * Constructs a Controller with the specified model and view.
     *
     * @param model the model to be used by the controller
     * @param view  the view to be managed by the controller
     * @throws NullPointerException if model or view is null
     */
    public Controller(Model model, View view) {
        Objects.requireNonNull(model, "Controller created with null model");
        Objects.requireNonNull(view, "Controller created with null view");
        this.view = view;
        this.model = model;
    }

    /**
     * Sets the controller for the view.
     *
     * @param controller the controller to be set
     */
    public void initialScene(Boolean isSaved) {
        LOGGER.info("Initial scene");
        view.goToInitialScene(isSaved);

    }

    /**
     * Navigates to the second scene.
     */
    public void secondScene() {
        LOGGER.info("Second scene");
        view.goToSecondScene();
    }

    /**
     * Navigates to the add scene.
     */
    public void addScene() {
        LOGGER.info("Add scene");
        view.goToAddScene();
    }

    /**
     * Retrieves a description based on the provided parameters.
     *
     * @param itaDescription the Italian description
     * @param engDescription the English description
     * @param group          the group type
     * @return the description matching the provided parameters
     * @throws IllegalArgumentException if no description is found for the provided parameters
     */
    public Description getDescription(String itaDescription, String engDescription, String group) {
        LOGGER.info("Getting description for: {}, {}, {}", itaDescription, engDescription, group);
        return model.getDescription(itaDescription, engDescription, group)
                .orElseThrow(() -> new IllegalArgumentException("No description found for the provided parameters"));
    }

    /**
     * Retrieves a list of descriptions based on the provided parameters.
     *
     * @param itaDescription the Italian description
     * @param engDescription the English description
     * @param group          the group type
     * @return a list of descriptions matching the provided parameters
     */
    public List<Description> getListDescription( String itaDescription, String engDescription, String group) {
        LOGGER.info("Getting list of descriptions");
        return model.getListDescription(itaDescription, engDescription, group  );

    }

    /**
     * Adds a new description to the model.
     *
     * @param description the description to be added
     */
    public void addDescription(Description description) {
        LOGGER.info("Adding description: {}", description);
        model.addDescription(description);
    }

    /**
     * Deletes a description from the model.
     *
     * @param description the description to be deleted
     */
    public void deleteDescription(Description description) {
        LOGGER.info("Delete description: {}", description);
        model.deleteDescription(description);
    }

    public void updateDescription(Description oldDescription, Description newDescription) {
        LOGGER.info("Updating description from: {}, {}, {} to: {}, {}, {}", oldDescription, newDescription);
        model.updateDescription(oldDescription, newDescription);
    }

    /**
     * Saves changes made to the model.
     */
    public void save() {
        LOGGER.info("Saving changes to the database");
        model.save();
    }

    /**
     * Retrieves all group types as a list of strings.
     *
     * @return a list of all group type strings
     */
    public List<String> getAllGroupTypeString() {
        LOGGER.info("Getting all group types");
        return model.getAllGroupTypeString();
    }
}
