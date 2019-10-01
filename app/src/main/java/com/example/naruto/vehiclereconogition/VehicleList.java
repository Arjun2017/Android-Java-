package com.example.naruto.vehiclereconogition;

public class VehicleList {
    private int imgResource;
    private String text;

    public VehicleList(int imgResource, String text) {
        this.imgResource = imgResource;
        this.text = text;
    }


    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
