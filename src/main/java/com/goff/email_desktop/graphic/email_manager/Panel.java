package com.goff.email_desktop.graphic.email_manager;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class Panel {
    private final PanelItem panelItem;

    public Panel(final PanelItem panelItem) {
        this.panelItem = panelItem;
    }

    public void organize(final EmailManagerApp emailManagerApp) {
        final JScrollPane bodyMessageWithScroll = new JScrollPane(panelItem.emailBody);
        final JScrollPane listScrollPane = new JScrollPane(panelItem.list);

        final JPanel buttonsPanel = createButtonsPanel();
        final JSplitPane splitPane =
                new JSplitPane(JSplitPane.VERTICAL_SPLIT, listScrollPane, bodyMessageWithScroll);
        splitPane.setResizeWeight(0.5);

        emailManagerApp.add(buttonsPanel, BorderLayout.PAGE_START);
        emailManagerApp.add(splitPane, BorderLayout.CENTER);
    }

    private JPanel createButtonsPanel() {
        final JPanel buttonsPanel = new JPanel(new GridLayout(3, 3));
        buttonsPanel.add(panelItem.addButton);
        buttonsPanel.add(panelItem.deleteButton);
        buttonsPanel.add(panelItem.editButton);
        buttonsPanel.add(panelItem.editDefaultBodyButton);
        buttonsPanel.add(panelItem.sendButton);
        buttonsPanel.add(panelItem.sendAllButton);

        return buttonsPanel;
    }
}
