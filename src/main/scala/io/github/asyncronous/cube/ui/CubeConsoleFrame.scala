package io.github.asyncronous.cube.ui

import java.awt.{Dimension, Color, BorderLayout}
import javax.swing.JFrame
import com.google.common.eventbus.Subscribe
import io.github.asyncronous.cube.Cube
import io.github.asyncronous.cube.event.PropertiesLoadEvent
import io.github.asyncronous.cube.ui.comp.Console;

object CubeConsoleFrame
extends JFrame("theCube|Console"){
  this.setLayout(new BorderLayout());
  this.add(Console, BorderLayout.CENTER);
  this.setBackground(Color.black);
  this.setMinimumSize(new Dimension(600, 400));

  Cube.BUS.register(this);

  @Subscribe
  def onPropertiesLoad(e: PropertiesLoadEvent): Unit ={

  }
}