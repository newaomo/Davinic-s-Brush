package com.turing.newaomo.davinsbrush.fragment.gen_article;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.turing.newaomo.davinsbrush.activity.gen.Gen_By_Article_Activity;

/**
 * Created by newao on 2018/2/6.
 */

public class Gen_Article_Fragment_Adapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 5;
    private Gen_Article_fragment1 myFragment1 = null;
    private Gen_Article_fragment2 myFragment2 = null;
    private Gen_Article_fragment3 myFragment3 = null;
    private Gen_Article_fragment4 myFragment4 = null;
    private Gen_Article_fragment5 myFragment5 = null;


    public Gen_Article_Fragment_Adapter(FragmentManager fm) {
        super(fm);
        myFragment1 = Gen_Article_fragment1.newInstance();
        myFragment2 = Gen_Article_fragment2.newInstance();
        myFragment3 = Gen_Article_fragment3.newInstance();
        myFragment4 = Gen_Article_fragment4.newInstance();
        myFragment5 = Gen_Article_fragment5.newInstance();

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
            case Gen_By_Article_Activity.PAGE_1:
                fragment = myFragment1;
                break;
            case Gen_By_Article_Activity.PAGE_2:
                fragment = myFragment2;
                break;
            case Gen_By_Article_Activity.PAGE_3:
                fragment = myFragment3;
                break;
            case Gen_By_Article_Activity.PAGE_4:
                fragment = myFragment4;
                break;
            case Gen_By_Article_Activity.PAGE_5:
                fragment = myFragment5;
                break;

        }
        return fragment;
    }
}