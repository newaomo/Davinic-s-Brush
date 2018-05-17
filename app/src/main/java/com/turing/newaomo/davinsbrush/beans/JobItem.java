package com.turing.newaomo.davinsbrush.beans;

/**
 * Created by newao on 2018/2/7.
 */

public class JobItem {

    private String job;
    private String jobDetail;
    private String colorId;

    public JobItem(){

    }

    public JobItem(String job, String jobDetail, String colorId ){
        this.colorId = colorId;
        this.job = job;
        this.jobDetail = jobDetail;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJobDetail() {
        return jobDetail;
    }

    public void setJobDetail(String jobDetail) {
        this.jobDetail = jobDetail;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }
}
