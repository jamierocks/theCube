package io.github.asyncronous.cube.event;

public final class UpdateEvent{
    public final String title;
    public final String center;

    public UpdateEvent(String title, String center){
        this.title = title;
        this.center = center;
    }
}