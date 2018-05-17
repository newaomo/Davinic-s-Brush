package com.turing.newaomo.davinsbrush.activity.other;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.turing.newaomo.davinsbrush.MainActivity;
import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.view.login_register.animationUtils.TransitionController;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences = getSharedPreferences("Profile",MODE_PRIVATE);
    String nickName = sharedPreferences.getString("nickName","");

    public static final MediaType JSON= MediaType.parse("application/json; charset=utf-8");

    EditText etUsername;
    EditText etPassword;
    CardView cv;
    FloatingActionButton fab;
    private EditText editTextDoudou;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (nickName == null || nickName.equals("")){
            Intent intent = new Intent(LoginActivity.this,EditUserInfoActivity.class);
            startActivity(intent);
        }
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        cv = (CardView) findViewById(R.id.cv);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        editTextDoudou = (EditText)findViewById(R.id.edit_doudou);  //这个是网址
        btn_login = (Button)findViewById(R.id.bt_login);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                TransitionController.getInstance().startActivity(this,new Intent(this, RegisterActivity.class),fab,R.id.fab);
                break;
            case R.id.bt_login:
                login();
                break;
        }
    }

    public void login(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient mOkHttpClient = new OkHttpClient();
                    String jsonToServer = "";
                    jsonToServer = "{\n" +
                            "    \"phoneNumber\": \""+ etUsername.getText().toString()+ "\",\n" +
                            "    \"password\": \""+etPassword.getText().toString()+"\"\n" +
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
                                showResponse(responseData);

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

    public void showResponse(final String responseData){
        if (responseData.startsWith("login_success")){
            Log.d("-----",responseData);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity.this,responseData.toString(),Toast.LENGTH_SHORT).show();
                }
            });
            final String id = responseData.substring(15);
            Log.d("-----","ip========================================="+id);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity.this,id,Toast.LENGTH_SHORT).show();
                }
            });
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else if (responseData.equals("no_this_phone")){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity.this,"没有这个电话号码",Toast.LENGTH_SHORT).show();
                }
            });
        }else if (responseData.equals("password_wrong")){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity.this,"账号存在 密码错误",Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity.this,"草拟吗什么错",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}

