package io.github.asyncronous.cube.ui.tabs;

import io.github.asyncronous.cube.UIUtils;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class SettingsTab
extends JPanel
implements Tab{
    public final JLabel label = new JLabel("Coming Soon", JLabel.CENTER);

    public SettingsTab(){
        super(new BorderLayout());
        this.setBackground(UIUtils.CUBE);
        this.add(this.label, BorderLayout.CENTER);
    }

    @Override
    public String id(){
        return "settings";
    }
}