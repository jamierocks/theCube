package io.github.asyncronous.cube.plaf.button;

import io.github.asyncronous.cube.Accounts;
import io.github.asyncronous.cube.UIUtils;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToggleButtonUI;

public final class MetroToggleButtonUI
extends BasicToggleButtonUI{
    public static ComponentUI createUI(JComponent comp){
        return new MetroToggleButtonUI();
    }

    private Rectangle viewRect = new Rectangle();
    private Rectangle iconRect = new Rectangle();
    private Rectangle textRect = new Rectangle();

    @Override
    public void installUI(JComponent comp){
        super.installUI(comp);

        if(comp instanceof JToggleButton){
            JToggleButton button = (JToggleButton) comp;
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setRolloverEnabled(true);
        }
    }

    @Override
    public void paint(Graphics g, JComponent comp){
        AbstractButton b = (AbstractButton) comp;
        Graphics2D g2 = (Graphics2D) g;

        if(b.getModel().isRollover() || b.getModel().isSelected()){
            g2.setColor(Accounts.current().color.brighter());
            g2.fillRect(0, 0, b.getWidth(), b.getHeight());
            g2.setColor(Color.white);
        } else{
            g2.setColor(Accounts.current().color);
            g2.fillRect(0, 0, b.getWidth(), b.getHeight());
            g2.setColor(Color.white);
        }

        String text = this.layout(b, g2.getFontMetrics(), b.getWidth(), b.getHeight());
        this.paintText(g2, b, this.textRect, text);
    }

    @Override
    protected void paintText(Graphics g, JComponent comp, Rectangle rect, String text){
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(comp.getFont());
        UIUtils.antialiasOn(g2);
        g2.drawString(text, rect.x, rect.y + g2.getFontMetrics().getAscent());
        UIUtils.antialiasOff(g2);
    }

    private String layout(AbstractButton b, FontMetrics fm, int width, int height) {
        Insets i = b.getInsets();
        viewRect.x = i.left;
        viewRect.y = i.top;
        viewRect.width = width - (i.right + viewRect.x);
        viewRect.height = height - (i.bottom + viewRect.y);

        textRect.x = textRect.y = textRect.width = textRect.height = 0;
        iconRect.x = iconRect.y = iconRect.width = iconRect.height = 0;

        return SwingUtilities.layoutCompoundLabel(
                b, fm, b.getText(), b.getIcon(),
                b.getVerticalAlignment(), b.getHorizontalAlignment(),
                b.getVerticalTextPosition(), b.getHorizontalTextPosition(),
                viewRect, iconRect, textRect,
                b.getText() == null ? 0 : b.getIconTextGap());
    }
}