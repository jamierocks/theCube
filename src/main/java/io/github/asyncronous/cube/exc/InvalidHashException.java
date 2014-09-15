package io.github.asyncronous.cube.exc;

import java.io.IOException;

public class InvalidHashException
extends IOException{
    public InvalidHashException(String expected, String got){
        super("Invalid Hash: " + got + ", expected: " + expected);
    }
}