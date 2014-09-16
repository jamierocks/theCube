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
package io.github.asyncronous.cube.obj;

import io.github.asyncronous.cube.Pathing;
import io.github.asyncronous.cube.Resources;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class Account{
    public final String name;
    public final String username;
    public final Color color;

    public static final Account DEFAULT = new Account("Default", "Default", Color.red);
    public static final Account ASYNC = new Account("Asyncronos", "asyncronous16@gmail.com", Color.pink);
    public static final Account JAKE = new Account("ashRidden", "Jake", Color.blue);

    public Account(String name, String username, Color color){
        this.name = name;
        this.username = username;
        this.color = color;
    }

    public BufferedImage getSkin(){
        Path path = Pathing.SKINS.resolve(this.name.toLowerCase() + ".png");

        if(!Files.exists(path)){
            this.updateSkin();
        }

        try{
            return ImageIO.read(path.toFile());
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public Account updateSkin(){
        if(!this.name.toLowerCase().equalsIgnoreCase("default")) {
            try {
                if (!Files.exists(Pathing.SKINS)) {
                    Files.createDirectories(Pathing.SKINS);
                }

                Path path = Pathing.SKINS.resolve(this.name.toLowerCase() + ".png");

                HttpURLConnection conn = (HttpURLConnection) new URL("http://s3.amazonaws.com/MinecraftSkins/" + this.name + ".png").openConnection();
                try (InputStream in = conn.getInputStream();
                     ReadableByteChannel rbc = Channels.newChannel(in);
                     FileChannel channel = FileChannel.open(path, Resources.WRITE)) {
                    channel.transferFrom(rbc, 0, Long.MAX_VALUE);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return this;
        }
    }

    public Image getHead(){
        try{
            BufferedImage skin = this.getSkin();
            BufferedImage main = skin.getSubimage(8, 8, 8, 8);
            BufferedImage helmet = skin.getSubimage(40, 8, 8, 8);
            BufferedImage head = new BufferedImage(8, 8, BufferedImage.TYPE_INT_ARGB);
            Graphics g = head.getGraphics();
            g.drawImage(main, 0, 0, null);
            if (nonTransparentPixels(helmet) <= 32) {
                g.drawImage(helmet, 0, 0, null);
            }
            return head.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static int nonTransparentPixels(BufferedImage image) {
        int count = 0;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (image.getRGB(x, y) == -1) {
                    count++;
                }
            }
        }
        return count;
    }
}