package io.github.asyncronous.cube;

import io.github.asyncronous.cube.plaf.CubeLookAndFeel;
import io.github.asyncronous.cube.ui.CubeFrame;
import io.github.asyncronous.cube.ui.diag.CreateAccountDialog;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public final class Cube{
    static
    {
        try{
            UIManager.setLookAndFeel(CubeLookAndFeel.class.getName());
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static CubeFrame cubeFrame = new CubeFrame();

    public static void main(String... args){
        Accounts.load();
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                cubeFrame.setVisible(true);
            }
        });
        Settings.EVENT_BUS.register(cubeFrame);

        if(Accounts.all().size() == 0){
            SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run(){
                    new CreateAccountDialog().setVisible(true);
                }
            });
        }
    }
}