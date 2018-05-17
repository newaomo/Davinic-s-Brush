package com.turing.newaomo.davinsbrush.fragment.gen_news;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.actionsheet.ActionSheet;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.activity.gen.Gen_By_News_Activity;
import com.turing.newaomo.davinsbrush.utils.SP.SPPostUtils;
import com.turing.newaomo.davinsbrush.utils.photo.ChoosePhotoListAdapter;
import com.turing.newaomo.davinsbrush.utils.photo.listener.GlidePauseOnScrollListener;
import com.turing.newaomo.davinsbrush.utils.photo.loader.GlideImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.PauseOnScrollListener;
import cn.finalteam.galleryfinal.PhotoPreviewActivity;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.HorizontalListView;

public class Gen_News_fragment3 extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener ,AdapterView.OnItemLongClickListener{
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    private final int REQUEST_CODE_CROP = 1002;
    private final int REQUEST_CODE_EDIT = 1003;
    static final String PHOTO_LIST = "photo_list";
    private static Map<String, String> map = new LinkedHashMap<>();

    private static final String TAG = "Gen_fragment_article3";
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String , String>();

    private int mScreenHeight;
    HorizontalListView mLvPhoto;
    private ArrayList<PhotoInfo> mPhotoList;
    private ChoosePhotoListAdapter mChoosePhotoListAdapter;
    private ImageView mOpenGallery;
    private String text = "请选择您要上传的背景图";
    private Button textViewNext;
    private TextView textViewSkip;
    private TextView textViewLast;
    public static Gen_News_fragment3 newInstance() {
        return new Gen_News_fragment3();
    }
    View root;

