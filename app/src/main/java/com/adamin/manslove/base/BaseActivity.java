package com.adamin.manslove.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.adamin.manslove.utils.Constant;
import com.adamin.manslove.utils.LogUtil;

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
 * //         Created by LiTao on 2016-02-23-16:57.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected  MyReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.error(BaseActivity.class,"createactivity");
        receiver=new MyReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction(Constant.ACTION_NETWORK_OPEN);
        filter.addAction(Constant.ACTION_NETWORK_CLOSE);
        registerReceiver(receiver,filter);
    }

    @Override
    protected void onDestroy() {
        if(receiver!=null){
            unregisterReceiver(receiver);

        }
        super.onDestroy();

    }

  class MyReceiver extends BroadcastReceiver{

      @Override
      public void onReceive(Context context, Intent intent) {
          if(intent.getAction().equals(Constant.ACTION_NETWORK_OPEN)){
              netWorkOpen();
              return;
          }
          if(intent.getAction().equals(Constant.ACTION_NETWORK_CLOSE)){
              netWorkClose();
              return;
          }

      }
  }

    /**
     * 网络开
     */
    public abstract void netWorkOpen();

    /**
     * 网络关
     */
    public  abstract void netWorkClose();
}
