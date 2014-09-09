package io.github.asyncronous.cube.obj;

import io.github.asyncronous.cube.data.Gsons;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

public final class Pack{
    @SerializedName("PackName")
    public final String packName;
    @SerializedName("PackDescription")
    public final String packDescription;
    @SerializedName("Version")
    public final String version;
    @SerializedName("Mods")
    public final String mods;
    @SerializedName("ImgUrl")
    public final String imgUrl;
    @SerializedName("Type")
    public final Type type;

    public Pack(String packName, String packDescription, String version, String mods, String imgUrl, Type type){
        this.packName = packName;
        this.packDescription = packDescription;
        this.version = version;
        this.mods = mods;
        this.imgUrl = imgUrl;
        this.type = type;
    }

    public Mod[] getMods(){
        return Gsons.GSON.fromJson(this.mods, Mod[].class);
    }

    public static enum Type{
        PUBLIC, PRIVATE
    }

    public static final class Mod{
        @SerializedName("Name")
        public final String name;
        @SerializedName("Description")
        public final String description;
        @SerializedName("Url")
        public final String url;
        @SerializedName("Md5")
        public final String md5;

        public Mod(String name, String description, String url, String md5){
            this.name = name;
            this.description = description;
            this.url = url;
            this.md5 = md5;
        }
    }

    @Override
    public String toString(){
        return Objects.toStringHelper(Pack.class.getName())
                .add("packName", this.packName)
                .add("packDescription", this.packDescription)
                .add("version", this.version)
                .add("mods", this.mods)
                .add("imgUrl", this.imgUrl)
                .add("type", this.type).toString();
    }
}