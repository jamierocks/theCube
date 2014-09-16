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

public class ServerList {
    private Server[] serverList;
    private Server defaultServer;
    private Server currentServer;

    public ServerList(Server[] serverList){
        this.serverList = serverList;

        for(Server server : serverList){
            if(server.isDefaultSelected()){
                defaultServer = server;
            }
        }
        defaultServer = serverList[0];
    }

    public Server getDefault(){
        return defaultServer;
    }
    public Server getCurrent(){
        for(Server server : serverList){
            if(server.isCurrentlySelected()){
                return server;
            }
        }
        return serverList[0];
    }
    public void setCurrent(int id){
        for(Server server : serverList){
            server.setCurrentlySelected(false);
        }
        serverList[id].setCurrentlySelected(true);
    }
}
