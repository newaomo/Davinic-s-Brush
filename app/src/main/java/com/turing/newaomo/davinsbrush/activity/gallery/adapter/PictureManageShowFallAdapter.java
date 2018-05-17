package com.turing.newaomo.davinsbrush.activity.gallery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.turing.newaomo.davinsbrush.R;

import java.util.List;

/**
 * Created by newao on 2018/3/2.
 */

public class PictureManageShowFallAdapter extends RecyclerView.Adapter   {
    ImageView imageView;



    private Context mContext;
//    private List<Integer> mData; //定义数据源
    private List<String> mData;

    private OnMyItemClickListener listener;
    public void setOnMyItemClickListener(OnMyItemClickListener listener){
        this.listener = listener;

    }
    public interface OnMyItemClickListener{
        void myClick(View v, int pos);
        void mLongClick(View v, int pos);
    }

    //定义构造方法，默认传入上下文和数据源
    public PictureManageShowFallAdapter(Context context, List<String> data) {
        mContext = context;
        mData = data;
    }

    @Override  //将ItemView渲染进来，创建ViewHolder
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_manage_show_picture, null);
        return new MyViewHolder(view);
    }

    @Override  //将数据源的数据绑定到相应控件上
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder holder2 = (MyViewHolder) holder;
//        int m  = mData.get(position);
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(mContext));


        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnFail(R.drawable.spinner_drawable)
                .showImageForEmptyUri(R.drawable.ic_news_empty)
                .showImageOnLoading(R.drawable.ic_loading).build();
        String path  = mData.get(position);
        ImageLoader.getInstance().displayImage("file://" + path, holder2.itemImage, options);

//        BitmapDrawable bitmapDrawable = (BitmapDrawable) mContext.getResources().getDrawable(m);
//        Bitmap BitmapOrg = bitmapDrawable.getBitmap();
//        int width = BitmapOrg.getWidth();
//        int height = BitmapOrg.getHeight();
//        int newWidth = 140;
//        int newHeight = 200;
//
//        float scaleWidth = ((float) newWidth) / width;
//        float scaleHeight = ((float) newHeight) / height;
//
//        Matrix matrix = new Matrix();
//        matrix.postScale(scaleWidth, scaleHeight);
//        // if you want to rotate the Bitmap
//        // matrix.postRotate(45);
//        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
//                height, matrix, true);


        Glide.with(mContext)
                .load(path)
                .into((ImageView) holder2.itemImage);


//        holder2.itemImage.setImageBitmap(resizedBitmap);
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


        public MyViewHolder(View itemView) {
            super(itemView);
            itemImage = (SimpleDraweeView) itemView.findViewById(R.id.item_image_picture_manage);
        }
    }
}
