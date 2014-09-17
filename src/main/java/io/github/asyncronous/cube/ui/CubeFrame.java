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
package io.github.asyncronous.cube.ui;

import io.github.asyncronous.cube.ui.comp.ToggleButtonGroup;
import io.github.asyncronous.cube.ui.tab.InstanceTab;
import io.github.asyncronous.cube.ui.tab.NewsTab;
import io.github.asyncronous.cube.ui.tab.PacksTab;
import io.github.asyncronous.cube.ui.tab.Tab;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.WindowConstants;

public final class CubeFrame
extends JFrame{
    private final JPanel content = new JPanel(new CardLayout());
    private final JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private final ToggleButtonGroup group = new ToggleButtonGroup();

    private final JToggleButton newsButton = new JToggleButton("News", true);
    private final JToggleButton packsButton = new JToggleButton("Packs");

    private static CubeFrame instance;

    public static CubeFrame instance(){
        return instance == null ? instance = new CubeFrame() : instance;
    }

    public CubeFrame(){
        super("theCube");
        this.setMinimumSize(new Dimension(830, 500));
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.group.add(this.newsButton);
        this.group.add(this.packsButton);

        this.register(new InstanceTab());
        this.register(new NewsTab());
        this.register(new PacksTab());

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.content, BorderLayout.CENTER);
        this.getContentPane().add(this.bottom, BorderLayout.SOUTH);

        this.addActionListeners();
        this.setupBottomPanel();
    }

    private void setupBottomPanel(){
        this.newsButton.setPreferredSize(new Dimension(100, 100));

        this.bottom.add(this.newsButton);
        this.bottom.add(this.packsButton);
    }

    private void addActionListeners(){
        this.newsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                show("news");
            }
        });
        this.packsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                show("packs");
            }
        });
    }

    private <T extends JPanel & Tab> void register(T t){
        this.content.add(t, t.id());
    }

    public void show(String id){
        ((CardLayout) this.content.getLayout()).show(this.content, id);
    }
}