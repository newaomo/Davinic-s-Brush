package com.turing.newaomo.davinsbrush.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.turing.newaomo.davinsbrush.MainActivity;
import com.turing.newaomo.davinsbrush.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login_RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    public static final MediaType JSON= MediaType.parse("application/json; charset=utf-8");
    public void showLoginResponse(final String responseData) {
        if (responseData.startsWith("login_success")) {
            Log.d("-----", responseData);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Login_RegisterActivity.this, responseData.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            final String id = responseData.substring(15);
            Log.d("-----", "ip=========================================" + id);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Login_RegisterActivity.this, id, Toast.LENGTH_SHORT).show();
                }
            });
            Intent intent = new Intent(Login_RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (responseData.equals("no_this_phone")) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Login_RegisterActivity.this, "没有这个电话号码", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (responseData.equals("password_wrong")) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Login_RegisterActivity.this, "账号存在 密码错误", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Login_RegisterActivity.this, "草拟吗什么错", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void showRegisterResponse(final String responseData) {
        if (responseData.startsWith("login_success")) {
            Log.d("-----", responseData);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Login_RegisterActivity.this, responseData.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            final String id = responseData.substring(15);
            Log.d("-----", "ip=========================================" + id);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Login_RegisterActivity.this, id, Toast.LENGTH_SHORT).show();
                }
            });
            Intent intent = new Intent(Login_RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (responseData.equals("no_this_phone")) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Login_RegisterActivity.this, "没有这个电话号码", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (responseData.equals("password_wrong")) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Login_RegisterActivity.this, "账号存在 密码错误", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Login_RegisterActivity.this, "草拟吗什么错", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private boolean isSigninScreen = true;
    private TextView tvSignupInvoker;
    private LinearLayout llSignup;
    private TextView tvSigninInvoker;
    private LinearLayout llSignin;
    private Button btnSignup;
    private Button btnSignin;
    LinearLayout llsignin,llsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__register);

        llSignin = (LinearLayout) findViewById(R.id.llSignin);
        llSignin.setOnClickListener(this);
        llsignup =(LinearLayout)findViewById(R.id.llSignup);
        llsignup.setOnClickListener(this);
        tvSignupInvoker = (TextView) findViewById(R.id.tvSignupInvoker);
        tvSigninInvoker = (TextView) findViewById(R.id.tvSigninInvoker);

        btnSignup= (Button) findViewById(R.id.btnSignup);
        btnSignin= (Button) findViewById(R.id.btnSignin);

        llSignup = (LinearLayout) findViewById(R.id.llSignup);
        llSignin = (LinearLayout) findViewById(R.id.llSignin);

        tvSignupInvoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSigninScreen = false;
                showSignupForm();
            }
        });

        tvSigninInvoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSigninScreen = true;
                showSigninForm();
            }
        });
        showSigninForm();
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login_RegisterActivity.this,"这是登录按钮...",Toast.LENGTH_SHORT).show();
                login();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_right_to_left);
                if(isSigninScreen)
                    btnSignup.startAnimation(clockwise);
                Toast.makeText(Login_RegisterActivity.this,"这是注册按钮...",Toast.LENGTH_SHORT).show();
                register();
            }
        });
    }

    private void showSignupForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) llSignin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.15f;
        llSignin.requestLayout();


        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) llSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.85f;
        llSignup.requestLayout();

        tvSignupInvoker.setVisibility(View.GONE);
        tvSigninInvoker.setVisibility(View.VISIBLE);
        Animation translate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_right_to_left);
        llSignup.startAnimation(translate);

        Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_right_to_left);
        btnSignup.startAnimation(clockwise);

    }

    private void showSigninForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) llSignin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.85f;
        llSignin.requestLayout();

        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) llSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.15f;
        llSignup.requestLayout();

        Animation translate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_left_to_right);
        llSignin.startAnimation(translate);

        tvSignupInvoker.setVisibility(View.VISIBLE);
        tvSigninInvoker.setVisibility(View.GONE);
        Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_left_to_right);
        btnSignin.startAnimation(clockwise);
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.llSignin || v.getId() ==R.id.llSignup){
            InputMethodManager methodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            methodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }

    }

    private void login(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient mOkHttpClient = new OkHttpClient();
                    String jsonToServer = "";
                    jsonToServer = "{\n" +
                            "    \"phoneNumber\": \""+ "18340018870"+ "\",\n" +
                            "    \"password\": \""+"123123"+"\"\n" +
                            "}";
                    RequestBody requestBody = RequestBody.create(JSON, jsonToServer);

                    //TODO  这里到时候根据固定的网址进行一下更换
                    Request request = new Request.Builder().
                            url("").
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
                                showLoginResponse(responseData);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }catch (Exception e){
                    Log.e("====",e.getMessage());
                }
            }
        }).start();
    }

    private void register(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient mOkHttpClient = new OkHttpClient();
                    String jsonToServer = "";
                    jsonToServer = "{\n" +
                            "    \"phoneNumber\": \""+ "18340018870"+ "\",\n" +
                            "    \"password\": \""+"123123"+ "\",\n" +
                            "    \"sex\": \""+"女"+ "\",\n" +
                            "    \"age\": \""+"15"+ "\",\n" +
                            "    \"job\": \""+"设计师"+"\"\n" +
                            "}";
                    RequestBody requestBody = RequestBody.create(JSON, jsonToServer);

                    Request request = new Request.Builder().
                            url("").
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
                                showRegisterResponse(responseData);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }catch (Exception e){
                    Log.e("====",e.getMessage());
                }
            }
        }).start();
    }




}
