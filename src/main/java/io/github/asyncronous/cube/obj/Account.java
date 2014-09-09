package io.github.asyncronous.cube.obj;

import java.awt.Color;

public final class Account{
    public final String name;
    public final String username;
    public final Color color;

    public static final Account DEFAULT = new Account("Default", "Default", Color.red);

    public Account(String name, String username, Color color){
        this.name = name;
        this.username = username;
        this.color = color;
    }
}