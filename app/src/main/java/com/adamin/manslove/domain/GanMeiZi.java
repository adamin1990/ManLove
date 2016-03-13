package com.adamin.manslove.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by adamlee on 2016/3/13.
 */
public class GanMeiZi implements Serializable {

    @SerializedName("_id")
    @Expose
    private String Id;
    @SerializedName("_ns")
    @Expose
    private String Ns;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("used")
    @Expose
    private Boolean used;
    @SerializedName("who")
    @Expose
    private String who;

    /**
     *
     * @return
     * The Id
     */
    public String getId() {
        return Id;
    }

    /**
     *
     * @param Id
     * The _id
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     *
     * @return
     * The Ns
     */
    public String getNs() {
        return Ns;
    }

    /**
     *
     * @param Ns
     * The _ns
     */
    public void setNs(String Ns) {
        this.Ns = Ns;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The createdAt
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     *
     * @param desc
     * The desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     *
     * @return
     * The publishedAt
     */
    public String getPublishedAt() {
        return publishedAt;
    }

    /**
     *
     * @param publishedAt
     * The publishedAt
     */
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The used
     */
    public Boolean getUsed() {
        return used;
    }

    /**
     *
     * @param used
     * The used
     */
    public void setUsed(Boolean used) {
        this.used = used;
    }

    /**
     *
     * @return
     * The who
     */
    public String getWho() {
        return who;
    }

    /**
     *
     * @param who
     * The who
     */
    public void setWho(String who) {
        this.who = who;
    }

}
