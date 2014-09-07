package io.github.asyncronous.cube.ui

import java.awt.{BorderLayout, Dimension}
import javax.swing.{WindowConstants, JFrame}

import com.google.common.eventbus.Subscribe
import io.github.asyncronous.cube.Cube
import io.github.asyncronous.cube.event.PropertiesLoadEvent
import io.github.asyncronous.cube.ui.panel.{CenterPanel, BottomPanel}

object CubeFrame
extends JFrame("theCube"){
  this.setMinimumSize(new Dimension(1000, 575));
  this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  this.setResizable(false);
  this.setLayout(new BorderLayout());
  this.add(new BottomPanel(), BorderLayout.SOUTH);;
  this.add(new CenterPanel(), BorderLayout.CENTER);

  Cube.BUS.register(this);

  @Subscribe
  def onPropertiesLoad(e: PropertiesLoadEvent): Unit ={

  }
}