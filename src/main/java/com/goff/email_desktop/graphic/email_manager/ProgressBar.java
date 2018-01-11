package com.goff.email_desktop.graphic.email_manager;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class ProgressBar extends JFrame {

    private SwingWorker swingWorker;

    public ProgressBar() {
        super("Exemplo ridiculo!");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(new Dimension(230, 100));
        pack();
        setLocationRelativeTo(null);
        init();
        setVisible(true);
        swingWorker.execute();
    }

    private void init() {
        final JPanel p = new JPanel();
        final JProgressBar pr = new JProgressBar();
        pr.setStringPainted(true);
        pr.setValue(0);
        pr.setSize(new Dimension(100, 23));
        p.add(pr);
        add(p);
        swingWorker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                for (int i = 1; i <= 100; i++) {
                    try {
                        pr.setValue(i);
                        pr.setString(i + "%");
                        Thread.sleep(20);
                    } catch (final InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                return 0;
            }
        };
    }
}
