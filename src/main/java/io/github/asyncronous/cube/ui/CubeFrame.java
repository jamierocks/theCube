package io.github.asyncronous.cube.ui;

import io.github.asyncronous.cube.Accounts;
import io.github.asyncronous.cube.Cube;
import io.github.asyncronous.cube.Settings;
import io.github.asyncronous.cube.UIUtils;
import io.github.asyncronous.cube.event.AccountRegisteredEvent;
import io.github.asyncronous.cube.obj.Account;
import io.github.asyncronous.cube.ui.comp.AccountComboBox;
import io.github.asyncronous.cube.ui.comp.CubeButton;
import io.github.asyncronous.cube.ui.comp.GitHubButton;
import io.github.asyncronous.cube.ui.comp.ToggleButtonGroup;
import io.github.asyncronous.cube.ui.comp.TwitterButton;
import io.github.asyncronous.cube.ui.frame.DraggableFrame;
import io.github.asyncronous.cube.ui.panel.CenterPanel;
import io.github.asyncronous.cube.ui.panel.LeftPanel;

import com.google.common.eventbus.Subscribe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.WindowConstants;

public final class CubeFrame
extends DraggableFrame{
    private final JPanel topPanel = new JPanel();
    private final JPanel leftPanel = new LeftPanel();
    private final CenterPanel centerPanel = new CenterPanel();

    private final ToggleButtonGroup group = new ToggleButtonGroup();
    private final JToggleButton modpacksButton = new JToggleButton("Mod Packs");
    private final JToggleButton dashboardButton = new JToggleButton("Dashboard", true);
    private final JToggleButton instancesButton = new JToggleButton("Instances");
    private final JToggleButton settingsButton = new JToggleButton("Settings");
    private final JToggleButton accountsButton = new JToggleButton("Accounts");
    private final JComboBox<Account> accountBox = new AccountComboBox();

    private final GridBagConstraints gbc = new GridBagConstraints();

    public CubeFrame(){
        super("theCube");

        this.topPanel.setBackground(UIUtils.CUBE.brighter());
        this.topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.topPanel.add(new CubeButton());
        this.topPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        this.topPanel.add(new TwitterButton());
        this.topPanel.add(Box.createRigidArea(new Dimension(25, 0)));
        this.topPanel.add(new GitHubButton());

        this.group.add(this.modpacksButton);
        this.group.add(this.dashboardButton);
        this.group.add(this.instancesButton);
        this.group.add(this.settingsButton);
        this.group.add(this.accountsButton);

        setupLeftPanel();
        this.addItemListeners();

        Settings.EVENT_BUS.register(this);

        this.setBackground(Color.black);
        this.setMinimumSize(new Dimension(830, 500));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.leftPanel, BorderLayout.WEST);
        this.getContentPane().add(this.topPanel, BorderLayout.NORTH);
        this.getContentPane().add(this.centerPanel, BorderLayout.CENTER);

        this.addActionListeners();
    }

    public Dimension getButtonSize(){
        return this.dashboardButton.getSize();
    }

    private void addActionListeners(){
        this.dashboardButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                centerPanel.show("dashboard");
            }
        });
        this.modpacksButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                centerPanel.show("packs");
            }
        });
        this.settingsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                centerPanel.show("settings");
            }
        });
        this.instancesButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                centerPanel.show("instances");
            }
        });
        this.accountsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                centerPanel.show("accounts");
            }
        });
    }

    @Subscribe
    public void onAccRegistered(AccountRegisteredEvent e){
        this.accountBox.addItem(e.account);
    }

    private void addItemListeners(){
        this.accountBox.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                Accounts.setCurrent((Account) e.getItem());
                Cube.cubeFrame.repaint();
            }
        });
    }

    private void setupLeftPanel(){
        this.gbc.fill = GridBagConstraints.BOTH;
        this.gbc.weighty = 0.1;
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;

        this.leftPanel.setLayout(new GridBagLayout());
        this.leftPanel.add(this.modpacksButton, this.gbc);
        this.gbc.gridy++;
        this.leftPanel.add(this.dashboardButton, this.gbc);
        this.gbc.gridy++;
        this.leftPanel.add(this.instancesButton, this.gbc);
        this.gbc.gridy++;
        this.leftPanel.add(this.accountsButton, this.gbc);
        this.gbc.gridy++;
        this.leftPanel.add(this.settingsButton, this.gbc);
        this.gbc.gridy++;
        this.leftPanel.add(Box.createRigidArea(new Dimension(125, 50)), this.gbc);
        this.gbc.gridy++;
        this.gbc.fill = GridBagConstraints.NONE;
        this.leftPanel.add(this.accountBox, this.gbc);
        this.gbc.fill = GridBagConstraints.BOTH;
        this.gbc.gridy++;
        this.leftPanel.add(Box.createRigidArea(new Dimension(125, 50)), this.gbc);
        this.gbc.gridy++;
        this.leftPanel.add(new JLabel("©CyanideX 2014"), this.gbc);
    }
}