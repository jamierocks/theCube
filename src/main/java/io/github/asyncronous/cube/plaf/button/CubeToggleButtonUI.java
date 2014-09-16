/**
 * Copyright (C) 2014 Asyncronous
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package io.github.asyncronous.cube.plaf.button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToggleButtonUI;

public final class CubeToggleButtonUI
extends BasicToggleButtonUI{
    public static ComponentUI createUI(JComponent comp){
        return new CubeToggleButtonUI();
    }

    @Override
    public void installUI(JComponent comp){
        super.installUI(comp);
        ((JToggleButton) comp).setFocusPainted(false);
    }

    @Override
    public void paint(Graphics g, JComponent comp){
        Graphics2D g2 = (Graphics2D) g;
        JToggleButton b = (JToggleButton) comp;

        int x = (b.getWidth() - g2.getFontMetrics().stringWidth(b.getText())) / 2;
        int y = (b.getHeight() - g2.getFontMetrics().getHeight()) / 2;

        g2.setColor(Color.gray);
        g2.fillRect(0, 0, b.getWidth() + 10, b.getHeight() + 5);
        g2.setColor(Color.white);
        g2.drawString(b.getText(), x, y + g2.getFontMetrics().getHeight());
    }
}