package com.adamin.manslove.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * //                           o8888888o
 * //                           88" . "88
 * //                           (| -_- |)
 * //                            O\ = /O
 * //                        ____/`---'\____
 * //                      .   ' \\| |// `.
 * //                       / \\||| : |||// \
 * //                     / _||||| -:- |||||- \
 * //                       | | \\\ - /// | |
 * //                     | \_| ''\---/'' | |
 * //                      \ .-\__ `-` ___/-. /
 * //                   ___`. .' /--.--\ `. . __
 * //                ."" '< `.___\_<|>_/___.' >'"".
 * //               | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * //                 \ \ `-. \_ __\ /__ _/ .-` / /
 * //         ======`-.____`-.___\_____/___.-`____.-'======
 * //                            `=---='
 * //
 * //         .............................................
 * //                  佛祖镇楼                  BUG辟易
 * //          佛曰:
 * //                  写字楼里写字间，写字间里程序员；
 * //                  程序人员写程序，又拿程序换酒钱。
 * //                  酒醒只在网上坐，酒醉还来网下眠；
 * //                  酒醉酒醒日复日，网上网下年复年。
 * //                  但愿老死电脑间，不愿鞠躬老板前；
 * //                  奔驰宝马贵者趣，公交自行程序员。
 * //                  别人笑我忒疯癫，我笑自己命太贱；
 * //                  不见满街漂亮妹，哪个归得程序员？
 * //
 * //         Created by LiTao on 2016-02-26-22:29.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public class DetailDataWrapper {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("before")
    @Expose
    private Integer before;
    @SerializedName("beforeUrl")
    @Expose
    private String beforeUrl;
    @SerializedName("after")
    @Expose
    private Integer after;
    @SerializedName("afterUrl")
    @Expose
    private String afterUrl;
    @SerializedName("media")
    @Expose
    private String media;
    @SerializedName("list")
    @Expose
    private java.util.List<DetailData> list = new ArrayList<DetailData>();

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
     * The before
     */
    public Integer getBefore() {
        return before;
    }

    /**
     *
     * @param before
     * The before
     */
    public void setBefore(Integer before) {
        this.before = before;
    }

    /**
     *
     * @return
     * The beforeUrl
     */
    public String getBeforeUrl() {
        return beforeUrl;
    }

    /**
     *
     * @param beforeUrl
     * The beforeUrl
     */
    public void setBeforeUrl(String beforeUrl) {
        this.beforeUrl = beforeUrl;
    }

    /**
     *
     * @return
     * The after
     */
    public Integer getAfter() {
        return after;
    }

    /**
     *
     * @param after
     * The after
     */
    public void setAfter(Integer after) {
        this.after = after;
    }

    /**
     *
     * @return
     * The afterUrl
     */
    public String getAfterUrl() {
        return afterUrl;
    }

    /**
     *
     * @param afterUrl
     * The afterUrl
     */
    public void setAfterUrl(String afterUrl) {
        this.afterUrl = afterUrl;
    }

    /**
     *
     * @return
     * The media
     */
    public String getMedia() {
        return media;
    }

    /**
     *
     * @param media
     * The media
     */
    public void setMedia(String media) {
        this.media = media;
    }

    /**
     *
     * @return
     * The list
     */
    public java.util.List<DetailData> getList() {
        return list;
    }

    /**
     *
     * @param list
     * The list
     */
    public void setList(java.util.List<DetailData> list) {
        this.list = list;
    }

}
