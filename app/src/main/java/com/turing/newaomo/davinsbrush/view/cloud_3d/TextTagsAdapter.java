package com.turing.newaomo.davinsbrush.view.cloud_3d;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.moxun.tagcloudlib.view.TagsAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by moxun on 16/1/19.
 */
public class TextTagsAdapter extends TagsAdapter {

    private List<String> dataSet = new ArrayList<>();

    public TextTagsAdapter(@NonNull String... data) {
        dataSet.clear();
        Collections.addAll(dataSet, data);
        initList();
    }

    private void initList(){
        dataSet.add("社会");
        dataSet.add("财政");
        dataSet.add("军事");
        dataSet.add("历史文化");
        dataSet.add("科技");
        dataSet.add("汽车");
        dataSet.add("房产");
        dataSet.add("体育");
        dataSet.add("娱乐");
        dataSet.add("健康");
        dataSet.add("亲子");
        dataSet.add("家居");
        dataSet.add("旅游");
        dataSet.add("时尚");
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public View getView(final Context context, final int position, ViewGroup parent) {
        TextView tv = new TextView(context);
        tv.setText(dataSet.get(position));
        //TODO 这里需要按照顺序提供一些新闻的关键词进行替换
        tv.setGravity(Gravity.CENTER);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Click", "Tag " + position + " clicked.");
                Toast.makeText(context, "Tag " + position + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
        tv.setTextColor(Color.WHITE);
        return tv;
    }

    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public int getPopularity(int position) {
        return position % 7;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor) {
        view.setBackgroundColor(themeColor);
    }
}