package com.adamin.manslove.domain.huasheng;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by adamlee on 2016/3/29.
 */
public class HuaShengImageListWrapper {

    @SerializedName("statecode")
    @Expose
    private String statecode;
    @SerializedName("aid")
    @Expose
    private String aid;
    @SerializedName("tid")
    @Expose
    private String tid;
    @SerializedName("imagelist")
    @Expose
    private HuaShengImageList imagelist;

    /**
     *
     * @return
     * The statecode
     */
    public String getStatecode() {
        return statecode;
    }

    /**
     *
     * @param statecode
     * The statecode
     */
    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

    /**
     *
     * @return
     * The aid
     */
    public String getAid() {
        return aid;
    }

    /**
     *
     * @param aid
     * The aid
     */
    public void setAid(String aid) {
        this.aid = aid;
    }

    /**
     *
     * @return
     * The tid
     */
    public String getTid() {
        return tid;
    }

    /**
     *
     * @param tid
     * The tid
     */
    public void setTid(String tid) {
        this.tid = tid;
    }

    /**
     *
     * @return
     * The imagelist
     */
    public HuaShengImageList getImagelist() {
        return imagelist;
    }

    /**
     *
     * @param imagelist
     * The imagelist
     */
    public void setImagelist(HuaShengImageList imagelist) {
        this.imagelist = imagelist;
    }
}
