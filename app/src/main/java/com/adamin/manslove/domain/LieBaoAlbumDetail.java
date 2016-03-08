package com.adamin.manslove.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

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
 * //         Created by LiTao on 2016-03-08-1:52.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public class LieBaoAlbumDetail implements Serializable {
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("mid_image")
    @Expose
    private String midImage;
    @SerializedName("images_id")
    @Expose
    private String imagesId;
    @SerializedName("small_image")
    @Expose
    private String smallImage;
    @SerializedName("big_image")
    @Expose
    private String bigImage;
    @SerializedName("title")
    @Expose
    private String title;

    /**
     *
     * @return
     * The source
     */
    public String getSource() {
        return source;
    }

    /**
     *
     * @param source
     * The source
     */
    public void setSource(String source) {
        this.source = source;
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
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The midImage
     */
    public String getMidImage() {
        return midImage;
    }

    /**
     *
     * @param midImage
     * The mid_image
     */
    public void setMidImage(String midImage) {
        this.midImage = midImage;
    }

    /**
     *
     * @return
     * The imagesId
     */
    public String getImagesId() {
        return imagesId;
    }

    /**
     *
     * @param imagesId
     * The images_id
     */
    public void setImagesId(String imagesId) {
        this.imagesId = imagesId;
    }

    /**
     *
     * @return
     * The smallImage
     */
    public String getSmallImage() {
        return smallImage;
    }

    /**
     *
     * @param smallImage
     * The small_image
     */
    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    /**
     *
     * @return
     * The bigImage
     */
    public String getBigImage() {
        return bigImage;
    }

    /**
     *
     * @param bigImage
     * The big_image
     */
    public void setBigImage(String bigImage) {
        this.bigImage = bigImage;
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
