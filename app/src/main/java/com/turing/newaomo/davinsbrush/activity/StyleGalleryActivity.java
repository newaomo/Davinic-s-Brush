package com.turing.newaomo.davinsbrush.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.view.photoview.Info;
import com.turing.newaomo.davinsbrush.view.photoview.PhotoView;

import mlxy.utils.T;

public class StyleGalleryActivity extends AppCompatActivity {


    GridView gv;

    View mParent;
    View mBg;
    PhotoView mPhotoView;
    Info mInfo;
    String[] imgs = new String[]{
      "/storage/emulated/0/tencent/QWallet/578552861/preload/5FA2C8751A490BA3CF89F2F49447E85AFolder/0003.png",
            "/storage/emulated/0/tencent/QWallet/578552861/preload/5FA2C8751A490BA3CF89F2F49447E85AFolder/0003.png",
            "/storage/emulated/0/tencent/QWallet/578552861/preload/5FA2C8751A490BA3CF89F2F49447E85AFolder/0003.png",
            "/storage/emulated/0/tencent/QWallet/578552861/preload/5FA2C8751A490BA3CF89F2F49447E85AFolder/0003.png",
            "/storage/emulated/0/tencent/QWallet/578552861/preload/5FA2C8751A490BA3CF89F2F49447E85AFolder/0003.png",
            "/storage/emulated/0/tencent/QWallet/578552861/preload/5FA2C8751A490BA3CF89F2F49447E85AFolder/0003.png",
            "/storage/emulated/0/tencent/QWallet/578552861/preload/5FA2C8751A490BA3CF89F2F49447E85AFolder/0003.png",
            "/storage/emulated/0/tencent/QWallet/578552861/preload/5FA2C8751A490BA3CF89F2F49447E85AFolder/0003.png"
    };

    AlphaAnimation in = new AlphaAnimation(0, 1);
    AlphaAnimation out = new AlphaAnimation(1, 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_style_gallery);

        in.setDuration(300);
        out.setDuration(300);
        out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mBg.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mParent = findViewById(R.id.parent);
        mBg = findViewById(R.id.bg);
        mPhotoView = (PhotoView) findViewById(R.id.img);
        mPhotoView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        gv = (GridView) findViewById(R.id.gv);
        gv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return imgs.length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                PhotoView p = new PhotoView(StyleGalleryActivity.this);
                p.setLayoutParams(new AbsListView.LayoutParams((int) (getResources().getDisplayMetrics().density * 100), (int) (getResources().getDisplayMetrics().density * 100)));
                p.setScaleType(ImageView.ScaleType.CENTER_CROP);

                Bitmap bmpDefaultPic ;
                bmpDefaultPic = BitmapFactory.decodeFile(imgs[position],null);
                p.setImageBitmap(bmpDefaultPic);

//                p.setImageResource(imgs[position]);
                // 把PhotoView当普通的控件把触摸功能关掉
                p.disenable();
                return p;
            }
        });
        gv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                T.showShort(StyleGalleryActivity.this,"您点胡可"+position);
                return true;
            }
        });

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhotoView p = (PhotoView) view;
                mInfo = p.getInfo();

                Bitmap bmpDefaultPic ;
                bmpDefaultPic = BitmapFactory.decodeFile(imgs[position],null);
                mPhotoView.setImageBitmap(bmpDefaultPic);

//                mPhotoView.setImageResource(imgs[position]);
                mBg.startAnimation(in);
                mBg.setVisibility(View.VISIBLE);
                mParent.setVisibility(View.VISIBLE);;
                mPhotoView.animaFrom(mInfo);
            }
        });

        mPhotoView.enable();
        mPhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBg.startAnimation(out);
                mPhotoView.animaTo(mInfo, new Runnable() {
                    @Override
                    public void run() {
                        mParent.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mParent.getVisibility() == View.VISIBLE) {
            mBg.startAnimation(out);
            mPhotoView.animaTo(mInfo, new Runnable() {
                @Override
                public void run() {
                    mParent.setVisibility(View.GONE);
                }
            });
        } else {
            super.onBackPressed();
        }
    }
}