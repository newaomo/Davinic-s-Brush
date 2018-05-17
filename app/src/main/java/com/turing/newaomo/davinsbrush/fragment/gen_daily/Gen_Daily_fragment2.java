package com.turing.newaomo.davinsbrush.fragment.gen_daily;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.activity.gen.Gen_By_Article_Activity;
import com.turing.newaomo.davinsbrush.utils.SP.SPPostUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by newao on 2018/2/6.
 */

public class Gen_Daily_fragment2  extends Fragment implements  View.OnClickListener {
    public static Gen_Daily_fragment2 newInstance() {
        return new Gen_Daily_fragment2();
    }
    private static final String TAG = "Gen_fragment_article1";
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String , String>();


    View root;
    //    private TyperEditText mTyperEditText;
    private LinearLayout linearSize1;
    private LinearLayout linearSize2;
    private LinearLayout linearSize3;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private Button buttonEdit;
    private static EditText editTextWidth,editTextHeight;
    private Button buttonNext;
    private LinearLayout layout;

    private void initSpeech() {
        // 将“12345678”替换成您申请的 APPID，申请地址： http://www.xfyun.cn
        // 请勿在 “ =”与 appid 之间添加任务空字符或者转义符
//        SpeechUtility. createUtility( getContext(), SpeechConstant. APPID + "=5a6be952" );
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.gen_daily_fragment2, container, false);
        layout = (LinearLayout)root.findViewById(R.id.daily_button_select_linear) ;
        layout.setVisibility(View.INVISIBLE);
        textView1 = (TextView)root.findViewById(R.id.daily_button_select_size_1);
        textView2 = (TextView)root.findViewById(R.id.daily_button_select_size_2);
        textView3 = (TextView)root.findViewById(R.id.daily_button_select_size_3);
        linearSize1 = (LinearLayout) root.findViewById(R.id.daily_linear_1_size);
        linearSize2 = (LinearLayout)root.findViewById(R.id.daily_linear_2_size);
        linearSize3 = (LinearLayout)root.findViewById(R.id.daily_linear_3_size);
        buttonEdit = (Button)root.findViewById(R.id.daily_button_select_size_edit);
        editTextWidth = (EditText)root.findViewById(R.id.daily_edittext_size_width);
        editTextHeight = (EditText)root.findViewById(R.id.daily_edittext_size_height);
        buttonNext = (Button)root.findViewById(R.id.daily_button_daily_next2);
        buttonNext.setOnClickListener(this);
        linearSize1.setOnClickListener(this);
        linearSize2.setOnClickListener(this);
        linearSize3.setOnClickListener(this);
        buttonEdit.setOnClickListener(this);

        initSpeech() ;
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_article_next1:
                //TODO  在这里添加存储该路径的代码
                saveData();
                Gen_By_Article_Activity.viewPager.setCurrentItem(1);
                break;
            case R.id.article_select_size_1:
                SPPostUtils.getInstance().setSizeWidth("1200");
                SPPostUtils.getInstance().setSizeHeight("1920");
                layout.setVisibility(View.INVISIBLE);
                textView1.setBackgroundResource(R.drawable.button_select_size_edit);
                textView2.setBackgroundResource(R.drawable.button_select_size);
                textView3.setBackgroundResource(R.drawable.button_select_size);
                buttonEdit.setBackgroundResource(R.drawable.button_select_size);
                textView1.setTextColor(Color.parseColor("#ffffff"));
                textView2.setTextColor(Color.parseColor("#000000"));
                textView3.setTextColor(Color.parseColor("#000000"));
                buttonEdit.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.article_select_size_2:
                SPPostUtils.getInstance().setSizeWidth("1080");
                SPPostUtils.getInstance().setSizeHeight("1920");
                layout.setVisibility(View.INVISIBLE);
                textView1.setBackgroundResource(R.drawable.button_select_size);
                textView2.setBackgroundResource(R.drawable.button_select_size_edit);
                textView3.setBackgroundResource(R.drawable.button_select_size);
                buttonEdit.setBackgroundResource(R.drawable.button_select_size);
                textView1.setTextColor(Color.parseColor("#000000"));
                textView2.setTextColor(Color.parseColor("#ffffff"));
                textView3.setTextColor(Color.parseColor("#000000"));
                buttonEdit.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.article_select_size_3:
                SPPostUtils.getInstance().setSizeWidth("720");
                SPPostUtils.getInstance().setSizeHeight("1280");
                layout.setVisibility(View.INVISIBLE);
                textView1.setBackgroundResource(R.drawable.button_select_size);
                textView2.setBackgroundResource(R.drawable.button_select_size);
                textView3.setBackgroundResource(R.drawable.button_select_size_edit);
                buttonEdit.setBackgroundResource(R.drawable.button_select_size);
                textView1.setTextColor(Color.parseColor("#000000"));
                textView2.setTextColor(Color.parseColor("#000000"));
                textView3.setTextColor(Color.parseColor("#ffffff"));
                buttonEdit.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.article_button_select_size_edit:
                SPPostUtils.getInstance().setSizeWidth("720");
                SPPostUtils.getInstance().setSizeHeight("1280");
                layout.setVisibility(View.VISIBLE);
                textView1.setBackgroundResource(R.drawable.button_select_size);
                textView2.setBackgroundResource(R.drawable.button_select_size);
                textView3.setBackgroundResource(R.drawable.button_select_size);
                buttonEdit.setBackgroundResource(R.drawable.button_select_size_edit);
                textView1.setTextColor(Color.parseColor("#000000"));
                textView2.setTextColor(Color.parseColor("#000000"));
                textView3.setTextColor(Color.parseColor("#000000"));
                buttonEdit.setTextColor(Color.parseColor("#ffffff"));
                break;

        }
    }

    public void saveData(){
        if (!TextUtils.isEmpty(editTextWidth.getText().toString())&&!TextUtils.isEmpty(editTextWidth.getText().toString())){
            SPPostUtils.getInstance().setSizeWidth(editTextWidth.getText().toString());
            SPPostUtils.getInstance().setSizeHeight(editTextHeight.getText().toString());
        }else {
//            Toast.makeText(getActivity(),"请正确填写！", Toast.LENGTH_SHORT).show();
            return;
        }
    }

