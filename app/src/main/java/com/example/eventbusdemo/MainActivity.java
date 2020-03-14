package com.example.eventbusdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.eventbusdemo.bean.EventBean;
import com.example.library.EventBus;
import com.example.library.Subscribe;
import com.example.library.ThreadMode;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(EventBean bean) {
        Log.d("EventBus>>>1", "Time= " + System.currentTimeMillis());
        Log.d("EventBus>>>1", "thread = " + Thread.currentThread().getName());
        Log.d("EventBus>>>1", bean.getName());
    }








    public void jump(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().unregister(this);
//        }
    }
}
