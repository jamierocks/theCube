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