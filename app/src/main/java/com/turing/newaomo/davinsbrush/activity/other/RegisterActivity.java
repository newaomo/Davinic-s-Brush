package com.turing.newaomo.davinsbrush.activity.other;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.turing.newaomo.davinsbrush.MainActivity;
import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.view.login_register.animationUtils.TransitionController;
import com.turing.newaomo.davinsbrush.view.login_register.animationUtils.ViewAnimationCompatUtils;
import com.turing.newaomo.davinsbrush.view.login_register.listener.TransitionCustomListener;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.turing.newaomo.davinsbrush.activity.other.LoginActivity.JSON;

public class RegisterActivity extends AppCompatActivity {

    FloatingActionButton fab;
    CardView cvAdd;

    private EditText editTextDoudou;
    private EditText editTextPhone;
    private EditText editTextPass;
    private EditText editTextPassRe;
    private Button button_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        MobSDK.init(this, "23f9d74a80c40","c18f8ebda7f2a18bfc5f6f84fe51331e");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initListener();
        fab = (FloatingActionButton) findViewById(R.id.fab);
        cvAdd = (CardView) findViewById(R.id.cv_add);

        cvAdd.setVisibility(View.INVISIBLE);
        TransitionController.getInstance().setEnterListener(new TransitionCustomListener() {
            @Override
            public void onTransitionStart(Animator animator) {
                //cvAdd.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onTransitionEnd(Animator animator) {
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Animator animator) {

            }
        });
        TransitionController.getInstance().show(this,getIntent());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateRevealClose();
            }
        });
    }

    private void initView(){
        editTextDoudou = (EditText)findViewById(R.id.register_doudou);
        editTextPhone = (EditText)findViewById(R.id.register_edit_phone);
        editTextPass = (EditText)findViewById(R.id.register_edit_pass);
        editTextPassRe = (EditText)findViewById(R.id.register_edit_pass_re);
        button_register = (Button)findViewById(R.id.register_btn_to_main);
    }

    private void initListener(){
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (isSamePassword(editTextPass.getText().toString(),editTextPassRe.getText().toString())){
//                    Intent intent = new Intent(RegisterActivity.this,SMSCodeActivity.class);
//                    intent.putExtra("phoneNumber",editTextPhone.getText().toString());
//                    intent.putExtra("password",editTextPass.getText().toString());
//                    startActivity(intent);
//                }else {
//                    Toast.makeText(RegisterActivity.this,"您两次输入的密码不一致，请重试",Toast.LENGTH_SHORT).show();
//                }
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            register();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //                postJson();
                    }
                }.start();

            }
        });
    }

    public void register()throws IOException{
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient mOkHttpClient = new OkHttpClient();
                    String jsonToServer = "";
                    jsonToServer = "{\n" +
                            "    \"phoneNumber\": \""+ editTextPhone.getText().toString()+ "\",\n" +
                            "    \"password\": \""+editTextPass.getText().toString()+"\"\n" +
                            "}";
                    RequestBody requestBody = RequestBody.create(JSON, jsonToServer);

                    Request request = new Request.Builder().
                            url(editTextDoudou.getText().toString()).
                            addHeader("Connection", "Keep-Alive").
                            addHeader("Charset", "UTF-8").
                            addHeader("User-Agent","  Mozilla/5.0 (Windows; U; Windows NT 5.2) Gecko/2008070208 Firefox/3.0.1").
                            addHeader("Accept","*/*").
                            post(requestBody).
                            build();
                    try {
                        mOkHttpClient.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String responseData = response.body().string();
                                Log.d("-----",responseData);
                                Toast.makeText(RegisterActivity.this,responseData.toString(),Toast.LENGTH_SHORT).show();
                                showResponse(responseData);

                            }
                        });
//                        Response response = mOkHttpClient.newCall(request).execute();
//                        String responseData = response.body().string();
//                        Log.d("-----",responseData);
//                        Toast.makeText(RegisterActivity.this,responseData.toString(),Toast.LENGTH_SHORT).show();
//                        showResponse(responseData);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }catch (Exception e){
                    Log.e("====",e.getMessage());
                }
            }
        }).start();

    }

    public void showResponse(String responseData){
        if (responseData.contains("register_success")){
            Log.d("-----",responseData);
            Toast.makeText(RegisterActivity.this,responseData.toString(),Toast.LENGTH_SHORT).show();
            String id = responseData.substring(16);
            Log.d("-----",id);
            Toast.makeText(RegisterActivity.this,id,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }

    //判断是不是手机号
    private static boolean isNumLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }


    //判断两次的密码是否相同
    private boolean isSamePassword(String pass,String passRe){
        if (pass.toString().equals(passRe.toString())){
            return true;
        }
        return false;
    }

    //去下一个活动SMSCodeActivity  TODO 注意之后改成在验证码的界面进行注册  验证码通过之后在进行自己这里的注册
    public void gotoSMS(){
        if (isNumLegal(editTextPhone.getText().toString()) && isSamePassword(editTextPass.getText().toString(),editTextPassRe.getText().toString())){
            String phoneNumber = editTextPhone.getText().toString();
            String password = editTextPass.getText().
                    toString();
            Intent intent = new Intent(RegisterActivity.this,SMSCodeActivity.class);
            intent.putExtra("phoneNumber",phoneNumber);
            intent.putExtra("password",password);
            startActivity(intent);
            finish();
        }
    }









    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationCompatUtils.createCircularReveal(cvAdd, cvAdd.getWidth()/2,0, fab.getWidth() / 2, cvAdd.getHeight());
        mAnimator.setDuration(300);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationCompatUtils.createCircularReveal(cvAdd,cvAdd.getWidth()/2,0, cvAdd.getHeight(), fab.getWidth() / 2);
        mAnimator.setDuration(300);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab.setImageResource(R.drawable.plus);
//                RegisterActivity.super.onBackPressed();
                TransitionController.getInstance().exitActivity(RegisterActivity.this);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }
    @Override
    public void onBackPressed() {
        animateRevealClose();
    }


}