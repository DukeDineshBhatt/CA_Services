package com.technuoma.caservices;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class OptionsFragment extends Fragment {

    Button btn_def, btn_benifit, btn_req, btn_doc, btn_faq;

    public OptionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_options, container, false);

        btn_def = view.findViewById(R.id.btn_def);
        btn_benifit = view.findViewById(R.id.btn_benifit);
        btn_req = view.findViewById(R.id.btn_req);
        btn_doc = view.findViewById(R.id.btn_doc);
        btn_faq = view.findViewById(R.id.btn_faq);

        btn_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), FormActivity.class);
                startActivity(intent);
            }
        });

        btn_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ImageUploadActivity.class);
                startActivity(intent);


            }
        });

        return view;
    }
}