package com.turing.newaomo.davinsbrush.utils.SP;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kaku.colorfulnews.App;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class SPPostUtils {
    private static final String KEY_UNIFIEDPATTERN = "unifiedPattern";
    private static final String KEY_SIZE_WIDTH = "size_width";
    private static final String KEY_SIZE_HEIGHT = "size_height";
    private static final String KEY_TITLE = "title";
    private static final String KEY_SLOGAN = "slogan";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_STYLE = "style";
    private static final String KEY_FILE_DOC_NAME= "file_doc_name";
    private static final String KEY_PICTURE_LIST = "pictureList";
    private static final String KEY_PICTURE_PERSONAL_LIST = "picturePersonalList";
    private static final String KEY_PROFILE_NAME = "profile_name";
    private static final String KEY_PROFILE_HONOR = "profile_honor";
    private static final String KEY_PROFILE_INTRODUCE = "profile_introduce";
    private static final String KEY_PROFILE_MOTTO = "profile_motto";



    private SharedPreferences sharePreferences;
    private SharedPreferences.Editor editor = null;

    //模式的选择
    public boolean setUnifiedPattern(String pattern){
        editor.putString(KEY_UNIFIEDPATTERN,pattern);
        return editor.commit();
    }
    public String getUnifiedPattern(){
        return sharePreferences.getString(KEY_UNIFIEDPATTERN,"");
    }
    //尺寸的选择
    public boolean setSizeWidth(String width){
        editor.putString(KEY_SIZE_WIDTH,width);
        return editor.commit();
    }
    public String getSizeWidth(){
        return sharePreferences.getString(KEY_SIZE_WIDTH,"");
    }
    public boolean setSizeHeight(String height){
        editor.putString(KEY_SIZE_HEIGHT,height);
        return editor.commit();
    }
    public String getSizeHeight(){
        return sharePreferences.getString(KEY_SIZE_HEIGHT,"");
    }
    //标题、标语、内容的选择
    public boolean setTitle(String title){
        editor.putString(KEY_TITLE,title);
        return editor.commit();
    }
    public String getTitle(){
        return sharePreferences.getString(KEY_TITLE,"");
    }
    public boolean setSlogan(String slogan){
        editor.putString(KEY_SLOGAN,slogan);
        return editor.commit();
    }
    public String getSlogan(){
        return sharePreferences.getString(KEY_SLOGAN,"");
    }
    public boolean setContent(String content){
        editor.putString(KEY_CONTENT,content);
        return editor.commit();
    }
    public String getContent(){
        return sharePreferences.getString(KEY_CONTENT,"");
    }
    //文件doc的选择
    public boolean setFileName(String fileName){
        editor.putString(KEY_FILE_DOC_NAME,fileName);
        return editor.commit();
    }
    public String getFileName(){
        return sharePreferences.getString(KEY_FILE_DOC_NAME,"");
    }
    //风格的选择
    public boolean setStyle(String style){
        editor.putString(KEY_STYLE,style);
        return editor.commit();
    }
    public String getStyle(){
        return sharePreferences.getString(KEY_STYLE,"");
    }
    //图片
    public <K, V> boolean putPictureList(Map<K,V>map){
        boolean result;
        try {
            Gson gson = new Gson();
            String json = gson.toJson(map);
            editor.putString(KEY_PICTURE_LIST, json);
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        editor.apply();
        return result;
    }
    public <V> HashMap<String, V> getPictureList(String key, Class<V> clsV) {
        String json = sharePreferences.getString(key, "");
        HashMap<String, V> map = new HashMap<>();
        Gson gson = new Gson();
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entrySet = obj.entrySet();
        for (Map.Entry<String, JsonElement> entry : entrySet) {
            String entryKey = entry.getKey();
            JsonObject value = (JsonObject) entry.getValue();
            map.put(entryKey, gson.fromJson(value, clsV));
        }
        Log.e("SharedPreferencesUtil", obj.toString());
        return map;
    }
    //图片（个人照片）
    public <K, V> boolean putPicturePersonalList(Map<K,V>map){
        boolean result;
        try {
            Gson gson = new Gson();
            String json = gson.toJson(map);
            editor.putString(KEY_PICTURE_LIST, json);
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        editor.apply();
        return result;
    }
    public <V> HashMap<String, V> getPicturePersonalList(String key, Class<V> clsV) {
        String json = sharePreferences.getString(key, "");
        HashMap<String, V> map = new HashMap<>();
        Gson gson = new Gson();
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entrySet = obj.entrySet();
        for (Map.Entry<String, JsonElement> entry : entrySet) {
            String entryKey = entry.getKey();
            JsonObject value = (JsonObject) entry.getValue();
            map.put(entryKey, gson.fromJson(value, clsV));
        }
        Log.e("SharedPreferencesUtil", obj.toString());
        return map;
    }
    //个人简报的四个个人信息模块
    public boolean setProfileName(String name){
        editor.putString(KEY_PROFILE_NAME,name);
        return editor.commit();
    }
    public String getProfileName(){
        return sharePreferences.getString(KEY_PROFILE_NAME,"");
    }
    public boolean setProfileHonor(String honor){
        editor.putString(KEY_PROFILE_HONOR,honor);
        return editor.commit();
    }
    public String getProfileHonor(){
        return sharePreferences.getString(KEY_PROFILE_HONOR,"");
    }
    public boolean setProfileIntroduce(String introduce){
        editor.putString(KEY_PROFILE_INTRODUCE,introduce);
        return editor.commit();
    }
    public String getProfileIntroduce(){
        return sharePreferences.getString(KEY_PROFILE_INTRODUCE,"");
    }
    public boolean setProfileMotto(String motto){
        editor.putString(KEY_PROFILE_MOTTO,motto);
        return editor.commit();
    }
    public String getProfileMotto(){
        return sharePreferences.getString(KEY_PROFILE_MOTTO,"");
    }

    //清空上次数据的方法
    public void clearData(){
        editor.clear();
    }


    public SPPostUtils(Context context, String name) {
        sharePreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        editor = sharePreferences.edit();
    }

    private static SPPostUtils spPostUtils;
    public synchronized static SPPostUtils getInstance() {
        if (spPostUtils == null) {
            spPostUtils = new SPPostUtils(App.INSTANCE(),"process_to_post");
            return spPostUtils;
        }
        return spPostUtils;
    }


}