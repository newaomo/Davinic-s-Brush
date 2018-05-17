package com.turing.newaomo.davinsbrush.fragment.gen_article;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.activity.gen.Gen_By_Article_Activity;
import com.turing.newaomo.davinsbrush.activity.gen.GenerateArticleActivity;
import com.turing.newaomo.davinsbrush.adapter.CustomSelectAdapter;
import com.turing.newaomo.davinsbrush.beans.CustomSelectItem;
import com.turing.newaomo.davinsbrush.utils.SP.SPPostUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import cn.finalteam.galleryfinal.widget.HorizontalListView;

/**
 * Created by newao on 2018/2/6.
 */

public class Gen_Article_fragment5 extends Fragment implements View.OnClickListener{
    private static final String TAG = "Gen_fragment_article5";
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String , String>();
    public static Gen_Article_fragment5 newInstance() {
        return new Gen_Article_fragment5();
    }
    View root;
    private HorizontalListView listViewCustomSelect;   //分享listview
    private CustomSelectAdapter customSelectAdapter;
    private List<CustomSelectItem> mData;
    private TextView textViewGenerate;
    private TextView textViewLast;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.gen_by_article_fragment5,container, false);
        initView();
        initAdapter();
        return root;
    }

    public void initView(){
        listViewCustomSelect = (HorizontalListView)root.findViewById(R.id.list_custom_select_article) ;
//        listViewCustomSelect.setFocusable(false);
        textViewGenerate = (TextView)root.findViewById(R.id.button_article_next5);
        textViewLast = (TextView)root.findViewById(R.id.article_textview_last5);
        textViewGenerate.setOnClickListener(this);
        textViewLast.setOnClickListener(this);
    }

    public void initAdapter(){
        initData();

        customSelectAdapter = new CustomSelectAdapter(getContext(),mData);
        listViewCustomSelect.setAdapter(customSelectAdapter);
        listViewCustomSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                customSelectAdapter.changeSelected(position);
                switch (position){
                    case 0:
                        saveData(0);
                        break;
                    case 1:
                        saveData(1);
                        break;
                    case 2:
                        saveData(2);
                        break;
                    case 3:
                        saveData(3);
                        break;
                    case 4:
                        break;

                }
            }
        });
        listViewCustomSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                customSelectAdapter.changeSelected(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_article_next5:
                Intent intent = new Intent(getActivity(), GenerateArticleActivity.class);
                startActivity(intent);
                break;
            case R.id.article_textview_last5:
                Gen_By_Article_Activity.viewPager.setCurrentItem(3);
                break;
        }
    }

    public void saveData(int position){
        switch (position){
            case 0:
                SPPostUtils.getInstance().setStyle("风格一");
                break;
            case 1:
                SPPostUtils.getInstance().setStyle("风格二");
                break;
            case 2:
                SPPostUtils.getInstance().setStyle("风格三");
                break;
            case 3:
                SPPostUtils.getInstance().setStyle("风格四");
                break;
        }
    }


    private void initData() {
        mData = new ArrayList<CustomSelectItem>();
        CustomSelectItem customSelectItem1 = new CustomSelectItem(R.drawable.katoonwoman_01,"katoonwomen");
        mData.add(customSelectItem1);
        CustomSelectItem customSelectItem2 = new CustomSelectItem(R.drawable.kanagawa_02,"简约2");
        mData.add(customSelectItem2);
        CustomSelectItem customSelectItem3 = new CustomSelectItem(R.drawable.waterandboat_05,"简约3");
        mData.add(customSelectItem3);
        CustomSelectItem customSelectItem4 = new CustomSelectItem(R.drawable.starry_03,"简约2");
        mData.add(customSelectItem4);
        CustomSelectItem customSelectItem5 = new CustomSelectItem(R.drawable.candy_04,"简约2");
        mData.add(customSelectItem5);
        CustomSelectItem customSelectItem6 = new CustomSelectItem(R.drawable.beautifulwoman_06,"简约3");
        mData.add(customSelectItem6);
        CustomSelectItem customSelectItem7 = new CustomSelectItem(R.drawable.bji_07,"简约3");
        mData.add(customSelectItem7);
        CustomSelectItem customSelectItem8 = new CustomSelectItem(R.drawable.blackperson_08,"简约3");
        mData.add(customSelectItem8);
        CustomSelectItem customSelectItem9 = new CustomSelectItem(R.drawable.blackwave_09,"简约3");
        mData.add(customSelectItem9);
        CustomSelectItem customSelectItem10 = new CustomSelectItem(R.drawable.cubist_10,"简约3");
        mData.add(customSelectItem10);
        CustomSelectItem customSelectItem11 = new CustomSelectItem(R.drawable.edtaonisl_11,"简约3");
        mData.add(customSelectItem11);
        CustomSelectItem customSelectItem12 = new CustomSelectItem(R.drawable.fangaoperson_12,"简约3");
        mData.add(customSelectItem12);
        CustomSelectItem customSelectItem13 = new CustomSelectItem(R.drawable.flower_13,"简约3");
        mData.add(customSelectItem13);
        CustomSelectItem customSelectItem14 = new CustomSelectItem(R.drawable.greatcloud_14,"简约3");
        mData.add(customSelectItem14);
        CustomSelectItem customSelectItem15 = new CustomSelectItem(R.drawable.hotstyle_15,"简约3");
        mData.add(customSelectItem15);
        CustomSelectItem customSelectItem16 = new CustomSelectItem(R.drawable.jiheperson_16,"简约3");
        mData.add(customSelectItem16);
        CustomSelectItem customSelectItem17 = new CustomSelectItem(R.drawable.kandinsky_17,"简约3");
        mData.add(customSelectItem17);
        CustomSelectItem customSelectItem18 = new CustomSelectItem(R.drawable.markman_18,"简约3");
        mData.add(customSelectItem18);
        CustomSelectItem customSelectItem19 = new CustomSelectItem(R.drawable.oilperson_19,"简约3");
        mData.add(customSelectItem19);
        CustomSelectItem customSelectItem20 = new CustomSelectItem(R.drawable.personandhorse_20,"简约3");
        mData.add(customSelectItem20);
        CustomSelectItem customSelectItem21 = new CustomSelectItem(R.drawable.starrynight_21,"简约3");
        mData.add(customSelectItem21);
        CustomSelectItem customSelectItem22 = new CustomSelectItem(R.drawable.woman_22,"简约3");
        mData.add(customSelectItem22);
        CustomSelectItem customSelectItem23 = new CustomSelectItem(R.drawable.womanwithhair_23,"简约3");
        mData.add(customSelectItem23);

    }



}
