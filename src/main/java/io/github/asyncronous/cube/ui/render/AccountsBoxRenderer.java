package io.github.asyncronous.cube.ui.render;

import io.github.asyncronous.cube.Accounts;
import io.github.asyncronous.cube.obj.Account;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public final class AccountsBoxRenderer
extends JLabel
implements ListCellRenderer<Account>{
    public AccountsBoxRenderer(){
        this.setOpaque(false);
        this.setHorizontalAlignment(CENTER);
        this.setVerticalAlignment(CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Account> list, Account value, int index, boolean isSelected, boolean cellHasFocus){
        if(value != null){
            if(isSelected){
                this.setBackground(Accounts.current().color);
                this.setForeground(list.getSelectionForeground());
            } else{
                this.setBackground(Accounts.current().color);
                this.setForeground(list.getForeground());
            }

            this.setIcon(new ImageIcon(value.getHead()));
            this.setText(value.name);
            this.setFont(list.getFont());
        }

        return this;
    }
}