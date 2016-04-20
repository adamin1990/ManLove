package com.adamin.manslove.domain.juedui;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adamlee on 2016/3/30.
 */
public class CustomFields {
    @SerializedName("views")
    @Expose
    private List<String> views = new ArrayList<String>();

    /**
     *
     * @return
     * The views
     */
    public List<String> getViews() {
        return views;
    }

    /**
     *
     * @param views
     * The views
     */
    public void setViews(List<String> views) {
        this.views = views;
    }
}