//    private void speekText() {
//        //1. 创建 SpeechSynthesizer 对象 , 第二个参数： 本地合成时传 InitListener
//        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer(getContext(), null);
////2.合成参数设置，详见《 MSC Reference Manual》 SpeechSynthesizer 类
////设置发音人（更多在线发音人，用户可参见 附录 13.2
//        mTts.setParameter(SpeechConstant. VOICE_NAME, "vixyun" ); // 设置发音人
//        mTts.setParameter(SpeechConstant. SPEED, "50" );// 设置语速
//        mTts.setParameter(SpeechConstant. VOLUME, "80" );// 设置音量，范围 0~100
//        mTts.setParameter(SpeechConstant. ENGINE_TYPE, SpeechConstant. TYPE_CLOUD); //设置云端
////设置合成音频保存位置（可自定义保存位置），保存在 “./sdcard/iflytek.pcm”
////保存在 SD 卡需要在 AndroidManifest.xml 添加写 SD 卡权限
////仅支持保存为 pcm 和 wav 格式， 如果不需要保存合成音频，注释该行代码
//        mTts.setParameter(SpeechConstant. TTS_AUDIO_PATH, "./sdcard/iflytek.pcm" );
////3.开始合成
//        mTts.startSpeaking("随便说就把", new MySynthesizerListener()) ;
//
//    }
//
//    class MySynthesizerListener implements SynthesizerListener {
//
//        @Override
//        public void onSpeakBegin() {
//            showTip(" 开始播放 ");
//        }
//
//        @Override
//        public void onSpeakPaused() {
//            showTip(" 暂停播放 ");
//        }
//
//        @Override
//        public void onSpeakResumed() {
//            showTip(" 继续播放 ");
//        }
//
//        @Override
//        public void onBufferProgress(int percent, int beginPos, int endPos ,
//                                     String info) {
//            // 合成进度
//        }
//
//        @Override
//        public void onSpeakProgress(int percent, int beginPos, int endPos) {
//            // 播放进度
//        }
//
//        @Override
//        public void onCompleted(SpeechError error) {
//            if (error == null) {
//                showTip("播放完成 ");
//            } else if (error != null ) {
//                showTip(error.getPlainDescription( true));
//            }
//        }
//
//        @Override
//        public void onEvent(int eventType, int arg1 , int arg2, Bundle obj) {
//            // 以下代码用于获取与云端的会话 id，当业务出错时将会话 id提供给技术支持人员，可用于查询会话日志，定位出错原因
//            // 若使用本地能力，会话 id为null
//            //if (SpeechEvent.EVENT_SESSION_ID == eventType) {
//            //     String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
//            //     Log.d(TAG, "session id =" + sid);
//            //}
//        }
//    }
//
//    private void startSpeechDialog() {
//        //1. 创建RecognizerDialog对象
//        RecognizerDialog mDialog = new RecognizerDialog(getContext(), new MyInitListener()) ;
//        //2. 设置accent、 language等参数
//        mDialog.setParameter(SpeechConstant. LANGUAGE, "zh_cn" );// 设置中文
//        mDialog.setParameter(SpeechConstant. ACCENT, "mandarin" );
//        // 若要将UI控件用于语义理解，必须添加以下参数设置，设置之后 onResult回调返回将是语义理解
//        // 结果
//        // mDialog.setParameter("asr_sch", "1");
//        // mDialog.setParameter("nlp_version", "2.0");
//        //3.设置回调接口
//        mDialog.setListener( new MyRecognizerDialogListener()) ;
//        //4. 显示dialog，接收语音输入
//        mDialog.show() ;
//    }
//
//    class MyRecognizerDialogListener implements RecognizerDialogListener {
//
//        /**
//         * @param results
//         * @param isLast  是否说完了
//         */
//        @Override
//        public void onResult(RecognizerResult results, boolean isLast) {
//            String result = results.getResultString(); //为解析的
//            showTip(result) ;
//            System. out.println(" 没有解析的 :" + result);
//
//            String text = JsonParser.parseIatResult(result) ;//解析过后的
//            System. out.println(" 解析后的 :" + text);
//
//            String sn = null;
//            // 读取json结果中的 sn字段
//            try {
//                JSONObject resultJson = new JSONObject(results.getResultString()) ;
//                sn = resultJson.optString("sn" );
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            mIatResults .put(sn, text) ;//没有得到一句，添加到
//
//            StringBuffer resultBuffer = new StringBuffer();
//            for (String key : mIatResults.keySet()) {
//                resultBuffer.append(mIatResults .get(key));
//            }
//
//            Log.d(TAG,"您说了------"+resultBuffer.toString());
//            if (resultBuffer.toString().contains("下一步")){
//                Gen_By_Article_Activity.viewPager.setCurrentItem(1);
//            }
////            et_input.setText(resultBuffer.toString());// 设置输入框的文本
////            et_input .setSelection(et_input.length()) ;//把光标定位末尾
////            if (resultBuffer.toString().equals("下一步。")){
////
////                et_input.setText("哈哈哈哈哈");
////                speekText();
////                try {
////                    Thread.sleep(3000);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
////                et_input.setText("");
////                startSpeechDialog();
////            }
//        }
//
//        @Override
//        public void onError(SpeechError speechError) {
//
//        }
//    }
//
//    class MyInitListener implements InitListener {
//
//        @Override
//        public void onInit(int code) {
//            if (code != ErrorCode.SUCCESS) {
//                showTip("初始化失败 ");
//            }
//
//        }
//    }
//
//    /**
//     * 语音识别
//     */
//    private void startSpeech() {
//        //1. 创建SpeechRecognizer对象，第二个参数： 本地识别时传 InitListener
//        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer(getContext(), null); //语音识别器
//        //2. 设置听写参数，详见《 MSC Reference Manual》 SpeechConstant类
//        mIat.setParameter(SpeechConstant. DOMAIN, "iat" );// 短信和日常用语： iat (默认)
//        mIat.setParameter(SpeechConstant. LANGUAGE, "zh_cn" );// 设置中文
//        mIat.setParameter(SpeechConstant. ACCENT, "mandarin" );// 设置普通话
//        //3. 开始听写
//        mIat.startListening( mRecoListener);
//    }
//
//
//    // 听写监听器
//    private RecognizerListener mRecoListener = new RecognizerListener() {
//        // 听写结果回调接口 (返回Json 格式结果，用户可参见附录 13.1)；
////一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
////关于解析Json的代码可参见 Demo中JsonParser 类；
////isLast等于true 时会话结束。
//        public void onResult(RecognizerResult results, boolean isLast) {
//            Log.e (TAG, results.getResultString());
//            System.out.println(results.getResultString()) ;
//            showTip(results.getResultString()) ;
//        }
//
//        // 会话发生错误回调接口
//        public void onError(SpeechError error) {
//            showTip(error.getPlainDescription(true)) ;
//            // 获取错误码描述
//            Log. e(TAG, "error.getPlainDescription(true)==" + error.getPlainDescription(true ));
//        }
//
//        // 开始录音
//        public void onBeginOfSpeech() {
//            showTip(" 开始录音 ");
//        }
//
//        //volume 音量值0~30， data音频数据
//        public void onVolumeChanged(int volume, byte[] data) {
//            showTip(" 声音改变了 ");
//        }
//
//        // 结束录音
//        public void onEndOfSpeech() {
//            showTip(" 结束录音 ");
//        }
//
//        // 扩展用接口
//        public void onEvent(int eventType, int arg1 , int arg2, Bundle obj) {
//        }
//    };
//
//    private void showTip (String data) {
//        Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show() ;
//    }


}
