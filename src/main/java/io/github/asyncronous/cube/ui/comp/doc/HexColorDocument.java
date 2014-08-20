package io.github.asyncronous.cube.ui.comp.doc;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public final class HexColorDocument
extends PlainDocument{
    @Override
    public void insertString(int offset, String str, AttributeSet attr)
    throws BadLocationException{
        if (str == null || this.getLength() + str.length() > "#000000".length() || !str.matches("[#0-9A-Fa-f]{0,7}")) {
            return;
        } else {
            if(getLength() == 0 && !str.startsWith("#")){
                return;
            } else{
                super.insertString(offset, str, attr);
            }
        }
    }
}