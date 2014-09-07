package io.github.asyncronous.cube.ui.comp

import java.awt.Color
import javax.swing.JTextPane
import javax.swing.text.SimpleAttributeSet

object Console
extends JTextPane{
  private val attrs: SimpleAttributeSet = new SimpleAttributeSet();

  this.setEditable(false);
  this.setBackground(Color.black);
  this.setForeground(Color.white);

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