    public boolean saveData(){
        Map<String,String> map = new HashMap<>();
        for (int i = 0;i<mPhotoList.size();i++){
            map.put("photo"+(i+1),mPhotoList.get(i).getPhotoPath());
        }
        SPPostUtils.getInstance().putPictureList(map);
        if (map.size()==0){
            Toast.makeText(getActivity(),"没有选择图片",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.news_textview_last3:
                Gen_By_News_Activity.viewPager.setCurrentItem(1);
                break;
            case R.id.button_news_next3:
                if (saveData()){
                    Gen_By_News_Activity.viewPager.setCurrentItem(3);
                }else {
                    Toast.makeText(getContext(),"还未选择图片",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.news_textview_skip3:
                Gen_By_News_Activity.viewPager.setCurrentItem(3);
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.gen_by_news_fragment3, container, false);
        mPhotoList = new ArrayList<>();
        // 获取屏幕实际像素
        mScreenHeight = getScreenHeight();
        textViewNext = (Button) root.findViewById(R.id.button_news_next3);
        textViewSkip = (TextView)root.findViewById(R.id.news_textview_skip3);
        textViewLast = (TextView)root.findViewById(R.id.news_textview_last3);
        textViewLast.setOnClickListener(this);
        textViewSkip.setOnClickListener(this);
        textViewNext.setOnClickListener(this);
        mLvPhoto = (HorizontalListView) root.findViewById(R.id.lv_photo);
        mLvPhoto.setOnItemClickListener(this);
        setLongClick();
        mChoosePhotoListAdapter = new ChoosePhotoListAdapter(getActivity(), mPhotoList);
        mLvPhoto.setAdapter(mChoosePhotoListAdapter);
        mOpenGallery = (ImageView) root.findViewById(R.id.news_add_picture);

        mOpenGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //公共配置都可以在application中配置，这里只是为了代码演示而写在此处
                ThemeConfig themeConfig = null;
//                if (mRbThemeDefault.isChecked()) {
//                    themeConfig = ThemeConfig.DEFAULT;
//                } else if (mRbThemeDark.isChecked()) {
//                    themeConfig = ThemeConfig.DARK;
//                } else if (mRbThemeCyan.isChecked()) {
//                    themeConfig = ThemeConfig.CYAN;
//                } else if (mRbThemeOrange.isChecked()) {
//                    themeConfig = ThemeConfig.ORANGE;
//                } else if (mRbThemeGreen.isChecked()) {
                themeConfig = ThemeConfig.GREEN;
//                } else if (mRbThemeTeal.isChecked()) {
//                    themeConfig = ThemeConfig.TEAL;
//                } else if (mRbThemeCustom.isChecked()) {
                //TODO  详细订制其他的方式
//                    ThemeConfig theme = new ThemeConfig.Builder()
//                            .setTitleBarBgColor(Color.rgb(0xFF, 0x57, 0x22))
//                            .setTitleBarTextColor(Color.BLACK)
//                            .setTitleBarIconColor(Color.BLACK)
//                            .setFabNornalColor(Color.RED)
//                            .setFabPressedColor(Color.BLUE)
//                            .setCheckNornalColor(Color.WHITE)
//                            .setCheckSelectedColor(Color.BLACK)
//                            .setIconBack(R.mipmap.ic_action_previous_item)
//                            .setIconRotate(R.mipmap.ic_action_repeat)
//                            .setIconCrop(R.mipmap.ic_action_crop)
//                            .setIconCamera(R.mipmap.ic_action_camera)
//                            .build();
//                    themeConfig = theme;
//                }
                FunctionConfig.Builder functionConfigBuilder = new FunctionConfig.Builder();
                cn.finalteam.galleryfinal.ImageLoader imageLoader;
                PauseOnScrollListener pauseOnScrollListener = null;
                imageLoader = new GlideImageLoader();
                pauseOnScrollListener = new GlidePauseOnScrollListener(false, true);
                functionConfigBuilder.setMutiSelectMaxSize(8);      //TODO  这里设置一下最多的选择数量
                functionConfigBuilder.setEnableEdit(true);
                functionConfigBuilder.setEnableRotate(true);
//                functionConfigBuilder.setRotateReplaceSource(true);      // 旋转覆盖
                functionConfigBuilder.setEnableCrop(true);
//                functionConfigBuilder.setCropWidth(300);            //TODO  可以设置用户定长度编辑的设置
//                functionConfigBuilder.setCropHeight(300);
                functionConfigBuilder.setCropSquare(true);
//                functionConfigBuilder.setCropReplaceSource(true);         //剪切覆盖
//                functionConfigBuilder.setForceCrop(true);               //强制编辑
                functionConfigBuilder.setForceCropEdit(true);           //强制编辑
                functionConfigBuilder.setEnableCamera(true);
                functionConfigBuilder.setEnablePreview(true);
                functionConfigBuilder.setSelected(mPhotoList);//添加过滤集合
                final FunctionConfig functionConfig = functionConfigBuilder.build();
                CoreConfig coreConfig = new CoreConfig.Builder(getActivity(), imageLoader, themeConfig)
                        .setFunctionConfig(functionConfig)
                        .setPauseOnScrollListener(pauseOnScrollListener)
                        .setNoAnimcation(false)
                        .build();
                GalleryFinal.init(coreConfig);

                ActionSheet.createBuilder(getActivity(), getActivity().getSupportFragmentManager())
                        .setCancelButtonTitle("取消(Cancel)")
                        .setOtherButtonTitles("打开相册(Open Gallery)", "拍照(Camera)", "裁剪(Crop)", "编辑(Edit)")
                        .setCancelableOnTouchOutside(true)
                        .setListener(new ActionSheet.ActionSheetListener() {
                            @Override
                            public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

                            }

                            @Override
                            public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                                String path = "/storage/emulated/0/DCIM/Screenshots/Screenshot_2018-02-02-11-52-03-62.png";
                                switch (index) {
                                    case 0:
                                        if (true) {
                                            GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, functionConfig, mOnHanlderResultCallback);
                                        } else {
                                            GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, functionConfig, mOnHanlderResultCallback);
                                        }
                                        break;
                                    case 1:
                                        GalleryFinal.openCamera(REQUEST_CODE_CAMERA, functionConfig, mOnHanlderResultCallback);
                                        break;
                                    case 2:
                                        if (new File(path).exists()) {
                                            GalleryFinal.openCrop(REQUEST_CODE_CROP, functionConfig, path, mOnHanlderResultCallback);
                                        } else {
                                            Toast.makeText(getActivity(), "图片不存在", Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                                    case 3:
                                        if (new File(path).exists()) {
                                            GalleryFinal.openEdit(REQUEST_CODE_EDIT, functionConfig, path, mOnHanlderResultCallback);
                                        } else {
                                            Toast.makeText(getActivity(), "图片不存在", Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });
        initImageLoader(getContext());
        return root;
    }

    private void setLongClick(){
        mLvPhoto.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("")
                        .setItems(R.array.arrcontent,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        String[] PK = getResources()
                                                .getStringArray(
                                                        R.array.arrcontent);
                                        Toast.makeText(
                                                getActivity(),
                                                PK[which], Toast.LENGTH_LONG)
                                                .show();
                                        if(PK[which].equals("剪切")) {
                                            FunctionConfig functionConfig = new FunctionConfig.Builder()
                                                    .setEnableCamera(true)
                                                    .setEnableEdit(true)
                                                    .setEnableCrop(true)
                                                    .setEnableRotate(true)
                                                    .setCropSquare(true)
                                                    .setEnablePreview(true)
                                                    .build();
                                            PhotoInfo photoInfo = mPhotoList.get(position);
                                            String path = photoInfo.getPhotoPath();
                                            if (new File(path).exists()) {
                                                GalleryFinal.openCrop(REQUEST_CODE_CROP, functionConfig, path, mOnHanlderResultCallback);
                                            } else {
                                                Toast.makeText(getActivity(), "图片不存在", Toast.LENGTH_SHORT).show();
                                            }                        }else if(PK[which].equals("编辑")) {
                                            FunctionConfig functionConfig = new FunctionConfig.Builder()
                                                    .setEnableCamera(true)
                                                    .setEnableEdit(true)
                                                    .setEnableCrop(true)
                                                    .setEnableRotate(true)
                                                    .setCropSquare(true)
                                                    .setEnablePreview(true)
                                                    .build();                            // 按照这种方式做删除操作，这个if内的代码有bug，实际代码中按需操作
                                            PhotoInfo photoInfo = mPhotoList.get(position);
                                            String path = photoInfo.getPhotoPath();
                                            if (new File(path).exists()) {
                                                GalleryFinal.openEdit(REQUEST_CODE_EDIT, functionConfig, path, mOnHanlderResultCallback);
                                            } else {
                                                Toast.makeText(getActivity(), "图片不存在", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }).setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                            }
                        }).show();
                return false;
            }
        });
    }


    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            if (resultList != null) {
                mPhotoList.clear(); //TODO  这里设置的先清理干净 再添加新的 因为他会显示上次已经选择的
                mPhotoList.addAll(resultList);
                mChoosePhotoListAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
        }
    };


