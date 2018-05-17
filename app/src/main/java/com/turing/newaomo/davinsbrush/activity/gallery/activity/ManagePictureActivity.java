package com.turing.newaomo.davinsbrush.activity.gallery.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lvleo.dataloadinglayout.DataLoadingLayout;
import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.activity.GalleryShow.SimpleArrayListAdapter;
import com.turing.newaomo.davinsbrush.activity.gallery.adapter.PictureManageShowFallAdapter;
import com.turing.newaomo.davinsbrush.activity.gallery.adapter.TestArrayAdapter;
import com.turing.newaomo.davinsbrush.mydb.MyDBHelper;

import java.util.ArrayList;
import java.util.List;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.IStatusListener;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;


public class ManagePictureActivity extends AppCompatActivity implements View.OnClickListener,DataLoadingLayout.OnViewTouchListener{
    private String color1;
    private String color2;
    private String color3;
    List<String> selectedColorList;
    String []colorList = new String[]{
            "黑色",
            "红色",
            "蓝色",
            "绿色",
            "黄色",
            "灰色",
            "白色"
    };

    SearchableSpinner mSearchableSpinner;
    SearchableSpinner mSearchableSpinner2;
    SearchableSpinner mSearchableSpinner3;
    SimpleArrayListAdapter mSimpleArrayListAdapter;
    Spinner mSpinner;
    final ArrayList<String> mStrings = new ArrayList<>();


