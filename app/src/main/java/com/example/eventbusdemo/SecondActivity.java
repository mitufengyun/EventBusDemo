package com.example.eventbusdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.eventbusdemo.bean.EventBean;
import com.example.library.EventBus;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void eventBus(View view) {
        /*//子线程
        new Thread(){
            @Override
            public void run() {
                super.run();
                Log.d("EventBus>>>2", "thread = " + Thread.currentThread().getName());
                EventBus.getDefault().post(new EventBean("EventBean"));

            }
        }.start();

        Log.d("EventBus>>>2", "thread = " + Thread.currentThread().getName());
        EventBus.getDefault().post(new EventBean("EventBean"));
        finish();*/

//        EventBus.getDefault().postSticky(new EventBean("id = 2"));
//        finish();

        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            EventBus.getDefault().post(new EventBean("EventBus" + i));
        }
        long finishTime = System.currentTimeMillis();
        Log.d("EventBus>>>2", "Tim = " +(finishTime - currentTime));
//        finish();

    }


    public void jump(View view) {
        startActivity(new Intent(this, ThirdActivity.class));
    }
}
