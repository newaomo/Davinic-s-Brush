package com.turing.newaomo.davinsbrush.fragment.gen_profile;

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

import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.activity.gen.Gen_By_Article_Activity;
import com.turing.newaomo.davinsbrush.utils.SP.SPPostUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by newao on 2018/2/5.
 */

public class Gen_Profile_fragment2 extends Fragment implements  View.OnClickListener {
    public static Gen_Profile_fragment2 newInstance() {
        return new Gen_Profile_fragment2();
    }

    private static final String TAG = "Gen_fragment_article1";
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();


    View root;
    //    private TyperEditText mTyperEditText;
    private LinearLayout linearSize1;
    private LinearLayout linearSize2;
    private LinearLayout linearSize3;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private Button buttonEdit;
    private static EditText editTextWidth, editTextHeight;
    private Button buttonNext;
    private LinearLayout layout;

    private void initSpeech() {
        // 将“12345678”替换成您申请的 APPID，申请地址： http://www.xfyun.cn
        // 请勿在 “ =”与 appid 之间添加任务空字符或者转义符
//        SpeechUtility. createUtility( getContext(), SpeechConstant. APPID + "=5a6be952" );
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.gen_by_profile_fragment2, container, false);
        layout = (LinearLayout) root.findViewById(R.id.profile_button_select_linear);
        layout.setVisibility(View.INVISIBLE);
        textView1 = (TextView) root.findViewById(R.id.profile_button_select_size_1);
        textView2 = (TextView) root.findViewById(R.id.profile_button_select_size_2);
        textView3 = (TextView) root.findViewById(R.id.profile_button_select_size_3);
        linearSize1 = (LinearLayout) root.findViewById(R.id.profile_linear_1_size);
        linearSize2 = (LinearLayout) root.findViewById(R.id.profile_linear_2_size);
        linearSize3 = (LinearLayout) root.findViewById(R.id.profile_linear_3_size);
        buttonEdit = (Button) root.findViewById(R.id.profile_button_select_size_edit);
        editTextWidth = (EditText) root.findViewById(R.id.profile_edittext_size_width);
        editTextHeight = (EditText) root.findViewById(R.id.profile_edittext_size_height);
        buttonNext = (Button) root.findViewById(R.id.profile_button_profile_next2);
        buttonNext.setOnClickListener(this);
        linearSize1.setOnClickListener(this);
        linearSize2.setOnClickListener(this);
        linearSize3.setOnClickListener(this);
        buttonEdit.setOnClickListener(this);

        initSpeech();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_article_next1:
                //TODO  在这里添加存储该路径的代码
                saveData();
                Gen_By_Article_Activity.viewPager.setCurrentItem(1);
                break;
            case R.id.article_select_size_1:
                SPPostUtils.getInstance().setSizeWidth("1200");
                SPPostUtils.getInstance().setSizeHeight("1920");
                layout.setVisibility(View.INVISIBLE);
                textView1.setBackgroundResource(R.drawable.button_select_size_edit);
                textView2.setBackgroundResource(R.drawable.button_select_size);
                textView3.setBackgroundResource(R.drawable.button_select_size);
                buttonEdit.setBackgroundResource(R.drawable.button_select_size);
                textView1.setTextColor(Color.parseColor("#ffffff"));
                textView2.setTextColor(Color.parseColor("#000000"));
                textView3.setTextColor(Color.parseColor("#000000"));
                buttonEdit.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.article_select_size_2:
                SPPostUtils.getInstance().setSizeWidth("1080");
                SPPostUtils.getInstance().setSizeHeight("1920");
                layout.setVisibility(View.INVISIBLE);
                textView1.setBackgroundResource(R.drawable.button_select_size);
                textView2.setBackgroundResource(R.drawable.button_select_size_edit);
                textView3.setBackgroundResource(R.drawable.button_select_size);
                buttonEdit.setBackgroundResource(R.drawable.button_select_size);
                textView1.setTextColor(Color.parseColor("#000000"));
                textView2.setTextColor(Color.parseColor("#ffffff"));
                textView3.setTextColor(Color.parseColor("#000000"));
                buttonEdit.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.article_select_size_3:
                SPPostUtils.getInstance().setSizeWidth("720");
                SPPostUtils.getInstance().setSizeHeight("1280");
                layout.setVisibility(View.INVISIBLE);
                textView1.setBackgroundResource(R.drawable.button_select_size);
                textView2.setBackgroundResource(R.drawable.button_select_size);
                textView3.setBackgroundResource(R.drawable.button_select_size_edit);
                buttonEdit.setBackgroundResource(R.drawable.button_select_size);
                textView1.setTextColor(Color.parseColor("#000000"));
                textView2.setTextColor(Color.parseColor("#000000"));
                textView3.setTextColor(Color.parseColor("#ffffff"));
                buttonEdit.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.article_button_select_size_edit:
                SPPostUtils.getInstance().setSizeWidth("720");
                SPPostUtils.getInstance().setSizeHeight("1280");
                layout.setVisibility(View.VISIBLE);
                textView1.setBackgroundResource(R.drawable.button_select_size);
                textView2.setBackgroundResource(R.drawable.button_select_size);
                textView3.setBackgroundResource(R.drawable.button_select_size);
                buttonEdit.setBackgroundResource(R.drawable.button_select_size_edit);
                textView1.setTextColor(Color.parseColor("#000000"));
                textView2.setTextColor(Color.parseColor("#000000"));
                textView3.setTextColor(Color.parseColor("#000000"));
                buttonEdit.setTextColor(Color.parseColor("#ffffff"));
                break;

        }
    }

    public void saveData() {
        if (!TextUtils.isEmpty(editTextWidth.getText().toString()) && !TextUtils.isEmpty(editTextWidth.getText().toString())) {
            SPPostUtils.getInstance().setSizeWidth(editTextWidth.getText().toString());
            SPPostUtils.getInstance().setSizeHeight(editTextHeight.getText().toString());
        } else {
//            Toast.makeText(getActivity(),"请正确填写！", Toast.LENGTH_SHORT).show();
            return;
        }
    }



}
