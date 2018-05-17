package com.turing.newaomo.davinsbrush.activity.gen;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
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
import com.turing.newaomo.davinsbrush.network.API;
import com.turing.newaomo.davinsbrush.network.HttpParameterBuilder;
import com.turing.newaomo.davinsbrush.utils.share.MyShareUtil;
import com.turing.newaomo.davinsbrush.utils.share.PictureSaveUtil;
import com.turing.newaomo.davinsbrush.view.grade.GradeDialog;
import com.turing.newaomo.davinsbrush.view.grade.RatingBar;
import com.turing.newaomo.davinsbrush.view.photoview.Info;
import com.turing.newaomo.davinsbrush.view.photoview.PhotoView;
import com.turing.newaomo.davinsbrush.view.step.StepperIndicator;
import com.turing.newaomo.davinsbrush.view.wave.WaveDrawable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.finalteam.galleryfinal.widget.HorizontalListView;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;

public class GenerateArticleActivity extends AppCompatActivity {
    public static final MediaType JSON= MediaType.parse("application/json; charset=utf-8");
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
    private TextView textViewStep5;
    private PhotoView imageViewResult;  //图片结果大图
    View mParent;   //包含bg和预览imageView的一个layout
    View mBg;
    private PhotoView mPhotoView;
    Info mInfo;
    AlphaAnimation in = new AlphaAnimation(0, 1);
    AlphaAnimation out = new AlphaAnimation(1, 0);

    private View linearButton;      //两个按钮和下面的listview
    private Button btn_edit;
    private Button btn_save;
    private Button button_grade;

//    private ImageView imageView;

    private static final int STEP_COMPLETED_0 = 0;
    private static final int STEP_COMPLETED_1 = 1;
    private static final int STEP_COMPLETED_2 = 2;
    private static final int STEP_COMPLETED_3 = 3;
    private static final int STEP_COMPLETED_4 = 4;
    private static final int STEP_COMPLETED_5 = 5;
    private String imageUrl = "";

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            Bitmap bitmap = (Bitmap) msg.obj;
            try{
//                imageView.setImageBitmap(bitmap);
                imageViewResult.setImageBitmap(bitmap);
            }catch (Exception e){
                Log.d("-----",e.getMessage()+e.toString());
            }

        }
    };

    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case STEP_COMPLETED_0:
                    changeStep1();
                    break;
                case STEP_COMPLETED_1:
                    changeStep2();
                    break;
                case STEP_COMPLETED_2:
                    changeStep3();
                    break;
                case STEP_COMPLETED_3:
                    changeStep4();
                    break;
                case STEP_COMPLETED_4:
                    changeStep5();
                    break;
                case STEP_COMPLETED_5:
