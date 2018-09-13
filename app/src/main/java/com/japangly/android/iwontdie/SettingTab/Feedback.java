package com.japangly.android.iwontdie.SettingTab;

public class Feedback {

    private String userId;
    private String username;
    private String message;

    public Feedback(String id, String username, String message) {

        this.userId = userId;
        this.username = username;
        this.message = message;
    }

    public String getuserId() {

        return userId;
    }

    public String getUsername() {

        return username;
    }

    public String getMessage() {

        return message;
    }
}
