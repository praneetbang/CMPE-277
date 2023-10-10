package com.example.cmpe_277_assingment_1;

import android.app.Dialog;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textViewThreadCount;
    private int threadCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewThreadCount = (TextView) findViewById(R.id.threadCount);
    }

    public void GotoActivityB(View view) {
        Intent intent = new Intent(MainActivity.this, Activity_B.class);
        intent.putExtra("ThreadCount", threadCount);
        startActivity(intent);
        MainActivity.this.finish();
    }

    public void GotoActivityC(View view) {
        Intent intent = new Intent(MainActivity.this, Activity_C.class);
        intent.putExtra("ThreadCount", threadCount);
        startActivity(intent);
        MainActivity.this.finish();
    }

    public void GotoDialog(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.content_dialog);
        Button closeDialog = (Button) dialog.findViewById(R.id.closeDialog);
        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void CloseApp(View view) {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if (bundle!=null) {
            threadCount = bundle.getInt("ThreadCount");
        }

        textViewThreadCount.setText(String.valueOf(threadCount));
    }
}