package com.adamin.manslove.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
 * //         Created by LiTao on 2016-02-23-23:34.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 *
 * 数据格式
 * {
 "data": {
 "page_info": {
 "page": 1,
 "count": 447,
 "media": "http://img.1985t.com/"
 },
 "list": [
 {
 "id": 55416,
 "image": "uploads/previews/2016/02/0-3CeiHjM.jpg",
 "height": 300,
 "width": 225,
 "item_num": 5,
 "zan": 1
 },
 {
 "id": 55415,
 "image": "uploads/previews/2016/02/0-xo6LqgB.jpg",
 "height": 300,
 "width": 225,
 "item_num": 8,
 "zan": 1
 },
 {
 "id": 55414,
 "image": "uploads/previews/2016/02/0-DJHpKhs.jpg",
 "height": 300,
 "width": 225,
 "item_num": 8,
 "zan": 0
 },
 {
 "id": 55413,
 "image": "uploads/previews/2016/02/0-CK2GD7Z.jpg",
 "height": 300,
 "width": 225,
 "item_num": 9,
 "zan": 2
 },
 * //
 */
public class HomeData {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("item_num")
    @Expose
    private Integer itemNum;
    @SerializedName("zan")
    @Expose
    private Integer zan;

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
     * The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     *
     * @return
     * The height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     *
     * @param height
     * The height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     *
     * @return
     * The width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     *
     * @param width
     * The width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     *
     * @return
     * The itemNum
     */
    public Integer getItemNum() {
        return itemNum;
    }

    /**
     *
     * @param itemNum
     * The item_num
     */
    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    /**
     *
     * @return
     * The zan
     */
    public Integer getZan() {
        return zan;
    }

    /**
     *
     * @param zan
     * The zan
     */
    public void setZan(Integer zan) {
        this.zan = zan;
    }

}
