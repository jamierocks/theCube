package io.github.asyncronous.cube.worker;

import io.github.asyncronous.cube.Cube;
import io.github.asyncronous.cube.Pathing;
import io.github.asyncronous.cube.data.Gsons;
import io.github.asyncronous.cube.event.AccountEvent;
import io.github.asyncronous.cube.obj.Account;
import io.github.asyncronous.cube.ui.diag.LoadingDialog;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public final class AccountLoaderWorker
extends SwingWorker<Void, Void>{
    private final LoadingDialog dialog;

    public AccountLoaderWorker(LoadingDialog dialog){
        this.dialog = dialog;
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                AccountLoaderWorker.this.dialog.setVisible(true);
            }
        });
    }

    @Override
    protected Void doInBackground()
    throws Exception{
        if(!Files.exists(Pathing.ACCOUNTS)){
            Cube.LOGGER.debug("Creating Accounts Directory");
            Files.createDirectories(Pathing.ACCOUNTS);
        }

        File[] files = Pathing.ACCOUNTS.toFile().listFiles();

        if(files == null || files.length == 0){
            Cube.LOGGER.info("No Accounts Saved, not loading any");
            return null;
        }

        Cube.LOGGER.info("Loading Accounts");
        for(int i = 0; i < files.length; i++){
            this.dialog.bar.setValue((i * 100) / files.length);
            Account acc = Gsons.GSON.fromJson(new FileReader(files[i]), Account.class);
            Cube.BUS.post(new AccountEvent(acc, AccountEvent.ADDED));
        }
        return null;
    }

    @Override
    public void done(){
        Cube.LOGGER.info("Done Loading Accounts");
        this.dialog.dispose();
    }
}