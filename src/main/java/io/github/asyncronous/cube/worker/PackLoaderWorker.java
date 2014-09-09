package io.github.asyncronous.cube.worker;

import io.github.asyncronous.cube.Cube;
import io.github.asyncronous.cube.event.PackLoadedEvent;
import io.github.asyncronous.cube.obj.Pack;
import io.github.asyncronous.cube.ui.diag.LoadingDialog;
import io.github.asyncronous.cube.utils.NetUtils;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class PackLoaderWorker
extends SwingWorker<Void, Void>{
    private final LoadingDialog dialog = new LoadingDialog("Loading Accounts");

    public PackLoaderWorker(){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                dialog.setVisible(true);
            }
        });
    }

    @Override
    protected Void doInBackground()
    throws Exception{
        Pack[] packs = NetUtils.getPublicPacks();
        for(Pack pack : packs){
            Cube.BUS.post(new PackLoadedEvent(pack));
        }

        return null;
    }
}