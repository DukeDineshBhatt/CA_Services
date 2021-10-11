package com.technuoma.caservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FormActivity extends Fragment {

    private Toolbar toolbar;
    MainActivity mainActivity;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Purchase Service");
        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.activity_form, container, false);

            mainActivity = (MainActivity) getActivity();

            /*toolbar = view.findViewById(R.id.toolbar);

            mainActivity.setSupportActionBar(toolbar);
            mainActivity.getSupportActionBar().setTitle("Purchase Service");
            toolbar.setTitleTextColor(Color.WHITE);

            toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mainActivity.finish();
                }
            });*/

            return view;

    }
}