package com.turing.newaomo.davinsbrush.view.grade;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.turing.newaomo.davinsbrush.R;


/**
 * Created by newao on 2018/2/22.
 */

public class GradeDialog extends Dialog {

    private final Context mContext;
    private Button button;
    private RatingBar ratingBar;
    private float evaluateNumber = 0;

    public GradeDialog(Context context) {
        super(context);
        mContext = context;
        initView();
        initListView();
    }

    private void initView() {
        View contentView = View.inflate(mContext, R.layout.grade_dialog, null);
        button = (Button)contentView.findViewById(R.id.button_grade_sure);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (evaluateNumber == 0){
                    Toast.makeText(getContext(),"还没有打分呢",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"您打分为"+evaluateNumber,Toast.LENGTH_SHORT).show();
                }
            }
        });
        ratingBar = (RatingBar)contentView.findViewById(R.id.ratingbar_grade);
        setContentView(contentView);
    }



    private void initListView() {
        ratingBar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {
                evaluateNumber = ratingCount;
            }
        });
    }

//
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        if (!hasFocus) {
//            return;
//        }
//        setHeight();
//    }
//
//    private void setHeight() {
//        Window window = getWindow();
//        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
//        WindowManager.LayoutParams attributes = window.getAttributes();
//        if (window.getDecorView().getHeight() >= (int) (displayMetrics.heightPixels * 0.6)) {
//            attributes.height = (int) (displayMetrics.heightPixels * 0.6);
//        }
//        window.setAttributes(attributes);
//    }


}