//                    imageViewResult.setImageResource(R.drawable.news_show);   //这是展示新闻时候设置的图片
                    imageViewResult.setImageResource(R.drawable.gaoshaoliushui);
                    PhotoView p = (PhotoView) imageViewResult;
                    mInfo = p.getInfo();
                    imageResult = ((BitmapDrawable)imageViewResult.getDrawable()).getBitmap();
                    mPhotoView.setImageBitmap(imageResult);
                    mBg.startAnimation(in);
                    mBg.setVisibility(View.VISIBLE);
                    mParent.setVisibility(View.VISIBLE);;
                    mPhotoView.animaFrom(mInfo);
                    linearButton.setVisibility(View.VISIBLE);
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
    private Runnable mRunnable6 = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(STEP_COMPLETED_5);
            textViewStep1.setVisibility(View.GONE);
            textViewStep2.setVisibility(View.GONE);
            textViewStep3.setVisibility(View.GONE);
            textViewStep4.setVisibility(View.GONE);
            textViewStep5.setVisibility(View.GONE);
            linearButton.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_article);
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
        mHandler .postDelayed(mRunnable1, 0); // 在Handler中执行子线程并延迟3s。
    }
    public void startStep2(){
        mHandler .postDelayed(mRunnable2, 0); // 在Handler中执行子线程并延迟3s。
    }
    public void startStep3(){
        mHandler .postDelayed(mRunnable3, 0); // 在Handler中执行子线程并延迟3s。
    }
    public void startStep4(){
        mHandler .postDelayed(mRunnable4, 0); // 在Handler中执行子线程并延迟3s。
    }
    public void startStep5(){
        mHandler .postDelayed(mRunnable5, 0); // 在Handler中执行子线程并延迟3s。
    }
    public void startStep6(){
        mHandler .postDelayed(mRunnable6, 0); // 在Handler中执行子线程并延迟3s。
    }



    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private static final MediaType MEDIA_TYPE_WORD = MediaType.parse("application/msword");


    private final OkHttpClient client = new OkHttpClient();
    //TODO 这里是将文件上传服务器

    public void upload2(){
        MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder();
        multipartBodyBuilder.setType(MultipartBody.FORM);
        multipartBodyBuilder.addFormDataPart("mode","03");
        multipartBodyBuilder.addFormDataPart("pictureSize","0");
        multipartBodyBuilder.addFormDataPart("phoneNumber","18340018870");
        multipartBodyBuilder.addFormDataPart("dateNumber","2018-02-18");
        multipartBodyBuilder.addFormDataPart("unifiedPattern","");
        multipartBodyBuilder.addFormDataPart("size_width","720");
        multipartBodyBuilder.addFormDataPart("size_height","1280");
        multipartBodyBuilder.addFormDataPart("title","");
        multipartBodyBuilder.addFormDataPart("slogan","");
        multipartBodyBuilder.addFormDataPart("content","");
        multipartBodyBuilder.addFormDataPart("style","");
        multipartBodyBuilder.addFormDataPart("profile_name","");
        multipartBodyBuilder.addFormDataPart("profile_honor","");
        multipartBodyBuilder.addFormDataPart("profile_motto","");


//        File docFile = new File("/storage/emulated/0/Download/QQMail/lalalala.docx");
        File docFile = new File("/storage/emulated/0/tencent/QQfile_recv/lalalala.docx");
//        RequestBody body = RequestBody.create(MediaType.parse("application/msword"), docFile);

        multipartBodyBuilder.addFormDataPart("file_doc_name","lalalala.docx", RequestBody.create(MediaType.parse("application/msword"), docFile));

//        File photo1 = new File("/storage/emulated/0/tencent/QWallet/578552861/preload/5FA2C8751A490BA3CF89F2F49447E85AFolder/0003.png");
//        multipartBodyBuilder.addFormDataPart("photo1","0001.png",RequestBody.create(MediaType.parse("image/png"), photo1));
//        File photo2 = new File("/storage/emulated/0/GalleryFinal/edittemp/open_screen_bg_img_1485_crop.png");
//        multipartBodyBuilder.addFormDataPart("photo2","0002.png",RequestBody.create(MediaType.parse("image/png"), photo2));
//        File photo3 = new File("/storage/emulated/0/tencent/QWallet/578552861/preload/5FA2C8751A490BA3CF89F2F49447E85AFolder/0003.png");
//        multipartBodyBuilder.addFormDataPart("photo3","0003.png",RequestBody.create(MediaType.parse("image/png"), photo3));
//        File photo4 = new File("/storage/emulated/0/GalleryFinal/edittemp/open_screen_bg_img_1485_crop.png");
//        multipartBodyBuilder.addFormDataPart("photo4","0004.png",RequestBody.create(MediaType.parse("image/png"), photo4));
//        File photo5 = new File("/storage/emulated/0/tencent/QWallet/578552861/preload/5FA2C8751A490BA3CF89F2F49447E85AFolder/0003.png");
//        multipartBodyBuilder.addFormDataPart("photo5","0005.png",RequestBody.create(MediaType.parse("image/png"), photo5));
//        File photo6 = new File("/storage/emulated/0/GalleryFinal/edittemp/open_screen_bg_img_1485_crop.png");
//        multipartBodyBuilder.addFormDataPart("photo6","0006.png",RequestBody.create(MediaType.parse("image/png"), photo6));
//        File photo7 = new File("/storage/emulated/0/tencent/QWallet/578552861/preload/5FA2C8751A490BA3CF89F2F49447E85AFolder/0003.png");
//        multipartBodyBuilder.addFormDataPart("photo7","0007.png",RequestBody.create(MediaType.parse("image/png"), photo7));
//        File photo8 = new File("/storage/emulated/0/GalleryFinal/edittemp/open_screen_bg_img_1485_crop.png");
//        multipartBodyBuilder.addFormDataPart("photo8","0008.png",RequestBody.create(MediaType.parse("image/png"), photo8));
//        File photo9 = new File("/storage/emulated/0/tencent/QWallet/578552861/preload/5FA2C8751A490BA3CF89F2F49447E85AFolder/0003.png");
//        multipartBodyBuilder.addFormDataPart("photo9","0009.png",RequestBody.create(MediaType.parse("image/png"), photo9));
//        File photo10 = new File("/storage/emulated/0/GalleryFinal/edittemp/open_screen_bg_img_1485_crop.png");
//        multipartBodyBuilder.addFormDataPart("photo10","00010.png",RequestBody.create(MediaType.parse("image/png"), photo10));
//构建请求体
        RequestBody requestBody = multipartBodyBuilder.build();

        OkHttpClient mOkHttpClient = new OkHttpClient.Builder().connectTimeout(300, TimeUnit.SECONDS)

                .readTimeout(300, TimeUnit.SECONDS)

                .build();

        Request.Builder RequestBuilder = new Request.Builder();
        RequestBuilder.url("http://ba7f3fd8.ngrok.io/mainserver");// 添加URL地址
        RequestBuilder.post(requestBody);

        Request request = RequestBuilder.
                addHeader("Connection", "Keep-Alive").
                addHeader("Charset", "UTF-8").
                addHeader("User-Agent","  Mozilla/5.0 (Windows; U; Windows NT 5.2) Gecko/2008070208 Firefox/3.0.1").
                addHeader("Accept","*/*")
                .build();

//        client.newBuilder().readTimeout(50000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(okhttp3.Call call, IOException e) {
//                call.cancel();
//                Log.d("-----","失败");
//                Log.d("-----",e.getMessage()+e.toString());
//            }
//
//            @Override
//            public void onResponse(okhttp3.Call call, Response response) throws IOException {
//                Log.d("-----","成功");
//                Log.d("-----",response.toString());
//                Log.d("-----",response.request().toString());
//                Log.d("-----",response.body().toString());
//                InputStream inputStream=response.body().byteStream();
//                int m  = inputStream.read();
//                Log.d("-----",m+"");
//                //将输入流数据转化为Bitmap位图数据
//                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
//                Message msg= Message.obtain();
//                msg.obj=bitmap;
//                handler.sendMessage(msg);
//            }
//
//
//        });

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                call.cancel();
                Log.d("-----","失败");
                Log.d("-----",e.getMessage()+e.toString());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

                //将响应数据转化为输入流数据
                InputStream inputStream=response.body().byteStream();
                //将输入流数据转化为Bitmap位图数据
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);


                Message msg= Message.obtain();
                msg.obj=bitmap;
                handler.sendMessage(msg);



            }

        });

    }

    public void upload(){
        Map<String, RequestBody> params = HttpParameterBuilder.newBuilder()
                .addParameter("mode","01")
                .addParameter("pictureSize","2")
                .addParameter("phoneNumber","18340018870")
                .addParameter("dateNumber","2018-02-18")
                .addParameter("unifiedPattern","01")
                .addParameter("size_width","1200")
                .addParameter("size_height","1920")
                .addParameter("title","这是title")
                .addParameter("slogan","这是slogan")
                .addParameter("content","这是content")
                .addParameter("style","01")
                .addParameter("profile_name","这是profile_name")
                .addParameter("profile_honor","这是profile_honor")
                .bulider();
        File docFile = new File("/storage/emulated/0/tencent/QQfile_recv/1_参赛项目计划书.docx");
        RequestBody body = RequestBody.create(MediaType.parse("application/msword"), docFile);
        params.put("file_name_doc",  body);
        File photo1 = new File("/storage/emulated/0/tencent/QWallet/578552861/preload/5FA2C8751A490BA3CF89F2F49447E85AFolder/0003.png");
        RequestBody body1 = RequestBody.create(MediaType.parse("image/*"), photo1);
        params.put("photo1",body1);
        File photo2 = new File("/storage/emulated/0/GalleryFinal/edittemp/open_screen_bg_img_1485_crop.png");
        RequestBody body2 = RequestBody.create(MediaType.parse("image/*"), photo2);
        params.put("photo2",body2);
        File photo3 = new File("/storage/emulated/0/tencent/QWallet/578552861/preload/5FA2C8751A490BA3CF89F2F49447E85AFolder/0003.png");
        RequestBody body3 = RequestBody.create(MediaType.parse("image/*"), photo3);
        params.put("photo3",body3);
        API.Retrofit().updateImage(params).enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {

//                Toast.makeText(GenerateArticleActivity.this,response.body().toString(),Toast.LENGTH_SHORT).show();
                if (response.body().toString() == null){
                    Log.d("-----","返回为null");

                }else {
                    imageUrl = response.body().toString();
                    Log.d("-----",response.body().toString());
                    setImage(imageUrl);

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(GenerateArticleActivity.this,call.toString(),Toast.LENGTH_SHORT).show();
                Log.d("-----","失败");
                Log.d("-----",call.toString());
                Log.d("-----",t.getMessage()+ "  "+t.toString());

            }
        });
    }



    public void start(){
        // mImgUrls为存放图片的url集合
        File file = new File("这是一个doc文件");
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("fileInfo","fileInfo", RequestBody.create(MEDIA_TYPE_PNG, file));
        //开始添加图片
        List<String> mImgUrls = new ArrayList<>();
        for (int i = 0; i <mImgUrls.size() ; i++) {
            File f=new File(mImgUrls.get(i));
            if (f!=null) {
                builder.addFormDataPart("img", f.getName(), RequestBody.create(MEDIA_TYPE_PNG, f));
            }
        }
        //TODO 这里可能还需要传过去手机号
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String reTime = format.format(new Date());
        builder.addFormDataPart("dateNumber",reTime);
        //添加其它信息
//        builder.addFormDataPart("time",takePicTime);
        MultipartBody requestBody = builder.build();
        //构建请求
        Request request = new Request.Builder()
                .url("")//地址
                .post(requestBody)//添加请求体
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                System.out.println("上传失败:e.getLocalizedMessage() = " + e.getLocalizedMessage());
            }
            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                System.out.println("上传照片成功：response = " + response.body().string());
            }
        });