    RecyclerView recyclerViewPictureManage;
    ArrayAdapter<String> mAdapter ;
    private String [] mStringArray;
    private PictureManageShowFallAdapter mAdapterPicture;
    RecyclerView.LayoutManager mLayoutManager;
    private List<String> pictureList = new ArrayList<>();
    private List<String> pictureList2 = new ArrayList<>();
    private List<String> pictureList3 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_picture);
        Fresco.initialize(this);
        MultiDex.install(this);
        initPicture();
        initSpinner();
        initStyleSpinner();
    }

    private void initStyleSpinner(){
        mSpinner=(Spinner) findViewById(R.id.spinner_style_manage);
        mStringArray=getResources().getStringArray(R.array.test_string_array);
        //使用自定义的ArrayAdapter
        mAdapter = new TestArrayAdapter(ManagePictureActivity.this,mStringArray);
        //设置下拉列表风格(这句不些也行)
        //mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);
        //监听Item选中事件
        mSpinner.setOnItemSelectedListener(new ItemSelectedListenerImpl());
    }
    private class ItemSelectedListenerImpl implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long arg3) {
            System.out.println("选中了:"+mStringArray[position]);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}

    }

    private void initPicture() {
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");

        mAdapterPicture = new PictureManageShowFallAdapter(ManagePictureActivity.this,pictureList );
        mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerViewPictureManage = (RecyclerView)findViewById(R.id.recyclerview_picture_manage);
        recyclerViewPictureManage.setLayoutManager(mLayoutManager);
        recyclerViewPictureManage.setAdapter(mAdapterPicture);
//        mLoadingLayout.setDataView(recyclerViewPictureManage);
//        mLoadingLayout.setOnMyViewTouchListener(this);
        // 建立数据源
        mAdapterPicture.setOnMyItemClickListener(new PictureManageShowFallAdapter.OnMyItemClickListener() {
            @Override
            public void myClick(View v, int position) {
                Toast.makeText(ManagePictureActivity.this,position+"", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void mLongClick(View v, int position) {
                Log.d("-----","-----"+position);
            }
        });
    }

//        标号  黑色 01    红色 02   蓝色 03 绿色 04  黄色 05  灰色 06  白色 07
        //蓝色  绿色  灰色
//        山水风景 城市交通 美食餐饮 星空夜景 田园山庄




    //第一个颜色圆圈的被选中事件
    //TODO 包括后面的都需要添加选中相应的图片后对应的事件
    private OnItemSelectedListener mOnItemSelectedListener1 = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(View view, int position, long id) {
            pictureList.clear();
            selectedColorList.remove(color1);
            color1 = colorList[position];
            selectedColorList.add(color1);
            MyDBHelper.getInstance().getAllPictureByColor(selectedColorList);
            Log.d("---选中的第一种颜色是---",color1);
            pictureList.addAll(selectedColorList);
            mAdapterPicture.notifyDataSetChanged();
        }
        @Override
        public void onNothingSelected() {
        }
    };
    //第二个颜色圆圈的被选中事件
    private OnItemSelectedListener mOnItemSelectedListener2 = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(View view, int position, long id) {
            pictureList.clear();
            selectedColorList.remove(color2);
            color2 = colorList[position];
            selectedColorList.add(color2);
            MyDBHelper.getInstance().getAllPictureByColor(selectedColorList);
            Log.d("---选中的第二种颜色是---",color2);
            pictureList.addAll(selectedColorList);
            mAdapterPicture.notifyDataSetChanged();
        }

        @Override
        public void onNothingSelected() {
        }
    };
    //第三个颜色圆圈的被选中事件
    private OnItemSelectedListener mOnItemSelectedListener3 = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(View view, int position, long id) {
            pictureList.clear();
            selectedColorList.remove(color3);
            color3 = colorList[position];
            selectedColorList.add(color3);
            MyDBHelper.getInstance().getAllPictureByColor(selectedColorList);
            Log.d("---选中的第三种颜色是---",color3);
            pictureList.addAll(selectedColorList);
            mAdapterPicture.notifyDataSetChanged();
        }

        @Override
        public void onNothingSelected() {
        }
    };
    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }

    @Override
    public void onTouchUp() {
        //获取数据  或者刷新
    }



    //TODO  加载上面的圆圈的颜色资源
    //        标号  黑色 01    红色 02   蓝色 03 绿色 04  黄色 05  灰色 06  白色 07
    private void initListValues() {
        mStrings.add("#000000");
        mStrings.add("#FF0000");
        mStrings.add("#0000FF");
        mStrings.add("#00FF00");
        mStrings.add("#FFFF00");
        mStrings.add("#A9A9A9");
        mStrings.add("#ffffff");

    }

    //加载Spinner
    private void initSpinner() {
        initListValues();
        //这是获取颜色的Adapter
        mSimpleArrayListAdapter = new SimpleArrayListAdapter(this, mStrings);
        mSearchableSpinner = (SearchableSpinner) findViewById(R.id.SearchableSpinner1);
        mSearchableSpinner.setAdapter(mSimpleArrayListAdapter);
        mSearchableSpinner.setOnItemSelectedListener(mOnItemSelectedListener1);
        mSearchableSpinner.setSelectedItem(0);
        mSearchableSpinner.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {}
            @Override
            public void spinnerIsClosing() {}
        });
        mSearchableSpinner2 = (SearchableSpinner) findViewById(R.id.SearchableSpinner2);
        mSearchableSpinner2.setAdapter(mSimpleArrayListAdapter);
        mSearchableSpinner2.setOnItemSelectedListener(mOnItemSelectedListener2);
        mSearchableSpinner2.setSelectedItem(0);
        mSearchableSpinner2.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {}
            @Override
            public void spinnerIsClosing() {}
        });
        mSearchableSpinner3 = (SearchableSpinner) findViewById(R.id.SearchableSpinner3);
        mSearchableSpinner3.setAdapter(mSimpleArrayListAdapter);
        mSearchableSpinner3.setOnItemSelectedListener(mOnItemSelectedListener3);
        mSearchableSpinner3.setSelectedItem(0);
        mSearchableSpinner3.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {}
            @Override
            public void spinnerIsClosing() {}
        });
    }

