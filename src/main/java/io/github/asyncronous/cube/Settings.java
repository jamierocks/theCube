package io.github.asyncronous.cube;

import com.google.common.eventbus.EventBus;
import com.google.gson.Gson;

import java.nio.file.Path;
import java.util.concurrent.ForkJoinPool;

public final class Settings{
    public static final Gson GSON = new Gson();
    public static final EventBus EVENT_BUS = new EventBus();
    public static final ForkJoinPool TASKS = new ForkJoinPool();

    public static final Path CORE = OS.getStorageLocation();
    public static final Path DATA = CORE.resolve("data");
    public static final Path SKINS = DATA.resolve("skins");
    public static final Path ACCOUNTS = CORE.resolve("acc");
}