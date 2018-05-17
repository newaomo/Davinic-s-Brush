package com.turing.newaomo.davinsbrush.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.hanks.htextview.base.AnimationListener;
import com.hanks.htextview.base.HTextView;
import com.turing.newaomo.davinsbrush.R;

public class TestActivity extends AppCompatActivity {


    String[] sentences = {"What is design?",
            "Design is not just",
            "what it looks like and feels like.",
            "Design is how it works. \n- Steve Jobs",
            "Older people",
            "sit down and ask,",
            "'What is it?'",
            "but the boy asks,",
            "'What can I do with it?'. \n- Steve Jobs",
            "Swift",
            "Objective-C",
            "iPhone",
            "iPad",
            "Mac Mini", "MacBook Pro", "Mac Pro", "爱老婆", "老婆和女儿"};
    int index;

    class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v instanceof HTextView) {
                if (index + 1 >= sentences.length) {
                    index = 0;
                }
                ((HTextView) v).animateText(sentences[index++]);
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


    private HTextView textView, textView2, textView3, textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        textView = (HTextView) findViewById(R.id.textview);
        textView1 = (HTextView) findViewById(R.id.textview1);
        textView2 = (HTextView) findViewById(R.id.textview2);
        textView3 = (HTextView) findViewById(R.id.textview3);

        textView.setOnClickListener(new ClickListener());
        textView1.setOnClickListener(new ClickListener());
        textView2.setOnClickListener(new ClickListener());
        textView3.setOnClickListener(new ClickListener());

        textView.setAnimationListener(new SimpleAnimationListener(this));
        textView1.setAnimationListener(new SimpleAnimationListener(this));
        textView2.setAnimationListener(new SimpleAnimationListener(this));
        textView3.setAnimationListener(new SimpleAnimationListener(this));

        new Handler().postDelayed(new Runnable() {
            public void run() {
                // do something
                textView.performClick();
            }

        }, 3 * 1000);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                // do something
                textView.performClick();
            }

        }, 6 * 1000);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                // do something
                textView.performClick();
            }

        }, 8 * 1000);

        textView3.animateText("this is a init sentence");

        ((SeekBar) findViewById(R.id.seekbar)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setProgress(progress / 100f);
                textView1.setProgress(progress / 100f);
                textView2.setProgress(progress / 100f);
                textView3.setProgress(progress / 100f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}