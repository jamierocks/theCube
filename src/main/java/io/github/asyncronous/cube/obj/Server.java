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
package io.github.asyncronous.cube.obj;

public class Server{
    private String name;
    private String url;
    private boolean defaultSelected;
    private boolean currentlySelected;

    public Server(String name, String url, boolean defaultSelected){
        this.name = name;
        this.url = url;
        this.defaultSelected = defaultSelected;
        this.currentlySelected = false;
    }

    public String getName(){
        return name;
    }
    public String getURL(){
        return url;
    }
    public boolean isDefaultSelected(){
        return defaultSelected;
    }
    public boolean isCurrentlySelected(){
        return currentlySelected;
    }

    public void setCurrentlySelected(boolean selected){
        currentlySelected = selected;
    }
}
