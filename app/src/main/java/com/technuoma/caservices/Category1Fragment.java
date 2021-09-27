package com.technuoma.caservices;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Category1Fragment extends Fragment {

    RecyclerView cat;
    Category1Adapter adapter2;
    ArrayList<Category1> cat1_list;
    MainActivity mainActivity;

    public Category1Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category1, container, false);


        cat = view.findViewById(R.id.cat);

        mainActivity = (MainActivity) getActivity();

        cat1_list = new ArrayList<>();
        cat1_list.add(new Category1("Category 1"));
        cat1_list.add(new Category1("Category 1"));
        cat1_list.add(new Category1("Category 1"));
        cat1_list.add(new Category1("Category 1"));
        cat1_list.add(new Category1("Category 1"));
        cat1_list.add(new Category1("Category 1"));

        adapter2 = new Category1Adapter(mainActivity, cat1_list);

        GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 3);

        cat.setAdapter(adapter2);
        cat.setLayoutManager(manager1);

        return view;
    }


    class Category1Adapter extends RecyclerView.Adapter<Category1Adapter.ViewHolder> {

        Context context;
        List<Category1> list = new ArrayList<>();

        public Category1Adapter(Context context, List<Category1> list) {
            this.context = context;
            this.list = list;
        }

        public void setData(List<Category1> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public Category1Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.cat1_list_model, parent, false);

            return new Category1Adapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Category1Adapter.ViewHolder holder, int position) {

            final Category1 item = list.get(position);

            holder.name.setText(item.getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FragmentManager fm4 = mainActivity.getSupportFragmentManager();
                    FragmentTransaction ft4 = fm4.beginTransaction();
                    Category2Fragment frag14 = new Category2Fragment();
                    ft4.replace(R.id.flFragment, frag14);
                    ft4.addToBackStack(null);
                    ft4.commit();


                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ImageView image;
            TextView name;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                image = itemView.findViewById(R.id.img);
                name = itemView.findViewById(R.id.name);


            }
        }
    }

}