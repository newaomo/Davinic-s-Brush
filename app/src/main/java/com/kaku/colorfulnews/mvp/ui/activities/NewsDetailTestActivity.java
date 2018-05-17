package com.kaku.colorfulnews.mvp.ui.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.turing.newaomo.davinsbrush.R;

public class NewsDetailTestActivity extends AppCompatActivity {

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail_test);
        initData();
        initView();
    }

    private void initView(){
        textView1 = (TextView)findViewById(R.id.detail1);
        textView2 = (TextView)findViewById(R.id.detail2);
        textView3 = (TextView)findViewById(R.id.detail3);
        textView4 = (TextView)findViewById(R.id.detail4);
    }

    private void initData(){
        SharedPreferences s = getSharedPreferences("newsDetail",MODE_PRIVATE);
        SharedPreferences.Editor editor = s.edit();
        String title = s.getString("title","");
        String content = s.getString("content","");
        String imgSrc = s.getString("imgSrc","");
        Log.d("title",title);
        Log.d("content",content);
        Log.d("imgSrc",imgSrc);
        String sss = "";
        textView1.setText(title);
        textView2.setText(content);
        textView3.setText(imgSrc);

        int numberImg = s.getInt("numberImg",0);
        for (int i = 0;i < numberImg; i++){
            String img = s.getString("imgItem"+i,"");
            Log.d("-----image-----",img);
            sss = sss+img;
        }
        textView4.setText(sss);

        Toast.makeText(NewsDetailTestActivity.this,title,Toast.LENGTH_SHORT).show();

    }

}
