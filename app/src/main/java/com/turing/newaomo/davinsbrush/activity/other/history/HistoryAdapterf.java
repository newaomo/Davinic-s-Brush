//package com.turing.newaomo.davinsbrush.activity.other.history;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
//import com.turing.newaomo.davinsbrush.R;
//
//import java.util.List;
//
///**
// * Created by newao on 2018/5/14.
// */
//
//public class HistoryAdapterf extends RecyclerView.Adapter<HistoryAdapterf.ViewHolder> {
//
//    private List<HistoryItem> data;
//    private LayoutInflater layoutInflater;
//    private Context context;
//
//    public HistoryAdapterf(Context context, List<HistoryItem> data){
//        this.context = context;
//        this.data = data;
//        this.layoutInflater = LayoutInflater.from(context);
//        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
//
//
//
//    }
//
//    @Override
//    public int getCount() {
//        return data.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return data.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder = null;
//        convertView = layoutInflater.inflate(R.layout.item_share,parent,false);
//        viewHolder = new ViewHolder();
//        viewHolder.imageHistory = (ImageView) convertView.findViewById(R.id.item_image_history);
//        viewHolder.textSize = (TextView)convertView.findViewById(R.id.item_history_size);
//        viewHolder.textType = (TextView)convertView.findViewById(R.id.item_history_type);
//        viewHolder.textDate = (TextView)convertView.findViewById(R.id.item_history_date);
//        HistoryItem historyItem = data.get(position);
//        viewHolder.imageHistory = (ImageView) convertView.findViewById(R.id.item_image_history);
//        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnFail(R.drawable.spinner_drawable)
//                .showImageForEmptyUri(R.drawable.ic_news_empty)
//                .showImageOnLoading(R.drawable.ic_loading).build();
//        ImageLoader.getInstance().displayImage("file://" + historyItem.getPath(), viewHolder.imageHistory, options);
//        viewHolder.textSize.setText(historyItem.getSize());
//        viewHolder.textType.setText(historyItem.getType());
//        viewHolder.textDate.setText(historyItem.getDate());
//        return convertView;
//    }
//
//    private class ViewHolder{
//        ImageView imageHistory;
//        TextView textSize;
//        TextView textType;
//        TextView textDate;
//    }
//
//}
