package com.turing.newaomo.davinsbrush.utils.share;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.List;

/**
 * Created by newao on 2018/2/5.
 */

public class MyShareUtil {

    private Bitmap bitmap;
    private List<Bitmap>bitmapList;
    private Context context;

    private static MyShareUtil instance;

    public static MyShareUtil getInstance(Context context) {
        if (instance == null){
            instance = new MyShareUtil(context);
        }
        return instance;
    }

    public MyShareUtil(Context context){
        this.context = context;
    }

    public MyShareUtil(){}
    public void shareImageToQQ(Context context,Bitmap bitmap){
        this.context = context;
        this.bitmap = bitmap;
        try {
            Uri uriToImage = Uri.parse(MediaStore.Images.Media.insertImage(
                    context.getContentResolver(), bitmap, null, null));
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
            shareIntent.setType("image/*");
            // 遍历所有支持发送图片的应用。找到需要的应用
            PackageManager packageManager = context.getPackageManager();
            List<ResolveInfo> resolveInfoList = packageManager
                    .queryIntentActivities(shareIntent,
                            PackageManager.MATCH_DEFAULT_ONLY);
            ComponentName componentName = null;
            for (int i = 0; i < resolveInfoList.size(); i++) {
                if (TextUtils.equals(
                        resolveInfoList.get(i).activityInfo.packageName,
                        "com.tencent.mobileqq")) {
                    componentName = new ComponentName(
                            resolveInfoList.get(i).activityInfo.packageName,
                            resolveInfoList.get(i).activityInfo.name);
                    break;
                }
            }
            // 已安装**
            if (null != componentName) {
                shareIntent.setComponent(componentName);
                context.startActivity(shareIntent);
            } else {
                Toast.makeText(context,"请先安装QQ",Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context,"请分享图片到QQ失败",Toast.LENGTH_SHORT).show();
        }
    }

    public void shareImageToWeixin(Context context,Bitmap bitmap){
        this.context = context;
        this.bitmap = bitmap;
        try {
            Uri uriToImage = Uri.parse(MediaStore.Images.Media.insertImage(
                    context.getContentResolver(), bitmap, null, null));
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
            shareIntent.setType("image/*");
            // 遍历所有支持发送图片的应用。找到需要的应用
            PackageManager packageManager = context.getPackageManager();
            List<ResolveInfo> resolveInfoList = packageManager
                    .queryIntentActivities(shareIntent,
                            PackageManager.MATCH_DEFAULT_ONLY);
            ComponentName componentName = null;
            for (int i = 0; i < resolveInfoList.size(); i++) {
                if (TextUtils.equals(
                        resolveInfoList.get(i).activityInfo.packageName,
                        "com.tencent.mm")) {
                    componentName = new ComponentName(
                            resolveInfoList.get(i).activityInfo.packageName,
                            resolveInfoList.get(i).activityInfo.name);
                    break;
                }
            }
            // 已安装**
            if (null != componentName) {
                shareIntent.setComponent(componentName);
                context.startActivity(shareIntent);
            } else {
                Toast.makeText(context,"请先安装微信",Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context,"请分享图片到微信失败",Toast.LENGTH_SHORT).show();
        }
    }

    public void shareImageToWeibo(Context context,Bitmap bitmap){
        this.context = context;
        this.bitmap = bitmap;
        try {
            Uri uriToImage = Uri.parse(MediaStore.Images.Media.insertImage(
                    context.getContentResolver(), bitmap, null, null));
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
            shareIntent.setType("image/*");
            // 遍历所有支持发送图片的应用。找到需要的应用
            PackageManager packageManager = context.getPackageManager();
            List<ResolveInfo> resolveInfoList = packageManager
                    .queryIntentActivities(shareIntent,
                            PackageManager.MATCH_DEFAULT_ONLY);
            ComponentName componentName = null;
            for (int i = 0; i < resolveInfoList.size(); i++) {
                if (TextUtils.equals(
                        resolveInfoList.get(i).activityInfo.packageName,
                        "com.sina.weibo")) {
                    componentName = new ComponentName(
                            resolveInfoList.get(i).activityInfo.packageName,
                            resolveInfoList.get(i).activityInfo.name);
                    break;
                }
            }
            // 已安装**
            if (null != componentName) {
                shareIntent.setComponent(componentName);
                context.startActivity(shareIntent);
            } else {
                Toast.makeText(context,"请先安装微博",Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context,"分享图片到微博失败",Toast.LENGTH_SHORT).show();
        }
    }

    public void shareImageToFaceBook(Context context,Bitmap bitmap){
        this.context = context;
        this.bitmap = bitmap;
        try {
            Uri uriToImage = Uri.parse(MediaStore.Images.Media.insertImage(
                    context.getContentResolver(), bitmap, null, null));
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
            shareIntent.setType("image/*");
            // 遍历所有支持发送图片的应用。找到需要的应用
            PackageManager packageManager = context.getPackageManager();
            List<ResolveInfo> resolveInfoList = packageManager
                    .queryIntentActivities(shareIntent,
                            PackageManager.MATCH_DEFAULT_ONLY);
            ComponentName componentName = null;
            for (int i = 0; i < resolveInfoList.size(); i++) {
                if (TextUtils.equals(
                        resolveInfoList.get(i).activityInfo.packageName,
                        "com.facebook.katana")) {
                    componentName = new ComponentName(
                            resolveInfoList.get(i).activityInfo.packageName,
                            resolveInfoList.get(i).activityInfo.name);
                    break;
                }
            }
            // 已安装**
            if (null != componentName) {
                shareIntent.setComponent(componentName);
                context.startActivity(shareIntent);
            } else {
                Toast.makeText(context,"请先安装FaceBook",Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context,"分享图片到FaceBook失败",Toast.LENGTH_SHORT).show();
        }
    }

    public void shareImageToOther(Context context,Bitmap bitmap){
        this.context = context;
        this.bitmap = bitmap;
            Uri uriToImage = Uri.parse(MediaStore.Images.Media.insertImage(
                    context.getContentResolver(), bitmap, null, null));
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
            shareIntent.setType("image/*");
            context.startActivity(Intent.createChooser(shareIntent, "分享图片"));
    }


}
