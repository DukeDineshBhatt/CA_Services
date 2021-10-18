package com.technuoma.caservices;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.technuoma.caservices.Category1POJO.Category1Bean;
import com.technuoma.caservices.SubcategoryPOJO.Datum;
import com.technuoma.caservices.SubcategoryPOJO.SubCategoryBean;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class Category2Fragment extends Fragment {

    RecyclerView cat;
    Category2Adapter adapter3;
    MainActivity mainActivity;
    List<Datum> list;
    String user_id,catId;
    ProgressBar progressBar;

    public Category2Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category2, container, false);

        mainActivity = (MainActivity) getActivity();

        SharedPreferences shared = mainActivity.getSharedPreferences("myAppPrefs", MODE_PRIVATE);
        user_id = (shared.getString("user_id", ""));

         catId = getArguments().getString("catId");

        list = new ArrayList<>();

        cat = view.findViewById(R.id.cat);
        progressBar = view.findViewById(R.id.progressBar);
        adapter3 = new Category2Adapter(getActivity(), list);

        GridLayoutManager manager2 = new GridLayoutManager(getActivity(), 3);

        cat.setAdapter(adapter3);
        cat.setLayoutManager(manager2);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        progressBar.setVisibility(View.VISIBLE);

        Bean b = (Bean) getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseurl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiIneterface cr = retrofit.create(AllApiIneterface.class);

        SubCategoryRequest body = new SubCategoryRequest();
        SubCategoryRequestData body1 = new SubCategoryRequestData();

        body1.setUserId(user_id);
        body1.setCatId(catId);
        body.setData(body1);
        body.setAction("category_list");

        Call<SubCategoryBean> call = cr.subcategory(body);

        call.enqueue(new Callback<SubCategoryBean>() {
            @Override
            public void onResponse(@NotNull Call<SubCategoryBean> call, @NotNull Response<SubCategoryBean> response) {

                adapter3.setData(response.body().getData());

                progressBar.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(@NotNull Call<SubCategoryBean> call, @NotNull Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });

    }


    class Category2Adapter extends RecyclerView.Adapter<Category2Adapter.ViewHolder> {

        Context context;
        List<Datum> list;

        public Category2Adapter(Context context,   List<Datum> list) {
            this.context = context;
            this.list = list;
        }

        public void setData(  List<Datum> list) {
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

            final Datum item = list.get(position);

            holder.name.setText(item.getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   /* FragmentManager fm4 = mainActivity.getSupportFragmentManager();

                    FragmentTransaction ft4 = fm4.beginTransaction();
                    Category3Fragment frag14 = new Category3Fragment();
                    ft4.replace(R.id.flFragment, frag14);
                    ft4.addToBackStack(null);
                    ft4.commit();*/

                    Intent intent = new Intent(getActivity(), OptionsFragment.class);
                    startActivity(intent);


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