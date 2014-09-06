package io.github.asyncronous.cube;

import io.github.asyncronous.cube.plaf.CubeLookAndFeel;
import io.github.asyncronous.cube.ui.CubeFrame;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public final class Cube{
    static
    {
        try{
            UIManager.setLookAndFeel(CubeLookAndFeel.class.getName());

            Path props = Settings.CORE.resolve("theCube.cfg");
            if(Files.exists(props)){
                Settings.properties.load(new FileReader(props.toFile()));
            }
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
        Accounts.load();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    Path props = Settings.CORE.resolve("theCube.cfg");
                    Settings.properties.store(new FileWriter(props.toFile()), "Don't edit");
                } catch(Exception e){
                    throw new RuntimeException(e);
                }
            }
        }));
    }

    public static CubeFrame cubeFrame = new CubeFrame();

    public static void main(String... args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                cubeFrame.setVisible(true);
            }
        });
        Settings.EVENT_BUS.register(cubeFrame);
    }
}