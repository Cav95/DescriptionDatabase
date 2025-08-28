package descriptionupdate.view.utils;

import java.util.Arrays;

import descriptionupdate.view.api.ProibenCaratter;

public class ControllUtilies {

    public static boolean isProhibitedCharacter(String character) {
        return Arrays.asList(ProibenCaratter.values()).stream()
        .anyMatch(c -> character.contains(c.getCharacter()));
    }
}
