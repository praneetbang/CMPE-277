package com.example.cmpe_277_assingment_1;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_B extends AppCompatActivity {

    private int threadCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__b);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        threadCount = bundle.getInt("ThreadCount");
        threadCount+=5;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        threadCount = bundle.getInt("ThreadCount");
        threadCount+=5;
    }

    public void finishB(View view) {
        Intent intent=new Intent(Activity_B.this, MainActivity.class);
        intent.putExtra("ThreadCount", threadCount);
        startActivity(intent);
        Activity_B.this.finish();
    }


}