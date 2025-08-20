package descriptionupdate.view.utils;

public enum UserAdmit {
    CAVINAM("mcavina"),
    MATTI("matti");

    private final String name;

    UserAdmit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}
