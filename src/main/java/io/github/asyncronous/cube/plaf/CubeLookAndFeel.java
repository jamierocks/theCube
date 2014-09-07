package io.github.asyncronous.cube.plaf;

import io.github.asyncronous.cube.plaf.button.CubeToggleButtonUI;
import io.github.asyncronous.cube.plaf.output.CubeSeparatorUI;

import javax.swing.UIDefaults;
import javax.swing.plaf.metal.MetalLookAndFeel;

public final class CubeLookAndFeel
extends MetalLookAndFeel{
    public static final CubeLookAndFeel instance = new CubeLookAndFeel();

    private CubeLookAndFeel(){}

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