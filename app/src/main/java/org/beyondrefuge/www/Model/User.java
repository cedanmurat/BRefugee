package org.beyondrefuge.www.Model;

import java.util.Date;

/**
 * Created by Recoded Cedan on 1.03.2018.
 */

public class User {
    private String userId;
    private String name;
    private String email;
    private boolean isTagCompleted=false;

    public User(String userId, String name, String email, boolean isTagCompleted) {
        this.userId=userId;
        this.name = name;
        this.email = email;
        this.isTagCompleted = isTagCompleted;
    }

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isTagCompleted() {
        return isTagCompleted;
    }

    public void setTagCompleted(boolean tagCompleted) {
        isTagCompleted = tagCompleted;
    }
}
