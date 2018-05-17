package com.turing.newaomo.davinsbrush.activity.gen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.fragment.gen_daily.Gen_Daily_Fragment_Adapter;
import com.turing.newaomo.davinsbrush.fragment.gen_daily.Gen_Daily_fragment1;
import com.turing.newaomo.davinsbrush.fragment.gen_daily.Gen_Daily_fragment2;
import com.turing.newaomo.davinsbrush.fragment.gen_daily.Gen_Daily_fragment3;
import com.turing.newaomo.davinsbrush.fragment.gen_daily.Gen_Daily_fragment4;
import com.turing.newaomo.davinsbrush.fragment.gen_daily.Gen_Daily_fragment5;
import com.turing.newaomo.davinsbrush.fragment.gen_daily.Gen_Daily_fragment6;

import java.util.ArrayList;
import java.util.List;

public class Gen_By_Daily_Activity extends AppCompatActivity implements View.OnClickListener{
    public static boolean isNeedContent = true;
    //TODO  特别要注意后期文件如何保存上传给后台


    public static ViewPager viewPager;
    public FragmentPagerAdapter mFragmentPagerAdapter; //Fragment适配器
    List<Fragment> myContionter;     //存放的容器
    Gen_Daily_fragment1 myFragment1;
    Gen_Daily_fragment2 myFragment2;
    Gen_Daily_fragment3 myFragment3;
    Gen_Daily_fragment4 myFragment4;
    Gen_Daily_fragment5 myFragment5;
    Gen_Daily_fragment6 myFragment6;

    public static final int PAGE_1 = 0;
    public static final int PAGE_2 = 1;
    public static final int PAGE_3 = 2;
    public static final int PAGE_4 = 3;
    public static final int PAGE_5 = 4;
    public static final int PAGE_6 = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_by_daily);
        initUI();
    }

    private void initUI() {

        viewPager = (ViewPager) findViewById(R.id.gen_by_daily_viewpager);
//        viewPager.setPageTransformer(true,
//                new DepthPageTransformer());
        viewPager.setPageTransformer(true, new com.turing.newaomo.davinsbrush.utils.ScaleInOutTransformer());
        myFragment1 = Gen_Daily_fragment1.newInstance();
        myFragment2 = Gen_Daily_fragment2.newInstance();
        myFragment3 = Gen_Daily_fragment3.newInstance();
        myFragment4 = Gen_Daily_fragment4.newInstance();
        myFragment5 = Gen_Daily_fragment5.newInstance();
        myFragment6 = Gen_Daily_fragment6.newInstance();

//初始化容器
        myContionter = new ArrayList<>();
        myContionter.add(myFragment1);
        myContionter.add(myFragment2);
        myContionter.add(myFragment3);
        myContionter.add(myFragment4);
        myContionter.add(myFragment5);
        myContionter.add(myFragment6);
        mFragmentPagerAdapter = new Gen_Daily_Fragment_Adapter(getSupportFragmentManager());
        viewPager.setAdapter(mFragmentPagerAdapter);
        viewPager.setOffscreenPageLimit(5);          //设置活动的最大缓存数量

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.main_btn_menu:
//                if (mMainSwipemenu.isMenuShowing()) {
//                    mMainSwipemenu.hideMenu();
//                } else {
//                    mMainSwipemenu.showMenu();
//                }
//                break;
        }
    }

}
