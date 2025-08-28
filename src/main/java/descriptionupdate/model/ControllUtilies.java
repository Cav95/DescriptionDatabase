package descriptionupdate.model;

import java.util.Arrays;

import descriptionupdate.model.api.ProibenCaratter;

public class ControllUtilies {

    public static boolean isProhibitedCharacter(String character) {
        return Arrays.asList(ProibenCaratter.values()).stream().anyMatch(c -> c.getCharacter().equals(character));
    }
}
