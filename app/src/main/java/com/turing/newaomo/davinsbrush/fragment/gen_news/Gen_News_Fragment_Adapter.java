package com.turing.newaomo.davinsbrush.fragment.gen_news;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.turing.newaomo.davinsbrush.activity.gen.Gen_By_News_Activity;

/**
 * Created by newao on 2018/2/3.
 */

public class Gen_News_Fragment_Adapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 4;
    private Gen_News_fragment1 myFragment1 = null;
    private Gen_News_fragment2 myFragment2 = null;
    private Gen_News_fragment3 myFragment3 = null;
    private Gen_News_fragment4 myFragment4 = null;


    public Gen_News_Fragment_Adapter(FragmentManager fm) {
        super(fm);
        myFragment1 = Gen_News_fragment1.newInstance();
        myFragment2 = new Gen_News_fragment2();
        myFragment3 = new Gen_News_fragment3();
        myFragment4 = new Gen_News_fragment4();
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case Gen_By_News_Activity.PAGE_1:
                fragment = myFragment1;
                break;
            case  Gen_By_News_Activity.PAGE_2:
                fragment = myFragment2;
                break;
            case  Gen_By_News_Activity.PAGE_3:
                fragment = myFragment3;
                break;
            case Gen_By_News_Activity.PAGE_4:
                fragment = myFragment4;
        }
        return fragment;
    }
}