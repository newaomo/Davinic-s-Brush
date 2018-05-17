package com.turing.newaomo.davinsbrush.utils.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by newao on 2018/2/5.
 */

public class PictureSaveUtil {

    private Bitmap bitmap;
    private List<Bitmap> bitmapList;
    private Context context;

    private static PictureSaveUtil instance;

    public static PictureSaveUtil getInstance(Context context) {
        if (instance == null){
            instance = new PictureSaveUtil(context);
        }
        return instance;
    }
    public PictureSaveUtil(){}

    public PictureSaveUtil(Context context){
        this.context = context;
    }

    public void saveImageToGallery(Bitmap bitmap){
        this.bitmap = bitmap;

        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Pictures/DavinBrush");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
//        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + "/sdcard/Pictures/DavinBrush/"+fileName)));
    }


}
