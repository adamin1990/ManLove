package com.adamin.manslove.domain.lexun;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by adamlee on 2016/4/2.
 */
public class LeXunDataDetail {


    @SerializedName("infoid")
    @Expose
    private Integer infoid;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("filesize")
    @Expose
    private Integer filesize;
    @SerializedName("actpath")
    @Expose
    private String actpath;
    @SerializedName("prevpath")
    @Expose
    private String prevpath;
    @SerializedName("writetime")
    @Expose
    private String writetime;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("typeid")
    @Expose
    private Integer typeid;
    @SerializedName("unitid")
    @Expose
    private Integer unitid;
    @SerializedName("flower")
    @Expose
    private Integer flower;
    @SerializedName("egg")
    @Expose
    private Integer egg;
    @SerializedName("totaldown")
    @Expose
    private Integer totaldown;

    /**
     *
     * @return
     * The infoid
     */
    public Integer getInfoid() {
        return infoid;
    }

    /**
     *
     * @param infoid
     * The infoid
     */
    public void setInfoid(Integer infoid) {
        this.infoid = infoid;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The filesize
     */
    public Integer getFilesize() {
        return filesize;
    }

    /**
     *
     * @param filesize
     * The filesize
     */
    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    /**
     *
     * @return
     * The actpath
     */
    public String getActpath() {
        return actpath;
    }

    /**
     *
     * @param actpath
     * The actpath
     */
    public void setActpath(String actpath) {
        this.actpath = actpath;
    }

    /**
     *
     * @return
     * The prevpath
     */
    public String getPrevpath() {
        return prevpath;
    }

    /**
     *
     * @param prevpath
     * The prevpath
     */
    public void setPrevpath(String prevpath) {
        this.prevpath = prevpath;
    }

    /**
     *
     * @return
     * The writetime
     */
    public String getWritetime() {
        return writetime;
    }

    /**
     *
     * @param writetime
     * The writetime
     */
    public void setWritetime(String writetime) {
        this.writetime = writetime;
    }

    /**
     *
     * @return
     * The width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     *
     * @param width
     * The width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     *
     * @return
     * The height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     *
     * @param height
     * The height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     *
     * @return
     * The typeid
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     *
     * @param typeid
     * The typeid
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     *
     * @return
     * The unitid
     */
    public Integer getUnitid() {
        return unitid;
    }

    /**
     *
     * @param unitid
     * The unitid
     */
    public void setUnitid(Integer unitid) {
        this.unitid = unitid;
    }

    /**
     *
     * @return
     * The flower
     */
    public Integer getFlower() {
        return flower;
    }

    /**
     *
     * @param flower
     * The flower
     */
    public void setFlower(Integer flower) {
        this.flower = flower;
    }

    /**
     *
     * @return
     * The egg
     */
    public Integer getEgg() {
        return egg;
    }

    /**
     *
     * @param egg
     * The egg
     */
    public void setEgg(Integer egg) {
        this.egg = egg;
    }

    /**
     *
     * @return
     * The totaldown
     */
    public Integer getTotaldown() {
        return totaldown;
    }

    /**
     *
     * @param totaldown
     * The totaldown
     */
    public void setTotaldown(Integer totaldown) {
        this.totaldown = totaldown;
    }

}
