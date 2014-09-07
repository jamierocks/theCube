package io.github.asyncronous.cube.ui

import java.awt.BorderLayout
import javax.swing.JFrame
import io.github.asyncronous.cube.ui.comp.Console;

object CubeConsoleFrame
extends JFrame("theCube|Console"){
  this.setLayout(new BorderLayout());
  this.add(Console, BorderLayout.CENTER);
}