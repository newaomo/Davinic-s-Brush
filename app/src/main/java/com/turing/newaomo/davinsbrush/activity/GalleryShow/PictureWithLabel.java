package com.turing.newaomo.davinsbrush.activity.GalleryShow;

/**
 * Created by newao on 2018/3/2.
 */

public class PictureWithLabel {

    private String colorId;         //颜色的数字表示
    private String colorName;       //颜色的名字
    private int imageId;
    private int styleName;

    public PictureWithLabel(){
    }

    public PictureWithLabel(String colorId,String colorName){
        this.colorId = colorId;
        this.colorName = colorName;
    }

    public PictureWithLabel(int imageId){
        this.imageId = imageId ;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getStyleName() {
        return styleName;
    }

    public void setStyleName(int styleName) {
        this.styleName = styleName;
    }
}
