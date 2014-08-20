package io.github.asyncronous.cube.plaf;

import io.github.asyncronous.cube.plaf.button.MetroToggleButtonUI;
import io.github.asyncronous.cube.plaf.ctrl.MetroComboBoxUI;

import java.awt.Color;
import javax.swing.UIDefaults;
import javax.swing.plaf.metal.MetalLookAndFeel;

public final class CubeLookAndFeel
extends MetalLookAndFeel{
    @Override
    public void initComponentDefaults(UIDefaults table){
        super.initComponentDefaults(table);

        // Output
        table.put("Label.foreground", Color.white);

        // Control
        table.put("ComboBox.foreground", Color.white);
        table.put("ComboBox.selectionForeground", Color.white);
    }

    @Override
    public void initClassDefaults(UIDefaults table){
        super.initClassDefaults(table);

        // Button
        table.put("ToggleButtonUI", MetroToggleButtonUI.class.getCanonicalName());

        // Control
        table.put("ComboBoxUI", MetroComboBoxUI.class.getCanonicalName());
    }

    @Override
    public String getName(){
        return "CubeMetroLookAndFeel";
    }

    @Override
    public String getID(){
        return CubeLookAndFeel.class.getName();
    }

    @Override
    public String getDescription(){
        return "A lighter metro look and feel";
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