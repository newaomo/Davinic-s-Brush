package com.turing.newaomo.davinsbrush.activity.gallery.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.turing.newaomo.davinsbrush.R;

import java.util.List;

/**
 * Created by newao on 2018/4/24.
 */

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.MasonryView>{
    private List<String> products;


    private OnMyItemClickListener listener;
    public void setOnMyItemClickListener(OnMyItemClickListener listener){
        this.listener = listener;

    }

    public interface OnMyItemClickListener{
        void myClick(View v, int pos);
        void mLongClick(View v, int pos);
    }

    public ColorAdapter(List<String> list) {
        products=list;
    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_select_color, viewGroup, false);
        return new MasonryView(view);
    }

    @Override
    public void onBindViewHolder(MasonryView masonryView, final int position) {
        Log.d("需要解析的颜色是",products.get(position));
        masonryView.imageView.setBackgroundColor(Color.parseColor(products.get(position)));
        if (listener!=null) {
             masonryView.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.myClick(v,position);
                }
            });
            // set LongClick
            masonryView.imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.mLongClick(v,position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class MasonryView extends  RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public MasonryView(View itemView){
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.color_select_item );

        }

    }

}