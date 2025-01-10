package view;

import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View Manager for the application. It listens for change events in the viewManagerModel and updates which
 * View (what is shown on the Java Application) should be visible.
 * This is sample code from CSC207 Lab 5 from the University of Toronto.
 */
public class ViewManager implements PropertyChangeListener {
    private final CardLayout cardLayout;
    private final JPanel views;
    private final ViewManagerModel viewManagerModel;

    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        if (evt.getPropertyName().equals("state")) {
//            final String viewModelName = (String) evt.getNewValue();
//            cardLayout.show(views, viewModelName);
//        }
        String newState = (String) evt.getNewValue();
        System.out.println("Switching to view: " + newState);
        cardLayout.show(views, newState);
    }
}
