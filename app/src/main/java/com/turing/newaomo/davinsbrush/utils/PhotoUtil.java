package com.turing.newaomo.davinsbrush.utils;

import android.app.Activity;
import android.net.Uri;
import android.os.Environment;


public class PhotoUtil {

        private static final String CROP_NAME = "crop.jpg";

        public static Uri buildUri(Activity activity) {
                if (CommonUtils.isSupportSdcard()) {
                       return Uri.fromFile(Environment.getExternalStorageDirectory()).buildUpon().appendPath(CROP_NAME).build();
                } else {
                        return Uri.fromFile(activity.getCacheDir()).buildUpon().appendPath(CROP_NAME).build();
                }
        }
}
