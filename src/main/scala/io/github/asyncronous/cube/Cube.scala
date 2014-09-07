package io.github.asyncronous.cube

import java.io.PrintStream
import javax.swing.SwingUtilities

import io.github.asyncronous.cube.stream.ConsoleStream
import io.github.asyncronous.cube.ui.CubeConsoleFrame
import org.apache.logging.log4j.{LogManager, Logger}

object Cube{
  System.setOut(new PrintStream(new ConsoleStream()));;

  val LOGGER: Logger = LogManager.getRootLogger();

  def main(args: Array[String]): Unit ={
    SwingUtilities.invokeLater(new Runnable() {
      override def run(): Unit ={
        CubeConsoleFrame.setVisible(true);
      }
    });
  }
}