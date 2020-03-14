package com.example.eventbusdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.eventbusdemo.bean.EventBean;
import com.example.library.EventBus;
import com.example.library.Subscribe;
import com.example.library.ThreadMode;


public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    public void getMessage(View view) {
        EventBus.getDefault().register(this);
    }


//    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
//    public void receiveEventBus(EventBean bean) {
//        Log.d("EventBus>>>3", "thread = " + Thread.currentThread().getName());
//        Log.d("EventBus>>>3", bean.getName());
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }
}
