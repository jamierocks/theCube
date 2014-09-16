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