package descriptionupdate.view.utils;

import java.awt.Component;

import javax.swing.JOptionPane;

public class OptionalPaneFactory {

    public static void caractherInvalid(Component dialog) {
        JOptionPane.showMessageDialog(dialog,
                "Usati caratteri non validi");
    }

}
