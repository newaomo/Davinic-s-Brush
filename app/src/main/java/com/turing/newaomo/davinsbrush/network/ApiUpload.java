package com.turing.newaomo.davinsbrush.network;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by newao on 2018/2/18.
 */

public interface ApiUpload {

    //图片上传
    @Multipart
    @POST("upload")
    Call<String> updateImage(@PartMap Map<String, RequestBody> params);

    //传多个文件
    @Multipart
    @POST("/upload")
    Call<String> uploadImage(@Field("mode") String mode,
                             @Field("phoneNumber") String phoneNumber,
                             @Field("dateNumber") String dateNumber,
                             @Field("unifiedPattern") String unifiedPattern,
                             @Field("size_width") String size_width,
                             @Field("size_height") String size_height,
                             @Field("title") String title,
                             @Field("slogan") String slogan,
                             @Field("content") String content,
                             @Field("style") String style,
                             @Field("profile_name") String profile_name,
                             @Field("profile_honor") String profile_honor,
                             @Part MultipartBody.Part file_doc,     //这是doc文档
                             @Part MultipartBody.Part file_personal_picture,    //这是个人照
                             @PartMap Map<String, RequestBody> params);         //这是背景照

}
