package io.github.asyncronous.cube

import java.io.PrintStream
import javax.swing.{UIManager, SwingUtilities}

import com.google.common.eventbus.EventBus
import io.github.asyncronous.cube.plaf.CubeLookAndFeel
import io.github.asyncronous.cube.stream.ConsoleStream
import io.github.asyncronous.cube.ui.CubeFrame
import org.apache.logging.log4j.{LogManager, Logger}

object Cube{
  System.setOut(new PrintStream(new ConsoleStream()));
  UIManager.setLookAndFeel(CubeLookAndFeel.instance);

  val LOGGER: Logger = LogManager.getRootLogger();
  val BUS: EventBus = new EventBus();

  def main(args: Array[String]): Unit ={
    SwingUtilities.invokeLater(new Runnable() {
      override def run(): Unit ={
        CubeFrame.setVisible(true);
      }
    });
  }
}