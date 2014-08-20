package io.github.asyncronous.cube.obj;

import io.github.asyncronous.cube.Settings;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import javax.imageio.ImageIO;

public final class Account
implements Comparable<Account>{
    public static final Account DEFAULT = new Account("Default", "default", new Color(38, 140, 64));

    public final String name, email;
    public final Color color;

    public Account(String name, String email, Color color){
        this.name = name;
        this.email = email;
        this.color = color;
    }

    public BufferedImage getSkin(){
        try{
            if(!Files.exists(Settings.SKINS)){
                Files.createDirectories(Settings.SKINS);
            }

            Path path = Settings.SKINS.resolve(this.name.toLowerCase() + ".png");

            if(!Files.exists(path)){
                this.updateSkin();
            }

            return ImageIO.read(path.toFile());
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public Account updateSkin(){
        try{
            if(this.name.toLowerCase().equalsIgnoreCase("default")){
                return this;
            }

            if(!Files.exists(Settings.SKINS)){
                Files.createDirectories(Settings.SKINS);
            }

            Path path = Settings.SKINS.resolve(this.name.toLowerCase() + ".png");

            HttpURLConnection conn = (HttpURLConnection) new URL("http://s3.amazonaws.com/MinecraftSkins/" + this.name + ".png").openConnection();

            System.out.println("http://s3.amazonaws.com/MinecraftSkins/" + this.name.toLowerCase() + ".png");

            try(InputStream in = conn.getInputStream();
                ReadableByteChannel rbc = Channels.newChannel(in);
                FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE)){

                channel.transferFrom(rbc, 0, Long.MAX_VALUE);
            }
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return this;
    }

    public Image getHead(){
        try{
            BufferedImage skin = this.getSkin();
            BufferedImage main = skin.getSubimage(8, 8, 8, 8);
            BufferedImage head = new BufferedImage(8, 8, BufferedImage.TYPE_INT_ARGB);

            Graphics g = head.getGraphics();
            g.drawImage(main, 0, 0, null);
            return head.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int compareTo(Account o){
        return this.name.compareToIgnoreCase(o.name);
    }

    @Override
    public boolean equals(Object o){
        return o instanceof Account && ((Account) o).name.equalsIgnoreCase(this.name);
    }

    @Override
    public int hashCode(){
        return this.name.hashCode();
    }
}