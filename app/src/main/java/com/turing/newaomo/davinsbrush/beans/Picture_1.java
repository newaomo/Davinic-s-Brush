package com.turing.newaomo.davinsbrush.beans;

import java.io.Serializable;

/**
 * Created by newao on 2018/2/2.
 */

public class Picture_1 implements Serializable {

    private String title;
    private String content;
    private int pictureId;
    private String url;

    public Picture_1(){
    }

    public Picture_1(String title,String content,int pictureId,String url){
        this.title = title;
        this.content = content;
        this.pictureId = pictureId;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
