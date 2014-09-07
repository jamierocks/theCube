package io.github.asyncronous.cube.ui.comp

import java.awt.Color
import javax.swing.JTextPane
import javax.swing.text.{StyleConstants, SimpleAttributeSet}

object Console
extends JTextPane{
  private val attrs: SimpleAttributeSet = new SimpleAttributeSet();

  this.setEditable(false);

  def setColor(c: Color): Unit ={
    StyleConstants.setForeground(this.attrs, c);
  }

  def setBold(b: Boolean): Unit ={
    StyleConstants.setBold(this.attrs, b);
  }

  override def getScrollableTracksViewportWidth(): Boolean= true

  def write(str: String): Unit ={
    try{
      this.getDocument.insertString(this.getDocument().getLength(), str, this.attrs);
      this.setCaretPosition(this.getDocument.getLength());
    } catch{
      case ex: Exception=>{
        ex.printStackTrace(System.err);
      }
    }
  };
}