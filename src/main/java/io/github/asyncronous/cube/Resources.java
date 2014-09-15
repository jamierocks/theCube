package io.github.asyncronous.cube;

import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public final class Resources{
    public static final EnumSet<StandardOpenOption> WRITE = EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    public static final EnumSet<StandardOpenOption> READ = EnumSet.of(StandardOpenOption.READ);
}