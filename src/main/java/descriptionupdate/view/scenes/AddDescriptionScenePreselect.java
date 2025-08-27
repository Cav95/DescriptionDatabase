package descriptionupdate.view.scenes;

import descriptionupdate.view.View;

public class AddDescriptionScenePreselect extends AddDescriptionScene {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for AddDescriptionScenePreselect.
     *
     * @param view  the main view of the application
     * @param group the group to preselect in the combo box
     */
    public AddDescriptionScenePreselect(View view, String ita , String eng, String group) {
        super(view);
        itaTextField.setText(ita);
        engTextField.setText(eng);
        groupTextField.setSelectedItem(group);
    }
    
}
