package com.turing.newaomo.davinsbrush.activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.beans.ShowMainItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by newao on 2018/2/26.
 */

public class ShowPostMainAdapter extends RecyclerView.Adapter   {

    private Context mContext;
    private List<ShowMainItem> mData; //定义数据源

    private OnMyItemClickListener listener;
    public void setOnMyItemClickListener(OnMyItemClickListener listener){
        this.listener = listener;

    }
    public interface OnMyItemClickListener{
        void myClick(View v, int pos);
        void mLongClick(View v, int pos);
    }

    //定义构造方法，默认传入上下文和数据源
    public ShowPostMainAdapter(Context context, List<ShowMainItem> data) {
        mContext = context;
        mData = data;
    }

    @Override  //将ItemView渲染进来，创建ViewHolder
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_post_show_main, null);
        return new MyViewHolder(view);
    }

    public static Drawable resizeImage(Bitmap bitmap, int w, int h)
    {
        Bitmap BitmapOrg = bitmap;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = w;
        int newHeight = h;

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // if you want to rotate the Bitmap
        // matrix.postRotate(45);
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
                height, matrix, true);
        return new BitmapDrawable(resizedBitmap);
    }

    @Override  //将数据源的数据绑定到相应控件上
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder holder2 = (MyViewHolder) holder;
        ShowMainItem showMainItem = mData.get(position);
        Uri uri = Uri.parse("file://" +showMainItem.imagePath);
        Resources resources = mContext.getResources();
        switch (position){
            case 0:
                Bitmap bmp= BitmapFactory.decodeResource(resources, R.drawable.show_poster_main1);
                holder2.itemImage.setImageDrawable(resizeImage(bmp,360,657));
                break;
            case 1:
                Bitmap bmp1= BitmapFactory.decodeResource(resources, R.drawable.show_poster_main2);
                holder2.itemImage.setImageDrawable(resizeImage(bmp1,360,657));
                break;
            case 2:
                Bitmap bmp2= BitmapFactory.decodeResource(resources, R.drawable.show_poster_main3);
                holder2.itemImage.setImageDrawable(resizeImage(bmp2,360,657));
                break;
            case 3:
                Bitmap bmp3= BitmapFactory.decodeResource(resources, R.drawable.show_poster_main8);
                holder2.itemImage.setImageDrawable(resizeImage(bmp3,360,657));
                break;
            case 4:
                Bitmap bmp4= BitmapFactory.decodeResource(resources, R.drawable.show_poster_main9);
                holder2.itemImage.setImageDrawable(resizeImage(bmp4,360,657));
                break;
            case 5:
                Bitmap bmp5= BitmapFactory.decodeResource(resources, R.drawable.show_poster_main7);
                holder2.itemImage.setImageDrawable(resizeImage(bmp5,360,657));
                break;
            case 6:
                Bitmap bmp6= BitmapFactory.decodeResource(resources, R.drawable.show_poster_main11);
                holder2.itemImage.setImageDrawable(resizeImage(bmp6,360,657));
                break;
            case 7:
                Bitmap bmp7= BitmapFactory.decodeResource(resources, R.drawable.show_poster_main12);
                holder2.itemImage.setImageDrawable(resizeImage(bmp7,360,657));
                break;
            case 8:
                Bitmap bmp8 = BitmapFactory.decodeResource(resources, R.drawable.show_poster_main13);
                holder2.itemImage.setImageDrawable(resizeImage(bmp8,360,657));
                break;
            case 9:
                Bitmap bmp9= BitmapFactory.decodeResource(resources, R.drawable.show_poster_main10);
                holder2.itemImage.setImageDrawable(resizeImage(bmp9,360,657));
                break;
            case 10:
                Bitmap bmp10= BitmapFactory.decodeResource(resources, R.drawable.show_poster_main5);
                holder2.itemImage.setImageDrawable(resizeImage(bmp10,360,657));
                break;
            case 11:
                Bitmap bmp11= BitmapFactory.decodeResource(resources, R.drawable.show_poster_main4);
                holder2.itemImage.setImageDrawable(resizeImage(bmp11,360,657));
                break;
            default:
                Bitmap bmp0= BitmapFactory.decodeResource(resources, R.drawable.fragment1_show_1);
                holder2.itemImage.setImageDrawable(resizeImage(bmp0,360,657));
                break;
        }

//        holder2.itemImage.setImageURI(uri);
////        holder2.itemImage.setImageURI(itemClassify.imagePath);
//        holder2.itemImage.getLayoutParams().height = showMainItem.imgHeight;



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

    public void setDataList(List<ShowMainItem> datas) {
        mData = new ArrayList<>();
        mData.clear();
        if (null != datas) {
            mData.addAll(datas);
        }
        notifyDataSetChanged();
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
            itemImage = (SimpleDraweeView) itemView.findViewById(R.id.show_post_main_image);
        }
    }
}
