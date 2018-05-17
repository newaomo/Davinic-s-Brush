package com.turing.newaomo.davinsbrush.activity.gen;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.adapter.SharePictureAdapter;
import com.turing.newaomo.davinsbrush.beans.ShareItem;
import com.turing.newaomo.davinsbrush.utils.share.MyShareUtil;
import com.turing.newaomo.davinsbrush.utils.share.PictureSaveUtil;
import com.turing.newaomo.davinsbrush.view.photoview.Info;
import com.turing.newaomo.davinsbrush.view.photoview.PhotoView;
import com.turing.newaomo.davinsbrush.view.step.StepperIndicator;
import com.turing.newaomo.davinsbrush.view.wave.WaveDrawable;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.widget.HorizontalListView;

public class GenerateNewsActivity extends AppCompatActivity {
    Bitmap imageResult = null;      //图像结果
    private HorizontalListView listViewShare;   //分享listview
    private SharePictureAdapter sharePictureAdapter;
    private List<ShareItem> mData;

    private ImageView imageViewWave;   //水波效果
    private WaveDrawable mWaveDrawable; //同上
    StepperIndicator indicator;     //分步骤控件
    private TextView textViewStep1;
    private TextView textViewStep2;
    private TextView textViewStep3;
    private TextView textViewStep4;
    private PhotoView imageViewResult;  //图片结果大图
    private Button button;
    View mParent;   //包含bg和预览imageView的一个layout
    View mBg;
    private PhotoView mPhotoView;
    Info mInfo;
    AlphaAnimation in = new AlphaAnimation(0, 1);
    AlphaAnimation out = new AlphaAnimation(1, 0);

    private View linearButton;      //两个按钮和下面的listview
    private TextView btn_edit;
    private TextView btn_save;

    private static final int STEP_COMPLETED_0 = 0;
    private static final int STEP_COMPLETED_1 = 1;
    private static final int STEP_COMPLETED_2 = 2;
    private static final int STEP_COMPLETED_3 = 3;
    private static final int STEP_COMPLETED_4 = 4;

    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case STEP_COMPLETED_0:
                    changeStep1();
                    startStep2();
                    break;
                case STEP_COMPLETED_1:
                    changeStep2();
                    startStep3();
                    break;
                case STEP_COMPLETED_2:
                    changeStep3();
                    startStep4();
                    break;
                case STEP_COMPLETED_3:
                    changeStep4();
                    startStep5();
                    break;
                case STEP_COMPLETED_4:
                    changeStep5();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private Runnable mRunnable1 = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(STEP_COMPLETED_0);
        }
    };
    private Runnable mRunnable2 = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(STEP_COMPLETED_1);
        }
    };
    private Runnable mRunnable3 = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(STEP_COMPLETED_2);
        }
    };
    private Runnable mRunnable4 = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(STEP_COMPLETED_3);
        }
    };
    private Runnable mRunnable5 = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(STEP_COMPLETED_4);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_news);
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
        initView();
        initShare();
    }
    public void startStep1(){
        mHandler .postDelayed(mRunnable1, 3000); // 在Handler中执行子线程并延迟3s。
    }
    public void startStep2(){
        mHandler .postDelayed(mRunnable2, 3000); // 在Handler中执行子线程并延迟3s。
    }
    public void startStep3(){
        mHandler .postDelayed(mRunnable3, 3000); // 在Handler中执行子线程并延迟3s。
    }
    public void startStep4(){
        mHandler .postDelayed(mRunnable4, 3000); // 在Handler中执行子线程并延迟3s。
    }
    public void startStep5(){
        mHandler .postDelayed(mRunnable5, 3000); // 在Handler中执行子线程并延迟3s。
    }

    public void start(){
        Log.d("---","开始了");
        startStep1();
    }

    public void initView(){
        //TODO 这里先设置为不可见  等到加载图片结束后需要设置为可见状态
        linearButton = (View)findViewById(R.id.gen_news_linear_btn);
        linearButton.setVisibility(View.INVISIBLE);

        listViewShare = (HorizontalListView)findViewById(R.id.list_share) ;
        mPhotoView = (PhotoView)findViewById(R.id.image_news_result_preview);
        mPhotoView.enable();

        btn_edit = (TextView)findViewById(R.id.gen_news_btn_edit) ;
        btn_save = (TextView)findViewById(R.id.gen_news_btn_save) ;
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editImage();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveImage();
            }
        });

        textViewStep1 = (TextView)findViewById(R.id.gen_process_text1);
        textViewStep2 = (TextView)findViewById(R.id.gen_process_text2);
        textViewStep3 = (TextView)findViewById(R.id.gen_process_text3);
        textViewStep4 = (TextView)findViewById(R.id.gen_process_text4);
        imageViewResult = (PhotoView) findViewById(R.id.gen_process_result_image);
        textViewStep1.setVisibility(View.INVISIBLE);
        textViewStep2.setVisibility(View.INVISIBLE);
        textViewStep3.setVisibility(View.INVISIBLE);
        textViewStep4.setVisibility(View.INVISIBLE);
        imageViewWave = (ImageView)findViewById(R.id.gen_process_wave_image);
        mWaveDrawable = new WaveDrawable(this, R.drawable.vw_ic_ppt);
        imageViewWave.setImageDrawable(mWaveDrawable);
        mWaveDrawable.setIndeterminate(false);
