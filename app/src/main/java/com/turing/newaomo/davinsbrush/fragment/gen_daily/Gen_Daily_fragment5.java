package com.turing.newaomo.davinsbrush.fragment.gen_daily;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.activity.gen.Gen_By_Article_Activity;
import com.turing.newaomo.davinsbrush.activity.gen.Gen_By_Daily_Activity;
import com.turing.newaomo.davinsbrush.utils.SP.SPPostUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;


public class Gen_Daily_fragment5 extends Fragment implements View.OnClickListener{
    private static final String TAG = "Gen_fragment_article4";
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String , String>();
    public static Gen_Daily_fragment5 newInstance() {
        return new Gen_Daily_fragment5();
    }

    EditText editTextTitle;
    EditText editTextSlogan;
    EditText editTextContent;
    TextView textViewLast;
    Button textViewNext;
    TextView textViewSkip;

    View root;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.gen_daily_fragment5  , container, false);

        editTextTitle = (EditText)root.findViewById(R.id.daily_edit_title);
        editTextSlogan = (EditText)root.findViewById(R.id.daily_edit_slogan);
        editTextContent = (EditText)root.findViewById(R.id.daily_edit_content);
        textViewLast = (TextView)root.findViewById(R.id.daily_textview_last5);
        textViewNext = (Button)root.findViewById(R.id.button_daily_next5);
        textViewSkip = (TextView)root.findViewById(R.id.daily_textview_skip5);
        textViewSkip.setOnClickListener(this);
        textViewNext.setOnClickListener(this);
        textViewLast.setOnClickListener(this);
        if (!Gen_By_Article_Activity.isNeedContent){
            //TODO  这里有个问题就是有了哪个不需要哪个的问题
//            filedContent.setVisibility(View.GONE);
            editTextContent.setVisibility(View.GONE);
        }
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.daily_textview_last5:
                Gen_By_Daily_Activity.viewPager.setCurrentItem(2);
                break;
            case R.id.button_daily_next5:
                saveData();
                Gen_By_Daily_Activity.viewPager.setCurrentItem(4);
                break;
            case R.id.daily_textview_skip5:
                Gen_By_Daily_Activity.viewPager.setCurrentItem(4);
        }
    }

    public void saveData(){
        if (!TextUtils.isEmpty(editTextTitle.getText().toString())){
            if (!TextUtils.isEmpty(editTextSlogan.getText().toString())){
                if (!TextUtils.isEmpty(editTextContent.getText().toString())){
                    SPPostUtils.getInstance().setTitle(editTextTitle.getText().toString());
                    SPPostUtils.getInstance().setSlogan(editTextSlogan.getText().toString());
                    SPPostUtils.getInstance().setContent(editTextContent.getText().toString());
                }
            }else {
                Toast.makeText(getActivity(),"请正确填写", Toast.LENGTH_SHORT).show();
            }
        }
    }



}
