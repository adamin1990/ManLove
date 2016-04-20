package com.adamin.manslove.domain.juedui;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adamlee on 2016/3/30.
 */
public class JueDuiWrapper {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("count_total")
    @Expose
    private Integer countTotal;
    @SerializedName("pages")
    @Expose
    private Integer pages;
    @SerializedName("posts")
    @Expose
    private List<JdPost> posts = new ArrayList<JdPost>();

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

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
     * The countTotal
     */
    public Integer getCountTotal() {
        return countTotal;
    }

    /**
     *
     * @param countTotal
     * The count_total
     */
    public void setCountTotal(Integer countTotal) {
        this.countTotal = countTotal;
    }

    /**
     *
     * @return
     * The pages
     */
    public Integer getPages() {
        return pages;
    }

    /**
     *
     * @param pages
     * The pages
     */
    public void setPages(Integer pages) {
        this.pages = pages;
    }

    /**
     *
     * @return
     * The posts
     */
    public List<JdPost> getPosts() {
        return posts;
    }

    /**
     *
     * @param posts
     * The posts
     */
    public void setPosts(List<JdPost> posts) {
        this.posts = posts;
    }

}
