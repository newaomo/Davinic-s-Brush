package com.turing.newaomo.davinsbrush.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.beans.CustomSelectItem;

import java.util.List;

/**
 * Created by newao on 2018/2/6.
 */

public class CustomSelectAdapter extends BaseAdapter {

    private List<CustomSelectItem> data;
    private LayoutInflater layoutInflater;
    private Context context;

    int mSelect = -1;

    public void changeSelected(int positon){ //刷新方法
        if(positon != mSelect){
            mSelect = positon;
            notifyDataSetChanged();
        }
    }

    public CustomSelectAdapter(Context context, List<CustomSelectItem> data){
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        convertView = layoutInflater.inflate(R.layout.item_custom_select,parent,false);
        viewHolder = new ViewHolder();
        viewHolder.imageViewCustomSelect = (ImageView)convertView.findViewById(R.id.item_image_custom_select);
        CustomSelectItem customSelectItem = data.get(position);
        viewHolder.imageViewCustomSelect.setImageResource(customSelectItem.getImageId());


        if (mSelect == position) {
            Animation testAnim = AnimationUtils.loadAnimation(context, R.anim.anim);
            viewHolder.imageViewCustomSelect.startAnimation(testAnim);
        } else {
            // the rest
            viewHolder.imageViewCustomSelect.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }

//        if(mSelect==position){
//            convertView.setBackgroundColor(Color.parseColor("#f60025"));//选中项背景
//        }else{
//            //其它项背景
//        }

        return convertView;
    }

    private class ViewHolder{
        ImageView imageViewCustomSelect;
    }

}
