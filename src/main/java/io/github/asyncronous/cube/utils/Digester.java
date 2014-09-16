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