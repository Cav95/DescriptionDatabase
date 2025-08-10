package template.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Objects;
import java.util.Optional;

import javax.swing.JFrame;
import javax.swing.JPanel;

import template.Controller;
import template.view.scenes.SecondScene;
import template.view.scenes.WelcomeScene;

public final class View {

    private static final String SN_WELCOME = "welcome";
    private static final String SN_SECOND = "SecondScene";

    private static final double FRAME_SIZE_FACTOR = 0.8;

    private Optional<Controller> controller;
    private final JFrame mainFrame;
    private final JPanel mainPanel;
    private final CardLayout cardLayout;

        public View() {
        this.controller = Optional.empty();
        this.mainFrame = new JFrame("Porto Morte Nera");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // this.mainFrame.setIconImage(new ImageIcon(ClassLoader.getSystemResource("images/icon.png")).getImage());

        var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final var initWidth = (int) (screenSize.width * FRAME_SIZE_FACTOR);
        final var initHeight = (int) (screenSize.height * FRAME_SIZE_FACTOR);
        this.mainFrame.setSize(new Dimension(initWidth, initHeight));
        this.mainFrame.setMinimumSize(new Dimension(800, 600));

        this.cardLayout = new CardLayout();
        this.mainPanel = new JPanel(cardLayout);
        this.mainFrame.setContentPane(this.mainPanel);

        this.mainPanel.add(new WelcomeScene(this), SN_WELCOME);

        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.setVisible(true);

       /* this.mainFrame.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        onClose.run();
                        System.exit(0);
                    }
                });*/
    }

    public JFrame getMainFrame() {
        return this.mainFrame;
    }

    public Controller getController() {
        if (this.controller.isPresent()) {
            return this.controller.get();
        } else {
            throw new IllegalStateException(
                    """
                            The View's Controller is undefined, did you remember to call
                            `setController` before starting the application?
                            Remeber that `View` needs a reference to the controller in order
                            to notify it of button clicks and other changes.
                            """);
        }
    }

    public void setController(Controller controller) {
        Objects.requireNonNull(controller, "Set null controller in view");
        this.controller = Optional.of(controller);
    }

    public void goToInitialScene() {
        this.mainPanel.add(new WelcomeScene(this), SN_WELCOME);
        this.cardLayout.show(this.mainPanel, SN_WELCOME);
    }

     public void goToSecondScene() {
        this.mainPanel.add(new SecondScene(this), SN_SECOND);
        this.cardLayout.show(this.mainPanel, SN_SECOND);
    }


}
