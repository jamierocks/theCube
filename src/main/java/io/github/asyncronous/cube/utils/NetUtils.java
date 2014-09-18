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

        News[] news = Gsons.GSON.fromJson(new InputStreamReader(in), News[].class);
        in.close();
        return news;
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