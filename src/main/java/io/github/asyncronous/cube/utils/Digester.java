package io.github.asyncronous.cube.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;

public final class Digester{
    private static final MessageDigest MD5;

    static
    {
        try{
            MD5 = MessageDigest.getInstance("MD5");
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    private Digester(){}

    public static String md5Hex(InputStream source)
    throws IOException{
        byte[] m = new Digest(source).digest(MD5);
        MD5.reset();
        return toHex(m);
    }

    public static String md5Hex(Path path)
    throws IOException{
        return md5Hex(Files.newInputStream(path));
    }

    public static String md5Hex(String text)
    throws IOException{
        byte[] m = MD5.digest(text.getBytes(StandardCharsets.UTF_8));
        MD5.reset();
        return toHex(m);
    }

    public static String toHex(byte[] bytes){
        StringBuilder builder = new StringBuilder();

        for(byte aByte : bytes){
            builder.append(Integer.toString((aByte & 0xFF) + 0x100, 16).substring(1));
        }

        return builder.toString();
    }

    private static final class Digest{
        private final InputStream stream;

        private Digest(InputStream stream){
            this.stream = stream;
        }

        public byte[] digest(MessageDigest d)
        throws IOException{
            byte[] buffer = new byte[1024];
            int len;
            while((len = stream.read(buffer)) > 0){
                d.update(buffer, 0, len);
            }
            return d.digest();
        }
    }
}