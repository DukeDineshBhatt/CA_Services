package com.technuoma.caservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SelectCityActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView cities;
    CitiesAdapter adapter;
    ArrayList<Cities> cities_list;
    Button btn_continue;

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
        btn_continue = findViewById(R.id.btn_continue);

        cities_list = new ArrayList<>();

        cities_list.add(new Cities("Delhi"));
        cities_list.add(new Cities("Mumbai"));
        cities_list.add(new Cities("Pune"));
        cities_list.add(new Cities("Noida"));
        cities_list.add(new Cities("Bangalore"));
        cities_list.add(new Cities("Hyderabad"));
        cities_list.add(new Cities("Jaipur"));
        cities_list.add(new Cities("Ghaziabad"));

        adapter = new CitiesAdapter(SelectCityActivity.this, cities_list);

        GridLayoutManager manager = new GridLayoutManager(SelectCityActivity.this, 2);

        cities.setAdapter(adapter);
        cities.setLayoutManager(manager);


        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (adapter.getSelected() != null) {

                    Intent intent = new Intent(SelectCityActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(SelectCityActivity.this, "No Selection", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }


    class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> {

        Context context;
        List<Cities> list = new ArrayList<>();
        private int checkedPosition = -1;

        public CitiesAdapter(Context context, List<Cities> list) {
            this.context = context;
            this.list = list;
        }

        public void setData(List<Cities> list) {
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

            final Cities item = list.get(position);

            holder.bind(list.get(position));


        }

        public Cities getSelected() {
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

            void bind(final Cities employee) {
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