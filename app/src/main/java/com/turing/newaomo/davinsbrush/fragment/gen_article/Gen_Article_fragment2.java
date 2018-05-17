package com.turing.newaomo.davinsbrush.fragment.gen_article;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.turing.newaomo.davinsbrush.R;
import com.turing.newaomo.davinsbrush.activity.gen.Gen_By_Article_Activity;
import com.turing.newaomo.davinsbrush.fileSelect.SelectFileFromQQActivity;
import com.turing.newaomo.davinsbrush.fileSelect.SelectFileFromWeixinActivity;
import com.turing.newaomo.davinsbrush.fileSelect.cache.LastCacheFileActivity;
import com.turing.newaomo.davinsbrush.utils.SP.SPPostUtils;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static java.lang.String.valueOf;

/**
 * Created by newao on 2018/2/6.
 */

public class Gen_Article_fragment2  extends Fragment implements View.OnClickListener {
    private static final String TAG = "Gen_fragment_article2";
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String , String>();

    private String filePath;
    private String text = "请选择您要上传的文件";
    View root;
    private ImageView select_from_history;
    private ImageView select_from_local;
    private ImageView select_from_qq;
    private ImageView select_from_weixin;
    private TextView textViewTitle;

    private TextView textViewLast;
    private TextView textViewSkip;
    private Button buttonNext;

    public static Gen_Article_fragment2 newInstance() {
        return new Gen_Article_fragment2();
    }


    private void gotoSelectActivity() {
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        //txt的格式为
        // intent.setType("text/plain;text/plain");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        //doc和docx
        intent.setType("application/msword");
        //intent.setType("image/*");
        startActivityForResult(intent,0);
    }

    private void gotoLastActivity() {
        Intent intent = new Intent(getActivity(), LastCacheFileActivity.class);
        startActivityForResult(intent,1);
    }

