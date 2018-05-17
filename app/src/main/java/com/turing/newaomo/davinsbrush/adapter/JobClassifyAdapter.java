package com.turing.newaomo.davinsbrush.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.beans.JobItem;

import java.util.List;

/**
 * Created by newao on 2018/2/7.
 */

public class JobClassifyAdapter extends BaseAdapter {

    private List<JobItem> data;
    private LayoutInflater layoutInflater;
    private Context context;

    public JobClassifyAdapter(Context context, List<JobItem> data){
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        convertView = layoutInflater.inflate(R.layout.item_job,parent,false);
        viewHolder = new ViewHolder();
        viewHolder.textViewJob = (TextView) convertView.findViewById(R.id.item_job);
        viewHolder.textViewJobDetail = (TextView)convertView.findViewById(R.id.item_jobDetail);
        JobItem jobItem = data.get(position);
        viewHolder.textViewJob.setText(jobItem.getJob());
        viewHolder.textViewJobDetail.setText(jobItem.getJobDetail());
        return convertView;
    }

    private class ViewHolder{
        TextView textViewJob;
        TextView textViewJobDetail;
    }

}
