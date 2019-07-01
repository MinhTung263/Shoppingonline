package com.minhtung.muasamonline.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.minhtung.muasamonline.Adapter.SanPham_Adapter;
import com.minhtung.muasamonline.Model.LoaiSanPham;
import com.minhtung.muasamonline.Model.SanPham;
import com.minhtung.muasamonline.R;
import com.minhtung.muasamonline.Server.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoaiSPActivity extends AppCompatActivity {
Toolbar toolbar;
RecyclerView recyclerViewLoaiSanPham;
ArrayList<SanPham> sanPhamArrayList=new ArrayList<>();
SanPham_Adapter sanPham_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_sp);

        AnhXa();
        ActionToolbar();
        getDataIntent();
    }

    private void AnhXa() {
        toolbar=findViewById(R.id.toolbarLoaiSanPham);
        recyclerViewLoaiSanPham=findViewById(R.id.recyclerviewLoaiSanPham);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getDataIntent() {
        Intent intent=getIntent();
        if (intent!=null) {
            int idLoaiSP = intent.getIntExtra("idLoaiSP", 0);
            int idDM = intent.getIntExtra("idDM", 0);
            getDataDMToId(idDM);
            getDataLoaiSanPhamToId(idLoaiSP);
            sanPham_adapter = new SanPham_Adapter(getApplicationContext(), sanPhamArrayList);
            StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            recyclerViewLoaiSanPham.setLayoutManager(gridLayoutManager);
            recyclerViewLoaiSanPham.setAdapter(sanPham_adapter);
        }
    }
    private void getDataDMToId(int idDM) {
        API.initRetrofit().create(API.ApiInterface.class).getIdDM(idDM).enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                if (response != null && response.body() != null) {
                    sanPhamArrayList.addAll(response.body());

                }
                sanPham_adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
            }
        });
    }

    private void getDataLoaiSanPhamToId(int idLoaiSP) {
        API.initRetrofit().create(API.ApiInterface.class).getIdLoaiSanPham(idLoaiSP).enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                if (response != null && response.body() != null) {
                    sanPhamArrayList.addAll(response.body());

                }
                sanPham_adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
            }
        });
    }
}