//
//        Log.d("---","开始了");
//        startStep1();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && requestCode == BLBeautifyParam.REQUEST_CODE_BEAUTIFY_IMAGE) {
//            String filePath = data.getStringExtra("image_show_result");
//            Bitmap bitmap= BitmapFactory.decodeFile(filePath);
//            imageViewResult.setImageBitmap(bitmap);
//            imageResult = bitmap;
//        }
    }

    public void initView(){
        //TODO 这里先设置为不可见  等到加载图片结束后需要设置为可见状态
        linearButton = (View)findViewById(R.id.gen_article_linear_btn);
        linearButton.setVisibility(View.INVISIBLE);

        listViewShare = (HorizontalListView)findViewById(R.id.list_share) ;
        mPhotoView = (PhotoView)findViewById(R.id.image_article_result_preview);
        mPhotoView.enable();

        btn_edit = (Button)findViewById(R.id.gen_article_btn_edit) ;
        btn_save = (Button)findViewById(R.id.gen_article_btn_save) ;
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> arrayList = new ArrayList<String>();
                arrayList.add("/storage/emulated/0/Pictures/Davin's Brush/hhh.png");
//                BLBeautifyParam param = new BLBeautifyParam(arrayList);
//                BLBeautifyParam.startActivity(GenerateArticleActivity.this, param);


            }
        });
        button_grade = (Button)findViewById(R.id.gen_article_btn_grade) ;
        button_grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gradePoster();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveImage();
            }
        });

        textViewStep1 = (TextView)findViewById(R.id.gen_article_text1);
        textViewStep2 = (TextView)findViewById(R.id.gen_article_text2);
        textViewStep3 = (TextView)findViewById(R.id.gen_article_text3);
        textViewStep4 = (TextView)findViewById(R.id.gen_article_text4);
        textViewStep5 = (TextView)findViewById(R.id.gen_article_text5);
        imageViewResult = (PhotoView) findViewById(R.id.gen_article_result_image);
        textViewStep1.setVisibility(View.INVISIBLE);
        textViewStep2.setVisibility(View.INVISIBLE);
        textViewStep3.setVisibility(View.INVISIBLE);
        textViewStep4.setVisibility(View.INVISIBLE);
        textViewStep5.setVisibility(View.INVISIBLE);





        indicator = (StepperIndicator) findViewById(R.id.stepper_indicator_article);
        indicator.setStepCount(5);
        indicator.setCurrentStep(0);
