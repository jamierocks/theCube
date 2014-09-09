package io.github.asyncronous.cube.ui.diag;

import io.github.asyncronous.cube.ui.CubeFrame;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public final class LoadingDialog
extends JDialog{
    public final JLabel title;
    public final JProgressBar bar = new JProgressBar(0, 100);
    private final GridBagConstraints gbc = new GridBagConstraints();

    public LoadingDialog(String title){
        super(CubeFrame.instance(), title, ModalityType.APPLICATION_MODAL);
        this.title = new JLabel(title);
        this.setLayout(new GridBagLayout());
        this.setUndecorated(true);
        this.getContentPane().setBackground(Color.black);
        this.title.setForeground(Color.white);
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.insets.set(5, 5, 5, 5);
        this.gbc.anchor = GridBagConstraints.LINE_START;
        this.gbc.fill = GridBagConstraints.BOTH;
        this.add(this.title, this.gbc);
        this.gbc.gridy++;
        this.add(this.bar, this.gbc);
        this.pack();
        this.setLocationRelativeTo(CubeFrame.instance());
    }
}