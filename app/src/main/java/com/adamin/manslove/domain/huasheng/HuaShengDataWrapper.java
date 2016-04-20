package com.adamin.manslove.domain.huasheng;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by adamlee on 2016/3/29.
 */
public class HuaShengDataWrapper {

    @SerializedName("statecode")
    @Expose
    private String statecode;
    @SerializedName("data")
    @Expose
    private HuaShengInnerWrapper data;

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
     * The data
     */
    public HuaShengInnerWrapper getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(HuaShengInnerWrapper data) {
        this.data = data;
    }
}
