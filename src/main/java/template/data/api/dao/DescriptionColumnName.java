package template.data.api.dao;

public enum DescriptionColumnName {
    ITA_DES("DESCRIZIONE"),
    ENG_DES("INGLESE"),
    GROUP("Gruppo");

    private final String columnName;

    DescriptionColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
