package com.adamin.manslove.domain.tg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adamlee on 2016/4/7.
 */
public class TgDataWrapper {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("tngou")
    @Expose
    private List<TgData> tngou = new ArrayList<TgData>();

    /**
     *
     * @return
     * The status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(Boolean status) {
        this.status = status;
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
     * The tngou
     */
    public List<TgData> getTngou() {
        return tngou;
    }

    /**
     *
     * @param tngou
     * The tngou
     */
    public void setTngou(List<TgData> tngou) {
        this.tngou = tngou;
    }
}
