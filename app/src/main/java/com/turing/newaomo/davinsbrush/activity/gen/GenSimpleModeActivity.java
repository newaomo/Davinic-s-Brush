package com.turing.newaomo.davinsbrush.activity.gen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.turing.newaomo.davinsbrush.MainActivity;
import com.turing.newaomo.davinsbrush.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;


public class GenSimpleModeActivity extends AppCompatActivity implements View.OnClickListener{


    private static final String TAG = MainActivity.class .getSimpleName();
    Button btn_startspeech;
    Button btn_content;
    Button btn_next;


    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String , String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super .onCreate(savedInstanceState) ;
        initView() ;
        initSpeech() ;
    }

    private void initView() {
        setContentView(R.layout.activity_gen_simple_mode) ;
        btn_content = (Button) findViewById(R.id.button_simple_text);
        btn_startspeech = (Button) findViewById(R.id.button_simple_speak );
        btn_next = (Button) findViewById(R.id.button_simple_next );
        btn_next.setVisibility(View.INVISIBLE);
        btn_startspeech .setOnClickListener(this) ;
        btn_next .setOnClickListener(this) ;
    }

    private void initSpeech() {
// 将“12345678”替换成您申请的 APPID，申请地址： http://www.xfyun.cn
// 请勿在 “ =”与 appid 之间添加任务空字符或者转义符
        SpeechUtility. createUtility( this, SpeechConstant. APPID + "=5a6be952" );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_simple_speak: //语音识别（把声音转文字）
                startSpeechDialog();
                break;
            case R.id.button_simple_next:// 语音合成（把文字转声音）
                speekText();
                break;
        }

    }

    private void speekText() {
//1. 创建 SpeechSynthesizer 对象 , 第二个参数： 本地合成时传 InitListener
        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer( this, null);
//2.合成参数设置，详见《 MSC Reference Manual》 SpeechSynthesizer 类
//设置发音人（更多在线发音人，用户可参见 附录 13.2
        mTts.setParameter(SpeechConstant. VOICE_NAME, "vixyun" ); // 设置发音人
        mTts.setParameter(SpeechConstant. SPEED, "50" );// 设置语速
        mTts.setParameter(SpeechConstant. VOLUME, "80" );// 设置音量，范围 0~100
        mTts.setParameter(SpeechConstant. ENGINE_TYPE, SpeechConstant. TYPE_CLOUD); //设置云端
//设置合成音频保存位置（可自定义保存位置），保存在 “./sdcard/iflytek.pcm”
//保存在 SD 卡需要在 AndroidManifest.xml 添加写 SD 卡权限
//仅支持保存为 pcm 和 wav 格式， 如果不需要保存合成音频，注释该行代码
        mTts.setParameter(SpeechConstant. TTS_AUDIO_PATH, "./sdcard/iflytek.pcm" );
//3.开始合成
        mTts.startSpeaking("合成马上开始，请稍等！", new MySynthesizerListener()) ;
    }

    class MySynthesizerListener implements SynthesizerListener {
        @Override
        public void onSpeakBegin() {
            showTip(" 开始播放 ");
        }

        @Override
        public void onSpeakPaused() {
            showTip(" 暂停播放 ");
        }

        @Override
        public void onSpeakResumed() {
            showTip(" 继续播放 ");
        }

        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos ,
                                     String info) {
// 合成进度
        }

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
// 播放进度
        }

        @Override
        public void onCompleted(SpeechError error) {
            if (error == null) {
                showTip("播放完成 ");
                Intent intent = new Intent(GenSimpleModeActivity.this,GenerateNewsActivity.class);
                startActivity(intent);
            } else if (error != null ) {
                showTip(error.getPlainDescription( true));
            }
        }

        @Override
        public void onEvent(int eventType, int arg1 , int arg2, Bundle obj) {
// 以下代码用于获取与云端的会话 id，当业务出错时将会话 id提供给技术支持人员，可用于查询会话日志，定位出错原因
// 若使用本地能力，会话 id为null
            if (SpeechEvent.EVENT_SESSION_ID == eventType) {
                String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
                Log.d(TAG, "session id =" + sid);
            }
        }
    }

    private void startSpeechDialog() {
        RecognizerDialog mDialog = new RecognizerDialog(this, new MyInitListener()) ;
        mDialog.setParameter(SpeechConstant. LANGUAGE, "zh_cn" );// 设置中文
        mDialog.setParameter(SpeechConstant. ACCENT, "mandarin" );
        mDialog.setListener( new MyRecognizerDialogListener()) ;
        mDialog.show() ;
    }

    private class MyRecognizerDialogListener implements RecognizerDialogListener {
        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            String result = results.getResultString(); //为解析的
            showTip(result) ;
            System. out.println(" 没有解析的 :" + result);
            String text = JsonParser.parseIatResult(result) ;//解析过后的
            System. out.println(" 解析后的 :" + text);
            String sn = null;
            try {
                JSONObject resultJson = new JSONObject(results.getResultString()) ;
                sn = resultJson.optString("sn" );
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mIatResults .put(sn, text) ;//没有得到一句，添加到

            StringBuffer resultBuffer = new StringBuffer();
            for (String key : mIatResults.keySet()) {
                resultBuffer.append(mIatResults .get(key));
            }
            btn_content.setText(resultBuffer.toString());// 设置输入框的文本
            btn_next.setVisibility(View.VISIBLE);
//            et_input .setSelection(et_input.length()) ;//把光标定位末尾
        }

        @Override
        public void onError(SpeechError speechError) {

        }
    }

    private class MyInitListener implements InitListener {
        @Override
        public void onInit(int code) {
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败 ");
            }

        }
    }

    //语音识别并输出
    private void startSpeech() {
        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer( this, null); //语音识别器
        mIat.setParameter(SpeechConstant. DOMAIN, "iat" );// 短信和日常用语： iat (默认)
        mIat.setParameter(SpeechConstant. LANGUAGE, "zh_cn" );// 设置中文
        mIat.setParameter(SpeechConstant. ACCENT, "mandarin" );// 设置普通话
        mIat.startListening( mRecoListener);
    }


    private RecognizerListener mRecoListener = new RecognizerListener() {
        public void onResult(RecognizerResult results, boolean isLast) {
            Log.e (TAG, results.getResultString());
            System.out.println(results.getResultString()) ;
            showTip(results.getResultString()) ;
        }
        public void onError(SpeechError error) {
            showTip(error.getPlainDescription(true)) ;
            Log. e(TAG, "error.getPlainDescription(true)==" + error.getPlainDescription(true ));
        }
        // 开始录音
        public void onBeginOfSpeech() {
            showTip(" 开始录音 ");
        }
        //volume 音量值0~30， data音频数据
        public void onVolumeChanged(int volume, byte[] data) {
            showTip(" 声音改变了 ");
        }
        // 结束录音
        public void onEndOfSpeech() {
            showTip(" 结束录音 ");
        }
        // 扩展用接口
        public void onEvent(int eventType, int arg1 , int arg2, Bundle obj) {
        }
    };

    private void showTip (String data) {
        Toast.makeText( this, data, Toast.LENGTH_SHORT).show() ;
    }
}




