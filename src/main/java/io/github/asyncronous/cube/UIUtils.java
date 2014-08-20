package io.github.asyncronous.cube;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import javax.accessibility.AccessibleContext;
import javax.imageio.ImageIO;

public final class UIUtils{
    private UIUtils(){}

    public static Color compliment(Color c){
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();

        return new Color((~r) & 0xFF, (~b) & 0xFF, (~g) & 0xFF);
    }

    public static final Color CUBE = new Color(35, 40, 45).darker();

    private static Map<RenderingHints.Key, Object> hints;
    private static Map<RenderingHints.Key, Object> oldHints;

    public static void setBackground(Component c, Color bg) {
        setBackground0(c.getAccessibleContext(), bg);
    }

    private static void setBackground0(AccessibleContext ac, Color bg) {
        ac.getAccessibleComponent().setBackground(bg);
        int n = ac.getAccessibleChildrenCount();
        for (int i=0; i<n; i++) {
            setBackground0(ac.getAccessibleChild(i).getAccessibleContext(), bg);
        }
    }

    public static void setForeground(Component c, Color fg){
        setForeground0(c.getAccessibleContext(), fg);
    }

    private static void setForeground0(AccessibleContext ac, Color fg){
        ac.getAccessibleComponent().setForeground(fg);
        for(int i = 0; i < ac.getAccessibleChildrenCount(); i++){
            setForeground0(ac.getAccessibleChild(i).getAccessibleContext(), fg);
        }
    }

    public static void antialiasOn(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        if(hints == null){
            hints = new HashMap<>();
            hints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            oldHints = new HashMap<>();
            oldHints.put(RenderingHints.KEY_FRACTIONALMETRICS, g2.getRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS));
            oldHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, g2.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING));
            oldHints.put(RenderingHints.KEY_ANTIALIASING, g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING));
        }

        g2.addRenderingHints(hints);
    }

    public static AlphaComposite alpha(float f){
        return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, f);
    }

    public static void antialiasOff(Graphics g){
        ((Graphics2D) g).addRenderingHints(oldHints);
    }

    public static Image load(String s){
        try{
            return ImageIO.read(System.class.getResourceAsStream("/assets/theCube/images/" + s + ".png"));
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static Image load(String s, int scale){
        return resize((BufferedImage) load(s), scale);
    }
    private static BufferedImage resize(BufferedImage originalImage, int scale){
        BufferedImage resizedImage = new BufferedImage(scale, scale, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, scale, scale, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR
        );
        g.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY
        );
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        return resizedImage;
    }
}