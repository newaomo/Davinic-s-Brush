package com.turing.newaomo.davinsbrush.activity.other;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.turing.newaomo.davinsbrush.Constant;
import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.beans.BrushUser;
import com.turing.newaomo.davinsbrush.utils.PhotoUtil;
import com.turing.newaomo.davinsbrush.view.RoundAngleImageView;
import com.turing.newaomo.davinsbrush.view.ToolBarOption;
import com.turing.newaomo.davinsbrush.view.setting.CircleTransform;

import java.io.File;

import static android.view.View.GONE;

public class EditUserInfoActivity extends AppCompatActivity implements View.OnClickListener{
    Uri cropUri;
    Boolean isSelectAvatar = false;
    SharedPreferences sharedPreferences;
    private Boolean isUseUri = false;
    private static final int UPDATE_SUCCESS  =111;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATE_SUCCESS:
                    finish();
            }
        }
    };


    private RelativeLayout avatarLayout, nickLayout, sexLayout, birthLayout, phoneLayout,
            emailLayout, signatureLayout, addressLayout,jobLayout;
    private RoundAngleImageView avatar;
    private TextView nick, sex, birth, phone, email, signature, address,job;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_userinfo);
        initView();
        initData();
    }

    private RoundAngleImageView icon;
    private TextView right;
    private TextView title;
    private ImageView rightImage;
    protected ImageView back;

    public void initView() {
        avatarLayout = (RelativeLayout) findViewById(R.id.rl_edit_user_info_avatar);
        nickLayout = (RelativeLayout) findViewById(R.id.rl_edit_user_info_nick);
        sexLayout = (RelativeLayout) findViewById(R.id.rl_edit_user_info_sex);
        birthLayout = (RelativeLayout) findViewById(R.id.rl_edit_user_info_birth);
        phoneLayout = (RelativeLayout) findViewById(R.id.rl_edit_user_info_phone);
        emailLayout = (RelativeLayout) findViewById(R.id.rl_edit_user_info_email);
        signatureLayout = (RelativeLayout) findViewById(R.id.rl_edit_user_info_signature);
        addressLayout = (RelativeLayout) findViewById(R.id.rl_edit_user_info_address);
        jobLayout = (RelativeLayout)findViewById(R.id.rl_edit_user_info_job);
        avatar = (RoundAngleImageView) findViewById(R.id.riv_edit_user_info_avatar);
        SharedPreferences sharedPreferences = getSharedPreferences("Profile", Context.MODE_PRIVATE);
        isUseUri = sharedPreferences.getBoolean("isUseUri",false);
        if (isUseUri){
            Log.d("-----","使用111");
            Picasso.with(EditUserInfoActivity.this).load(cropUri).transform(new CircleTransform()).into(avatar);
        }else {
            Log.d("-----","不使用");
            Picasso.with(EditUserInfoActivity.this).load(R.drawable.girl).transform(new CircleTransform()).into(avatar);

        }
        nick = (TextView) findViewById(R.id.tv_edit_user_info_nick);
        sex = (TextView) findViewById(R.id.tv_edit_user_info_sex);
        birth = (TextView) findViewById(R.id.tv_edit_user_info_birth);
        phone = (TextView) findViewById(R.id.tv_edit_user_info_phone);
        email = (TextView) findViewById(R.id.tv_edit_user_info_email);
        signature = (TextView) findViewById(R.id.tv_edit_user_info_signature);
        address = (TextView) findViewById(R.id.tv_edit_user_info_address);
        job = (TextView) findViewById(R.id.tv_edit_user_info_job);

        icon = (RoundAngleImageView) findViewById(R.id.riv_header_layout_icon);
        title = (TextView) findViewById(R.id.tv_header_layout_title);
        right = (TextView) findViewById(R.id.tv_header_layout_right);
        back = (ImageView) findViewById(R.id.iv_header_layout_back);
        rightImage = (ImageView) findViewById(R.id.iv_header_layout_right);
        rightImage.setVisibility(View.GONE);
        right.setVisibility(View.VISIBLE);

        jobLayout.setOnClickListener(EditUserInfoActivity.this);
        avatarLayout.setOnClickListener(EditUserInfoActivity.this);
        nickLayout.setOnClickListener(EditUserInfoActivity.this);
        sexLayout.setOnClickListener(EditUserInfoActivity.this);
        birthLayout.setOnClickListener(EditUserInfoActivity.this);
        phoneLayout.setOnClickListener(EditUserInfoActivity.this);
        emailLayout.setOnClickListener(EditUserInfoActivity.this);
        signatureLayout.setOnClickListener(EditUserInfoActivity.this);
        addressLayout.setOnClickListener(EditUserInfoActivity.this);
    }

    public void initData() {
        //TODO  加载个人数据
        sharedPreferences = getSharedPreferences("Profile",MODE_PRIVATE);
        nick.setText(sharedPreferences.getString("nickName","子桓"));
        birth.setText(sharedPreferences.getString("birthday","1999-02-24"));
        phone.setText(sharedPreferences.getString("phoneNumber","18300000000"));
        sex.setText(sharedPreferences.getString("sex","男"));
        email.setText(sharedPreferences.getString("email","729801842@qq.com"));
        signature.setText(sharedPreferences.getString("signature","奋斗不止！"));
        address.setText(sharedPreferences.getString("address","山东省"));
        Glide.with(EditUserInfoActivity.this).load(sharedPreferences.getString("avatar","http://jinan.bfnic.cn/templates/default/app2018/images/phone.png"));
        ToolBarOption toolBarOption = new ToolBarOption();
        toolBarOption.setAvatar(null);
        toolBarOption.setRightResId(R.drawable.ic_file_upload_blue_grey_900_24dp);
        toolBarOption.setTitle("编辑个人资料");
        toolBarOption.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSelectAvatar) {
                    Toast.makeText(EditUserInfoActivity.this,"请设置个人头像了^_^", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent();
                //TODO  这里是将保存的信息还给上一个活动  可以直接上一个活动根据sharedprefencecs来更新个人信息
                setResult(Activity.RESULT_OK, intent);
                Log.d("-----","这里可以保存资料了！");
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
                        updateUserInfo();
//                    }
//                });
            }
        });
        //TODO  下面设置一下个人左上角的返回按钮的事件  记得要finish
        toolBarOption.setNeedNavigation(true);
        setToolBar(toolBarOption);
    }

    public void updateUserInfo(){
        //TODO  更改个人资料  先更新本地 再更新网络的
        SharedPreferences sharedPreferences = getSharedPreferences("Profile",MODE_PRIVATE);
        BrushUser brushUser = new BrushUser(sharedPreferences.getString("phoneNumber","18340018870"));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isUseUri",true);
        editor.putString("avatar", String.valueOf(cropUri));
        editor.putString("sex",sex.getText().toString());
        editor.putString("job",job.getText().toString());
        editor.putString("address",address.getText().toString());
        editor.putString("email",email.getText().toString());
        editor.putString("nickName",nick.getText().toString());
        editor.putString("signature",signature.getText().toString());
        editor.putString("birthday",birth.getText().toString());
        editor.putString("email",email.getText().toString());
        if (editor.commit()){
            finish();
        }

        //TODO  保存一下图片  可能格式为file

        brushUser.setJob(job.getText().toString());
        brushUser.setAddress(address.getText().toString());
        brushUser.setBirthday(birth.getText().toString());
        brushUser.setEmail(email.getText().toString());
        brushUser.setNickName(nick.getText().toString());
        brushUser.setSex(sex.getText().toString());
        brushUser.setSignature(signature.getText().toString());
        brushUser.setAvatar("");    //TODO  需要修改为真正的头像  可以先将图片上传到网络之后  再设置为网址的形式

        //更细成功通过handler返回
//        Message message = Message.obtain();
//        message.what = UPDATE_SUCCESS;
//        handler.sendMessage(message);

    }


    public void setToolBar(ToolBarOption option) {
        if (option.getAvatar() != null) {
            icon.setVisibility(View.VISIBLE);
            Glide.with(EditUserInfoActivity.this).load(option.getAvatar()).into(icon);
        } else {
            icon.setVisibility(GONE);
        }
        if (option.getRightResId() != 0) {
            right.setVisibility(GONE);
            rightImage.setVisibility(View.VISIBLE);
            rightImage.setImageResource(option.getRightResId());
            rightImage.setOnClickListener(option.getRightListener());
        } else if (option.getRightText() != null) {
            right.setVisibility(View.VISIBLE);
            rightImage.setVisibility(GONE);
            right.setText(option.getRightText());
            right.setOnClickListener(option.getRightListener());
        } else {
            right.setVisibility(GONE);
            rightImage.setVisibility(GONE);
        }
        if (option.getTitle() != null) {
            title.setVisibility(View.VISIBLE);
            title.setText(option.getTitle());
        } else {
            title.setVisibility(GONE);
        }
        if (option.isNeedNavigation()) {
            back.setVisibility(View.VISIBLE);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            back.setVisibility(GONE);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.rl_edit_user_info_avatar:
                //TODO  这里先让他选择图片  然后对对应的图片进行
                cropPhoto("/storage/emulated/0/GalleryFinal/edittemp/Screenshot_2018-02-22-21-46-39-47_crop.png");
                break;
            case R.id.rl_edit_user_info_nick:
                intent.putExtra("from", "nickName");
                intent.putExtra("message", sharedPreferences.getString("nickName",""));
                intent.setClass(EditUserInfoActivity.this, EditUserInfoDetailActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE_NICK);
                break;
            case R.id.rl_edit_user_info_sex:
                intent.putExtra("from", "gender");
                intent.putExtra("message", sharedPreferences.getString("sex",""));
                intent.setClass(EditUserInfoActivity.this, EditUserInfoDetailActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE_SEX);
                break;
            case R.id.rl_edit_user_info_birth:
                intent.putExtra("from", "birth");
                intent.putExtra("message",sharedPreferences.getString("birthday",""));
                intent.setClass(EditUserInfoActivity.this, EditUserInfoDetailActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE_BIRTH);
                break;
            case R.id.rl_edit_user_info_phone:
                intent.putExtra("from", "phone");
                intent.putExtra("message",sharedPreferences.getString("phoneNumber",""));
                intent.setClass(EditUserInfoActivity.this, EditUserInfoDetailActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE_PHONE);
                break;
            case R.id.rl_edit_user_info_email:
                intent.putExtra("from", "email");
                intent.putExtra("message", sharedPreferences.getString("email",""));
                intent.setClass(EditUserInfoActivity.this, EditUserInfoDetailActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE_EMAIL);
                break;
            case R.id.rl_edit_user_info_signature:
                intent.putExtra("from", "signature");
                intent.putExtra("message",sharedPreferences.getString("signature",""));
                intent.setClass(EditUserInfoActivity.this, EditUserInfoDetailActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE_SIGNATURE);
                break;
            case R.id.rl_edit_user_info_address:
                intent.putExtra("from", "address");
                intent.putExtra("message", sharedPreferences.getString("address",""));
                intent.setClass(EditUserInfoActivity.this, EditUserInfoDetailActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE_ADDRESS);
                break;
            case R.id.rl_edit_user_info_job:
                intent.putExtra("from", "job");
                intent.putExtra("message", sharedPreferences.getString("job",""));
                intent.setClass(EditUserInfoActivity.this, EditUserInfoDetailActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE_JOB);
                break;
        }
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String message = null;
            if (data != null) {
                message = data.getStringExtra("message");
            }
            switch (requestCode) {
                case Constant.REQUEST_CODE_CROP:
                    Log.d("-----","裁剪完成");
                    try {
                        mProgressDialog = new ProgressDialog(EditUserInfoActivity.this);
//                        mProgressDialog.setMessage("正在上传头像，请稍候........");
                        mProgressDialog.show();
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(),cropUri);
                        Drawable drawable = new BitmapDrawable(bitmap);
                        avatar.setImageDrawable(drawable);
                        isSelectAvatar = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case Constant.REQUEST_CODE_JOB:
                    job.setText(message);
                    break;
                case Constant.REQUEST_CODE_SEX:
                    sex.setText(message);
                    if (message != null) {
                        sex.setText(message);
                    }
                    break;
                case Constant.REQUEST_CODE_BIRTH:
                    birth.setText(message);
                    break;
                case Constant.REQUEST_CODE_SIGNATURE:
                    signature.setText(message);
                    break;
                case Constant.REQUEST_CODE_EMAIL:
                    email.setText(message);
                    break;
                case Constant.REQUEST_CODE_NICK:
                    nick.setText(message);
                    break;
                case Constant.REQUEST_CODE_ADDRESS:
                    address.setText(message);
                case Constant.REQUEST_CODE_PHONE:
                    phone.setText(message);
                default:
                    break;
            }
        }
    }

    private void cropPhoto(String path) {
        Uri uri = Uri.fromFile(new File(path));
        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        cropIntent.setDataAndType(uri, "image/*");
        cropIntent.putExtra("crop", "true");
        cropIntent.putExtra("aspectX", 1);
        cropIntent.putExtra("aspectY", 1);
        cropIntent.putExtra("outputX", 80);
        cropIntent.putExtra("outputY", 80);
        cropIntent.putExtra("return-data", false);
        cropIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        cropUri = PhotoUtil.buildUri(EditUserInfoActivity.this);
        cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);

        if (cropIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cropIntent, Constant.REQUEST_CODE_CROP);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void finish() {
        Log.d("-----","editUserInfo_finish");
        Intent intent = new Intent();
        //TODO  将信息还给上一个活动用来更新数据  可以考虑本地存储的方式
        setResult(Activity.RESULT_OK, intent);
        super.finish();
    }


}
