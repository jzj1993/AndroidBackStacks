package com.jzj1993.task;

import android.content.Intent;
import android.net.Uri;

/**
 * Android Task学习
 */
public class MainActivity extends BaseActivity {

    @Override
    void init() {
//        addButton("startBrowser", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startBrowser();
//            }
//        });
    }

    private void startBrowser() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri uri = Uri.parse("http://www.baidu.com");
        intent.setData(uri);
        startActivity(intent);
    }
}
