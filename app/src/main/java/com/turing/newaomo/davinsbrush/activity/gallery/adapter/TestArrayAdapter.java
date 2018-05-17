package com.turing.newaomo.davinsbrush.activity.gallery.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by newao on 2018/4/19.
 */

public class TestArrayAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private String [] mStringArray;
    public TestArrayAdapter(Context context, String[] stringArray) {
        super(context, android.R.layout.simple_spinner_item, stringArray);
        mContext = context;
        mStringArray=stringArray;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        //修改Spinner展开后的字体颜色
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent,false);
        }

        //此处text1是Spinner默认的用来显示文字的TextView
        TextView tv = (TextView) convertView.findViewById(android.R.id.text1);

        ViewGroup.LayoutParams linearParams = tv.getLayoutParams();
        linearParams.height = 120;

        tv.setText(mStringArray[position]);
        tv.setTextSize(20f);
        tv.setTextColor(Color.GRAY);
        tv.setLayoutParams(linearParams);


        return convertView;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 修改Spinner选择后结果的字体颜色
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        //此处text1是Spinner默认的用来显示文字的TextView
        TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
        tv.setText(mStringArray[position]);
        tv.setTextSize(30f);
        tv.setTextColor(Color.GRAY);
        return convertView;
    }

}

