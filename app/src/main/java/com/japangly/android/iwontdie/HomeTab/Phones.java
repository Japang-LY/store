package com.japangly.android.iwontdie.HomeTab;

public class Phones {

    private String name;
    private String price;
    private String description;
    private String imageUrl;

    public Phones() {
        // Default constructor required for calls to DataSnapshot
    }

    public Phones(String name, String price, String description, String imageUrl) {

        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getPrice() {

        return price;
    }

    public void setPrice(String price) {

        this.price = price;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
