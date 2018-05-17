package com.turing.newaomo.davinsbrush.colorUi.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.turing.newaomo.davinsbrush.colorUi.ColorUiInterface;
import com.turing.newaomo.davinsbrush.colorUi.util.ViewAttributeUtil;


public class ColorLinerLayout extends LinearLayout implements ColorUiInterface {

    private int attr_backgound = -1;

    public ColorLinerLayout(Context context) {
        super(context);
    }

    public ColorLinerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attr_backgound = ViewAttributeUtil.getBackgroundAttibute(attrs);
    }

    public ColorLinerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attr_backgound = ViewAttributeUtil.getBackgroundAttibute(attrs);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {
        if(attr_backgound != -1) {
            ViewAttributeUtil.applyBackgroundDrawable(this, themeId, attr_backgound);
        }
    }
}
