package app;

import javax.swing.JFrame;
import java.io.FileNotFoundException;

/**
 * Main class of this program.
 * Runs the app using AppBuilder to build the app.
 */
public class Main {
    static final int WIDTH = 1400;
    static final int HEIGHT = 800;

    /**
     * Builds and runs the application.
     * @param args unused arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        final AppBuilder appBuilder = new AppBuilder();
        //final JFrame application = appBuilder
        //        .build();

        //application.pack();

        //application.setSize(WIDTH, HEIGHT);

        //application.setVisible(true);
    }
}

