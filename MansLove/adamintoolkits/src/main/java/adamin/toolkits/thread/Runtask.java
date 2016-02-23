package adamin.toolkits.thread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;
import java.util.Collection;

import adamin.toolkits.service.CancelableTask;

/**
 * Created with IntelliJ IDEA.
 * Author: wangjie  email:tiantian.china.2@gmail.com
 * Date: 14-2-26
 * Time: 下午3:50
 */
public abstract class Runtask<U, R> implements Runnable, CancelableTask {
    public Object[] objs;

    protected Runtask(Object... objs) {
        this.objs = objs;
    }

    private static final int TASK_BEFORE_UI = 0X001;
    private static final int TASK_UPDATE_UI = 0x002;
    private static final int TASK_RESULT = 0x003;

    private boolean isCanceled;

    protected Handler rHandler = new MyHandler(Looper.getMainLooper(), this);

    private class MyHandler extends Handler {
        WeakReference<Runtask<U, R>> runtaskWeakReference;

        public MyHandler(Looper looper, Runtask<U, R> runtask) {
            super(looper);
            this.runtaskWeakReference = new WeakReference<>(runtask);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Runtask<U, R> runtask = runtaskWeakReference.get();
            if (null == runtask) {
                return;
            }
            switch (msg.arg1) {
                case TASK_BEFORE_UI:
                    runtask.onBefore();
                    break;
                case TASK_UPDATE_UI:
                    runtask.onUpdateUiCallBack((U) msg.obj);
                    break;
                case TASK_RESULT:
                    runtask.onResult((R) msg.obj);
                    break;
            }

        }
    }

//    protected Handler rHandler = new Handler(Looper.getMainLooper()) {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.arg1) {
//                case TASK_BEFORE_UI:
//                    onBefore();
//                    break;
//                case TASK_UPDATE_UI:
//                    onUpdateUiCallBack((U) msg.obj);
//                    break;
//                case TASK_RESULT:
//                    onResult((R) msg.obj);
//                    break;
//            }
//
//        }
//    };


    @Override
    public void run() {
        if (isCanceled) {
            return;
        }

        Message msg = rHandler.obtainMessage();
        msg.arg1 = TASK_BEFORE_UI;
        rHandler.sendMessage(msg);

        if (isCanceled) {
            return;
        }
        R result = runInBackground();
        if (isCanceled) {
            return;
        }

        msg = rHandler.obtainMessage();
        msg.arg1 = TASK_RESULT;
        msg.obj = result;
        rHandler.sendMessage(msg);
    }

    public abstract R runInBackground();

    public void updateUi(U obj) {
        Message msg = rHandler.obtainMessage();
        msg.arg1 = TASK_UPDATE_UI;
        msg.obj = obj;
        rHandler.sendMessage(msg);
    }

    protected void onBefore() {
    }

    protected void onUpdateUiCallBack(U obj) {
    }

    protected void onResult(R result) {
    }

    public void cancel() {
        if (!isCanceled) {
            isCanceled = true;
        }
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        if (mayInterruptIfRunning) {
            Thread.currentThread().interrupt();
        }
        cancel();
        return true;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    @Override
    public void remove() {
        // ignore
    }

    @Override
    public void addListener(Collection<CancelableTask> cancelableTaskList) {
        // ignore
    }
}
