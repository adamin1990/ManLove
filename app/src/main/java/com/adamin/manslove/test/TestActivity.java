package com.adamin.manslove.test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.adamin.manslove.R;

import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {
   @Bind(R.id.webview)
    WebView webView;
    final String floortemplate="\t  <li>\n" +
            "  <div class='list-top'>\n" +
            "    <div class='list-top-lf'>\n" +
            "      <img src='{{avator}}' />\n" +
            "      <h4>\n" +
            "        {{title}}\n" +
            "      </h4>\n" +
            "    </div>\n" +
            "    <div class='list-top-rg'>\n" +
            "      <h3>\n" +
            "        {{floor}}楼\n" +
            "      </h3>\n" +
            "      <span>\n" +
            "        {{time}}\n" +
            "      </span>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "  <div class='list-con'>\n" +
            "    {{content}}\n" +
            "  </div>\n" +
            "  <br/>\n" +
            "  <div class='list-btm'>\n" +
            "    <a href='url:justlookat?{{userid}}'>\n" +
            "      只看该用户\n" +
            "    </a>\n" +
            "    <a href='url:reply?%@'>\n" +
            "      引用\n" +
            "    </a>\n" +
            "    <a href='url:report?%@'>\n" +
            "      举报\n" +
            "    </a>\n" +
            "  </div>\n" +
            "  <div style='clear: both;'>\n" +
            "  </div>\n" +
            "</li>";
    final String avator="http://img4q.duitang.com/uploads/people/201603/14/20160314180630_wiYys.thumb.224_224_c.jpeg";
    private String html;
    private String floors;
    private String ddd="<p style=\"text-align: center;\"><img alt=\"\" src=\"http://www.jdlingyu.org/wp-content/uploads/2016/02/2016-02-14_15-34-12.jpg\" width=\"960\" height=\"1280\" /><img alt=\"\" src=\"http://www.jdlingyu.org/wp-content/uploads/2016/02/2016-02-14_15-33-55.jpg\" width=\"960\" height=\"1280\" /><img alt=\"\" src=\"http://www.jdlingyu.org/wp-content/uploads/2016/02/2016-02-14_15-33-56.jpg\" width=\"960\" height=\"1280\" /><img alt=\"\" src=\"http://www.jdlingyu.org/wp-content/uploads/2016/02/2016-02-14_15-33-57.jpg\" width=\"960\" height=\"1280\" /><img alt=\"\" src=\"http://www.jdlingyu.org/wp-content/uploads/2016/02/2016-02-14_15-34-10.jpg\" width=\"672\" height=\"960\" /><img alt=\"\" src=\"http://www.jdlingyu.org/wp-content/uploads/2016/02/2016-02-14_15-34-11.jpg\" width=\"960\" height=\"1280\" /><br /> 链接：<div style=\"text-align:center;border:1px dashed #FF9A9A;padding:8px;margin:10px auto;color:#FF6666;\">本文隐藏内容 <a href=\"http://www.jdlingyu.org/wp-login.php?redirect_to=http%3A%2F%2Fwww.jdlingyu.org%2F1658%2F\">登陆</a> 后才可以浏览</div><br /> 解压密码：jdlingyu.net</p> \",\n" +
            "excerpt: \"<p>链接：本文隐藏内容 登陆 后才可以浏览 解压密码：jdlingyu.net</p>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        new GetDataTask().execute();

    }
    class GetDataTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            html=readTemplate();
            html=createHtml();
            webView.loadDataWithBaseURL(null,html,"text/html","utf-8",null);

        }


    }

    private String createHtml() {
//        html.replace("{{pagenow}}","1/110");
        String temp="";
        for(int i=0;i<10;i++){
            temp=temp+floortemplate.replace("{{avator}}",avator)
                    .replace("{{title}}","猴赛雷雅蠛蝶")
                    .replace("{{floor}}",i+"")
                    .replace("{{time}}","2016.3.31")
                    .replace("{{content}}",ddd);
        }


        return html.replace("{{floors}}",temp).replace("{{pagenow}}","1/110");
    }

    private String readTemplate() {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = getAssets().open("huaren_template.html");
            byte[] buffer = new byte[AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT];
            while (true) {
                int length = is.read(buffer);
                if (length != -1) {
                    sb.append(new String(buffer, 0, length));
                } else {
                    is.close();
                    return sb.toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
