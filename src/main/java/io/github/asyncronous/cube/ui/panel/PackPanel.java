package io.github.asyncronous.cube.ui.panel;

import io.github.asyncronous.cube.UIUtils;
import io.github.asyncronous.cube.obj.Pack;
import io.github.asyncronous.cube.ui.tabs.Tab;
import io.github.asyncronous.cube.utils.Strings;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;

public final class PackPanel
extends JPanel
implements Tab{
    private final Pack pack;

    private static final Image bg = UIUtils.load("vanillaminecraft");

    public PackPanel(Pack pack){
        this.pack = pack;
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        // Paint Background
        g2.drawImage(bg, 0, 0, this.getWidth(), this.getHeight(), null);

        // Paint Description
        g2.setColor(Color.black);
        Composite comp = g2.getComposite();
        g2.setComposite(UIUtils.alpha(0.5F));
        g2.fillRect(0, 0, this.getWidth() / 4, this.getHeight());
        g2.setComposite(comp);
        UIUtils.antialiasOn(g2);
        g2.setColor(Color.white);
        int wrap = 25;
        int y = 53;
        String wrapped = Strings.wrap(this.pack.description, wrap);
        g2.drawString(pack.name, 5 + g2.getFontMetrics().charWidth('M'), y+= g2.getFontMetrics().getHeight() * 2);
        for(String str : wrapped.split("\n")){
            g2.drawString(str, 5 + g2.getFontMetrics().charWidth('M'), y += g2.getFontMetrics().getHeight());
        }
        UIUtils.antialiasOff(g2);
    }

    @Override
    public String id(){
        return this.pack.name.toLowerCase();
    }
}