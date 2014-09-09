package io.github.asyncronous.cube.event;

import io.github.asyncronous.cube.obj.Account;

public final class AccountEvent{
    public static final int CREATED = 0x0;
    public static final int ADDED = 0x1;

    public final Account acc;
    public final int type;

    public AccountEvent(Account acc, int type){
        this.acc = acc;
        this.type = type;
    }
}