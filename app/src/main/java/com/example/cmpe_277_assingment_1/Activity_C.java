package com.example.cmpe_277_assingment_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Example Activity to demonstrate the lifecycle callback methods.
 */
public class Activity_C extends AppCompatActivity {

    private int threadCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__c);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        threadCount = bundle.getInt("ThreadCount");
        threadCount+=10;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        threadCount = bundle.getInt("ThreadCount");
        threadCount+=10;
    }

    public void finishC(View view) {
        Intent intent=new Intent(Activity_C.this, MainActivity.class);
        intent.putExtra("ThreadCount", threadCount);
        startActivity(intent);
        Activity_C.this.finish();
    }
}

