package com.linstick.timeaxis.beans;

/**
 * Created by Administrator on 2018/9/5/005.
 */

public class Feature {
    int imgRes;
    String title;
    Class intentClass;

    public Feature() {

    }

    public Feature(int imgRes, String title, Class intentClass) {
        this.imgRes = imgRes;
        this.title = title;
        this.intentClass = intentClass;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getIntentClass() {
        return intentClass;
    }

    public void setIntentClass(Class intentClass) {
        this.intentClass = intentClass;
    }
}
