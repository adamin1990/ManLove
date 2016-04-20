package com.adamin.manslove.domain.tg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by adamlee on 2016/4/7.
 */
public class TgData {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("fcount")
    @Expose
    private Integer fcount;
    @SerializedName("galleryclass")
    @Expose
    private Integer galleryclass;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("rcount")
    @Expose
    private Integer rcount;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("time")
    @Expose
    private Object time;
    @SerializedName("title")
    @Expose
    private String title;

    /**
     *
     * @return
     * The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     *
     * @param count
     * The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     *
     * @return
     * The fcount
     */
    public Integer getFcount() {
        return fcount;
    }

    /**
     *
     * @param fcount
     * The fcount
     */
    public void setFcount(Integer fcount) {
        this.fcount = fcount;
    }

    /**
     *
     * @return
     * The galleryclass
     */
    public Integer getGalleryclass() {
        return galleryclass;
    }

    /**
     *
     * @param galleryclass
     * The galleryclass
     */
    public void setGalleryclass(Integer galleryclass) {
        this.galleryclass = galleryclass;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The img
     */
    public String getImg() {
        return img;
    }

    /**
     *
     * @param img
     * The img
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     *
     * @return
     * The rcount
     */
    public Integer getRcount() {
        return rcount;
    }

    /**
     *
     * @param rcount
     * The rcount
     */
    public void setRcount(Integer rcount) {
        this.rcount = rcount;
    }

    /**
     *
     * @return
     * The size
     */
    public Integer getSize() {
        return size;
    }

    /**
     *
     * @param size
     * The size
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     *
     * @return
     * The time
     */
    public Object getTime() {
        return time;
    }

    /**
     *
     * @param time
     * The time
     */
    public void setTime(Object time) {
        this.time = time;
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

}
