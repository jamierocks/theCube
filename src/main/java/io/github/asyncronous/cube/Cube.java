/**
 * Copyright (C) 2014 Asyncronous
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package io.github.asyncronous.cube;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.asyncronous.cube.hook.SettingsHook;
import io.github.asyncronous.cube.ui.CubeFrame;

import com.google.common.eventbus.EventBus;

import java.io.FileReader;
import java.nio.file.Files;
import java.util.Properties;

public final class Cube{
    static
    {
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

        CubeFrame.instance().setVisible(true);
    }
}