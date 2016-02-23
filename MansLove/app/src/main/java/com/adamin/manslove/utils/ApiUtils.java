package com.adamin.manslove.utils;

import android.support.v4.view.MotionEventCompat;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by LiTao on 2016-02-20-17:36.
 * Company: QD24so
 * Email: 14846869@qq.com
 * WebSite: http://lixiaopeng.top
 */
public class ApiUtils {
    public static String getCrc(Map<String, String> params) {
        return getSHA1Str(getMapAppendUrl(params) + "562487d0560a67.08735608");
    }

    public static String getMapAppendUrl(Map<String, String> params) {
        StringBuffer sb1 = new StringBuffer();
        for (String key : params.keySet()) {
            sb1.append(key);
            sb1.append("=");
            sb1.append((String) params.get(key));
            sb1.append("&");
        }
        sb1.deleteCharAt(sb1.length() - 1);
        return sb1.toString();
    }

    public static String getSHA1Str(String var) {
        try {
            MessageDigest sha_1 = MessageDigest.getInstance("SHA-1");
            sha_1.update(var.getBytes());
            return byteToHexString(sha_1.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String byteToHexString(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        for (byte b2 : b) {
            String hex = Integer.toHexString(b2 & MotionEventCompat.ACTION_MASK);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toLowerCase());
        }
        return hexString.toString();
    }


    public static Map<String, String> getRealMap(Map<String, String> mMap) {
        mMap.put("crc",getCrc(mMap));
        return mMap;
    }

    /**
     * 获取话题
     * @param page
     * @param pagesize
     * @return
     */
    public static Map<String, String> getAlbumtopic(int page,String pagesize) {
        Map<String,String> mMap=new TreeMap<>();
        mMap.put("api","album.topic");
        mMap.put("page",page+"");
        mMap.put("pagesize",pagesize);
        mMap.put("appkey","562487d0560a0");
        mMap.put("ver","1.0");
        return getRealMap(mMap);
    }

    /**
     * 主页推荐   搜索   搜索channelid为 “”
     * @param page
     * @param pagesize
     * @param keyword
     * @param channelid  3 唯美写真  2性感美女   8丝袜美腿  6 高清美女  7 模特美女
     *                   4 网络美女 9 动漫美女   5体育美女  1 女星写真    11 男星图片
     * @return
     */
    public static Map<String, String> getAlbumtHomeData(int page,String pagesize,String keyword,String channelid) {
        Map<String,String> mMap=new TreeMap<>();
        mMap.put("api","album.home");
        mMap.put("channel_id",channelid);
        mMap.put("keyword",keyword);
        mMap.put("page",page+"");
        mMap.put("pagesize",pagesize);
        mMap.put("market","4493");
        mMap.put("version","2.0.4");
        mMap.put("appkey","562487d0560a0");
        mMap.put("ver","1.0");
        return getRealMap(mMap);
    }

    /**
     * 获取专辑详情
     * @param albumid
     * @return
     */
    public static Map<String, String> getAlbumDetail(String albumid) {
        Map<String,String> mMap=new TreeMap<>();
        mMap.put("api","album.detail");
        mMap.put("album_id",albumid);
        mMap.put("appkey","562487d0560a0");
        mMap.put("ver","1.0");
        return getRealMap(mMap);
    }

    public static Map<String, String> getrequestAlbumHot() {
        Map<String,String> mMap=new TreeMap<>();
        mMap.put("api","album.hot");
        mMap.put("appkey","562487d0560a0");
        mMap.put("ver","1.0");
        return getRealMap(mMap);
    }

    /**
     * 获取热门tab
     * @return
     */
    public static Map<String, String> getrequestAlbumChannel() {
        Map<String,String> mMap=new TreeMap<>();
        mMap.put("api","album.channel");
        mMap.put("market", "4493");
        mMap.put("version", "2.0.4");
        mMap.put("appkey","562487d0560a0");
        mMap.put("ver","1.0");
        return getRealMap(mMap);
    }
}
