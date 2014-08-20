package io.github.asyncronous.cube.ui.diag;

import io.github.asyncronous.cube.Accounts;
import io.github.asyncronous.cube.Cube;
import io.github.asyncronous.cube.UIUtils;
import io.github.asyncronous.cube.obj.Account;
import io.github.asyncronous.cube.ui.comp.ColorHexField;
import io.github.asyncronous.cube.utils.Authentication;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public final class CreateAccountDialog
extends JDialog{
    private final GridBagConstraints gbc = new GridBagConstraints();
    private final JTextField uField = new JTextField(16);
    private final JPasswordField pField = new JPasswordField(16);
    private final ColorHexField cField = new ColorHexField(Color.green);
    private final JButton cancelButton = new JButton("Cancel");
    private final JButton createButton = new JButton("Create");
    private final JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    public CreateAccountDialog(){
        super(Cube.cubeFrame, "Create Account", ModalityType.APPLICATION_MODAL);
        this.setUndecorated(true);
        this.getContentPane().setBackground(UIUtils.CUBE);
        this.getContentPane().setLayout(new GridBagLayout());

        this.wrapper.setOpaque(false);
        this.wrapper.add(this.cancelButton);
        this.wrapper.add(this.createButton);

        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.insets.set(5, 5, 5, 5);
        this.gbc.anchor = GridBagConstraints.LINE_START;
        this.getContentPane().add(new JLabel("Username: "), this.gbc);
        this.gbc.gridy++;
        this.getContentPane().add(new JLabel("Password: "), this.gbc);
        this.gbc.gridy++;
        this.getContentPane().add(new JLabel("Profile Color: "), this.gbc);
        this.gbc.gridy++;
        this.getContentPane().add(this.wrapper, this.gbc);

        this.gbc.gridx = 1;
        this.gbc.gridy = 0;
        this.gbc.fill = GridBagConstraints.HORIZONTAL;
        this.getContentPane().add(this.uField, this.gbc);
        this.gbc.gridy++;
        this.getContentPane().add(this.pField, this.gbc);
        this.gbc.gridy++;
        this.getContentPane().add(this.cField, this.gbc);

        this.addActionListeners();

        this.pack();
    }

    private void addActionListeners(){
        this.cancelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        this.createButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String username = uField.getText();
                String password = new String(pField.getPassword());

                if(Authentication.login(username, password)){
                    Account acc = new Account(Authentication.getDisplayName(), username, cField.getColor());
                    Accounts.setCurrent(acc);
                    dispose();
                }
            }
        });
    }
}