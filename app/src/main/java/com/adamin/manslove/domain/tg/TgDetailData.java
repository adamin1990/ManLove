package com.adamin.manslove.domain.tg;

import android.app.Service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by adamlee on 2016/4/15.
 */
public class TgDetailData implements Serializable{
    @SerializedName("gallery")
    @Expose
    private Integer gallery;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("src")
    @Expose
    private String src;

    /**
     *
     * @return
     * The gallery
     */
    public Integer getGallery() {
        return gallery;
    }

    /**
     *
     * @param gallery
     * The gallery
     */
    public void setGallery(Integer gallery) {
        this.gallery = gallery;
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
     * The src
     */
    public String getSrc() {
        return src;
    }

    /**
     *
     * @param src
     * The src
     */
    public void setSrc(String src) {
        this.src = src;
    }


}
