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
package io.github.asyncronous.cube.utils;

import org.junit.Test;

import io.github.asyncronous.cube.obj.Pack;

import java.nio.file.Path;
import java.nio.file.Paths;

public class NetUtilsTest{
    @Test
    public void testGetPublicPacks()
    throws Exception{
        Path DESKTOP = Paths.get(System.getProperty("user.home"), "Desktop");

        Pack[] packs = NetUtils.getPublicPacks();
        if(packs != null){
            for(Pack pack : packs){
                Path output = DESKTOP.resolve(pack.packName);
                pack.downloadMods(output);
            }
        } else{
            System.out.println("Packs == null");
        }
    }
}