package com.turing.newaomo.davinsbrush.fragment.gen_profile;

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
import com.turing.newaomo.davinsbrush.activity.gen.Gen_By_Profile_Activity;
import com.turing.newaomo.davinsbrush.activity.gen.GenerateProfileActivity;
import com.turing.newaomo.davinsbrush.adapter.CustomSelectAdapter;
import com.turing.newaomo.davinsbrush.beans.CustomSelectItem;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.widget.HorizontalListView;

/**
 * Created by newao on 2018/2/5.
 */

public class Gen_Profile_fragment7 extends Fragment implements View.OnClickListener{

    View root;
    private HorizontalListView listViewCustomSelect;   //分享listview
    private CustomSelectAdapter customSelectAdapter;
    private List<CustomSelectItem> mData;
    private TextView textViewIntroduce;
    private TextView textViewGenerate;
    private TextView textViewLast;

    public static Gen_Profile_fragment7 newInstance() {
        return new Gen_Profile_fragment7();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.gen_by_profile_fragment7,container, false);
        initView();
        initAdapter();
        return root;
    }

    public void initView(){
        listViewCustomSelect = (HorizontalListView)root.findViewById(R.id.list_custom_select_profile) ;
        listViewCustomSelect.setFocusable(false);
        textViewIntroduce = (TextView)root.findViewById(R.id.profile_btn_custom_introduce);
        textViewGenerate = (TextView)root.findViewById(R.id.profile_btn_add_gen);
        textViewLast = (TextView)root.findViewById(R.id.gen_by_profile_last7);
        textViewGenerate.setOnClickListener(this);
        textViewLast.setOnClickListener(this);
    }

    public void initAdapter(){
        mData = new ArrayList<CustomSelectItem>();
//        for (int i = 0;i <10;i++){
            CustomSelectItem customSelectItem1 = new CustomSelectItem(R.drawable.girl,"简约");
        mData.add(customSelectItem1);
        CustomSelectItem customSelectItem2 = new CustomSelectItem(R.drawable.girl,"简约2");
        mData.add(customSelectItem2);
        CustomSelectItem customSelectItem4 = new CustomSelectItem(R.drawable.girl,"简约2");
        mData.add(customSelectItem4);
        CustomSelectItem customSelectItem5 = new CustomSelectItem(R.drawable.girl,"简约2");
        mData.add(customSelectItem5);
        CustomSelectItem customSelectItem3 = new CustomSelectItem(R.drawable.girl,"简约3");
        mData.add(customSelectItem3);
//        }
        customSelectAdapter = new CustomSelectAdapter(getContext(),mData);
        listViewCustomSelect.setAdapter(customSelectAdapter);
        listViewCustomSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;

                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.profile_btn_add_gen:
                Intent intent = new Intent(getActivity(), GenerateProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.gen_by_profile_last7:
                Gen_By_Profile_Activity.viewPager.setCurrentItem(5);
                break;
        }
    }
}
