package io.github.asyncronous.cube.ui.comp;

import io.github.asyncronous.cube.ui.comp.doc.HexColorDocument;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

public final class ColorHexField
extends JTextField{
    public ColorHexField(Color c){
        super(16);
        this.setDocument(new HexColorDocument());
        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                setCaretPosition(getDocument().getLength());
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(getDocument().getLength() < "#000000".length()){
                    while(getDocument().getLength() < "#000000".length()){
                        try {
                            getDocument().insertString(getDocument().getLength(), (getDocument().getLength() == 0 ? "#" : "0"), null);
                        } catch (BadLocationException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });
        this.setText(c);
    }

    public Color getColor(){
        return Color.decode(this.getText());
    }

    public void setText(Color c){
        String hex = Integer.toHexString(c.getRGB() & 0xFFFFFF);
        if(hex.length() < "000000".length()){
            while(hex.length() < "000000".length()){
                hex += '0';
            }
        }
        this.setText("#" + hex);
    }
}