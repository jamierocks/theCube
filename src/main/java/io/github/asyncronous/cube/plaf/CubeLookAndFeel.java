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
package io.github.asyncronous.cube.plaf;

import io.github.asyncronous.cube.plaf.button.CubeToggleButtonUI;
import io.github.asyncronous.cube.plaf.output.CubeSeparatorUI;

import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

public final class CubeLookAndFeel
extends MetalLookAndFeel{
    public static final CubeLookAndFeel instance = new CubeLookAndFeel();

    private CubeLookAndFeel(){}

    public static void install(){
        try{
            UIManager.setLookAndFeel(instance);
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initComponentDefaults(UIDefaults table){
        super.initComponentDefaults(table);
    }

    @Override
    public void initClassDefaults(UIDefaults table){
        super.initClassDefaults(table);

        // Button
        table.put("ToggleButtonUI", CubeToggleButtonUI.class.getCanonicalName());

        // Output
        table.put("SeparatorUI", CubeSeparatorUI.class.getCanonicalName());
    }

    @Override
    public String getName(){
        return "CubeLookAndFeel";
    }

    @Override
    public String getID(){
        return CubeLookAndFeel.class.getName();
    }

    @Override
    public String getDescription(){
        return "Cube Look and Feel";
    }

    @Override
    public boolean isNativeLookAndFeel(){
        return false;
    }

    @Override
    public boolean isSupportedLookAndFeel(){
        return true;
    }
}