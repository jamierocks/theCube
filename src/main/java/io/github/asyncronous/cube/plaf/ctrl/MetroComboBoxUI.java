package io.github.asyncronous.cube.plaf.ctrl;

import io.github.asyncronous.cube.UIUtils;
import io.github.asyncronous.cube.plaf.MetroArrowButton;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.metal.MetalComboBoxUI;

public final class MetroComboBoxUI
extends MetalComboBoxUI{
    public static ComponentUI createUI(JComponent comp){
        return new MetroComboBoxUI();
    }

    @Override
    public void installUI(JComponent comp){
        super.installUI(comp);
        this.comboBox.setBorder(BorderFactory.createEmptyBorder());
        this.comboBox.setPreferredSize(new Dimension(125, 50));
        this.listBox.setBackground(UIUtils.CUBE);
    }

    @Override
    protected JButton createArrowButton() {
        return new MetroArrowButton(BasicArrowButton.EAST);
    }

    @Override
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean focus){
        g.setColor(UIUtils.CUBE);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}