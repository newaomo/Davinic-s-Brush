package com.turing.newaomo.davinsbrush.beans;

/**
 * Created by newao on 2018/2/8.
 */

public class BrushUser {

    private String brushNumber;

    private String phoneNumber;
    private String avatar;
    private String nickName;
    private String sex;
    private String job;
    private String birthday;
    private String email;
    private String signature;
    private String address;

    public BrushUser(){}

    public BrushUser(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getBrushNumber() {
        return brushNumber;
    }

    public void setBrushNumber(String brushNumber) {
        this.brushNumber = brushNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
