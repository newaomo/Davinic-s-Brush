package com.turing.newaomo.davinsbrush.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.activity.gallery.activity.ManagePictureActivity;
import com.turing.newaomo.davinsbrush.activity.other.EditUserInfoActivity;
import com.turing.newaomo.davinsbrush.view.setting.CircleTransform;
import com.turing.newaomo.davinsbrush.view.setting.LSettingItem;


public class MyFragment4 extends Fragment implements View.OnClickListener {

    private static final int GET_PROFILE_FROM_NETWORK = 0;
    private Boolean isUseUri = false;
    private Uri cropUri ;

    String nickName;
    String brushNumber;
    String downloadNumber;
    String historyNumber;
    String collectionNumber;
    String galleryNumber;

    View view;
    ImageView headImage;
    TextView nickTextView;
    TextView brushNumberTextView;
    LSettingItem lSettingItemDownload;
    LSettingItem lSettingItemHistory;
    LSettingItem lSettingItemCollection;
    LSettingItem lSettingItemGallery;
    LSettingItem lSettingItemSetting;
    ImageView imageViewEdit;

    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GET_PROFILE_FROM_NETWORK:
                    getValueHandler();
            }
            super.handleMessage(msg);
        }
    };
    private Runnable mRunnable1 = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(GET_PROFILE_FROM_NETWORK);
        }
    };
    public void getValue(){
        mHandler.postDelayed(mRunnable1, 1000); // 在Handler中执行子线程并延迟3s。
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_4,container,false);
        initView();
        getValueHandler();

//        getValue();
        initAvatar();
        return view;
    }

    public void initAvatar(){
        if (isUseUri){
            Picasso.with(getActivity()).load(cropUri).transform(new CircleTransform()).into(headImage);
        }
    }

    public void getValueHandler(){
        //默认每次从本地获取数据 在数据改变时候设置更新
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Profile", Context.MODE_PRIVATE);
        if (!sharedPreferences.getBoolean("isUpdate",false)){
            nickName = sharedPreferences.getString("nickName","子桓");
            brushNumber = sharedPreferences.getString("brushNumber","1234567890");
            downloadNumber = sharedPreferences.getString("downloadNumber","99");
            historyNumber = sharedPreferences.getString("historyNumber","99");
            collectionNumber = sharedPreferences.getString("collectionNumber","99");
            galleryNumber = sharedPreferences.getString("galleryNumber","99");
            isUseUri = sharedPreferences.getBoolean("isUseUri",false);
            if (isUseUri){
                Log.d("-----","使用");
                Log.d("-----",sharedPreferences.getString("avatar",""));

            }else {
                Log.d("-----","不使用");

            }
            cropUri = Uri.parse(sharedPreferences.getString("avatar",""));
            nickTextView.setText(nickName);
            brushNumberTextView.setText(brushNumber);
            lSettingItemDownload.setRightText(downloadNumber);
            lSettingItemHistory.setRightText(historyNumber);
            lSettingItemCollection.setRightText(collectionNumber);
            lSettingItemGallery.setRightText(galleryNumber);
        }else {

        }
    }

    public void initView(){
        imageViewEdit = (ImageView)view.findViewById(R.id.fragment_profile_image_edit);
        headImage = (ImageView)view.findViewById(R.id.fragment_profile_head);
        nickTextView = (TextView)view.findViewById(R.id.fragment_profile_nick);
        brushNumberTextView = (TextView)view.findViewById(R.id.fragment_profile_brush_number);
        lSettingItemDownload = (LSettingItem)view.findViewById(R.id.fragment_profile_download);
        lSettingItemHistory = (LSettingItem)view.findViewById(R.id.fragment_profile_history);
        lSettingItemCollection = (LSettingItem)view.findViewById(R.id.fragment_profile_collection);
        lSettingItemGallery = (LSettingItem)view.findViewById(R.id.fragment_profile_gallery);
        lSettingItemGallery.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Intent intentG = new Intent(getContext(), ManagePictureActivity.class);
                startActivity(intentG);
            }
        });
        lSettingItemSetting = (LSettingItem)view.findViewById(R.id.fragment_profile_setting);
        imageViewEdit.setOnClickListener(this);
        lSettingItemDownload.setOnClickListener(this);
        lSettingItemSetting.setOnClickListener(this);
        lSettingItemCollection.setOnClickListener(this);
        lSettingItemHistory.setOnClickListener(this);
        if (isUseUri){
            Log.d("-----","使用111");
            Picasso.with(getActivity()).load(cropUri).transform(new CircleTransform()).into(headImage);
        }else {
            Log.d("-----","不使用");
            Picasso.with(getActivity()).load(R.drawable.girl).transform(new CircleTransform()).into(headImage);

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_profile_download:

                break;
            case R.id.fragment_profile_history:

                break;
            case R.id.fragment_profile_collection:

                break;
            case R.id.fragment_profile_gallery:
                Toast.makeText(getContext(),"ddd",Toast.LENGTH_SHORT).show();
                Intent intentG = new Intent(getContext(), ManagePictureActivity.class);
                startActivity(intentG);
                break;
            case R.id.fragment_profile_setting:

                break;
            case R.id.fragment_profile_image_edit:
                //点击进行修改资料
                Intent intent = new Intent(getActivity(), EditUserInfoActivity.class);
                startActivity(intent);
                break;
        }
    }

}
