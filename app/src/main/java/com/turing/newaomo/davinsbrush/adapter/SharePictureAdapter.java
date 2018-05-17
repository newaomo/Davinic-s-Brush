package com.turing.newaomo.davinsbrush.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.beans.ShareItem;

import java.util.List;

/**
 * Created by newao on 2018/2/5.
 */

public class SharePictureAdapter extends BaseAdapter {

    private List<ShareItem> data;
    private LayoutInflater layoutInflater;
    private Context context;

    public SharePictureAdapter(Context context, List<ShareItem> data){
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
        convertView = layoutInflater.inflate(R.layout.item_share,parent,false);
        viewHolder = new ViewHolder();
        viewHolder.imageViewShare = (ImageView) convertView.findViewById(R.id.item_image_share);
        viewHolder.textViewShare = (TextView)convertView.findViewById(R.id.item_text_share);
        ShareItem shareItem = data.get(position);
        viewHolder.imageViewShare.setImageResource(shareItem.getImageId());
        viewHolder.textViewShare.setText(shareItem.getText());
        return convertView;
    }

    private class ViewHolder{
        ImageView imageViewShare;
        TextView textViewShare;
    }

}
