package com.technuoma.caservices;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Context;
import android.content.Intent;
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


public class Category3Fragment extends Fragment {

    Category3Adapter adapter4;
    ArrayList<Category3> cat3_list;
    MainActivity mainActivity;
    RecyclerView cat;

    public Category3Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category3, container, false);

        mainActivity = (MainActivity) getActivity();


        cat = view.findViewById(R.id.cat);

        cat3_list = new ArrayList<>();
        cat3_list.add(new Category3("Category 3"));
        cat3_list.add(new Category3("Category 3"));
        cat3_list.add(new Category3("Category 3"));
        cat3_list.add(new Category3("Category 3"));
        cat3_list.add(new Category3("Category 3"));
        cat3_list.add(new Category3("Category 3"));

        adapter4 = new Category3Adapter(getActivity(), cat3_list);

        GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 3);

        cat.setAdapter(adapter4);
        cat.setLayoutManager(manager3);


        return view;
    }

    class Category3Adapter extends RecyclerView.Adapter<Category3Adapter.ViewHolder> {

        Context context;
        List<Category3> list = new ArrayList<>();

        public Category3Adapter(Context context, List<Category3> list) {
            this.context = context;
            this.list = list;
        }

        public void setData(List<Category3> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public Category3Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.cat3_list_model, parent, false);
            return new Category3Adapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Category3Adapter.ViewHolder holder, int position) {

            final Category3 item = list.get(position);

            holder.name.setText(item.getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   /* Intent intent = new Intent(getActivity(), ImageUploadActivity.class);
                    startActivity(intent);*/

                    FragmentManager fm3 = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft3 = fm3.beginTransaction();
                    ft3.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    OptionsFragment frag13 = new OptionsFragment();
                    ft3.replace(R.id.flFragment, frag13);
                    ft3.addToBackStack(null);
                    ft3.commit();

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