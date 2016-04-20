package com.adamin.manslove.domain.huasheng;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adamlee on 2016/3/29.
 */
public class HuaShengInnerWrapper {

    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("thread")
    @Expose
    private List<HuaShengData> thread = new ArrayList<HuaShengData>();

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
     * The thread
     */
    public List<HuaShengData> getThread() {
        return thread;
    }

    /**
     *
     * @param thread
     * The thread
     */
    public void setThread(List<HuaShengData> thread) {
        this.thread = thread;
    }
}
