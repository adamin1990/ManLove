package adamin.toolkits.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.ClipboardManager;

import java.io.*;
import java.nio.charset.Charset;

import adamin.toolkits.log.Logger;

/**
 * Created with IntelliJ IDEA.
 * Author: wangjie  email:tiantian.china.2@gmail.com
 * Date: 14-4-1
 * Time: 下午1:43
 */
public class ABIOUtil {
    public static final String TAG = ABIOUtil.class.getSimpleName();


    /**
     * 复制功能
     *
     * @param context
     * @param content
     */
    public static void copy(Context context, String content) {
        ClipboardManager cm = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setText(content);
    }


    /**
     * 关闭流
     *
     * @param closeables
     */
    public static void closeIO(Closeable... closeables) {
        if (null == closeables || closeables.length <= 0) {
            return;
        }
        for (Closeable cb : closeables) {
            try {
                if (null == cb) {
                    continue;
                }
                cb.close();
            } catch (IOException e) {
                Logger.e(TAG, "close IO ERROR...", e);
            }
        }
    }

    /**
     * recyle bitmaps
     *
     * @param bitmaps
     */
    public static void recycleBitmap(Bitmap... bitmaps) {
        if (ABTextUtil.isEmpty(bitmaps)) {
            return;
        }

        for (Bitmap bm : bitmaps) {
            if (null != bm && !bm.isRecycled()) {
                bm.recycle();
            }
        }
    }


    /**
     * 复制文件
     *
     * @param from
     * @param to
     */
    public static void copyFile(File from, File to) {
        if (null == from || !from.exists()) {
            Logger.e(TAG, "file(from) is null or is not exists!!");
            return;
        }
        if (null == to) {
            Logger.e(TAG, "file(to) is null!!");
            return;
        }

        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(from);
            if (!to.exists()) {
                to.createNewFile();
            }
            os = new FileOutputStream(to);

            byte[] buffer = new byte[1024];
            int len = 0;
            while (-1 != (len = is.read(buffer))) {
                os.write(buffer, 0, len);
            }
            os.flush();
        } catch (Exception e) {
            Logger.e(TAG, e);
        } finally {
            closeIO(is, os);
        }


    }

    /**
     * 从文件中读取文本
     *
     * @param filePath
     * @return
     */
    public static String readFile(String filePath) {
        StringBuilder resultSb = null;
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
        } catch (Exception e) {
            Logger.e(TAG, e);
        }
        return inputStream2String(is);
    }

    public static String readFile(File file) {
        return readFile(file.getPath());
    }

    /**
     * 从assets中读取文本
     *
     * @param name
     * @return
     */
    public static String readFileFromAssets(Context context, String name) {
        InputStream is = null;
        try {
            is = context.getResources().getAssets().open(name);
        } catch (Exception e) {
            Logger.e(TAG, e);
        }
        return inputStream2String(is);

    }

    public static String inputStream2String(InputStream is) {
        if (null == is) {
            return null;
        }
        StringBuilder resultSb = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            resultSb = new StringBuilder();
            String len;
            while (null != (len = br.readLine())) {
                resultSb.append(len);
            }
        } catch (Exception ex) {
            Logger.e(TAG, ex);
        } finally {
            closeIO(is);
        }
        return null == resultSb ? null : resultSb.toString();
    }

    /**
     * 写文本到文件
     * @param path
     * @param content
     * @return
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static int writeFile(String path, String content) {
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(path);
            os.write(content.getBytes(Charset.forName("UTF-8")));
            os.flush();
            return 0;
        } catch (Exception e) {
            Logger.e(TAG, e);
        } finally {
            ABIOUtil.closeIO(os);
        }
        return -1;
    }

    public static int writeFile(File file, String content) {
        return writeFile(file.getPath(), content);
    }


}