//        button = (Button)findViewById(R.id.btn_start_article);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("-----","开始测试了！！！");
////                saveImage2();
////                upload2();
////                start();
//                startStep1();
//            }
//        });
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
        mBg = findViewById(R.id.image_bg_article);
        mPhotoView = (PhotoView) findViewById(R.id.image_article_result_preview);
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

        imageViewWave = (ImageView)findViewById(R.id.gen_article_wave_image);
        mWaveDrawable = new WaveDrawable(this, R.mipmap.logo4);
        imageViewWave.setImageDrawable(mWaveDrawable);

        final int[] m = {0};

        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(5);
        animator.setDuration(4000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        changeStep1();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
//                Toast.makeText(GenerateArticleActivity.this,"结束了",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                m[0] = m[0] +1;
                switch (m[0]){
                    case 1:
                        changeStep2();
                        break;
                    case 2:
                        changeStep3();
                        break;
                    case 3:
                        changeStep4();
                        break;
                    case 4:
                        changeStep5();
                        break;
                    case 5:
                        changeStep6();
                        break;
                }
            }
        });
        mWaveDrawable.setIndeterminateAnimator(animator);
        mWaveDrawable.setIndeterminate(true);


    }

//    Bitmap image = ((BitmapDrawable)imageViewResult.getDrawable()).getBitmap();
//    MyShareUtil myShareUtil = new MyShareUtil(GenerateArticleActivity.this);
//                myShareUtil.shareImageToQQ(GenerateArticleActivity.this,image);


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
        textViewStep2.setText("素材处理成功");
        textViewStep3.setVisibility(View.VISIBLE);
        indicator.setCurrentStep(2);
    }

    public void changeStep4(){
        textViewStep3.setText("情感分析结束");
        textViewStep4.setVisibility(View.VISIBLE);
        indicator.setCurrentStep(3);
    }

    public void setImage(final String imageUrl){
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(imageUrl)
//                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(imageUrl);
                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    connection.connect();
                    if(200==connection.getResponseCode()){
                        InputStream in = connection.getInputStream();
                        final Bitmap bitmap = BitmapFactory.decodeStream(in);
                        GenerateArticleActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageViewResult.setImageBitmap(bitmap);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void changeStep5(){
        textViewStep4.setText("图像渲染结束");
        textViewStep4.setVisibility(View.VISIBLE);
        textViewStep5.setText("正在下载图片");
        indicator.setCurrentStep(4);    //生成完成
//        PhotoView p = (PhotoView) imageViewResult;
//        mInfo = p.getInfo();
//        imageResult = ((BitmapDrawable)imageViewResult.getDrawable()).getBitmap();
//        mPhotoView.setImageBitmap(imageResult);
//        mBg.startAnimation(in);
//        mBg.setVisibility(View.VISIBLE);
//        mParent.setVisibility(View.VISIBLE);;
//        mPhotoView.animaFrom(mInfo);
//        linearButton.setVisibility(View.VISIBLE);
    }


    public void changeStep6(){
        textViewStep5.setText("图像下载完成");
        indicator.setCurrentStep(5);    //生成完成
        mHandler .postDelayed(mRunnable6, 2000); // 在Handler中执行子线程并延迟3s。


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
            Toast.makeText(GenerateArticleActivity.this,"下载出了问题",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(GenerateArticleActivity.this,"已成功帮您保存",Toast.LENGTH_SHORT).show();
            PictureSaveUtil.getInstance(GenerateArticleActivity.this).saveImageToGallery(imageResult);
        }
    }

    //TODO  这里暂时先变成打分
    public void gradePoster(){
        final GradeDialog gradeDialog = new GradeDialog(GenerateArticleActivity.this);
        gradeDialog.show();
        gradeDialog.setContentView(R.layout.grade_dialog);
        final float[] mmm = {0};
        RatingBar ratingBar;
        ratingBar = (RatingBar)gradeDialog.findViewById(R.id.ratingbar_grade);
        Button button = (Button)gradeDialog.findViewById(R.id.button_grade_sure);
        ratingBar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {
                mmm[0] = ratingCount;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mmm[0] == 0){
//                    Toast.makeText(getContext(),"还没有打分呢",Toast.LENGTH_SHORT).show();
                }else {
                    int m = 0;
                    m = (int) mmm[0];
                    Toast.makeText(GenerateArticleActivity.this,"您打了"+ mmm[0]+"分",Toast.LENGTH_SHORT).show();
                    gradeDialog.dismiss();

                    final int finalM = m;
                    final int finalM1 = m;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                OkHttpClient mOkHttpClient = new OkHttpClient();
                                String jsonToServer = "";
                                jsonToServer = "{\n" +
                                        "    \"phoneNumber\": \"18340018870\",\n" +
                                        "    \"modelId\": \"03\",\n" +
                                        "    \"score\": \""+ finalM +""    +" \"\n" +
                                        "}";
                                RequestBody requestBody = RequestBody.create(JSON, jsonToServer);

                                //TODO  下面添加一下密码
                                Request request = new Request.Builder().
                                        url("http://ba7f3fd8.ngrok.io/scores").
                                        addHeader("Connection", "Keep-Alive").
                                        addHeader("Charset", "UTF-8").
                                        addHeader("User-Agent","  Mozilla/5.0 (Windows; U; Windows NT 5.2) Gecko/2008070208 Firefox/3.0.1").
                                        addHeader("Accept","*/*").
                                        post(requestBody).
                                        build();
                                try {
                                    mOkHttpClient.newCall(request).enqueue(new Callback() {
                                        @Override
                                        public void onFailure(okhttp3.Call call, IOException e) {
                                            Log.d("-----","打分失败");
                                        }

                                        @Override
                                        public void onResponse(okhttp3.Call call, Response response) throws IOException {
                                            Log.d("-----","打分成功 分数为"+ finalM1);
                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }catch (Exception e){
                                Log.e("====",e.getMessage());
                            }
                        }
                    }).start();

                }
            }
        });
    }

    public void initShare(){
        mData = new ArrayList<ShareItem>();
        ShareItem shareItem1 = new ShareItem(R.drawable.select_file_from_qq,"QQ");
        mData.add(shareItem1);
        ShareItem shareItem2 = new ShareItem(R.drawable.select_file_from_weixin,"微信");
        mData.add(shareItem2);
        ShareItem shareItem3 = new ShareItem(R.drawable.select_from_weibo,"微博");
        mData.add(shareItem3);
        ShareItem shareItem4 = new ShareItem(R.drawable.select_file_fromfacebook,"FaceBook");
        mData.add(shareItem4);
        ShareItem shareItem5 = new ShareItem(R.drawable.select_file_form_other,"其他");
        mData.add(shareItem5);
        sharePictureAdapter = new SharePictureAdapter(this,mData);
        listViewShare.setAdapter(sharePictureAdapter);
        listViewShare.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        MyShareUtil.getInstance(GenerateArticleActivity.this).shareImageToQQ(GenerateArticleActivity.this,imageResult);
                        break;
                    case 1:
                        MyShareUtil.getInstance(GenerateArticleActivity.this).shareImageToWeixin(GenerateArticleActivity.this,imageResult);
                        break;
                    case 2:
                        MyShareUtil.getInstance(GenerateArticleActivity.this).shareImageToWeibo(GenerateArticleActivity.this,imageResult);
                        break;
                    case 3:
                        MyShareUtil.getInstance(GenerateArticleActivity.this).shareImageToFaceBook(GenerateArticleActivity.this,imageResult);
                        break;
                    case 4:
                        MyShareUtil.getInstance(GenerateArticleActivity.this).shareImageToOther(GenerateArticleActivity.this,imageResult);
                        break;

                }
            }
        });
    }

}
