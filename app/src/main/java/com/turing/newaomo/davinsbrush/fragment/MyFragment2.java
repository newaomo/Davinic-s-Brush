package com.turing.newaomo.davinsbrush.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kaku.colorfulnews.mvp.ui.activities.NewsActivity;
import com.moxun.tagcloudlib.view.TagCloudView;
import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.view.cloud_3d.TextTagsAdapter;


/**
 * Created by newao on 2018/1/29.
 */

public class MyFragment2  extends Fragment {

    View view;
    private CardView cardViewNews;
    private TagCloudView fragmentTagcloud;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_2,container,false);;

        cardViewNews = (CardView) view.findViewById(R.id.main_fragment2_button_news);
        cardViewNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"开始选择新闻",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), NewsActivity.class);
                startActivity(intent);

            }
        });
        fragmentTagcloud = (TagCloudView) view.findViewById(R.id.fragment_tagcloud);
        TextTagsAdapter adapter = new TextTagsAdapter(new String[14]);
        fragmentTagcloud.setAdapter(adapter);
        return view;
    }



//    final View rootView = getActivity().getWindow().getDecorView();
//                if(Build.VERSION.SDK_INT >= 14) {
//        rootView.setDrawingCacheEnabled(true);
//        rootView.buildDrawingCache(true);
//        final Bitmap localBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
//        rootView.setDrawingCacheEnabled(false);
//        if (null != localBitmap && rootView instanceof ViewGroup) {
//            final View localView2 = new View(getActivity());
//            localView2.setBackgroundDrawable(new BitmapDrawable(getResources(), localBitmap));
//            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//            ((ViewGroup) rootView).addView(localView2, params);
//            localView2.animate().alpha(0).setDuration(400).setListener(new Animator.AnimatorListener() {
//                @Override
//                public void onAnimationStart(Animator animation) {
//                    ColorUiUtil.changeTheme(rootView, getActivity().getTheme());
//                }
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    ((ViewGroup) rootView).removeView(localView2);
//                    localBitmap.recycle();
//                }
//                @Override
//                public void onAnimationCancel(Animator animation) {
//                }
//                @Override
//                public void onAnimationRepeat(Animator animation) {
//                }
//            }).start();
//        }
//    } else {
//        ColorUiUtil.changeTheme(rootView, getActivity().getTheme());
//    }

}
