package com.example.assignment_3;

//package com.example.androidactivitylifecycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.assignment_3.R;

import java.io.IOException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    int threadCounter = 0;
    String lastActivity = "None";
    private TextView resultText;
    AsyncTaskRunner runner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void onSend(View view)
    {
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        EditText et = findViewById(R.id.editTextText);
        resultText = findViewById(R.id.resultText);
        resultText.setText("Request in Progress");
        runner = new AsyncTaskRunner();
        runner.execute(et.getText().toString());
    }

    public void cancelRequest(View view)
    {
        if(runner != null)
        {
            runner.cancel(true);
            resultText.setText("Request Cancelled, Enter your prompt again");
        }
    }

    public void onEmailInfo(View view)
    {
        EditText et = findViewById(R.id.emailText);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://" + et.getText().toString())); // Only email apps handle this.
        startActivity(intent);
    }

    public void dialPhoneNumber(View view) {

        EditText et = findViewById(R.id.callNumber);

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + et.getText().toString()));

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
            }
        }

            startActivity(intent);
    }

    public static String chatGPT(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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";
        String model = "gpt-3.5-turbo";

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // The request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            System.out.println("body: " + body);
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Response from ChatGPT
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            Log.d("Response from GPT",response.toString());
            // calls the method to extract the message.
            return extractMessageFromJSONResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content")+ 11;

        int end = response.indexOf("\"", start);

        return response.substring(start, end);

    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String res = "";
            try {
                res = chatGPT(strings[0]);
            }
            catch (Exception e)
            {
                Log.e("Exception tag",e.toString());
            }
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            resultText.setText(s);
        }
    }

    public void onCloseApp (View view) {
        finish();
        System.exit(0);
    }
}