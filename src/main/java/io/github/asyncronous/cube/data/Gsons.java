package io.github.asyncronous.cube.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class Gsons{
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
}