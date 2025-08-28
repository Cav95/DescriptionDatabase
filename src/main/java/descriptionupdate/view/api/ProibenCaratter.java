package descriptionupdate.view.api;

/**
 * Enum representing various prohibited characters.
 */
public enum ProibenCaratter {

    PARENTHESES_OPEN("("),
    PARENTHESES_CLOSE(")"),
    AMPERSAND("&"),
    PERCENT("%"),
    COMMA(","),
    QUESTION_MARK("?"),
    PIPE("|"),
    EXCLAMATION_MARK("!"),
    AT("@"),
    SQUARE_BRACKET_OPEN("["),
    SQUARE_BRACKET_CLOSE("]"),
    DOLLAR("$"),
    QUOTATION_MARK("\""),
    SEMICOLON(";");

    private String character;

    ProibenCaratter(String character) {
        this.character = character;
    }

    /**
     * Gets the character representation of the enum constant.
     * 
     * @return The character as a string.
     */
    public String getCharacter() {
        return character;
    }
}
