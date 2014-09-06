package io.github.asyncronous.cube.obj;

import com.google.gson.annotations.SerializedName;

public final class Pack{
    @SerializedName("packName")
    public final String name;
    @SerializedName("packDescription")
    public final String description;
    public final Type type;
    public final Mod[] mods;

    private Pack(String name, String description, Type type, Mod[] mods){
        this.name = name;
        this.description = description;
        this.type = type;
        this.mods = mods;
    }

    public static enum Type{
        @SerializedName("public") PUBLIC,
        @SerializedName("private") PRIVATE
    }

    public static final class Mod{
        public final String name;
        public final String description;
        public final String url;
        public final String md5;

        public Mod(String name, String description, String url, String md5){
            this.name = name;
            this.description = description;
            this.url = url;
            this.md5 = md5;
        }
    }
}