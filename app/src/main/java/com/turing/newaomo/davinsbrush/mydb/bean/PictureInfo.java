package com.turing.newaomo.davinsbrush.mydb.bean;

import com.turing.newaomo.davinsbrush.mydb.StringConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

import java.util.List;

/**
 * Created by newao on 2018/4/16.
 */

@Entity
public class PictureInfo {
    private Long id;
    private String style;
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> colors;
    private String color1;
    private String color2;
    private String color3;
    private String info;
    private String path;

    @Generated(hash = 1001300316)
    public PictureInfo(Long id, String style, List<String> colors, String color1,
            String color2, String color3, String info, String path) {
        this.id = id;
        this.style = style;
        this.colors = colors;
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.info = info;
        this.path = path;
    }

    @Generated(hash = 671166560)
    public PictureInfo() {
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public String getColor3() {
        return color3;
    }

    public void setColor3(String color3) {
        this.color3 = color3;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
