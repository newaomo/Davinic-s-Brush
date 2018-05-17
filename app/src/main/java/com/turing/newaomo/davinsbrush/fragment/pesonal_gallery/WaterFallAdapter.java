package com.turing.newaomo.davinsbrush.fragment.pesonal_gallery;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.turing.newaomo.davinsbrush.R;

import java.util.List;

public class WaterFallAdapter extends RecyclerView.Adapter   {

    private Context mContext;
    private List<ItemClassify> mData; //定义数据源

    private OnMyItemClickListener listener;
    public void setOnMyItemClickListener(OnMyItemClickListener listener){
        this.listener = listener;

    }
    public interface OnMyItemClickListener{
        void myClick(View v, int pos);
        void mLongClick(View v, int pos);
    }

    //定义构造方法，默认传入上下文和数据源
    public WaterFallAdapter(Context context, List<ItemClassify> data) {
        mContext = context;
        mData = data;
    }

    @Override  //将ItemView渲染进来，创建ViewHolder
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item, null);
        return new MyViewHolder(view);
    }

    @Override  //将数据源的数据绑定到相应控件上
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder holder2 = (MyViewHolder) holder;
        ItemClassify itemClassify = mData.get(position);
        Uri uri = Uri.parse("file://" +itemClassify.imagePath);
        holder2.itemImage.setImageURI(uri);
//        holder2.itemImage.setImageURI(itemClassify.imagePath);
        holder2.itemImage.getLayoutParams().height = itemClassify.imgHeight;
        holder2.itemDescribe.setText(itemClassify.describe);
        holder2.itemNumber.setText(itemClassify.countNumber);


        if (listener!=null) {
            ((MyViewHolder) holder).itemImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.myClick(v,position);
                }
            });
            // set LongClick
            ((MyViewHolder) holder).itemImage.setOnLongClickListener(new View.OnLongClickListener() {
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
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }



    //定义自己的ViewHolder，将View的控件引用在成员变量上
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView itemImage;
        public TextView itemDescribe;
        public TextView itemNumber;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemImage = (SimpleDraweeView) itemView.findViewById(R.id.item_image_gallery);
            itemDescribe = (TextView) itemView.findViewById(R.id.item_describe);
            itemNumber = (TextView)itemView.findViewById(R.id.item_number);
        }
    }
}
