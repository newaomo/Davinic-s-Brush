package com.turing.newaomo.davinsbrush.mydb;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.turing.newaomo.davinsbrush.mydb.bean.PictureInfo;
import com.turing.newaomo.davinsbrush.mydb.gen.DaoMaster;
import com.turing.newaomo.davinsbrush.mydb.gen.DaoSession;
import com.turing.newaomo.davinsbrush.mydb.gen.PictureInfoDao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 *
 * Created by newao on 2018/4/16.
 */

public class MyDBHelper {

    private static MyDBHelper instance;
    private static Context mContext;

    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    //获得所有的风格

    //获得所有的风格数量

    //获得所有的颜色

    //获得所有的颜色数量

    //返回某一种风格标签下面所有的图片
    public List<PictureInfo> getAllPictureByStyle(String style){
        PictureInfoDao pictureInfoDao = daoSession.getPictureInfoDao();
        return pictureInfoDao.queryBuilder().where(PictureInfoDao.Properties.Style.eq(style)).list();
    }

    public List<PictureInfo>getAllPictureByColor(List<String>colors){
        List<PictureInfo> pictureInfoList = new ArrayList<>();
        PictureInfoDao pictureInfoDao = daoSession.getPictureInfoDao();
        for(int i = 0;i<colors.size();i++){
            switch (i){
                case 0:
                    List<PictureInfo> list = pictureInfoDao.queryBuilder().where(PictureInfoDao.Properties.Color1.eq(colors.get(0))).list();
                    pictureInfoList.addAll(list);
                    HashSet<PictureInfo> h = new HashSet<>(pictureInfoList);
                    pictureInfoList.clear();
                    pictureInfoList.addAll(h);
                break;
                case 1:
                    List<PictureInfo> list1 = pictureInfoDao.queryBuilder().where(PictureInfoDao.Properties.Color1.eq(colors.get(1))).list();
                    pictureInfoList.addAll(list1);
                    HashSet<PictureInfo> h1 = new HashSet<>(pictureInfoList);
                    pictureInfoList.clear();
                    pictureInfoList.addAll(h1);
                    break;
                case 2:
                    List<PictureInfo> list2 = pictureInfoDao.queryBuilder().where(PictureInfoDao.Properties.Color1.eq(colors.get(2))).list();
                    pictureInfoList.addAll(list2);
                    HashSet<PictureInfo> h2 = new HashSet<>(pictureInfoList);
                    pictureInfoList.clear();
                    pictureInfoList.addAll(h2);
                    break;
            }
        }
        return pictureInfoList;
    }

    //返回某一种颜色标签下面所有的图片
//    public List<PictureInfo> getPictureColorList(String typeName){
//        PictureDao pictureDao = daoSession.getPictureDao();
//        List<Picture> labelStyles1 = pictureDao.queryBuilder().where(PictureDao.Properties.LabelColorName1.eq(typeName)).list();
//        List<Picture> labelStyles2 = pictureDao.queryBuilder().where(PictureDao.Properties.LabelColorName2.eq(typeName)).list();
//        List<Picture> labelStyles3 = pictureDao.queryBuilder().where(PictureDao.Properties.LabelColorName3.eq(typeName)).list();
//        List<Picture> labelStyles = new ArrayList<>();
//        labelStyles.addAll(labelStyles1);
//        labelStyles.addAll(labelStyles2);
//        labelStyles.addAll(labelStyles3);
//        return labelStyles;
//    }

    public int getStyleSize(String style){
        PictureInfoDao pictureInfoDao = daoSession.getPictureInfoDao();
        List<PictureInfo> list = pictureInfoDao.queryBuilder().where(PictureInfoDao.Properties.Style.eq(style)).list();
        return list.size();
    }

//    添加一个图片到图库
    public void addPictureToGallery(PictureInfo pictureInfo){
        PictureInfoDao pictureInfoDao = daoSession.getPictureInfoDao();
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("picture",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Long m = sharedPreferences.getLong("pictureId",0);
        editor.putLong("pictureId",m+1);
        Log.d("-----","这是第"+ (m+1)+"个图片");
        editor.apply();
        pictureInfo.setId((m+1));
        pictureInfoDao.insert(pictureInfo);
    }

    //编辑一个图片的信息
    public void editPicture(String info,String style,List<String>colors){
        PictureInfoDao pictureInfoDao = daoSession.getPictureInfoDao();
        PictureInfo pictureInfo = pictureInfoDao.queryBuilder().where(PictureInfoDao.Properties.Info.eq(info)).unique();
        pictureInfo.setColors(colors);
        for(int i = 0;i<colors.size();i++){
            switch (i){
                case 0:
                    pictureInfo.setColor1(colors.get(0));
                    break;
                case 1:
                    pictureInfo.setColor2(colors.get(1));
                    break;
                case 2:
                    pictureInfo.setColor3(colors.get(2));
                    break;
            }
        }
        pictureInfo.setStyle(style);
        pictureInfoDao.update(pictureInfo);
    }

    //删除一张图片
    public void deletePicture(String info){
        PictureInfoDao pictureInfoDao = daoSession.getPictureInfoDao();
        PictureInfo pictureInfo = pictureInfoDao.queryBuilder().where(PictureInfoDao.Properties.Info.eq(info)).unique();
        pictureInfoDao.delete(pictureInfo);
    }

    //获取所有的图片
    public List<PictureInfo> getAllPicture(){
        PictureInfoDao pictureInfoDao = daoSession.getPictureInfoDao();
        List<PictureInfo> pictureInfoList = pictureInfoDao.queryBuilder().where(PictureInfoDao.Properties.Info.isNotNull()).list();
        return pictureInfoList;
    }

    //TODO 在这里执行以下插入默认图片的做法
    public void createDefaultPicture(){
        PictureInfoDao pictureInfoDao = daoSession.getPictureInfoDao();
        Long size = Long.valueOf(getAllPicture().size());
        for(int i = 0;i < 8;i++){
            PictureInfo pictureInfo = new PictureInfo();
            pictureInfo.setId(i+size);
            pictureInfoDao.insert(pictureInfo);
        }
    }

    /**
     * 取得DaoMaster
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context,
                    "notes.db", null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     */
    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }


    public static void init(Context context) {
        mContext = context;
        instance = new MyDBHelper();
        // 数据库对象
        DaoSession daoSession = getDaoSession(mContext);
    }

    public static MyDBHelper getInstance() {
        return instance;
    }



}