package com.turing.newaomo.davinsbrush.fragment.gen_profile;

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
import com.turing.newaomo.davinsbrush.activity.gen.Gen_By_Profile_Activity;
import com.turing.newaomo.davinsbrush.utils.SP.SPPostUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by newao on 2018/2/5.
 */

public class Gen_Profile_fragment5 extends Fragment implements View.OnClickListener{
    private static final String TAG = "Gen_fragment_article4";
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String , String>();
    public static Gen_Profile_fragment5 newInstance() {
        return new Gen_Profile_fragment5();
    }

    EditText editTextTitle;
    EditText editTextSlogan;
    TextView textViewLast;
    Button textViewNext;

    View root;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.gen_by_profile_fragment5  , container, false);

        editTextTitle = (EditText)root.findViewById(R.id.profile_edit_title);
        editTextSlogan = (EditText)root.findViewById(R.id.profile_edit_slogan);
        textViewLast = (TextView)root.findViewById(R.id.profile_textview_last5);
        textViewNext = (Button)root.findViewById(R.id.button_profile_next5);
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
        }
    }

    public void saveData(){
        if (!TextUtils.isEmpty(editTextTitle.getText().toString())){
            if (!TextUtils.isEmpty(editTextSlogan.getText().toString())){
                SPPostUtils.getInstance().setTitle(editTextTitle.getText().toString());
                SPPostUtils.getInstance().setSlogan(editTextSlogan.getText().toString());
            }else {
                Toast.makeText(getActivity(),"请正确填写", Toast.LENGTH_SHORT).show();
            }
        }
    }



}
