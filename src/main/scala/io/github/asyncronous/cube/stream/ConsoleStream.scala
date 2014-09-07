package io.github.asyncronous.cube.stream

import java.io.OutputStream;
import io.github.asyncronous.cube.ui.comp.Console;

class ConsoleStream
extends OutputStream{
  override def write(b: Int): Unit={
    Console.write("" + b.asInstanceOf[Char]);
  }
}