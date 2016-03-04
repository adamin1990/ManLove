package com.adamin.manslove.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;

import com.adamin.manslove.utils.Constant;
import com.adamin.manslove.utils.LogUtil;
import com.adamin.manslove.view.main.NetWorkInterface;

public class NetWorkService extends Service {
    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;

    private BroadcastReceiver myreceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                LogUtil.error(NetWorkService.class, "网络状态已经改变");
                connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isAvailable()) {
                    String name = networkInfo.getTypeName();
                    LogUtil.error(NetWorkService.class, "当前网络名称：" + name);
                    sendBroadcast(new Intent(Constant.ACTION_NETWORK_OPEN));
                    //doSomething()
                } else {
                    LogUtil.error(NetWorkService.class, "没有可用网络");
                    sendBroadcast(new Intent(Constant.ACTION_NETWORK_CLOSE));
                    //doSomething()
                }
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(myreceiver, mFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myreceiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
