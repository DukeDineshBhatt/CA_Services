package com.technuoma.caservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ImageUploadActivity extends AppCompatActivity {

    private Toolbar toolbar;
    Button btn_upload;
    RecyclerView image_recycler;
    CategoryAdapter adapter1;
    ArrayList<Category> category_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("GST Registration");
        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_upload = findViewById(R.id.btn_upload);

        image_recycler = findViewById(R.id.image_recycler);

        category_list = new ArrayList<>();
        category_list.add(new Category("image-123002.jpg"));
        category_list.add(new Category("image-123002.jpg"));

        adapter1 = new CategoryAdapter(ImageUploadActivity.this, category_list);

        LinearLayoutManager manager = new LinearLayoutManager(ImageUploadActivity.this);

        image_recycler.setAdapter(adapter1);
        image_recycler.setLayoutManager(manager);

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent  intent = new Intent(ImageUploadActivity.this,StatusActivity.class);
                startActivity(intent);

            }
        });
    }


    class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

        Context context;
        List<Category> list = new ArrayList<>();

        public CategoryAdapter(Context context, List<Category> list) {
            this.context = context;
            this.list = list;
        }

        public void setData(List<Category> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.img_list_model, parent, false);
            return new CategoryAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

            final Category item = list.get(position);

            holder.name.setText(item.getName());


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


            }
        }
    }

}