package com.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Profile {
    private long id;
    private String login;
    private String password;

    public Profile(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }


}
