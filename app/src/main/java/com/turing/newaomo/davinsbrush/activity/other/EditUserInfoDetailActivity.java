package com.turing.newaomo.davinsbrush.activity.other;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.adapter.JobClassifyAdapter;
import com.turing.newaomo.davinsbrush.beans.JobItem;
import com.turing.newaomo.davinsbrush.utils.CommonUtils;
import com.turing.newaomo.davinsbrush.utils.TimeUtil;
import com.turing.newaomo.davinsbrush.view.AutoEditText;
import com.turing.newaomo.davinsbrush.view.CustomDatePickerDialog;
import com.turing.newaomo.davinsbrush.view.RoundAngleImageView;
import com.turing.newaomo.davinsbrush.view.ToolBarOption;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import chihane.jdaddressselector.BottomDialog;
import chihane.jdaddressselector.OnAddressSelectedListener;
import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;

import static android.view.View.GONE;

public class EditUserInfoDetailActivity extends AppCompatActivity implements View.OnClickListener, OnAddressSelectedListener {

    private AutoEditText mAutoEditText;
    private String from;
    private ImageView male;
    private ImageView female;
    private ImageView other;
    private TextView address;
    private String job;
    private int currentDay = 4;
    private int currentMonth = 9;
    private int currentYear = 1995;
    private TextView birth;
    private String content;
    private String currentGender = "男";
    private String currentSelectedAddress;


    private ProgressDialog mProgressDialog;

