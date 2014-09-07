package io.github.asyncronous.cube.plaf.output;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSeparatorUI;

public final class CubeSeparatorUI
extends BasicSeparatorUI{
    public static ComponentUI createUI(JComponent comp){
        return new CubeSeparatorUI();
    }

    @Override
    public void paint(Graphics g, JComponent comp){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.drawLine(3, comp.getHeight() / 2, comp.getWidth() - 4, comp.getHeight() / 2);
    }
}