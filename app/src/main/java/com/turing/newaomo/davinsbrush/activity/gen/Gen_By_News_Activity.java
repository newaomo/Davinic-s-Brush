package com.turing.newaomo.davinsbrush.activity.gen;

import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.fragment.gen_news.Gen_News_Fragment_Adapter;
import com.turing.newaomo.davinsbrush.fragment.gen_news.Gen_News_fragment1;
import com.turing.newaomo.davinsbrush.fragment.gen_news.Gen_News_fragment2;
import com.turing.newaomo.davinsbrush.fragment.gen_news.Gen_News_fragment3;
import com.turing.newaomo.davinsbrush.fragment.gen_news.Gen_News_fragment4;
import com.turing.newaomo.davinsbrush.utils.DepthPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class Gen_By_News_Activity extends AppCompatActivity implements View.OnClickListener{
    public static boolean isNeedContent = true;
    //TODO  特别要注意后期文件如何保存上传给后台


    public static ViewPager viewPager;
    public FragmentPagerAdapter mFragmentPagerAdapter; //Fragment适配器
    List<Fragment> myContionter;     //存放的容器
    Gen_News_fragment1 myFragment1;
    Gen_News_fragment2 myFragment2;
    Gen_News_fragment3 myFragment3;
    Gen_News_fragment4 myFragment4;

    public static final int PAGE_1 = 0;
    public static final int PAGE_2 = 1;
    public static final int PAGE_3 = 2;
    public static final int PAGE_4 = 3;
    private View statusBarView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_by_news);
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                if (isStatusBar()) {
                    initStatusBar();
                    getWindow().getDecorView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                        @Override
                        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                            initStatusBar();
                        }
                    });
                }
                //只走一次
                return false;
            }
        });

        initUI();
    }

    private void initStatusBar() {
        if (statusBarView == null) {
            int identifier = getResources().getIdentifier("statusBarBackground", "id", "android");
            statusBarView = getWindow().findViewById(identifier);
        }
        if (statusBarView != null) {
            statusBarView.setBackgroundResource(R.drawable.bmob_update_btn_check_off_focused_holo_light);
        }
    }

    protected boolean isStatusBar() {
        return true;
    }

    private void initUI() {

        viewPager = (ViewPager) findViewById(R.id.gen_by_news_viewpager);
        viewPager.setPageTransformer(true,
                new DepthPageTransformer());
//        viewPager.setPageTransformer(true, new com.turing.newaomo.davinsbrush.utils.ScaleInOutTransformer());
        myFragment1 = Gen_News_fragment1.newInstance();
        myFragment2 = new Gen_News_fragment2();
        myFragment3 = new Gen_News_fragment3();
        myFragment4 = new Gen_News_fragment4();
//初始化容器
        myContionter = new ArrayList<>();
        myContionter.add(myFragment1);
        myContionter.add(myFragment2);
        myContionter.add(myFragment3);
        myContionter.add(myFragment4);
        mFragmentPagerAdapter = new Gen_News_Fragment_Adapter(getSupportFragmentManager());
        viewPager.setAdapter(mFragmentPagerAdapter);
        viewPager.setOffscreenPageLimit(4);          //设置活动的最大缓存数量

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }

}
