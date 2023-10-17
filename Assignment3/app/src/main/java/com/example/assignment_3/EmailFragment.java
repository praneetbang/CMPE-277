package com.example.assignment_3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.assignment_3.R;


public class EmailFragment extends Fragment {
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_call, container, false);
        return view;
    }

    public void onEmailInfo(View view)
    {
        EditText et = (EditText) getView().findViewById(R.id.emailText);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://" + et.getText().toString())); // Only email apps handle this.
        startActivity(intent);
    }
}