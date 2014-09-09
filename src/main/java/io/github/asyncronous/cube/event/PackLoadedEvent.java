package io.github.asyncronous.cube.event;

import io.github.asyncronous.cube.obj.Pack;

public final class PackLoadedEvent{
    public final Pack pack;

    public PackLoadedEvent(Pack pack){
        this.pack = pack;
    }
}