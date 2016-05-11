package com.jzj1993.task;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzj on 15/8/12.
 */
public abstract class BaseActivity extends Activity {

    static List<BaseActivity> list;

    static {
        list = new ArrayList<BaseActivity>();
    }

    Context context = this;
    LinearLayout mBtnContainer;
    LinearLayout mTaskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list.add(this);

        mBtnContainer = (LinearLayout) findViewById(R.id.btn_container);
        mTaskList = (LinearLayout) findViewById(R.id.task_list);

        addButton(MainActivity.class);
        addButton(StandardActivity.class);
        addButton(StandardActivity.class, "NEW_TASK FLAG", Intent.FLAG_ACTIVITY_NEW_TASK);
        addButton(StandardNewAffinityActivity.class, "NEW_TASK FLAG", Intent.FLAG_ACTIVITY_NEW_TASK);
        addButton(SingleTopActivity.class);
        addButton(SingleTaskActivity.class);
        addButton(SingleTaskNewAffinityActivity.class);
        addButton(SingleInstanceActivity.class);
        addButton(FinishOnTaskLaunchActivity.class);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTaskList.removeAllViews();
        for (BaseActivity a : list) {
            TextView t = new TextView(this);
            t.setText(a.getDescription());
            if (a == this) {
                t.setTextColor(Color.RED);
            }
            if (a.getTaskId() != this.getTaskId()) {
                t.setPadding(20, 0, 0, 0);
            }
            mTaskList.addView(t);
        }
    }

    @Override
    protected void onDestroy() {
        list.remove(this);
        super.onDestroy();
    }

    public String getDescription() {
        return "Task " + this.getTaskId() + ", " + getClass().getSimpleName() + " @ " + hashCode();
    }

    void init() {

    }

    void addButton(String text, View.OnClickListener listener) {
        Button b = new Button(this);
        b.setText(text);
        b.setOnClickListener(listener);
        mBtnContainer.addView(b);
    }

    void addButton(final Class clazz) {
        addButton(clazz.getSimpleName(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, clazz));
            }
        });
    }

    void addButton(final Class clazz, String text, final int flags) {
        addButton(clazz.getSimpleName() + ", " + text, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, clazz).addFlags(flags));
            }
        });
    }
}
