package com.goff.email_desktop.graphic;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class StartApp {

    public static void main(final String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        final JFrame frame = new JFrame("E-mail manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JComponent newContentPane = new EmailManagerApp();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        newContentPane.setMinimumSize(new Dimension(newContentPane.getPreferredSize().width, 100));

        frame.pack();
        frame.setVisible(true);
    }

}
