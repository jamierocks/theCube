package io.github.asyncronous.cube.utils;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;

import io.github.asyncronous.cube.Settings;

import java.net.Proxy;

public final class Authentication{
    private static final YggdrasilUserAuthentication user_auth = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(
            Proxy.NO_PROXY, "1").createUserAuthentication(Agent.MINECRAFT);

    private Authentication(){}

    public static boolean login(String username, String password){
        user_auth.logOut();
        user_auth.setUsername(username);
        user_auth.setPassword(password);
        if(user_auth.canLogIn()){
            try{
                user_auth.logIn();
                Settings.properties.setProperty("lastAccount", username);
                return true;
            } catch(AuthenticationException e){
                e.printStackTrace(System.err);
                return false;
            }
        } else{
            System.err.println("Cannot login");
            return false;
        }
    }

    public static String getDisplayName(){
        return user_auth.getSelectedProfile().getName();
    }
}