package com.turing.newaomo.davinsbrush.beans;



/**
 * Created by newao on 2018/2/5.
 */

public class ShareItem {

    private String text;
    private int imageId;

    public ShareItem(){

    }
    public ShareItem(int imageId ,String text){
        this.text = text;
        this.imageId= imageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
