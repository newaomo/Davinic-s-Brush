package com.turing.newaomo.davinsbrush.view;

import android.view.View;

import com.turing.newaomo.davinsbrush.R;

/**
 * Created by newao on 2018/2/7.
 */

public class ToolBarOption {
    /**
     * 图标资源
     */
    private int logoId = R.mipmap.ic_launcher ;
    /**
     * 标题
     */
    private String title;


    private int titleId = 0;


    private String avatar;
    private int rightResId = 0;


    public int getRightResId() {
        return rightResId;
    }

    public void setRightResId(int rightResId) {
        this.rightResId = rightResId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 返回按钮图片资源
     */
    private int navigateId = R.drawable.ic_arrow_back_grey_900_24dp;
    private boolean isNeedNavigation = true;


    private String rightText;
    private View.OnClickListener rightListener;

    public View.OnClickListener getRightListener() {
        return rightListener;
    }

    public void setRightListener(View.OnClickListener rightListener) {
        this.rightListener = rightListener;
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }

    public int getLogoId() {
        return logoId;
    }

    public void setLogoId(int logoId) {
        this.logoId = logoId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public int getNavigateId() {
        return navigateId;
    }

    public void setNavigateId(int navigateId) {
        this.navigateId = navigateId;
    }

    public boolean isNeedNavigation() {
        return isNeedNavigation;
    }

    public void setNeedNavigation(boolean needNavigation) {
        isNeedNavigation = needNavigation;
    }
}

