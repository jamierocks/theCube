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

import io.github.asyncronous.cube.Resources;
import io.github.asyncronous.cube.utils.Digester;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Mod{
    @SerializedName("Name")
    public final String name;
    @SerializedName("Version")
    public final String version;
    @SerializedName("Description")
    public final String description;
    @SerializedName("Url")
    public final String url;
    @SerializedName("Md5")
    public final String md5;
    @SerializedName("MID")
    public final String mid;

    public Mod(String name, String version, String description, String url, String md5, String mid){
        this.name = name;
        this.version = version;
        this.description = description;
        this.url = url;
        this.md5 = md5;
        this.mid = mid;
    }

    public void download(Path parent)
    throws IOException{
        Path output = parent.resolve(this.name + "-" + this.version);

        if(Files.exists(output)){
            String hash = Digester.md5Hex(output);
            if(!hash.equalsIgnoreCase(this.md5)){
                try(FileChannel channel = FileChannel.open(output, Resources.WRITE);
                    ReadableByteChannel rbc = Channels.newChannel(new URL(this.url).openStream())){

                    channel.transferFrom(rbc, 0, Long.MAX_VALUE);
                }
            }
        } else{
            try(FileChannel channel = FileChannel.open(output, Resources.WRITE);
                ReadableByteChannel rbc = Channels.newChannel(new URL(this.url).openStream())){

                channel.transferFrom(rbc, 0, Long.MAX_VALUE);
            }
        }
    }

    @Override
    public String toString(){
        ToStringHelper helper = Objects.toStringHelper(Mod.class);

        try{
            for(Field field : this.getClass().getDeclaredFields()){
                helper.add(field.getName(), field.get(this));
            }
        } catch(Exception e){
            e.printStackTrace(System.err);
        }

        return helper.toString();
    }
}