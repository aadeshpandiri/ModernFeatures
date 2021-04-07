package com.example.myappnew;


public class Uploads {
    private String UploadsName;
    private String ImageUrl;

    public Uploads(String UploadsName, String imageUrl) {
        UploadsName = UploadsName;
        ImageUrl = imageUrl;
    }

    public Uploads() {
    }

    public String getUploadsName() {
        return UploadsName;
    }

    public void setUploadsName(String UploadsName) {
        UploadsName = UploadsName;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}



