package io.github.asyncronous.cube.ui.tabs;

import io.github.asyncronous.cube.UIUtils;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class DashboardTab
extends JPanel
implements Tab{
    private final JLabel label = new JLabel("Coming Soon", JLabel.CENTER);

    public DashboardTab(){
        super(new BorderLayout());
        this.setBackground(UIUtils.CUBE);
        this.add(this.label, BorderLayout.CENTER);
    }

    @Override
    public String id(){
        return "dashboard";
    }
}