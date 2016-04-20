package com.adamin.manslove.domain.huasheng;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adamlee on 2016/3/29.
 */
public class HuaShengImageList {
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("imglist")
    @Expose
    private List<String> imglist = new ArrayList<String>();

    /**
     *
     * @return
     * The total
     */
    public String getTotal() {
        return total;
    }

    /**
     *
     * @param total
     * The total
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     *
     * @return
     * The subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     *
     * @param subject
     * The subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     *
     * @return
     * The imglist
     */
    public List<String> getImglist() {
        return imglist;
    }

    /**
     *
     * @param imglist
     * The imglist
     */
    public void setImglist(List<String> imglist) {
        this.imglist = imglist;
    }

}
