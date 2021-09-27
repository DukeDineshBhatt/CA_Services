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


public class Category2Fragment extends Fragment {

    RecyclerView cat;
    Category2Adapter adapter3;
    ArrayList<Category2> cat2_list;
    MainActivity mainActivity;

    public Category2Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category2, container, false);

        cat = view.findViewById(R.id.cat);

        mainActivity = (MainActivity) getActivity();

        cat2_list = new ArrayList<>();
        cat2_list.add(new Category2("Category 2"));
        cat2_list.add(new Category2("Category 2"));
        cat2_list.add(new Category2("Category 2"));
        cat2_list.add(new Category2("Category 2"));
        cat2_list.add(new Category2("Category 2"));
        cat2_list.add(new Category2("Category 2"));

        adapter3 = new Category2Adapter(getActivity(), cat2_list);

        GridLayoutManager manager2 = new GridLayoutManager(getActivity(), 3);

        cat.setAdapter(adapter3);
        cat.setLayoutManager(manager2);

        return view;
    }


    class Category2Adapter extends RecyclerView.Adapter<Category2Adapter.ViewHolder> {

        Context context;
        List<Category2> list = new ArrayList<>();

        public Category2Adapter(Context context, List<Category2> list) {
            this.context = context;
            this.list = list;
        }

        public void setData(List<Category2> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public Category2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.cat2_list_model, parent, false);
            return new Category2Adapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Category2Adapter.ViewHolder holder, int position) {

            final Category2 item = list.get(position);

            holder.name.setText(item.getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FragmentManager fm4 = mainActivity.getSupportFragmentManager();

                    FragmentTransaction ft4 = fm4.beginTransaction();
                    Category3Fragment frag14 = new Category3Fragment();
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