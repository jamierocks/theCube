package io.github.asyncronous.cube.hook;

import io.github.asyncronous.cube.Cube;
import io.github.asyncronous.cube.Pathing;

import java.io.FileWriter;
import java.nio.file.Path;

public class SettingsHook
implements Runnable{
    @Override
    public void run(){
        try{
            Path path = Pathing.CORE.resolve("settings.cfg");
            Cube.SETTINGS.store(new FileWriter(path.toFile()), "Don't edit this");
        } catch(Exception e){
            e.printStackTrace(System.err);
        }
    }
}