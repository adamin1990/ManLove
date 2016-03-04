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
 * //         Created by LiTao on 2016-02-26-22:30.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public class DetailDataWrapperReal {
    @SerializedName("data")
    @Expose
    private DetailDataWrapper data;

    /**
     *
     * @return
     * The data
     */
    public DetailDataWrapper getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(DetailDataWrapper data) {
        this.data = data;
    }

}
/*
* {
    "data": {
        "title": "清新美女私房小性感写真",
        "before": 55732,
        "beforeUrl": "uploads/previews/2016/02/0-Sq6C4C8.jpg",
        "after": 55730,
        "afterUrl": "uploads/previews/2016/02/0-UASGQbe.jpg",
        "media": "http://img.1985t.com/",
        "list": [
            {
                "id": 424510,
                "url": "uploads/attaches/2016/02/64733-Kjwi0Vw.jpg"
            },
            {
                "id": 424511,
                "url": "uploads/attaches/2016/02/64733-ZqtfkdI.jpg"
            },
            {
                "id": 424512,
                "url": "uploads/attaches/2016/02/64733-a1xEYTq.jpg"
            },
            {
                "id": 424513,
                "url": "uploads/attaches/2016/02/64733-8cUkSuQ.jpg"
            },
            {
                "id": 424514,
                "url": "uploads/attaches/2016/02/64733-g7duqMS.jpg"
            },
            {
                "id": 424515,
                "url": "uploads/attaches/2016/02/64733-iNmyGjn.jpg"
            },
            {
                "id": 424516,
                "url": "uploads/attaches/2016/02/64733-uS2dOTz.jpg"
            }
        ]
    }
}*/
