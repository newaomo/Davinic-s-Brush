/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.kaku.colorfulnews.mvp.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.kaku.colorfulnews.App;
import com.kaku.colorfulnews.common.Constants;
import com.kaku.colorfulnews.mvp.entity.NewsDetail;
import com.kaku.colorfulnews.mvp.presenter.impl.NewsDetailPresenterImpl;
import com.kaku.colorfulnews.mvp.ui.activities.base.BaseActivity;
import com.kaku.colorfulnews.mvp.view.NewsDetailView;
import com.kaku.colorfulnews.utils.MyUtils;
import com.kaku.colorfulnews.utils.NetUtil;
import com.kaku.colorfulnews.utils.TransformUtils;
import com.kaku.colorfulnews.widget.URLImageGetter;
import com.socks.library.KLog;
import com.turing.newaomo.davinsbrush.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;

/**
 * @author 咖枯
 * @version 1.0 2016/6/5
 */
public class NewsDetailActivity extends BaseActivity implements NewsDetailView {
    @BindView(R.id.news_detail_photo_iv)
    ImageView mNewsDetailPhotoIv;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    /*    @BindView(R.id.news_detail_title_tv)
        TextView mNewsDetailTitleTv;*/
    @BindView(R.id.news_detail_from_tv)
    TextView mNewsDetailFromTv;
    @BindView(R.id.news_detail_body_tv)
    TextView mNewsDetailBodyTv;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.mask_view)
    View mMaskView;

    private String imgSrc;
    private List<String> imgSrcList;
    private String content;
    private String title;;

    public void saveData(){
        SharedPreferences s = getSharedPreferences("newsDetail",MODE_PRIVATE);
        SharedPreferences.Editor editor = s.edit();
        Toast.makeText(NewsDetailActivity.this,title,Toast.LENGTH_SHORT).show();
        Log.d("title",title.replaceAll(" ",""));
        if (content != null && !content.equals("")){
            Log.d("content",content.replaceAll(" ","").replaceAll("\\s*",""));
        }
        Log.d("imgSrc",imgSrc);
        for (int i = 0;i < imgSrcList.size(); i++){
            Log.d("imgItem"+i,imgSrcList.get(i));
        }
        editor.putString("imgSrc",imgSrc);
        editor.putString("content",content);
        editor.putString("title",title);
        editor.putInt("numberImg",imgSrcList.size());
        for (int i = 0;i < imgSrcList.size(); i++){
            editor.putString("imgItem"+i,imgSrcList.get(i));
        }
        editor.apply();
//        finish();
//        Intent intent = new Intent(NewsDetailActivity.this,NewsDetailTestActivity.class);
//        startActivity(intent);
    }


    @Inject
    NewsDetailPresenterImpl mNewsDetailPresenter;

    private URLImageGetter mUrlImageGetter;
    private String mNewsTitle;
    private String mShareLink;

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public void initViews() {
        String postId = getIntent().getStringExtra(Constants.NEWS_POST_ID);
        mNewsDetailPresenter.setPosId(postId);
        mPresenter = mNewsDetailPresenter;
        mPresenter.attachView(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void setNewsDetail(NewsDetail newsDetail) {
        mShareLink = newsDetail.getShareLink();
        mNewsTitle = newsDetail.getTitle();
        String newsSource = newsDetail.getSource();
        String newsTime = MyUtils.formatDate(newsDetail.getPtime());
        String newsBody = newsDetail.getBody();
        String NewsImgSrc = getImgSrcs(newsDetail);


        setToolBarLayout(mNewsTitle);
//        mNewsDetailTitleTv.setText(newsTitle);
        mNewsDetailFromTv.setText(getString(R.string.news_from, newsSource, newsTime));
        setNewsDetailPhotoIv(NewsImgSrc);
        setNewsDetailBodyTv(newsDetail, newsBody);
    }

    private void setToolBarLayout(String newsTitle) {
        mToolbarLayout.setTitle(newsTitle);
        mToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white));
        mToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.primary_text_white));
    }

    private void setNewsDetailPhotoIv(String imgSrc0) {
        //TODO  这里将图片保存一下  发给服务器
//        Log.d("---这是图片的链接地址------",imgSrc0);
        imgSrc = imgSrc0;
        Glide.with(this).load(imgSrc0).asBitmap()
                .placeholder(R.drawable.ic_loading)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .error(R.drawable.ic_load_fail)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mNewsDetailPhotoIv)/*(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        mNewsDetailPhotoIv.setImageBitmap(resource);
                        mMaskView.setVisibility(View.VISIBLE);
                    }
                })*/;
    }

    private void setNewsDetailBodyTv(final NewsDetail newsDetail, final String newsBody) {
//        Log.d("-----newsBody是-------",newsBody);
        Pattern pattern = Pattern.compile("<img src=\".*?\" />");
        Matcher matcher = pattern.matcher(newsBody);
        imgSrcList = new ArrayList<>();
        while(matcher.find()){
            String e=matcher.group(0);
            String []strs = e.split("\"");
//            Log.d("-----这是后来提取出来的图片的地址",strs[1]);
            imgSrcList.add(strs[1]);
        }

        List<String> contentList = new ArrayList<>();

        Pattern pattern2 = Pattern.compile("<p>(.*?)</p>");
        Matcher matcher2 = pattern2.matcher(newsBody);
        while(matcher2.find()){
            String e=matcher2.group(0);
            e = e.replace("<p>","").replace("</p>","").replace("<strong>","").replace("<b>","").replace("</b>","").replaceAll(" ","");
            contentList.add(e);
        }
        int size = contentList.size();
        String contentResult = "";
        for (int i =0;i <size;i++){
            if (i == (size-1)){
//                Log.d("-----这是新闻的标题",contentList.get(i));
                title = contentList.get(i).substring(4);
            }else if (i == (size -2)){
                contentResult = contentResult + contentList.get(i).replaceAll(" ","");
//                Log.d("-----这是后来提取出来的段落内容",contentResult);
                content = contentResult.replaceAll(" ","");
            }else {
                contentResult = contentResult + contentList.get(i).replaceAll(" ","");
            }
        }

        mSubscription = Observable.timer(500, TimeUnit.MILLISECONDS)
                .compose(TransformUtils.<Long>defaultSchedulers())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        mProgressBar.setVisibility(View.GONE);
                        mFab.setVisibility(View.VISIBLE);
                        YoYo.with(Techniques.RollIn).playOn(mFab);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        setBody(newsDetail, newsBody);
                    }
                });
    }

    private void setBody(NewsDetail newsDetail, String newsBody) {
        int imgTotal = newsDetail.getImg().size();
        if (isShowBody(newsBody, imgTotal)) {
//              mNewsDetailBodyTv.setMovementMethod(LinkMovementMethod.getInstance());//加这句才能让里面的超链接生效,实测经常卡机崩溃
            mUrlImageGetter = new URLImageGetter(mNewsDetailBodyTv, newsBody, imgTotal);
            mNewsDetailBodyTv.setText(Html.fromHtml(newsBody, mUrlImageGetter, null));

            //TODO  这里是可以解析html中的图片地址的地方
//            <img src="http://cms-bucket.nosdn.127.net/catchpic/6/68/68eeefa8eb2dc0589554af80d7cd780b.jpg" />
        } else {
            mNewsDetailBodyTv.setText(Html.fromHtml(newsBody));
        }
    }

    private boolean isShowBody(String newsBody, int imgTotal) {
        return App.isHavePhoto() && imgTotal >= 2 && newsBody != null;
    }

    private String getImgSrcs(NewsDetail newsDetail) {
        List<NewsDetail.ImgBean> imgSrcs = newsDetail.getImg();
        String imgSrc;
        if (imgSrcs != null && imgSrcs.size() > 0) {
            imgSrc = imgSrcs.get(0).getSrc();
        } else {
            imgSrc = getIntent().getStringExtra(Constants.NEWS_IMG_RES);
        }
        return imgSrc;
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
//        mProgressBar.setVisibility(View.GONE);

    }

    @Override
    public void showMsg(String message) {
        mProgressBar.setVisibility(View.GONE);
        if (NetUtil.isNetworkAvailable()) {
            Snackbar.make(mAppBar, message, Snackbar.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.news_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_web_view:
//                openByWebView();
                saveData();
                break;
            case R.id.action_browser:
                openByBrowser();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openByWebView() {
        Intent intent = new Intent(this, NewsBrowserActivity.class);
        intent.putExtra(Constants.NEWS_LINK, mShareLink);
        intent.putExtra(Constants.NEWS_TITLE, mNewsTitle);
        startActivity(intent);
    }

    private void openByBrowser() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        if (canBrowse(intent)) {
            Uri uri = Uri.parse(mShareLink);
            intent.setData(uri);
            startActivity(intent);
        }
    }

    private boolean canBrowse(Intent intent) {
        return intent.resolveActivity(getPackageManager()) != null && mShareLink != null;
    }

    @Override
    protected void onDestroy() {
        cancelUrlImageGetterSubscription();
        super.onDestroy();

    }

    private void cancelUrlImageGetterSubscription() {
        try {
            if (mUrlImageGetter != null && mUrlImageGetter.mSubscription != null
                    && !mUrlImageGetter.mSubscription.isUnsubscribed()) {
                mUrlImageGetter.mSubscription.unsubscribe();
                KLog.d("UrlImageGetter unsubscribe");
            }
        } catch (Exception e) {
            KLog.e("取消UrlImageGetter Subscription 出现异常： " + e.toString());
        }
    }

    @OnClick(R.id.fab)
    public void onClick() {
        share();
    }

    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share));
        intent.putExtra(Intent.EXTRA_TEXT, getShareContents());
        startActivity(Intent.createChooser(intent, getTitle()));
    }

    @NonNull
    private String getShareContents() {
        if (mShareLink == null) {
            mShareLink = "";
        }
        return getString(R.string.share_contents, mNewsTitle, mShareLink);
    }
}
