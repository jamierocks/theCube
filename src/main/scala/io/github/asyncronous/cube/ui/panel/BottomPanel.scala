package io.github.asyncronous.cube.ui.panel

import java.awt._
import javax.swing.{SwingConstants, JSeparator, JToggleButton, JPanel}

class BottomPanel
extends JPanel{
  this.setPreferredSize(new Dimension(1000, 150));
  this.setLayout(new FlowLayout(FlowLayout.LEFT));
  this.add(new JToggleButton("Hello World"));
  this.add(new JSeparator(SwingConstants.VERTICAL));

  override def paint(g: Graphics): Unit ={
    super.paint(g);
    val g2: Graphics2D = g.asInstanceOf[Graphics2D];
    g2.setComposite(alpha(0.5F));
    g2.fillRect(0, 0, this.getWidth(), this.getHeight());
  }

  def alpha(f: Float): Composite= AlphaComposite.getInstance(AlphaComposite.SRC_OVER, f);
}