//        mWaveDrawable.setLevel();   设置完成的百分比
//        mWaveDrawable.setWaveAmplitude();设置波浪的弯曲度
//        mWaveDrawable.setWaveLength();   设置波浪的长度
        mWaveDrawable.setWaveSpeed(15);  //设置水波涌动的速度
        indicator = (StepperIndicator) findViewById(R.id.stepper_indicator);
        indicator.setCurrentStep(0);
        button = (Button)findViewById(R.id.btn_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });
        //小图的点击事件（弹出大图）
        imageViewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoView p = (PhotoView) imageViewResult;
                mInfo = p.getInfo();
                Bitmap image = ((BitmapDrawable)imageViewResult.getDrawable()).getBitmap();
                mPhotoView.setImageBitmap(image);
                mBg.startAnimation(in);
                mBg.setVisibility(View.VISIBLE);
                mParent.setVisibility(View.VISIBLE);;
                mPhotoView.animaFrom(mInfo);
            }
        });
        //下面是图片预览的一些配置
        mParent = findViewById(R.id.parent);
        mBg = findViewById(R.id.image_bg);
        mPhotoView = (PhotoView) findViewById(R.id.image_news_result_preview);
        mPhotoView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
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

//    Bitmap image = ((BitmapDrawable)imageViewResult.getDrawable()).getBitmap();
//    MyShareUtil myShareUtil = new MyShareUtil(GenerateNewsActivity.this);
//                myShareUtil.shareImageToQQ(GenerateNewsActivity.this,image);


    public void changeStep1(){
        textViewStep1.setVisibility(View.VISIBLE);
        indicator.setCurrentStep(0);
    }

    public void changeStep2(){
        textViewStep1.setText("图片、文件上传成功");
        textViewStep2.setVisibility(View.VISIBLE);
        indicator.setCurrentStep(1);
    }

    public void changeStep3(){
        textViewStep2.setText("服务器生成成功");
        textViewStep3.setVisibility(View.VISIBLE);
        indicator.setCurrentStep(2);
    }

    public void changeStep4(){
        textViewStep3.setText("服务器精修成功");
        textViewStep4.setVisibility(View.VISIBLE);
        indicator.setCurrentStep(3);
    }

    public void changeStep5(){
        imageViewResult.setImageResource(R.drawable.girl);
        textViewStep4.setText("下载成功");
        indicator.setCurrentStep(4);    //生成完成
        PhotoView p = (PhotoView) imageViewResult;
        mInfo = p.getInfo();
        imageResult = ((BitmapDrawable)imageViewResult.getDrawable()).getBitmap();
        mPhotoView.setImageBitmap(imageResult);
        mBg.startAnimation(in);
        mBg.setVisibility(View.VISIBLE);
        mParent.setVisibility(View.VISIBLE);;
        mPhotoView.animaFrom(mInfo);
        linearButton.setVisibility(View.VISIBLE);
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

    public void saveImage(){
        if (imageViewResult == null){
            Toast.makeText(GenerateNewsActivity.this,"下载出了问题",Toast.LENGTH_SHORT).show();
        }else {
            PictureSaveUtil.getInstance(GenerateNewsActivity.this).saveImageToGallery(imageResult);
        }
    }

    public void  editImage(){

    }

    public void initShare(){
        mData = new ArrayList<ShareItem>();
        ShareItem shareItem1 = new ShareItem(R.drawable.vw_ic_ppt,"QQ");
        mData.add(shareItem1);
        ShareItem shareItem2 = new ShareItem(R.drawable.vw_ic_ppt,"微信");
        mData.add(shareItem2);
        ShareItem shareItem3 = new ShareItem(R.drawable.vw_ic_ppt,"微博");
        mData.add(shareItem3);
        ShareItem shareItem4 = new ShareItem(R.drawable.vw_ic_ppt,"FaceBook");
        mData.add(shareItem4);
        ShareItem shareItem5 = new ShareItem(R.drawable.vw_ic_ppt,"其他");
        mData.add(shareItem5);
        sharePictureAdapter = new SharePictureAdapter(this,mData);
        listViewShare.setAdapter(sharePictureAdapter);
        listViewShare.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        MyShareUtil.getInstance(GenerateNewsActivity.this).shareImageToQQ(GenerateNewsActivity.this,imageResult);
                        break;
                    case 1:
                        MyShareUtil.getInstance(GenerateNewsActivity.this).shareImageToWeixin(GenerateNewsActivity.this,imageResult);
                        break;
                    case 2:
                        MyShareUtil.getInstance(GenerateNewsActivity.this).shareImageToWeibo(GenerateNewsActivity.this,imageResult);
                        break;
                    case 3:
                        MyShareUtil.getInstance(GenerateNewsActivity.this).shareImageToFaceBook(GenerateNewsActivity.this,imageResult);
                        break;
                    case 4:
                        MyShareUtil.getInstance(GenerateNewsActivity.this).shareImageToOther(GenerateNewsActivity.this,imageResult);
                        break;

                }
            }
        });
    }

}
