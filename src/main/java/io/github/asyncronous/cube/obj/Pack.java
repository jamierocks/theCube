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

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Pack{
    @SerializedName("PackName")
    public final String packName;
    @SerializedName("PackDescription")
    public final String packDescription;
    @SerializedName("Version")
    public final String version;
    @SerializedName("Mods")
    public final Mod[] mods;
    @SerializedName("ImgUrl")
    public final String imgUrl;
    @SerializedName("Type")
    public final Type type;

    private Pack(String packName, String packDescription, String version, Mod[] mods, String imgUrl, Type type){
        this.packName = packName;
        this.packDescription = packDescription;
        this.version = version;
        this.mods = mods;
        this.imgUrl = imgUrl;
        this.type = type;
    }

    public static enum Type{
        PUBLIC, PRIVATE
    }

    public void downloadMods(Path dir)
    throws IOException{
        Path mods = dir.resolve("mods");
        if(!Files.exists(mods)){
            Files.createDirectories(mods);
        }

        for(Mod mod : this.mods){
            mod.download(mods);
        }
    }

    @Override
    public String toString(){
        for(Mod mod : this.mods){
            System.out.println(mod);
        }

        return Objects.toStringHelper(this.getClass())
                .add("packName", this.packName)
                .add("packDescription", this.packDescription)
                .add("version", this.version)
                .add("imgUrl", this.imgUrl)
                .add("type", this.type).toString();
    }
}