    private void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // TODO Remove for release app
        ImageLoader.getInstance().init(config.build());
    }

//            GalleryFinal.cleanCacheFile();


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        PhotoInfo photoInfo = mPhotoList.get(position);
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnFail(R.drawable.ic_gf_default_photo)
                .showImageForEmptyUri(R.drawable.ic_gf_default_photo)
                .showImageOnLoading(R.drawable.ic_gf_default_photo).build();
        Toast.makeText(getActivity(), photoInfo.getPhotoPath(), Toast.LENGTH_SHORT).show();
        //        ImageLoader.getInstance().displayImage("file:/" + photoInfo.getPhotoPath(), imageView, options);
        Intent intent = new Intent(getActivity(), PhotoPreviewActivity.class);
        //        ArrayList<PhotoInfo> mSelectPhotoMap = new ArrayList<>();
        ArrayList<PhotoInfo> mSelectPhotoMap = new ArrayList<>();
        intent.putExtra(PHOTO_LIST, mPhotoList);
        startActivity(intent);
        //TODO  其中photoInfo.getPhotoPath()表示的就是类似 /storage/emulated...这样的绝对图片路径
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(getActivity(), "你长按了第" + position + "项",
                Toast.LENGTH_SHORT).show();

        return false;
    }

    /**
     * 获取屏幕高度像素
     */
    private int getScreenHeight() {
        // 获取屏幕实际像素
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display display = getActivity().getWindowManager()
                .getDefaultDisplay();
        display.getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }


    @Override
    public void onResume() {
        super.onResume();
        // imitate http-request
    }


}