package io.github.asyncronous.cube;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.asyncronous.cube.hook.SettingsHook;
import io.github.asyncronous.cube.plaf.CubeLookAndFeel;

import com.google.common.eventbus.EventBus;

import java.io.FileReader;
import java.nio.file.Files;
import java.util.Properties;

public final class Cube{
    static
    {
        CubeLookAndFeel.install();
        Runtime.getRuntime().addShutdownHook(new Thread(new SettingsHook()));
    }

    public static final Properties SETTINGS = new Properties();
    public static final EventBus BUS = new EventBus();
    public static final Logger LOGGER = LogManager.getLogger();

    public static void main(String... args)
    throws Exception{
        if(!Files.exists(Pathing.CORE)){
            Files.createDirectories(Pathing.CORE);
        }

        if(Files.exists(Pathing.CORE.resolve("settings.cfg"))){
            SETTINGS.load(new FileReader(Pathing.CORE.resolve("settings.cfg").toFile()));
        }
    }
}