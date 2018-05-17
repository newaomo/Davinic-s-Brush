package com.turing.newaomo.davinsbrush.fragment.gen_news;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.activity.gen.Gen_By_News_Activity;
import com.turing.newaomo.davinsbrush.utils.SP.SPPostUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by newao on 2018/2/3.
 */

public class Gen_News_fragment1  extends Fragment  implements  View.OnClickListener{
    public static Gen_News_fragment1 newInstance() {
        return new Gen_News_fragment1();
    }
    private static final String TAG = "Gen_fragment_article1";
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String , String>();



    View root;
    //    private TyperEditText mTyperEditText;
    private TextView buttonSize1;
    private TextView buttonSize2;
    private TextView buttonSize3;
    private Button buttonEdit;
    private static EditText editTextWidth,editTextHeight;
    private Button buttonNext;
    private LinearLayout layout;
    private LinearLayout linearLayout;
    private LinearLayout linearLayoutButtonEdit;


    private void initSpeech() {
        // 将“12345678”替换成您申请的 APPID，申请地址： http://www.xfyun.cn
        // 请勿在 “ =”与 appid 之间添加任务空字符或者转义符
//        SpeechUtility. createUtility( getContext(), SpeechConstant. APPID + "=5a6be952" );
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.gen_by_news_fragment1, container, false);
        layout = (LinearLayout)root.findViewById(R.id.news_size_edit_liner) ;
        layout.setVisibility(View.GONE);
        linearLayoutButtonEdit = (LinearLayout)root.findViewById(R.id.news_button_select_linear);
        linearLayout = (LinearLayout)root.findViewById(R.id.news_linear_1) ;
        buttonSize1 = (TextView)root.findViewById(R.id.news_button_select_size_1);
        buttonSize2 = (TextView)root.findViewById(R.id.news_button_select_size_2);
        buttonSize3 = (TextView)root.findViewById(R.id.news_button_select_size_3);
        buttonEdit = (Button)root.findViewById(R.id.news_button_select_size_edit);
        editTextWidth = (EditText)root.findViewById(R.id.news_edittext_size_width);
        editTextHeight = (EditText)root.findViewById(R.id.news_edittext_size_height);
        buttonNext = (Button)root.findViewById(R.id.news_button_news_next1);
        buttonNext.setOnClickListener(this);
        buttonSize1.setOnClickListener(this);
        buttonSize2.setOnClickListener(this);
        buttonSize3.setOnClickListener(this);
        buttonEdit.setOnClickListener(this);

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
            case R.id.news_button_news_next1:
                //TODO  在这里添加存储该路径的代码
                saveData();
                Gen_By_News_Activity.viewPager.setCurrentItem(1);
                break;
            case R.id.news_button_select_size_1:
                SPPostUtils.getInstance().setSizeWidth("1200");
                SPPostUtils.getInstance().setSizeHeight("1920");
                buttonEdit.setVisibility(View.VISIBLE);
                layout.setVisibility(View.GONE);
                linearLayoutButtonEdit.setVisibility(View.VISIBLE);
                buttonSize1.setBackgroundResource(R.drawable.button_select_size_edit);
                buttonSize2.setBackgroundResource(R.drawable.button_select_size);
                buttonSize3.setBackgroundResource(R.drawable.button_select_size);
                buttonEdit.setBackgroundResource(R.drawable.button_select_size);
                buttonSize1.setTextColor(Color.parseColor("#ffffff"));
                buttonSize2.setTextColor(Color.parseColor("#000000"));
                buttonSize3.setTextColor(Color.parseColor("#000000"));
                buttonEdit.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.news_button_select_size_2:
                SPPostUtils.getInstance().setSizeWidth("1080");
                SPPostUtils.getInstance().setSizeHeight("1920");
                buttonEdit.setVisibility(View.VISIBLE);
                layout.setVisibility(View.GONE);
                linearLayoutButtonEdit.setVisibility(View.VISIBLE);
                buttonSize1.setBackgroundResource(R.drawable.button_select_size);
                buttonSize2.setBackgroundResource(R.drawable.button_select_size_edit);
                buttonSize3.setBackgroundResource(R.drawable.button_select_size);
                buttonEdit.setBackgroundResource(R.drawable.button_select_size);
                buttonSize1.setTextColor(Color.parseColor("#000000"));
                buttonSize2.setTextColor(Color.parseColor("#ffffff"));
                buttonSize3.setTextColor(Color.parseColor("#000000"));
                buttonEdit.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.news_button_select_size_3:
                SPPostUtils.getInstance().setSizeWidth("720");
                SPPostUtils.getInstance().setSizeHeight("1280");
                buttonEdit.setVisibility(View.VISIBLE);
                layout.setVisibility(View.GONE);
                linearLayoutButtonEdit.setVisibility(View.VISIBLE);
                buttonSize1.setBackgroundResource(R.drawable.button_select_size);
                buttonSize2.setBackgroundResource(R.drawable.button_select_size);
                buttonSize3.setBackgroundResource(R.drawable.button_select_size_edit);
                buttonEdit.setBackgroundResource(R.drawable.button_select_size);
                buttonSize1.setTextColor(Color.parseColor("#000000"));
                buttonSize2.setTextColor(Color.parseColor("#000000"));
                buttonSize3.setTextColor(Color.parseColor("#ffffff"));
                buttonEdit.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.news_button_select_size_edit:
                SPPostUtils.getInstance().setSizeWidth("720");
                SPPostUtils.getInstance().setSizeHeight("1280");
                linearLayoutButtonEdit.setVisibility(View.GONE);
                buttonEdit.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);

                buttonSize1.setBackgroundResource(R.drawable.button_select_size);
                buttonSize2.setBackgroundResource(R.drawable.button_select_size);
                buttonSize3.setBackgroundResource(R.drawable.button_select_size);
                buttonEdit.setBackgroundResource(R.drawable.button_select_size_edit);
                buttonSize1.setTextColor(Color.parseColor("#000000"));
                buttonSize2.setTextColor(Color.parseColor("#000000"));
                buttonSize3.setTextColor(Color.parseColor("#000000"));
                buttonEdit.setTextColor(Color.parseColor("#ffffff"));
                break;

        }
    }

    public void saveData(){
        if(buttonSize1.isFocused()){
            SPPostUtils.getInstance().setSizeWidth("1200");
            SPPostUtils.getInstance().setSizeHeight("1920");
        }else if (buttonSize2.isFocused()){
            SPPostUtils.getInstance().setSizeWidth("1080");
            SPPostUtils.getInstance().setSizeHeight("1920");
        }else if (buttonSize3.isFocused()){
            SPPostUtils.getInstance().setSizeWidth("720");
            SPPostUtils.getInstance().setSizeHeight("1280");
        }else if (!TextUtils.isEmpty(editTextWidth.getText().toString())&&!TextUtils.isEmpty(editTextWidth.getText().toString())){
            SPPostUtils.getInstance().setSizeWidth(editTextWidth.getText().toString());
            SPPostUtils.getInstance().setSizeHeight(editTextHeight.getText().toString());
        }else {
            Toast.makeText(getActivity(),"请正确填写！", Toast.LENGTH_SHORT).show();
            return;
        }
    }

}
