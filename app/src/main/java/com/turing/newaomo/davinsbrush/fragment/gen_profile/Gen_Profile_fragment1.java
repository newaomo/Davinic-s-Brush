package com.turing.newaomo.davinsbrush.fragment.gen_profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.activity.gen.Gen_By_Profile_Activity;

/**
 * Created by newao on 2018/2/5.
 */


public class Gen_Profile_fragment1 extends Fragment implements  View.OnClickListener {

    public static Gen_Profile_fragment1 newInstance() {
        return new Gen_Profile_fragment1();
    }
    private String text = "请选择您的海报的模式";
    View root;
    private CardView textViewNext;
    private CardView cardViewMode1;
    private CardView cardViewMode2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.gen_by_profile_fragment1, container, false);
        textViewNext = (CardView) root.findViewById(R.id.profile_mode_next);
        textViewNext.setOnClickListener(this);
        cardViewMode1 = (CardView)root.findViewById(R.id.profile_mode_1_card);
        cardViewMode2 = (CardView)root.findViewById(R.id.profile_mode_2_card);
        cardViewMode1.setOnClickListener(this);
        cardViewMode2.setOnClickListener(this);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_mode_next:
                //TODO  在这里添加存储该路径的代码
                Gen_By_Profile_Activity.viewPager.setCurrentItem(2);
                break;
            case R.id.profile_mode_1_card:
                Gen_By_Profile_Activity.viewPager.setCurrentItem(2);
                break;
            case R.id.profile_mode_2_card:
                Gen_By_Profile_Activity.viewPager.setCurrentItem(2);
                break;
        }
    }

}
