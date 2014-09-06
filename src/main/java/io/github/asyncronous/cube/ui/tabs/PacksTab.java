package io.github.asyncronous.cube.ui.tabs;

import io.github.asyncronous.cube.Settings;
import io.github.asyncronous.cube.obj.Pack;
import io.github.asyncronous.cube.ui.panel.PackPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

public final class PacksTab
extends JPanel
implements Tab{
    protected final List<Tab> cards = new LinkedList<>();
    protected int ptr = 0;

    private final JPanel displayPanel = new JPanel(new CardLayout());
    {
        this.displayPanel.setOpaque(false);
    }

    public PacksTab(){
        super(new BorderLayout());
        this.add(new JPanel(){
            @Override
            public int getHeight(){
                return 45;
            }
        }, BorderLayout.NORTH);
        this.add(this.displayPanel, BorderLayout.CENTER);
        this.register(new PackPanel(Settings.GSON.fromJson(new InputStreamReader(System.class.getResourceAsStream("/assets/theCube/json/Tech0.json")), Pack.class)));
    }

    protected void next(){
        if(this.ptr++ >= this.cards.size() - 1){
            this.ptr = this.cards.size() - 1;
        }

        CardLayout layout = (CardLayout) this.displayPanel.getLayout();
        layout.show(this.displayPanel, this.cards.get(this.ptr).id());
    }

    protected void back(){
        if(this.ptr-- <= 0){
            this.ptr = 0;
        }

        CardLayout layout = (CardLayout) this.displayPanel.getLayout();
        layout.show(this.displayPanel, this.cards.get(this.ptr).id());
    }

    public <T extends JPanel & Tab> void register(T card){
        this.cards.add(card);
        this.displayPanel.add(card, card.id());
    }

    @Override
    public String id(){
        return "packs";
    }
}
