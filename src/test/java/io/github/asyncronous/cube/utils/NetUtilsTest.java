package io.github.asyncronous.cube.utils;

import org.junit.Test;

import io.github.asyncronous.cube.obj.Pack;

import java.nio.file.Path;
import java.nio.file.Paths;

public class NetUtilsTest{
    @Test
    public void testGetPublicPacks()
    throws Exception{
        Path DESKTOP = Paths.get(System.getProperty("user.home"), "Desktop");

        Pack[] packs = NetUtils.getPublicPacks();
        if(packs != null){
            for(Pack pack : packs){
                Path output = DESKTOP.resolve(pack.packName);
                pack.downloadMods(output);
            }
        } else{
            System.out.println("Packs == null");
        }
    }
}