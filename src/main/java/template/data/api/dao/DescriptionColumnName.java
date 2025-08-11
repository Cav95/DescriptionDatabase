package template.data.api.dao;

public enum DescriptionColumnName {
    ITA_DES("Descrizione"),
    ENG_DES("DescrizioneEng"),
    GROUP("Gruppo");

    private final String columnName;

    DescriptionColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
