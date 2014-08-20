package io.github.asyncronous.cube;

import io.github.asyncronous.cube.event.AccountRegisteredEvent;
import io.github.asyncronous.cube.obj.Account;

import com.google.common.collect.ImmutableSet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public final class Accounts{
    private static Account current = Account.DEFAULT;

    static
    {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    for(Account acc : all()){
                        if(acc == Account.DEFAULT){
                            continue;
                        }
                        Path accf = Settings.ACCOUNTS.resolve(acc.name + ".json");
                        try(FileOutputStream fis = new FileOutputStream(accf.toFile())){
                            fis.write(Settings.GSON.toJson(acc).getBytes());
                            fis.flush();
                        } catch(Exception e){
                            e.printStackTrace(System.err);
                        }
                    }
                } catch(Exception ex){
                    ex.printStackTrace(System.err);
                }
            }
        }));
    }

    private static final Set<Account> loaded = new HashSet<>();

    public static Account current(){
        return current;
    }

    public static void load(){
        Settings.TASKS.execute(new Runnable(){
            @Override
            public void run(){
                if(!Files.exists(Settings.ACCOUNTS)){
                    try{
                        Files.createDirectories(Settings.ACCOUNTS);
                    } catch(IOException e){
                        e.printStackTrace(System.err);
                    }
                }
                loaded.clear();
                try(DirectoryStream<Path> accs = Files.newDirectoryStream(Settings.ACCOUNTS)){
                    for(Path path : accs){
                        if(!Files.isDirectory(path)){
                            if(path.getFileName().toString().endsWith(".json")){
                                try(FileInputStream fis = new FileInputStream(path.toFile())){
                                    Account acc = Settings.GSON.fromJson(new InputStreamReader(fis), Account.class);
                                    System.out.println("Loaded Account " + acc.name);
                                    Settings.EVENT_BUS.post(new AccountRegisteredEvent(acc));
                                    loaded.add(acc);
                                }
                            }
                        }
                    }
                } catch(IOException e){
                    e.printStackTrace(System.err);
                }
            }
        });
    }

    public static void setCurrent(Account acc){
        if(!loaded.contains(acc)){
            Settings.EVENT_BUS.post(new AccountRegisteredEvent(acc));
            loaded.add(acc);
        }

        current = acc;
    }

    public static Set<Account> all(){
        return ImmutableSet.copyOf(loaded);
    }
}