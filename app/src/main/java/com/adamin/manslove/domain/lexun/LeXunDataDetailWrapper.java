package com.adamin.manslove.domain.lexun;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adamlee on 2016/4/2.
 */
public class LeXunDataDetailWrapper {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("pagesize")
    @Expose
    private Integer pagesize;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("record")
    @Expose
    private List<LeXunDataDetail> record = new ArrayList<LeXunDataDetail>();

    /**
     *
     * @return
     * The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     *
     * @param page
     * The page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     *
     * @return
     * The pagesize
     */
    public Integer getPagesize() {
        return pagesize;
    }

    /**
     *
     * @param pagesize
     * The pagesize
     */
    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    /**
     *
     * @return
     * The total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     *
     * @param total
     * The total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     *
     * @return
     * The record
     */
    public List<LeXunDataDetail> getRecord() {
        return record;
    }

    /**
     *
     * @param record
     * The record
     */
    public void setRecord(List<LeXunDataDetail> record) {
        this.record = record;
    }
}