    public static File getFileByUri(Uri uri,Context context) {
        String path = null;
        if ("file".equals(uri.getScheme())) {
            path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = context.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=").append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] { MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA }, buff.toString(), null, null);
                int index = 0;
                int dataIdx = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cur.getInt(index);
                    dataIdx = cur.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    path = cur.getString(dataIdx);
                }
                cur.close();
                if (index == 0) {
                } else {
                    Uri u = Uri.parse("content://media/external/images/media/" + index);
                    System.out.println("temp uri is :" + u);
                }
            }
            if (path != null) {
                return new File(path);
            }
        } else if ("content".equals(uri.getScheme())) {
            String mm = uri.toString();
            String result = mm.substring(mm.indexOf("tencent"));
// 4.2.2以后
//            String[] proj = { MediaStore.Images.Media.DATA };
//            Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
//            if (cursor.moveToFirst()) {
//                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                path = cursor.getString(columnIndex);
//            }
//            cursor.close();
            String trues = "storage/emulated/0/"+result;
            return new File(trues);
        } else {
//Log.i(TAG, "Uri Scheme:" + uri.getScheme());
        }
        return null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Gen_By_Article_Activity.isNeedContent = false;
        if (resultCode == Activity.RESULT_OK&&requestCode==0){
            Uri uri = data.getData();//得到uri，后面就是将uri转化成file的过程。
            File file = getFileByUri(uri,getContext());
            Log.d("ssssss",file.getAbsolutePath());
            textViewTitle.setText(file.getAbsolutePath());

            String string =uri.toString();
            Log.d("ssssss",string);
            String a[]=new String[2];
            //判断文件是否在sd卡中
            if (string.indexOf(valueOf(Environment.getExternalStorageDirectory()))!=-1){
                //对Uri进行切割
                a = string.split(valueOf(Environment.getExternalStorageDirectory()));
                //获取到file
                file = new File(Environment.getExternalStorageDirectory(),a[1]);
                Log.d("-----", valueOf(Environment.getExternalStorageDirectory()));
                Log.d("-----", a[1]);
                Toast.makeText(getActivity(),a[1],Toast.LENGTH_LONG).show();
                textViewTitle.setText(string);
                Log.d("-----", valueOf(Environment.getExternalStorageDirectory()));
                Log.d("-----", a[1]);
            }else if(string.indexOf(valueOf(Environment.getDataDirectory()))!=-1){ //判断文件是否在手机内存中
                //对Uri进行切割
                a = string.split(valueOf(Environment.getDataDirectory()));
                //获取到file
                file = new File(Environment.getDataDirectory(),a[1]);
                file = new File(Environment.getDataDirectory(),a[1]);
                Toast.makeText(getActivity(),file.toString(),Toast.LENGTH_LONG).show();
                textViewTitle.setText(string);
                Log.d("------", valueOf(Environment.getExternalStorageDirectory()));
                Log.d("------", string);
            }else{
                //出现其他没有考虑到的情况
                // MyToast.makeshow(this,"文件路径解析失败！", Toast.LENGTH_SHORT);
                Log.d("-----","-----少时诵诗书");
                return;
            }
        }

        if (resultCode == Activity.RESULT_OK&&requestCode==1) {
            String fileName = data.getStringExtra("filePath");
            textViewTitle.setText(fileName);
            Toast.makeText(getActivity(), fileName, Toast.LENGTH_SHORT).show();


        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.gen_by_article_fragment2, container, false);

        select_from_history = (ImageView)root.findViewById(R.id.article_select_from_history) ;
        select_from_local = (ImageView)root.findViewById(R.id.article_select_from_local) ;
        select_from_qq = (ImageView)root.findViewById(R.id.article_select_from_qq) ;
        select_from_weixin = (ImageView)root.findViewById(R.id.article_select_from_weixin) ;
        select_from_history.setOnClickListener(this);
        select_from_local.setOnClickListener(this);
        select_from_qq.setOnClickListener(this);
        select_from_weixin.setOnClickListener(this);
        textViewTitle = (TextView) root.findViewById(R.id.gen_by_article_title);
        textViewLast = (TextView)root.findViewById(R.id.article_textview_last2) ;
        textViewSkip = (TextView)root.findViewById(R.id.article_textview_skip2);
        buttonNext = (Button) root.findViewById(R.id.button_article_next2);
        textViewTitle.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
        textViewSkip.setOnClickListener(this);
        textViewLast.setOnClickListener(this);
        return root;
    }



    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.article_textview_last2:
                Gen_By_Article_Activity.viewPager.setCurrentItem(0);
                break;
            case R.id.article_select_from_history: //查看上次缓存好的界面
                gotoLastActivity();
                break;
            case R.id.article_select_from_local: //通过Intent查找文件
                gotoSelectActivity();
                break;
            case R.id.article_select_from_qq: //查看QQ文件夹下的文件
                Intent intent = new Intent(getActivity(), SelectFileFromQQActivity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.article_select_from_weixin: //查看微信文件夹下的文件
                Intent intent2 = new Intent(getActivity(), SelectFileFromWeixinActivity.class);
                startActivityForResult(intent2,1);
                break;
            case R.id.gen_by_article_title:        //预览文件
                //String[] perms = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                //Manifest.permission.WRITE_EXTERNAL_STORAGE};
                String filePath = textViewTitle.getText().toString();

                Intent intent0 = null;
                try {
                    intent0 = new Intent("android.intent.action.VIEW");
                    intent0.addCategory("android.intent.category.DEFAULT");
                    intent0.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    File file = new File(filePath);
                    Uri fileUri = FileProvider.getUriForFile(getContext(), "com.turing.newaomo.davinsbrush.fileprovider", file);
                    intent0.setDataAndType(fileUri, "application/msword");
                    startActionFile(getContext(),fileUri,"application/msword");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    Log.d("-----------",e.getMessage()+e.toString());
                    e.printStackTrace();
                }
//                startActivity(intent0);


//                Intent textIntent = new Intent(Intent.ACTION_QUICK_VIEW);
//                textIntent.setType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
//                textIntent.putExtra(Intent.EXTRA_TEXT, "这是一段分享的文字");
//                startActivity(Intent.createChooser(textIntent, "分享"));
//
//                openFile(getContext(),new File(filePath));
//            //if (!EasyPermissions.hasPermissions(SelectHowFileActivity.this, perms)) {
//                //     EasyPermissions.requestPermissions(SelectHowFileActivity.this, "需要访问手机存储权限！", 10086, perms);
//                //} else {
//                //   startActivity(OpenFile.openFile(filePath));
//                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("currentFileShow",MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("currentFileView",filePath);
//                editor.apply();
//                FileDisplayActivity.show(getContext(), filePath);
//                // }
                break;
            case R.id.button_article_next2:        //TODO  跳转到下一个界面进行下一步
                saveData();
                Gen_By_Article_Activity.viewPager.setCurrentItem(2);
                break;
            case R.id.article_textview_skip2:         //TODO  跳过这一部
                Gen_By_Article_Activity.viewPager.setCurrentItem(2);
                break;
        }
    }

    public static void startActionFile(Context context, Uri uri, String contentType) throws ActivityNotFoundException {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setDataAndType(uri, contentType);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
            context.startActivity(intent);
    }

    public void saveData(){
        if (!TextUtils.isEmpty(textViewTitle.getText().toString())){
            SPPostUtils.getInstance().setFileName(textViewTitle.getText().toString());
        }else {
            Toast.makeText(getActivity(),"您还没有选择doc文件",Toast.LENGTH_SHORT).show();
        }
    }

    public static void openFile(Context context, File file){
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //设置intent的Action属性
        intent.setAction(Intent.ACTION_VIEW);
        //获取文件file的MIME类型
        String type = getMIMEType(file);
        //设置intent的data和Type属性。
        intent.setDataAndType(Uri.fromFile(file), type);
        //跳转
        try {
            context.startActivity(intent);
        } catch (Exception e) {

            Toast.makeText(context, "找不到打开此文件的应用！", Toast.LENGTH_SHORT).show();
        }
    }
    private static String getMIMEType(File file) {
        String type="*/*";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if(dotIndex < 0){
            return type;
        }
        /* 获取文件的后缀名*/
        String end=fName.substring(dotIndex,fName.length()).toLowerCase();
        if(end=="")return type;
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        for(int i=0;i<MIME_MapTable.length;i++){ //MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
            if(end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }
    private static final String[][] MIME_MapTable = {
            // {后缀名，MIME类型}
            { ".3gp", "video/3gpp" },
            { ".apk", "application/vnd.android.package-archive" },
            { ".asf", "video/x-ms-asf" },
            { ".avi", "video/x-msvideo" },
            { ".bin", "application/octet-stream" },
            { ".bmp", "image/bmp" },
            { ".c", "text/plain" },
            { ".class", "application/octet-stream" },
            { ".conf", "text/plain" },
            { ".cpp", "text/plain" },
            { ".doc", "application/msword" },
            { ".docx",
                    "application/vnd.openxmlformats-officedocument.wordprocessingml.document" },
            { ".xls", "application/vnd.ms-excel" },
            { ".xlsx",
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" },
            { ".exe", "application/octet-stream" },
            { ".gif", "image/gif" },
            { ".gtar", "application/x-gtar" },
            { ".gz", "application/x-gzip" },
            { ".h", "text/plain" },
            { ".htm", "text/html" },
            { ".html", "text/html" },
            { ".jar", "application/java-archive" },
            { ".java", "text/plain" },
            { ".jpeg", "image/jpeg" },
            { ".jpg", "image/jpeg" },
            { ".js", "application/x-javascript" },
            { ".log", "text/plain" },
            { ".m3u", "audio/x-mpegurl" },
            { ".m4a", "audio/mp4a-latm" },
            { ".m4b", "audio/mp4a-latm" },
            { ".m4p", "audio/mp4a-latm" },
            { ".m4u", "video/vnd.mpegurl" },
            { ".m4v", "video/x-m4v" },
            { ".mov", "video/quicktime" },
            { ".mp2", "audio/x-mpeg" },
            { ".mp3", "audio/x-mpeg" },
            { ".mp4", "video/mp4" },
            { ".mpc", "application/vnd.mpohun.certificate" },
            { ".mpe", "video/mpeg" },
            { ".mpeg", "video/mpeg" },
            { ".mpg", "video/mpeg" },
            { ".mpg4", "video/mp4" },
            { ".mpga", "audio/mpeg" },
            { ".msg", "application/vnd.ms-outlook" },
            { ".ogg", "audio/ogg" },
            { ".pdf", "application/pdf" },
            { ".png", "image/png" },
            { ".pps", "application/vnd.ms-powerpoint" },
            { ".ppt", "application/vnd.ms-powerpoint" },
            { ".pptx",
                    "application/vnd.openxmlformats-officedocument.presentationml.presentation" },
            { ".prop", "text/plain" }, { ".rc", "text/plain" },
            { ".rmvb", "audio/x-pn-realaudio" }, { ".rtf", "application/rtf" },
            { ".sh", "text/plain" }, { ".tar", "application/x-tar" },
            { ".tgz", "application/x-compressed" }, { ".txt", "text/plain" },
            { ".wav", "audio/x-wav" }, { ".wma", "audio/x-ms-wma" },
            { ".wmv", "audio/x-ms-wmv" },
            { ".wps", "application/vnd.ms-works" }, { ".xml", "text/plain" },
            { ".z", "application/x-compress" },
            { ".zip", "application/x-zip-compressed" }, { "", "*/*" } };






}
