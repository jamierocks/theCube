package io.github.asyncronous.cube.ui.comp;

import io.github.asyncronous.cube.Cube;
import io.github.asyncronous.cube.obj.Account;
import io.github.asyncronous.cube.ui.render.AccountsBoxRenderer;

import javax.swing.JComboBox;

public final class AccountComboBox
extends JComboBox<Account>{
    public AccountComboBox(){
        super();
        this.setRenderer(new AccountsBoxRenderer());
    }

    @Override
    public void addItem(Account acc){
        if(!this.has(acc)){
            super.addItem(acc);
            Cube.cubeFrame.repaint();
        }
        this.setSelectedItem(acc);
    }

    private boolean has(Account a){
        for(int i = 0; i < this.getItemCount(); i++){
            if(this.getItemAt(i) == a){
                return true;
            }
        }

        return false;
    }
}