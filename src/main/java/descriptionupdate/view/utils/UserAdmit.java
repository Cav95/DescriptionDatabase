package descriptionupdate.view.utils;

/**
 * Enum representing users who are allowed to admit changes.
 * Each user is identified by a unique name.
 */
public enum UserAdmit {
    CAVINAM("mcavina"),
    MATTI("matti");

    private final String name;

    UserAdmit(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }
    
}
