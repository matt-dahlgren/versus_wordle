package view;

import app.ColourConstants;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class SoloPlayView extends JPanel {

    private final String viewName = "SoloPlayView";

    public SoloPlayView(Map<String, List<Integer>> computerBoards) {

        setLayout(new BorderLayout());

        // add the guess panel
        add(new KeyboardView(), BorderLayout.SOUTH);

        // add the centre panel with guess view in the middle

        JPanel centrePanel = new JPanel(new GridLayout(1, 3));

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(ColourConstants.GREEN);
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(ColourConstants.GREEN);

        centrePanel.add(leftPanel);
        centrePanel.add(new GuessView(), BorderLayout.CENTER);
        centrePanel.add(rightPanel);

        add(centrePanel, BorderLayout.CENTER);

        // add a top panel, this houses to computer guess view
        add(new ComputerGuessView(computerBoards), BorderLayout.PAGE_START);
    }
}
