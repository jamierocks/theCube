package io.github.asyncronous.cube.utils;

import io.github.asyncronous.cube.data.Constants;
import io.github.asyncronous.cube.data.Gsons;
import io.github.asyncronous.cube.obj.News;
import io.github.asyncronous.cube.obj.Pack;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class NetUtils{
    public static News[] getNews()
    throws IOException{
        HttpURLConnection conn = (HttpURLConnection) new URL(Constants.SERVER + "/News/GetFeed").openConnection();

        InputStream in;
        try{
            in = conn.getInputStream();
        } catch(Exception e){
            in = conn.getErrorStream();
        }

        News[] packs = Gsons.GSON.fromJson(new InputStreamReader(in), News[].class);
        in.close();
        return packs;
    }

    public static Pack[] getUserPacks(String user)
    throws IOException{
        HttpURLConnection conn = (HttpURLConnection) new URL(Constants.SERVER + "/Packs/GetUser/" + user).openConnection();
        conn.connect();

        if(conn.getResponseCode() == 404){
            return null;
        }

        InputStream in;
        try{
            in = conn.getInputStream();
        } catch(Exception e){
            in = conn.getErrorStream();
        }

        Pack[] packs = Gsons.GSON.fromJson(new InputStreamReader(in), Pack[].class);
        in.close();
        return packs;
    }

    public static Pack[] getPublicPacks()
    throws IOException{
        HttpURLConnection conn = (HttpURLConnection) new URL(Constants.SERVER + "Packs/GetPublic").openConnection();
        conn.connect();

        InputStream in;
        try{
            in = conn.getInputStream();
        } catch(Exception e){
            in = conn.getErrorStream();
        }

        Pack[] packs = Gsons.GSON.fromJson(new InputStreamReader(in), Pack[].class);
        in.close();
        return packs;
    }
}