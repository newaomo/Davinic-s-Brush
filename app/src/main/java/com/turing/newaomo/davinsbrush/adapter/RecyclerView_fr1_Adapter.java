package com.turing.newaomo.davinsbrush.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.beans.Picture_1;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView_fr1_Adapter extends RecyclerView.Adapter<RecyclerView_fr1_Adapter.PictureViewHolder>{

    private List<Picture_1> picture_1s;
    private Context context;

    public RecyclerView_fr1_Adapter(List<Picture_1> picture_1s, Context context) {
        this.picture_1s = picture_1s;
        this.context=context;
    }


    //自定义ViewHolder类
    static class PictureViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView imageView;


        public PictureViewHolder(final View itemView) {
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.card_view);
            imageView= (ImageView) itemView.findViewById(R.id.fragment1_picture_item);
            //设置TextView背景为半透明
//            news_title.setBackgroundColor(Color.argb(20, 0, 0, 0))
        }


    }
    @Override
    public RecyclerView_fr1_Adapter.PictureViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.fragment_1_picture_item,viewGroup,false);
        PictureViewHolder nvh=new PictureViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(RecyclerView_fr1_Adapter.PictureViewHolder personViewHolder, int i) {
        final int j=i;

        personViewHolder.imageView.setImageResource(picture_1s.get(i).getPictureId());

        //为btn_share btn_readMore cardView设置点击事件
        personViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 这里应该是一个查看图片的活动
                Intent intent=new Intent(context,Picture_1.class);
                intent.putExtra("photoId",picture_1s.get(j));
                context.startActivity(intent);
            }
        });

        //TODO  这是分享的一种方式
//        personViewHolder.share.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(Intent.ACTION_SEND);
//                intent.setType("text/plain");
//                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
//                intent.putExtra(Intent.EXTRA_TEXT, newses.get(j).getDesc());
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(Intent.createChooser(intent, newses.get(j).getTitle()));
//            }
//        });

    }

    public void setDataList(List<Picture_1> datas) {
        picture_1s = new ArrayList<>();
        picture_1s.clear();
        if (null != datas) {
            picture_1s.addAll(datas);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return picture_1s.size();
    }
}