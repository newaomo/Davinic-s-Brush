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
//public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
//
//    private List<HistoryItem> data;
//    private LayoutInflater layoutInflater;
//    private Context context;
//
//    @Override
//    public HistoryAdapter(Context context, List<HistoryItem> data){
//        this.context = context;
//        this.data = data;
//        this.layoutInflater = LayoutInflater.from(context);
//        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
//    }
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View convertView = layoutInflater.inflate(R.layout.item_share,parent,false);
//        return new ViewHolder();
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    private class ViewHolder extends RecyclerView.ViewHolder{
//        ImageView imageHistory;
//        TextView textSize;
//        TextView textType;
//        TextView textDate;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//        }
//    }
//
//
//}
