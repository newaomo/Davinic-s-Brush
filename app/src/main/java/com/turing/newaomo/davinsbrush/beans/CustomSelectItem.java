package com.turing.newaomo.davinsbrush.beans;

/**
 * Created by newao on 2018/2/6.
 */

public class CustomSelectItem {

    private int imageId;
    private String text;

    public CustomSelectItem(){

    }
    public CustomSelectItem(int imageId,String text){
        this.imageId = imageId;
        this.text = text;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
