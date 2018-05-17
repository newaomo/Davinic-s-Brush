package com.turing.newaomo.davinsbrush.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.fragment.pesonal_gallery.FragmentColor;
import com.turing.newaomo.davinsbrush.fragment.pesonal_gallery.FragmentStyle;

public class PersonalGalleryActivity extends AppCompatActivity {

    ViewPager mViewPager;
    FragmentStyle mFragment1;
    FragmentColor mFragment2;
    PagerAdapter mPagerAdapter;

    private TabLayout mTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_gallery);
        initView(savedInstanceState);
    }

    public void initView(Bundle savedInstanceState){
        mViewPager = (ViewPager) findViewById(R.id.view_pager_personal_gallery);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout = (TabLayout) findViewById(R.id.toolbar_tab);
        if (savedInstanceState == null) {
            mFragment1 = new FragmentStyle();
            mFragment2 = new FragmentColor();
        }
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }


    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }



        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return mFragment1;
            } else if (position == 1) {
                return mFragment2;
            }

            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

    }

}
