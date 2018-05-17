package com.turing.newaomo.davinsbrush.fragment.gen_daily;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.utils.SP.SPPostUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;


public class Gen_Daily_fragment1 extends Fragment implements  View.OnClickListener {
    public static Gen_Daily_fragment1 newInstance() {
        return new Gen_Daily_fragment1();
    }
    private static final String TAG = "Gen_fragment_article1";
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String , String>();


    View root;
    //    private TyperEditText mTyperEditText;
    private CardView buttonMode1;
    private CardView buttonMode2;
    private CardView buttonMode3;
    private CardView buttonNext;

    private void initSpeech() {
        // 将“12345678”替换成您申请的 APPID，申请地址： http://www.xfyun.cn
        // 请勿在 “ =”与 appid 之间添加任务空字符或者转义符
//        SpeechUtility. createUtility( getContext(), SpeechConstant. APPID + "=5a6be952" );
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.gen_daily_fragment1, container, false);
//        layout = (LinearLayout)root.findViewById(R.id.daily_size_edit_liner) ;
//        layout.setVisibility(View.INVISIBLE);
        buttonMode1 = (CardView)root.findViewById(R.id.daily_mode_1_card);
        buttonMode2 = (CardView)root.findViewById(R.id.daily_mode_2_card);
        buttonMode3 = (CardView)root.findViewById(R.id.daily_mode_3_card);
        buttonNext = (CardView)root.findViewById(R.id.daily_mode_skip);
        buttonNext.setOnClickListener(this);
        buttonMode1.setOnClickListener(this);
        buttonMode2.setOnClickListener(this);
        buttonMode3.setOnClickListener(this);

        initSpeech() ;
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.daily_mode_1_card:
                SPPostUtils.getInstance().setSizeWidth("1200");
                SPPostUtils.getInstance().setSizeHeight("1920");
                break;
            case R.id.daily_mode_2_card:
                SPPostUtils.getInstance().setSizeWidth("1080");
                SPPostUtils.getInstance().setSizeHeight("1920");
                break;
            case R.id.daily_mode_3_card:
                SPPostUtils.getInstance().setSizeWidth("720");
                SPPostUtils.getInstance().setSizeHeight("1280");
                break;
            case R.id.daily_button_select_size_edit:
                SPPostUtils.getInstance().setSizeWidth("720");
                SPPostUtils.getInstance().setSizeHeight("1280");
                break;
        }
    }

    public void saveData(){

    }

}