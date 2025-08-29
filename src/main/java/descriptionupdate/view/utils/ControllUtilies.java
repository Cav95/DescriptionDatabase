package descriptionupdate.view.utils;

import java.util.Arrays;

import descriptionupdate.model.api.Description;
import descriptionupdate.view.api.ProibenCaratter;

public class ControllUtilies {

    public static boolean isProhibitedCharacter(String character) {
        return Arrays.asList(ProibenCaratter.values()).stream()
                .anyMatch(c -> character.contains(c.getCharacter()));
    }

    public static void descriptionValidCaracter(Description description) {
        if (ControllUtilies.isProhibitedCharacter(description.itaDescripion())
                || ControllUtilies.isProhibitedCharacter(description.engDescription())) {
            throw new IllegalArgumentException();
        }
    }
}
