package io.github.asyncronous.cube.ui.comp;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public final class CubeButton
extends JButton{
    private static final BufferedImage icon;
    static
    {
        try{
            icon = ImageIO.read(System.class.getResourceAsStream("/assets/theCube/images/theCube.png"));
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public CubeButton(){
        super("Cube");
        this.setPreferredSize(new Dimension(125, 50));
        this.setContentAreaFilled(false);
        this.setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(icon, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}