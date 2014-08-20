package io.github.asyncronous.cube.event;

import io.github.asyncronous.cube.obj.Account;

public final class AccountRegisteredEvent{
    public final Account account;

    public AccountRegisteredEvent(Account account){
        this.account = account;
    }
}