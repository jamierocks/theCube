package io.github.asyncronous.cube.ui.panel;

import io.github.asyncronous.cube.Settings;
import io.github.asyncronous.cube.ui.tabs.AccountsTab;
import io.github.asyncronous.cube.ui.tabs.DashboardTab;
import io.github.asyncronous.cube.ui.tabs.InstancesTab;
import io.github.asyncronous.cube.ui.tabs.PacksTab;
import io.github.asyncronous.cube.ui.tabs.SettingsTab;
import io.github.asyncronous.cube.ui.tabs.Tab;

import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public final class CenterPanel
extends JPanel{
    public CenterPanel(){
        super(new CardLayout());

        Settings.EVENT_BUS.register(this);

        this.register(new DashboardTab());
        this.register(new InstancesTab());
        this.register(new PacksTab());
        this.register(new AccountsTab());
        this.register(new SettingsTab());
    }

    public void show(final String id){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                ((CardLayout) getLayout()).show(CenterPanel.this, id);
                revalidate();
            }
        });
    }

    public <T extends JPanel & Tab> void register(T card){
        this.add(card, card.id());
    }
}