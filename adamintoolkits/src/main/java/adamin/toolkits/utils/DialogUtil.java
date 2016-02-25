package adamin.toolkits.utils;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import adamin.toolkits.R;


/**
 * Created by LiTao on 2016-01-11-22:48.
 * Company: QD24so
 * Email: 14846869@qq.com
 * WebSite: http://lixiaopeng.top
 */
public class DialogUtil {
    public static AlertDialog buildLoadingDialog(Context context){
        AlertDialog alertDialog=new AlertDialog.Builder(context)
                .setCancelable(false)
                .setView(R.layout.layout_progress)
                .create();
        return alertDialog;
    }
    public static AlertDialog buildLoginingDialog(Context context){
        AlertDialog alertDialog=new AlertDialog.Builder(context)
                .setCancelable(false)
                .setView(R.layout.layout_logining)
                .create();
        return alertDialog;
    }

    public static AlertDialog buildCheckingDialog(Context context){
        AlertDialog alertDialog=new AlertDialog.Builder(context)
                .setCancelable(false)
                .setView(R.layout.layout_checkingphone)
                .create();
        return alertDialog;
    }

    /**
     * 自定义文字
     * @param context
     * @param text
     * @return
     */
    public static AlertDialog buildCustomDialog(Context context,String text){
        View view= LayoutInflater.from(context).inflate(R.layout.layout_checkingphone,null);
        TextView textView= (TextView) view.findViewById(R.id.tvloading);
        textView.setText(text);
        AlertDialog alertDialog=new AlertDialog.Builder(context)
                .setCancelable(false)
                .setView(view)
                .create();
        return alertDialog;
    }
}
