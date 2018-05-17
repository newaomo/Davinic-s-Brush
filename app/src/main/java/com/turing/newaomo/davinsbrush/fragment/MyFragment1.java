package com.turing.newaomo.davinsbrush.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.hanks.htextview.base.AnimationListener;
import com.hanks.htextview.base.HTextView;
import com.hanks.htextview.scale.ScaleTextView;
import com.jaeger.library.StatusBarUtil;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.activity.ShowPostMainAdapter;
import com.turing.newaomo.davinsbrush.activity.gen.Gen_By_Daily_Activity;
import com.turing.newaomo.davinsbrush.activity.gen.Gen_By_News_Activity;
import com.turing.newaomo.davinsbrush.activity.gen.Gen_By_Profile_Activity;
import com.turing.newaomo.davinsbrush.activity.gen.GenerateArticleActivity;
import com.turing.newaomo.davinsbrush.beans.ShowMainItem;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by newao on 2018/1/29.
 */

public class MyFragment1 extends Fragment implements View.OnClickListener{
//    Banner banner;
    CarouselView carouselView;
    ArrayList<String> urls = new ArrayList<>();
    ImageView funImage1;
    ImageView funImage2;
    ImageView funImage3;
    ImageView funImage4;
    View view;
    ScaleTextView scaleTextView1;
    ScaleTextView scaleTextView2;
    ScaleTextView scaleTextView3;
    ScaleTextView scaleTextView4;
    int m = 1;

    int[] sampleImages = {R.drawable.show_banner1, R.drawable.show_banner2, R.drawable.show_banner3, R.drawable.show_banner1, R.drawable.show_banner2};

    private ShowPostMainAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ShowMainItem> showMainItemList = new ArrayList<>();
    private RecyclerView recyclerView;
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_1,container,false);
        mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView = (RecyclerView)view.findViewById(R.id.fragment1_recyclerview) ;
        //全屏设置
