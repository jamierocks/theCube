package io.github.asyncronous.cube.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.WindowConstants;

public final class CubeFrame
extends JFrame{
    private final JToggleButton packsButton = new JToggleButton("Packs");

    private static CubeFrame instance;
    public static CubeFrame instance(){
        return instance == null ? instance = new CubeFrame() : instance;
    }

    private final JPanel bottonPanel = new JPanel();

    public CubeFrame(){
        super("theCube");

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1000, 640));

        this.setupBottomPanel();

        this.add(this.bottonPanel, BorderLayout.SOUTH);
    }

    private void setupBottomPanel(){
        this.bottonPanel.setPreferredSize(new Dimension(1000, 140));
        this.bottonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.bottonPanel.add(this.packsButton);
    }
}