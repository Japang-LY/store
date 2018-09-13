package com.japangly.android.iwontdie.SettingTab;

public class SettingOption {

    private String title;
    private String subTitle;
    private int image;

    public SettingOption(String title, String subTitle, int image) {

        this.title = title;
        this.subTitle = subTitle;
        this.image = image;
    }

    public String getTitle() {

        return title;
    }

    public String getSubTitle() {

        return subTitle;
    }

    public int getImage() {

        return image;
    }
}