//    //刷新美食数据
//    private void refreshData_meishi() {
//        pictureList.clear();
//        pictureList.addAll(pictureList2);
//        pictureList.clear();
//        pictureList.add(R.drawable.p_02_01);
//        pictureList.add(R.drawable.p_02_02);
//        pictureList.add(R.drawable.p_02_03);
//        pictureList.add(R.drawable.p_02_04);
//        pictureList.add(R.drawable.p_02_05);
//        pictureList.add(R.drawable.p_02_06);
//        pictureList.add(R.drawable.p_02_07);
//        pictureList.add(R.drawable.p_02_08);
//        pictureList.add(R.drawable.p_02_09);
//        pictureList.add(R.drawable.p_02_10);
//        pictureList.add(R.drawable.p_02_11);
//        pictureList.add(R.drawable.p_02_12);
//        pictureList.add(R.drawable.p_02_13);
//        pictureList.add(R.drawable.p_02_14);
//        pictureList.add(R.drawable.p_02_15);
//        pictureList.add(R.drawable.p_02_16);
//        mAdapterPicture.notifyDataSetChanged();
////        mLoadingLayout.loadSuccess();
//    }
//    //加载所有山水图片
//    public void refreshData_shanshui_all(){
//        pictureList.clear();
//        pictureList.addAll(pictureList3);
//        mAdapterPicture.notifyDataSetChanged();
////        mLoadingLayout.loadSuccess();
//    }
//    //首先点击蓝色
//    public void refreshData_1_blue(){
//        pictureList.clear();
//        pictureList.add(R.drawable.picture_01_01_03_04_06);
//        pictureList.add(R.drawable.picture_02_01_03_04_06);
//        pictureList.add(R.drawable.picture_03_01_03_07_06);
//        pictureList.add(R.drawable.picture_04_01_03_06_01);
//        pictureList.add(R.drawable.picture_06_01_03_04_06);
//        pictureList.add(R.drawable.picture_08_01_03_07_01);
//        pictureList.add(R.drawable.picture_09_01_05_04_03);
//        pictureList.add(R.drawable.picture_10_01_06_03_04);
//        pictureList.add(R.drawable.picture_14_01_03_04_07);
//        pictureList.add(R.drawable.picture_19_01_03_04_07);
//        pictureList.add(R.drawable.picture_20_01_03_04_06);
//        pictureList.add(R.drawable.picture_25_01_03_06_07);
//        pictureList.add(R.drawable.picture_28_01_03_06_07);
//        pictureList.add(R.drawable.picture_29_01_03_04_07);
//        pictureList.add(R.drawable.picture_31_01_07_02_03);
//        pictureList.add(R.drawable.picture_32_01_07_03_04);
//        pictureList.add(R.drawable.picture_36_01_04_03_06);
//        pictureList.add(R.drawable.picture_37_01_03_04_06);
//        pictureList.add(R.drawable.picture_39_01_06_03_07);
//        pictureList.add(R.drawable.picture_41_01_03_04_06);
//        pictureList.add(R.drawable.picture_43_01_03_07_04);
//        pictureList.add(R.drawable.picture_45_01_03_07_06);
//        pictureList.add(R.drawable.picture_46_01_03_06_07);
//        pictureList.add(R.drawable.picture_47_01_03_07_04);
//        pictureList.add(R.drawable.picture_48_01_07_06_03);
//        pictureList.add(R.drawable.picture_54_01_04_07_03);
//        pictureList.add(R.drawable.picture_59_01_04_05_03);
//        pictureList.add(R.drawable.picture_60_01_03_01_05);
//        pictureList.add(R.drawable.picture_62_01_07_04_03);
//        pictureList.add(R.drawable.picture_66_01_03_06_04);
//        pictureList.add(R.drawable.picture_67_01_06_01_03);
//        pictureList.add(R.drawable.picture_70_01_03_04_06);
//        pictureList.add(R.drawable.picture_71_01_05_06_03);
//        pictureList.add(R.drawable.picture_72_01_01_03_07);
//        pictureList.add(R.drawable.picture_73_01_03_06_04);
//        pictureList.add(R.drawable.picture_74_01_06_03_07);
//        pictureList.add(R.drawable.picture_75_01_03_05_01);
//        mAdapterPicture.notifyDataSetChanged();
////        mLoadingLayout.loadSuccess();
// }
//    //然后点击蓝色+绿色
//    public void refreshData_2_green(){
//        pictureList.clear();
//        pictureList.add(R.drawable.picture_01_01_03_04_06);
//        pictureList.add(R.drawable.picture_06_01_03_04_06);
//        pictureList.add(R.drawable.picture_07_01_07_03_04);
//        pictureList.add(R.drawable.picture_09_01_05_04_03);
//        pictureList.add(R.drawable.picture_14_01_03_04_07);
//        pictureList.add(R.drawable.picture_19_01_03_04_07);
//        pictureList.add(R.drawable.picture_20_01_03_04_06);
//        pictureList.add(R.drawable.picture_32_01_07_03_04);
//        pictureList.add(R.drawable.picture_36_01_04_03_06);
//        pictureList.add(R.drawable.picture_37_01_03_04_06);
//        pictureList.add(R.drawable.picture_41_01_03_04_06);
//        pictureList.add(R.drawable.picture_43_01_03_07_04);
//        pictureList.add(R.drawable.picture_47_01_03_07_04);
//        pictureList.add(R.drawable.picture_53_01_04_07_03);
//        pictureList.add(R.drawable.picture_54_01_04_07_03);
//        pictureList.add(R.drawable.picture_58_01_03_04_06);
//        pictureList.add(R.drawable.picture_59_01_04_05_03);
//        pictureList.add(R.drawable.picture_62_01_07_04_03);
//        pictureList.add(R.drawable.picture_66_01_03_06_04);
//        pictureList.add(R.drawable.picture_70_01_03_04_06);
//        pictureList.add(R.drawable.picture_73_01_03_06_04);
//        pictureList.add(R.drawable.picture_10_01_06_03_04);
//        pictureList.add(R.drawable.picture_02_01_03_04_06);
//        pictureList.add(R.drawable.picture_29_01_03_04_07);
//        mAdapterPicture.notifyDataSetChanged();
////        mLoadingLayout.loadSuccess();
//    }
//    //然后点击灰色
    public void refreshData_3_grey(){
        pictureList.clear();
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("file://storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("file://storage/emulated/0/Pictures/1523975317496.jpg");
        pictureList.add("file://storage/emulated/0/Pictures/1523975317496.jpg");
//        pictureList.add(R.drawable.picture_01_01_03_04_06);
//        pictureList.add(R.drawable.picture_02_01_03_04_06);
//        pictureList.add(R.drawable.picture_58_01_03_04_06);
//        pictureList.add(R.drawable.picture_66_01_03_06_04);
//        pictureList.add(R.drawable.picture_37_01_03_04_06);
//        pictureList.add(R.drawabzle.picture_41_01_03_04_06);
//        pictureList.add(R.drawable.picture_06_01_03_04_06);
//        pictureList.add(R.drawable.picture_36_01_04_03_06);
//        pictureList.add(R.drawable.picture_70_01_03_04_06);
//        pictureList.add(R.drawable.picture_73_01_03_06_04);
//        pictureList.add(R.drawable.picture_10_01_06_03_04);
//        pictureList.add(R.drawable.picture_20_01_03_04_06);
        mAdapterPicture.notifyDataSetChanged();
//        mLoadingLayout.loadSuccess();
    }



    private Runnable mRunnable1 = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(1000);
        }
    };
    private Runnable mRunnable2 = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(2000);
        }
    };
    private Runnable mRunnable3 = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(3000);
        }
    };
    private Runnable mRunnable4 = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(4000);
        }
    };
    private Runnable mRunnable5 = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(5000);
        }
    };
    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1000:
                    break;
                case 2000:
                    break;
                case 3000:
                    break;
                case 4000:
                    break;
                case 5000:
                    refreshData_3_grey();
                    break;
            }
            super.handleMessage(msg);
        }
    };

//        标号  黑色 01    红色 02   蓝色 03 绿色 04  黄色 05  灰色 06  白色 07
    //蓝色  绿色  灰色
}
