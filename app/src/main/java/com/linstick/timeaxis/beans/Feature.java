package com.linstick.timeaxis.beans;

/**
 * Created by Administrator on 2018/9/5/005.
 */

public class Feature {

    public static final int TYPE_ADD_BOTTLE = 1;
    public static final int TYPE_GET_BOTTLE = 2;
    public static final int TYPE_BROWSE_MEMO = 3;
    public static final int TYPE_ADD_RECORD = 4;
    public static final int TYPE_BROWSE_RECORD = 5;
    public static final int TYPE_READ_ARTICLE = 6;


    private int imgRes;
    private String title;
    private int type;

    public Feature() {
    }

    public Feature(int imgRes, String title, int type) {
        this.imgRes = imgRes;
        this.title = title;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