    private RoundAngleImageView icon;
    private TextView right;
    private TextView title;
    private ImageView rightImage;
    protected ImageView back;
    protected ListView listViewJob;
    protected JobClassifyAdapter jobClassifyAdapter;
    private List<JobItem> mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        from = getIntent().getStringExtra("from");
        content = getIntent().getStringExtra("message");
        switch (from) {
            case "nickName":
            case "phone":
            case "email":
            case "signature":
                setContentView(R.layout.activity_edit_user_detail);
                break;
            case "gender":
                setContentView(R.layout.activity_edit_user_detail_gender);
                break;
            case "address":
                setContentView(R.layout.activity_edit_user_detail_address);
                break;
            case "job":
                setContentView(R.layout.activity_edit_user_detail_job);
                break;
            case "birth":
                setContentView(R.layout.activity_edit_user_detail_birth);
                break;
            default:
                setContentView(R.layout.activity_edit_user_detail_birth);
        }
        initView();
        initData();

    }

    protected void initView() {
        from = getIntent().getStringExtra("from");
        if (from.equals("job")){
            listViewJob = (ListView)findViewById(R.id.list_view_job);
        }
        icon = (RoundAngleImageView) findViewById(R.id.riv_header_layout_icon);
        title = (TextView) findViewById(R.id.tv_header_layout_title);
        right = (TextView) findViewById(R.id.tv_header_layout_right);
        back = (ImageView) findViewById(R.id.iv_header_layout_back);
        rightImage = (ImageView) findViewById(R.id.iv_header_layout_right);
        rightImage.setVisibility(View.GONE);
        right.setVisibility(View.VISIBLE);
        mProgressDialog = new ProgressDialog(this);
        if (from != null) {
            switch (from) {
                case "nickName":
                case "groupnickName":
                case "phone":
                case "email":
                case "signature":
                    initNormalView();
                    break;
                case "gender":
                    initGenderView();
                    break;
                case "address":
                    initAddressView();
                    break;
                case "job":
                    initJobView();
                    initJobData();
                    break;
                case "birth":
                    initBirthView();
                    break;
                default:
                    initBirthView();
                    break;
            }
        }
    }

    private void initAddressView() {
        findViewById(R.id.rl_edit_user_info_detail_address).setOnClickListener(this);
        address = (TextView) findViewById(R.id.tv_edit_user_info_detail_address);
        if (content != null) {
            address.setText(content);
        } else {
            address.setText("未填写地址");
        }
    }

    private void initJobView(){
    }

    private void initBirthView() {
        findViewById(R.id.rl_edit_user_info_detail_birth).setOnClickListener(this);
        RelativeLayout birthLayout = (RelativeLayout) findViewById(R.id.rl_edit_user_info_detail_birth);
        birth = (TextView) findViewById(R.id.tv_edit_user_info_detail_birth);
        birthLayout.setOnClickListener(this);
        if (content != null) {
            birth.setText(content);
            Date date = TimeUtil.getDateFormalFromString(content);
            if (date != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                currentDay = calendar.get(Calendar.DATE);
                currentMonth = calendar.get(Calendar.MONTH);
                currentYear = calendar.get(Calendar.YEAR);
            }
        }

    }

    private void initGenderView() {
        RelativeLayout femaleLayout = (RelativeLayout) findViewById(R.id.rl_edit_user_info_detail_female);
        RelativeLayout maleLayout = (RelativeLayout) findViewById(R.id.rl_edit_user_info_detail_male);
        RelativeLayout otherLayout = (RelativeLayout) findViewById(R.id.rl_edit_user_info_detail_other);
        female = (ImageView) findViewById(R.id.iv_edit_user_info_detail_female);
        male = (ImageView) findViewById(R.id.iv_edit_user_info_detail_male);
        other = (ImageView) findViewById(R.id.iv_edit_user_info_detail_other);
        femaleLayout.setOnClickListener(this);
        otherLayout.setOnClickListener(this);
        maleLayout.setOnClickListener(this);
        if (content != null) {
            if (content.equals("男")) {
                updateGenderChecked(0);
            } else if (content.equals("女")) {
                updateGenderChecked(1);
            } else {
                updateGenderChecked(3);
            }
        }
    }

    private void initNormalView() {
        mAutoEditText = (AutoEditText) findViewById(R.id.aet_edit_user_info_detail);
        mAutoEditText.setText(content);
    }

    public void initData() {
        ToolBarOption toolBarOption = new ToolBarOption();
        toolBarOption.setTitle("编辑" + getTitle(from));
        toolBarOption.setRightResId(R.drawable.ic_file_upload_blue_grey_900_24dp);
        toolBarOption.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 后期设置好Application之后 再来尝试一下网络连接
//                if (!CommonUtils.isNetWorkAvailable()) {
//                    Log.d("-----","请检查网络");
//                    return;
//                }
                final Intent intent = new Intent();
                switch (from) {
                    case "nickName":
                    case "phone":
                    case "email":
                    case "signature":
                        if (mAutoEditText.getText() != null && !mAutoEditText.getText().toString().trim().equals("")) {
//                            if (content != null && content.equals(mAutoEditText.getText().toString().trim())) {
//                                Log.d("-----","没有修改");
//                            } else {
                                content = mAutoEditText.getText().toString().trim();
                                if (from.equals("phone") && !CommonUtils.isPhone(content)) {
                                    Log.d("-----","输入的手机号码格式不对，请重新输入");
                                    return;
                                }
                                if (from.equals("email") && !CommonUtils.isEmail(content)) {
                                    Log.d("-----","输入的邮箱号码格式不对，请重新输入");
                                    return;
                                }
                                intent.putExtra("message", content);
                                setResult(Activity.RESULT_OK, intent);
//                            }
                        } else {
                            Toast.makeText(EditUserInfoDetailActivity.this,"内容不能为空",Toast.LENGTH_SHORT).show();
                            mAutoEditText.startShakeAnimation();
                            return;
                        }
                        break;
                    case "gender":
                        if (content != null && content.equals(currentGender)) {
                            Log.d("-----","当前的性别未修改");
                        } else {
                            content = currentGender;
                            Log.d("-----","当前的性别改变拉");
                            intent.putExtra("message", content);
                            setResult(Activity.RESULT_OK, intent);
                        }
                        break;
                    case "job":
                        if (content != null && content.equals(job)) {
                            Log.d("-----","当前的职业未修改");
                        } else {
                            content = job;
                            Log.d("-----","当前的职业改变拉");
                            intent.putExtra("message", content);
                            setResult(Activity.RESULT_OK, intent);
                        }
                        break;
                    case "address":
                        if (content != null && content.equals(currentSelectedAddress)) {
                            Log.d("-----","现在的地址并没有改变");
                        } else {
                            content = currentSelectedAddress;
                            intent.putExtra("message", content);
                            setResult(Activity.RESULT_OK, intent);
                        }
                        break;

                    default:
                        String time = TimeUtil.getDateFormalFromString(currentYear, currentMonth, currentDay);
                        if (content != null && content.equals(time)) {
                            Log.d("-----","现在时间并没有改变");
                        } else {
                            content = time;
                            intent.putExtra("message", content);
                            setResult(Activity.RESULT_OK, intent);
                        }
                        break;
                }
                finish();
            }
        });
        setToolBar(toolBarOption);
    }

    private String getTitle(String from) {
        switch (from) {
            case "nickName":
                return "昵称";
            case "avatar":
                return "头像";
            case "gender":
                return "性别";
            case "birth":
                return "生日";
            case "email":
                return "邮箱";
            case "phone":
                return "手机号码";
            case "signature":
                return "签名";
            case "address":
                return "地址";
            default:
                return "未知类型";
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_edit_user_info_detail_female:
                updateGenderChecked(1);
                break;
            case R.id.rl_edit_user_info_detail_male:
                updateGenderChecked(0);
                break;
            case R.id.rl_edit_user_info_detail_other:
                updateGenderChecked(2);
                break;
            case R.id.rl_edit_user_info_detail_birth:
                openDatePicker();
                break;
            case R.id.rl_edit_user_info_detail_address:
                showBottomDialog();
            break;
            case R.id.list_view_job:
//                showBottomDialog();
                break;
        }
    }

    private BottomDialog mBottomDialog;

    private void showBottomDialog() {
        if (mBottomDialog == null) {
            mBottomDialog = new BottomDialog(this);
            mBottomDialog.setOnAddressSelectedListener(this);
            mBottomDialog.show();
        }else {
            mBottomDialog.show();
        }
    }

    private void openDatePicker() {
        CustomDatePickerDialog dialog = new CustomDatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                currentYear = year;
                currentMonth = month;
                currentDay = dayOfMonth;
                updateDateChanged();
            }
        }, currentYear, currentMonth, currentDay);
        dialog.show();
    }

    private void updateDateChanged() {
        birth.setText(TimeUtil.getDateFormalFromString(currentYear, currentMonth, currentDay));
    }

    private void updateGenderChecked(int position) {

        if (position == 0) {
            currentGender = "男";
            male.setImageResource(R.mipmap.nim_contact_checkbox_checked_green);
            female.setImageResource(R.mipmap.nim_contact_checkbox_unchecked);
            other.setImageResource(R.mipmap.nim_contact_checkbox_unchecked);
        } else if (position == 1) {
            currentGender = "女";
            male.setImageResource(R.mipmap.nim_contact_checkbox_unchecked);
            female.setImageResource(R.mipmap.nim_contact_checkbox_checked_green);
            other.setImageResource(R.mipmap.nim_contact_checkbox_unchecked);
        } else {
            currentGender = "其他";
            male.setImageResource(R.mipmap.nim_contact_checkbox_unchecked);
            female.setImageResource(R.mipmap.nim_contact_checkbox_unchecked);
            other.setImageResource(R.mipmap.nim_contact_checkbox_checked_green);
        }
    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        if (mBottomDialog.isShowing()) {
            mBottomDialog.dismiss();
        }
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(province.name).append("-").append(city.name).append("-").append(county.name);
        if (street != null) {
            stringBuilder.append("-").append(street.name);
        }
        currentSelectedAddress=stringBuilder.toString();
        address.setText(currentSelectedAddress);
    }


    @Override
    public void finish() {
        super.finish();
        if (mBottomDialog != null && mBottomDialog.isShowing()) {
            mBottomDialog.dismiss();
        }
    }

    public void setToolBar(ToolBarOption option) {
        if (option.getAvatar() != null) {
            icon.setVisibility(View.VISIBLE);
            Glide.with(this).load(option.getAvatar()).into(icon);
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

    public void showLoadDialog(final String message) {
        if (!isFinishing()) {
            mProgressDialog.setMessage(message);
            mProgressDialog.show();
        }
    }

    public void dismissLoadDialog() {
        if (!isFinishing()) {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }
    }

    public void cancelLoadDialog() {
        if (!isFinishing()) {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.cancel();
            }
        }
    }

    public void initJobData(){
        mData = new ArrayList<JobItem>();
        for(int i = 0;i <10;i++){
            JobItem jobItem = new JobItem();
            jobItem.setColorId("#c25555");
            jobItem.setJob("IT");
            jobItem.setJobDetail("计算机/互联网/通信");
            mData.add(jobItem);
        }
        jobClassifyAdapter = new JobClassifyAdapter(this,mData);
        listViewJob.setAdapter(jobClassifyAdapter);
        listViewJob.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                switch (position){
                job = mData.get(position).getJob();
                Toast.makeText(EditUserInfoDetailActivity.this,"您点击了"+mData.get(position).getJobDetail(),Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }


}