package com.minhtung.muasamonline.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.minhtung.muasamonline.Adapter.KhachHang_Adapter;
import com.minhtung.muasamonline.Model.KhachHang;
import com.minhtung.muasamonline.Model.SanPham;
import com.minhtung.muasamonline.Model.TaiKhoan;
import com.minhtung.muasamonline.R;
import com.minhtung.muasamonline.Server.API;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KhachHangActivity extends AppCompatActivity {
    TextView tvTenKhach;
    TextView tvEmail;
    Toolbar toolbar;
    ProgressDialog progressDialog;
    ListView lvKhachHang;
    KhachHang_Adapter khachHang_adapter;
    ArrayList<KhachHang> khachHangArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_hang);
        lvKhachHang=findViewById(R.id.lvKhachHang);
        tvTenKhach= findViewById(R.id.tvTenKhachHang);
        tvEmail=findViewById(R.id.tvEmail);
        toolbar=findViewById(R.id.toolbarKhachHang);
        ActionToolbar();
        addControl();
        progressDialog=new ProgressDialog(this,R.style.StyledDialog);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Đang tải dữ liệu...");
        getDaTaKH(MainActivity.taiKhoanArrayList.get(0).getUser_id());
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

    private void getDaTaKH(String user_id) {
        progressDialog.show();
        API.initRetrofit().create(API.ApiInterface.class).getIdDonHang(user_id).enqueue(new Callback<List<KhachHang>>() {
            @Override
            public void onResponse(Call<List<KhachHang>> call, Response<List<KhachHang>> response) {
                khachHangArrayList.addAll(response.body());
               khachHang_adapter=new KhachHang_Adapter(getApplicationContext(),khachHangArrayList);
               lvKhachHang.setAdapter(khachHang_adapter);
               khachHang_adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<List<KhachHang>> call, Throwable t) {
                Toast.makeText(KhachHangActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addControl() {
            tvTenKhach.setText( MainActivity.taiKhoanArrayList.get(0).getUserName());
            tvEmail.setText( MainActivity.taiKhoanArrayList.get(0).getEmail());
    }
}