//        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        StatusBarUtil.setTransparent(getActivity());
        StatusBarUtil.setTranslucent(getActivity(),110);
        mAdapter = new ShowPostMainAdapter(getContext(), buildData());
        mAdapter.setOnMyItemClickListener(new ShowPostMainAdapter.OnMyItemClickListener() {
            @Override
            public void myClick(View v, int position) {
                Toast.makeText(getActivity(),position+"", Toast.LENGTH_SHORT).show();
                Log.d("-----","-----"+position);
            }
            @Override
            public void mLongClick(View v, int position) {
                    Toast.makeText(getActivity(),"删除失败", Toast.LENGTH_SHORT).show();

                Log.d("-----","-----"+position);
            }
        });
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(mLayoutManager);
        initPictureData();

        setupRecyclerView((RecyclerView) view.findViewById(R.id.fragment1_recyclerview));

        funImage1 = (ImageView)view.findViewById(R.id.main_fr_1_image1);
        funImage2 = (ImageView)view.findViewById(R.id.main_fr_1_image2);
        funImage3 = (ImageView)view.findViewById(R.id.main_fr_1_image3);
        funImage4 = (ImageView)view.findViewById(R.id.main_fr_1_image4);
        scaleTextView1 = (ScaleTextView)view.findViewById(R.id.fragment_main_text1);
        scaleTextView2 = (ScaleTextView)view.findViewById(R.id.fragment_main_text2);
        scaleTextView3 = (ScaleTextView)view.findViewById(R.id.fragment_main_text3);
        scaleTextView4 = (ScaleTextView)view.findViewById(R.id.fragment_main_text4);
        scaleTextView1.setOnClickListener(new ClickListener());
        scaleTextView2.setOnClickListener(new ClickListener());
        scaleTextView3.setOnClickListener(new ClickListener());
        scaleTextView4.setOnClickListener(new ClickListener());
        scaleTextView1.setAnimationListener(new SimpleAnimationListener(getContext()));
        scaleTextView2.setAnimationListener(new SimpleAnimationListener(getContext()));
        scaleTextView4.setAnimationListener(new SimpleAnimationListener(getContext()));
        scaleTextView2.setAnimationListener(new SimpleAnimationListener(getContext()));

        funImage1.setOnClickListener(this);
        funImage2.setOnClickListener(this);
        funImage3.setOnClickListener(this);
        funImage4.setOnClickListener(this);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                // do something
                scaleTextView1.performClick();
                scaleTextView2.performClick();
                scaleTextView3.performClick();
                scaleTextView4.performClick();
            }

        }, 3 * 1000);

        carouselView = (CarouselView) view.findViewById(R.id.banner);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        return view;
    }



    private List<ShowMainItem> buildData() {
        showMainItemList = new ArrayList<>();
        for(int i=0;i<8;i++) {
            ShowMainItem showItem = new ShowMainItem();
            showItem.imagePath = "/storage/emulated/0/DCIM/Screenshots/Screenshot_2018-02-23-20-26-44-52.png";
            showItem.imgHeight =  200; //偶数和奇数的图片设置不同的高度，以到达错开的目的
            showMainItemList.add(showItem);
        }
        return showMainItemList;
    }


    private void setupRecyclerView(RecyclerView rv) {

        final TwinklingRefreshLayout refreshLayout = (TwinklingRefreshLayout) view.findViewById(R.id.refresh);
        ProgressLayout header = new ProgressLayout(getActivity());
        refreshLayout.setHeaderView(header);
        refreshLayout.setFloatRefresh(true);
        refreshLayout.setEnableOverScroll(false);
        refreshLayout.setHeaderHeight(140);
        refreshLayout.setMaxHeadHeight(240);
        refreshLayout.setTargetView(rv);

        refreshCard();

        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshCard();
                        refreshLayout.finishRefreshing();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadMoreCard();
                        refreshLayout.finishLoadmore();
                    }
                }, 2000);
            }
        });

        AppBarLayout appBarLayout = (AppBarLayout) view.findViewById(R.id.appbar_layout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {
                    refreshLayout.setEnableRefresh(true);
                    refreshLayout.setEnableOverScroll(false);
                } else {
                    refreshLayout.setEnableRefresh(false);
                    refreshLayout.setEnableOverScroll(false);
                }
            }
        });
    }

    void loadMoreCard() {
        showMainItemList = new ArrayList<>();
        for(int i=0;i<20;i++) {
            ShowMainItem showItem = new ShowMainItem();
            showItem.imagePath = "/storage/emulated/0/DCIM/Screenshots/Screenshot_2018-02-23-20-26-44-52.png";
            showItem.imgHeight =  200; //偶数和奇数的图片设置不同的高度，以到达错开的目的
            showMainItemList.add(showItem);
        }
        mAdapter.setDataList(showMainItemList);
        mAdapter.notifyDataSetChanged();
    }


    void refreshCard() {
        showMainItemList = new ArrayList<>();
        for(int i=0;i<15;i++) {
            ShowMainItem showItem = new ShowMainItem();
            showItem.imagePath = "/storage/emulated/0/DCIM/Screenshots/Screenshot_2018-02-23-20-26-44-52.png";
            showItem.imgHeight =  200; //偶数和奇数的图片设置不同的高度，以到达错开的目的
            showMainItemList.add(showItem);
        }

        mAdapter.setDataList(showMainItemList);
        mAdapter.notifyDataSetChanged();

    }

    public void initPictureData(){
        showMainItemList = new ArrayList<>();
        for(int i=0;i<8;i++) {
            ShowMainItem showItem = new ShowMainItem();
            showItem.imagePath = "/storage/emulated/0/DCIM/Screenshots/Screenshot_2018-02-23-20-26-44-52.png";
            showItem.imgHeight =  200; //偶数和奇数的图片设置不同的高度，以到达错开的目的
            showMainItemList.add(showItem);
        }
        mAdapter.setDataList(showMainItemList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_fr_1_image1:
//                SPPostUtils.getInstance().clearData();
                Intent intent1 = new Intent(getActivity(), Gen_By_Profile_Activity.class);
                startActivity(intent1);
                break;
            case R.id.main_fr_1_image2:
//                SPPostUtils.getInstance().clearData();
                Intent intent2 = new Intent(getActivity(), GenerateArticleActivity.class);
                startActivity(intent2);
                break;
            case R.id.main_fr_1_image3:
//                SPPostUtils.getInstance().clearData();
                Intent intent3 = new Intent(getActivity(), Gen_By_News_Activity.class);
                startActivity(intent3);
                break;
            case R.id.main_fr_1_image4:
//                SPPostUtils.getInstance().clearData();
                Intent intent4 = new Intent(getActivity(), Gen_By_Daily_Activity.class);
                startActivity(intent4);
                break;
        }
    }

    class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v instanceof HTextView) {
                }
                m = m+1;
                if((m%2) == 0 ){
                    ((HTextView) v).animateText("个 人 简 报");
                }else {
                    ((HTextView) v).animateText(" 凸显风格 做想要的自我");
                }
            }
        }

    class SimpleAnimationListener implements AnimationListener {

        private Context context;

        public SimpleAnimationListener(Context context) {
            this.context = context;
        }
        @Override
        public void onAnimationEnd(HTextView hTextView) {
            Toast.makeText(context, "Animation finished", Toast.LENGTH_SHORT).show();
        }
    }

}
