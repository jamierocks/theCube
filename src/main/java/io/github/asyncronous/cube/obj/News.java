package io.github.asyncronous.cube.obj;

import com.google.gson.annotations.SerializedName;

public final class News{
    @SerializedName("PubDate")
    public final String date;
    @SerializedName("Title")
    public final String title;
    @SerializedName("Body")
    public final String body;

    public News(String date, String title, String body){
        this.date = date;
        this.title = title;
        this.body = body;
    }
}