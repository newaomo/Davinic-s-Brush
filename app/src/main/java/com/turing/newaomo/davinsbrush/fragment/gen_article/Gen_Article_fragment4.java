package com.turing.newaomo.davinsbrush.fragment.gen_article;

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
import com.turing.newaomo.davinsbrush.utils.SP.SPPostUtils;

/**
 * Created by newao on 2018/2/6.
 */

public class Gen_Article_fragment4  extends Fragment implements View.OnClickListener{
    private static final String TAG = "Gen_fragment_article4";
    public static Gen_Article_fragment4 newInstance() {
        return new Gen_Article_fragment4();
    }

    EditText editTextTitle;
    EditText editTextSlogan;
    EditText editTextContent;
    TextView textViewLast;
    Button textViewNext;

    View root;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.gen_by_article_fragment4  , container, false);
        editTextTitle = (EditText)root.findViewById(R.id.article_edit_title);
        editTextSlogan = (EditText)root.findViewById(R.id.article_edit_slogan);
        editTextContent = (EditText)root.findViewById(R.id.article_edit_content);
        textViewLast = (TextView)root.findViewById(R.id.article_textview_last4);
        textViewNext = (Button)root.findViewById(R.id.button_article_next4);
        textViewNext.setOnClickListener(this);
        textViewLast.setOnClickListener(this);
//        if (!Gen_By_Article_Activity.isNeedContent){
//            //TODO  这里有个问题就是有了哪个不需要哪个的问题
////            filedContent.setVisibility(View.GONE);
//            editTextContent.setVisibility(View.GONE);
//        }
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.article_textview_last4:
                Gen_By_Article_Activity.viewPager.setCurrentItem(2);
                break;
            case R.id.button_article_next4:
                saveData();
                Gen_By_Article_Activity.viewPager.setCurrentItem(4);
                break;
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
