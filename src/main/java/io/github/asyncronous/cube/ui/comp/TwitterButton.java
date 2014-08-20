package io.github.asyncronous.cube.ui.comp;

import io.github.asyncronous.cube.UIUtils;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public final class TwitterButton
extends JButton{
    private static final Image icon = UIUtils.load("twitter");

    public TwitterButton(){
        super("Twitter");
        this.setPreferredSize(new Dimension(32, 32));
        this.setContentAreaFilled(false);
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        UIUtils.antialiasOn(g2);
        g2.drawImage(icon, 0, 0, this.getWidth(), this.getHeight(), null);
        UIUtils.antialiasOff(g2);
    }
}