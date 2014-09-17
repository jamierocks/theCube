package io.github.asyncronous.cube.ui.comp;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;

public final class ToggleButtonGroup
extends ButtonGroup{
    @Override
    public void setSelected(ButtonModel model, boolean selected){
        if(selected){
            super.setSelected(model, selected);
        } else{
            clearSelection();

            if(this.getSelection() == null){
                super.setSelected(model, true);
            }
        }
    }
}