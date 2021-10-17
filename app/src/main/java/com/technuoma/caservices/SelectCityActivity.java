package com.technuoma.caservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.technuoma.caservices.CitiesPOJO.CitiesBean;
import com.technuoma.caservices.CitiesPOJO.Datum;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SelectCityActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView cities;
    CitiesAdapter adapter;
    Button btn_continue;
    ProgressBar progressBar;
    String user_id;
    List<Datum> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Select City");
        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cities = findViewById(R.id.cities);
        progressBar = findViewById(R.id.progressBar);
        btn_continue = findViewById(R.id.btn_continue);

        SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("hasLoggedIn", true);
        editor.commit();

        SharedPreferences shared = getSharedPreferences("myAppPrefs", MODE_PRIVATE);
        user_id = (shared.getString("user_id", ""));

        list = new ArrayList<>();

        adapter = new CitiesAdapter(SelectCityActivity.this, list);

        GridLayoutManager manager = new GridLayoutManager(SelectCityActivity.this, 2);

        cities.setAdapter(adapter);
        cities.setLayoutManager(manager);


        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (adapter.getSelected() != null) {

                    SharedPreferences mPrefs = getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = mPrefs.edit();
                    editor.putBoolean("hasCity", true);
                    editor.putString("city", adapter.getSelected().getName());
                    editor.putBoolean("hasLoggedIn", true);
                    editor.commit();

                    Intent intent = new Intent(SelectCityActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(SelectCityActivity.this, "No Selection", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();

        progressBar.setVisibility(View.VISIBLE);

        Bean b = (Bean) getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseurl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiIneterface cr = retrofit.create(AllApiIneterface.class);

        CitiesRequest body = new CitiesRequest();
        CitiesRequestData body1 = new CitiesRequestData();

        body1.setUserId(user_id);
        body.setData(body1);
        body.setAction("all_service_cites");

        Call<CitiesBean> call = cr.cities(body);

        call.enqueue(new Callback<CitiesBean>() {
            @Override
            public void onResponse(@NotNull Call<CitiesBean> call, @NotNull Response<CitiesBean> response) {

                adapter.setData(response.body().getData());

                progressBar.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(@NotNull Call<CitiesBean> call, @NotNull Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });

    }


    class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> {

        Context context;
        List<Datum> list;
        private int checkedPosition = -1;

        public CitiesAdapter(Context context, List<Datum> list) {
            this.context = context;
            this.list = list;
        }

        public void setData(List<Datum> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.city_list_model, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            final Datum item = list.get(position);

            holder.bind(list.get(position));


        }

        public Datum getSelected() {
            if (checkedPosition != -1) {
                return list.get(checkedPosition);
            }
            return null;
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

                name = itemView.findViewById(R.id.name);
                image = itemView.findViewById(R.id.img);


            }

            void bind(final Datum employee) {
                if (checkedPosition == -1) {
                    image.setVisibility(View.GONE);
                    itemView.setBackground(getResources().getDrawable(R.drawable.bg_capsule_unselected));
                } else {
                    if (checkedPosition == getAdapterPosition()) {
                        image.setVisibility(View.VISIBLE);
                        itemView.setBackground(getResources().getDrawable(R.drawable.bg_capsule_selected));
                    } else {
                        image.setVisibility(View.GONE);
                        itemView.setBackground(getResources().getDrawable(R.drawable.bg_capsule_unselected));
                    }
                }
                name.setText(employee.getName());

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        image.setVisibility(View.VISIBLE);
                        itemView.setBackground(getResources().getDrawable(R.drawable.bg_capsule_selected));
                        if (checkedPosition != getAdapterPosition()) {
                            notifyItemChanged(checkedPosition);
                            checkedPosition = getAdapterPosition();
                        }
                    }
                });
            }
        }
    }
}