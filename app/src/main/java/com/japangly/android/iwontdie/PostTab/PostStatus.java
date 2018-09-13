package com.japangly.android.iwontdie.PostTab;

public class PostStatus {

    private String postStatus;
    private String imageUrl;
    private String postUser;
    private String postTime;

    public PostStatus() {
        // Default constructor required for calls to DataSnapshot.getValue(PostStatus.class)
    }

    public PostStatus(String postStatus, String imageUrl, String postUser, String postTime) {

        this.postStatus = postStatus;
        this.imageUrl = imageUrl;
        this.postUser = postUser;
        this.postTime = postTime;
    }

    public String getPostStatus() {

        return postStatus;
    }

    public void setPostStatus(String postStatus) {

        this.postStatus = postStatus;
    }

    public String getImageUrl() {

        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {

        this.imageUrl = imageUrl;
    }

    public String getPostUser() {

        return postUser;
    }

    public void setPostUser(String postUser) {

        this.postUser = postUser;
    }

    public String getPostTime() {

        return postTime;
    }

    public void setPostTime(String postTime) {

        this.postTime = postTime;
    }
}
