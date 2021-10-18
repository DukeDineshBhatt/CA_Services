package com.technuoma.caservices;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
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
import com.technuoma.caservices.Category1POJO.Datum;
import com.technuoma.caservices.ServicesPOJO.ServicesBean;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class Category1Fragment extends Fragment {

    RecyclerView cat;
    Category1Adapter adapter2;
    MainActivity mainActivity;

    List<Datum> list;
    String user_id;
    ProgressBar progressBar;

    public Category1Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category1, container, false);;

        mainActivity = (MainActivity) getActivity();

        SharedPreferences shared = mainActivity.getSharedPreferences("myAppPrefs", MODE_PRIVATE);
        user_id = (shared.getString("user_id", ""));

        cat = view.findViewById(R.id.cat);
        progressBar = view.findViewById(R.id.progressBar);

        list = new ArrayList<>();

        adapter2 = new Category1Adapter(mainActivity, list);

        GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 3);

        cat.setAdapter(adapter2);
        cat.setLayoutManager(manager1);

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

        Category1Request body = new Category1Request();
        Category1RequestData body1 = new Category1RequestData();

        body1.setUserId(user_id);
        body.setData(body1);
        body.setAction("category_list");

        Call<Category1Bean> call = cr.category1(body);

        call.enqueue(new Callback<Category1Bean>() {
            @Override
            public void onResponse(@NotNull Call<Category1Bean> call, @NotNull Response<Category1Bean> response) {

                adapter2.setData(response.body().getData());

                progressBar.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(@NotNull Call<Category1Bean> call, @NotNull Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });

    }


    class Category1Adapter extends RecyclerView.Adapter<Category1Adapter.ViewHolder> {

        Context context;
        List<Datum> list;

        public Category1Adapter(Context context,List<Datum> list) {
            this.context = context;
            this.list = list;
        }

        public void setData(List<Datum> list) {
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

            final Datum item = list.get(position);

            holder.name.setText(item.getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FragmentManager fm4 = mainActivity.getSupportFragmentManager();
                    FragmentTransaction ft4 = fm4.beginTransaction();
                    Category2Fragment frag14 = new Category2Fragment();

                    Bundle args = new Bundle();
                    args.putString("catId", item.getId());
                    frag14.setArguments(args);

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