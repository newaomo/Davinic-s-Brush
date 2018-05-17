package com.turing.newaomo.davinsbrush.fragment.gen_profile;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.turing.newaomo.davinsbrush.activity.gen.Gen_By_Profile_Activity;

/**
 * Created by newao on 2018/2/6.
 */

public class Gen_Profile_Fragment_Adapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 7;
    private Gen_Profile_fragment1 myFragment1 = null;
    private Gen_Profile_fragment2 myFragment2 = null;
    private Gen_Profile_fragment3 myFragment3 = null;
    private Gen_Profile_fragment4 myFragment4 = null;
    private Gen_Profile_fragment5 myFragment5 = null;
    private Gen_Profile_fragment6 myFragment6 = null;
    private Gen_Profile_fragment7 myFragment7 = null;


    public Gen_Profile_Fragment_Adapter(FragmentManager fm) {
        super(fm);
        myFragment1 = Gen_Profile_fragment1.newInstance();
        myFragment2 = Gen_Profile_fragment2.newInstance();
        myFragment3 = Gen_Profile_fragment3.newInstance();
        myFragment4 = Gen_Profile_fragment4.newInstance();
        myFragment5 = Gen_Profile_fragment5.newInstance();
        myFragment6 = Gen_Profile_fragment6.newInstance();
        myFragment7 = Gen_Profile_fragment7.newInstance();
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
            case Gen_By_Profile_Activity.PAGE_1:
                fragment = myFragment1;
                break;
            case Gen_By_Profile_Activity.PAGE_2:
                fragment = myFragment2;
                break;
            case Gen_By_Profile_Activity.PAGE_3:
                fragment = myFragment3;
                break;
            case Gen_By_Profile_Activity.PAGE_4:
                fragment = myFragment4;
                break;
            case Gen_By_Profile_Activity.PAGE_5:
                fragment = myFragment5;
                break;
            case Gen_By_Profile_Activity.PAGE_6:
                fragment = myFragment6;
                break;
            case Gen_By_Profile_Activity.PAGE_7:
                fragment = myFragment7;
                break;

        }
        return fragment;
    }
}