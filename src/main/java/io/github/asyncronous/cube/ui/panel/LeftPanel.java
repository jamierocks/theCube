package io.github.asyncronous.cube.ui.panel;

import io.github.asyncronous.cube.Accounts;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public final class LeftPanel
extends JPanel{
    @Override
    public int getWidth(){
        return 125;
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Accounts.current().color);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}