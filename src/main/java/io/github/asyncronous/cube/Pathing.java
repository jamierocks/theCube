package io.github.asyncronous.cube;

import java.nio.file.Path;

public final class Pathing{
    public static final Path CORE = OS.getStorageLocation();
    public static final Path ACCOUNTS = CORE.resolve("accounts");
    public static final Path SKINS = CORE.resolve("skins");
}