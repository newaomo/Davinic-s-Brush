package com.turing.newaomo.davinsbrush.fragment.gen_profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.activity.gen.Gen_By_Profile_Activity;

import java.util.HashMap;
import java.util.LinkedHashMap;


public class Gen_Profile_fragment6 extends Fragment implements View.OnClickListener{
    private static final String TAG = "Gen_fragment_article4";
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String , String>();
    public static Gen_Profile_fragment6 newInstance() {
        return new Gen_Profile_fragment6();
    }

    EditText editTextName;
    EditText editTextHonor;
    EditText editTextIntroduce;
    EditText editTextMotto;
    TextView textViewLast;
    Button textViewNext;
    TextView textViewSkip;

    View root;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.gen_by_profile_fragment6  , container, false);

        editTextName = (EditText)root.findViewById(R.id.profile_edit_name);
        editTextHonor = (EditText)root.findViewById(R.id.profile_edit_honor);
        editTextIntroduce = (EditText)root.findViewById(R.id.profile_edit_introduce);
        editTextMotto = (EditText)root.findViewById(R.id.profile_edit_motto);
        textViewLast = (TextView)root.findViewById(R.id.profile_textview_last6);
        textViewNext = (Button)root.findViewById(R.id.button_profile_next6);
        textViewSkip = (TextView)root.findViewById(R.id.profile_textview_skip6);
        textViewSkip.setOnClickListener(this);
        textViewNext.setOnClickListener(this);
        textViewLast.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.profile_textview_last5:
                Gen_By_Profile_Activity.viewPager.setCurrentItem(3);
                break;
            case R.id.button_profile_next5:
                saveData();
                Gen_By_Profile_Activity.viewPager.setCurrentItem(5);
                break;
            case R.id.profile_textview_skip6:
                Gen_By_Profile_Activity.viewPager.setCurrentItem(5);
        }
    }

    public void saveData() {

    }

}
