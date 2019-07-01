package com.minhtung.muasamonline.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.minhtung.muasamonline.Model.GioHang;
import com.minhtung.muasamonline.Model.SanPham;
import com.minhtung.muasamonline.R;
import com.minhtung.muasamonline.Server.API;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChitietsanphamActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imgHinhSP;
    TextView tvTenSP,tvGia,tvMoTa;
    Button btnMua;
    ArrayList<SanPham> sanPhamArrayList=new ArrayList<>();
    int position=0;
    ProgressBar progressBar;
    TextView tvNotify;
    LinearLayout linearLayoutChitietsanpham;
    FrameLayout rootView,redCircle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsanpham);
        AnhXa();
        ActionToolbar();
        getDataIntent();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_giohang,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent=new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public  boolean onPrepareOptionsMenu(Menu menu) {

        final MenuItem alertMenuItem = menu.findItem(R.id.menugiohang);
        rootView = (FrameLayout) alertMenuItem.getActionView();
        redCircle = (FrameLayout) rootView.findViewById(R.id.view_alert_red_circle);
        MainActivity.countTextView = rootView.findViewById(R.id.view_alert_count_textview);
        GioHangActivity.laysoluongsp();
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(alertMenuItem);
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }
    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }


    private void AnhXa() {
        toolbar=findViewById(R.id.toolbarChiTietSanPham);
        imgHinhSP=findViewById(R.id.imgHinhSP);
        tvTenSP=findViewById(R.id.tvTenSP);
        tvGia=findViewById(R.id.tvGiaSP);
        tvMoTa=findViewById(R.id.tvMoTa);
        btnMua=findViewById(R.id.btnMua);
        linearLayoutChitietsanpham=findViewById(R.id.linearlayoutChitietsanpham);
        tvNotify=findViewById(R.id.tvNotify);
        progressBar=findViewById(R.id.progressBar);


    }

    private void eventButtonClick() {
        btnMua.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int id=sanPhamArrayList.get(position).getIdSP();
                String TenSP=sanPhamArrayList.get(position).getTenSP();
                int GiaSP=sanPhamArrayList.get(position).getGiaSP();
                String HinhSP=sanPhamArrayList.get(position).getHinhSP();

                if (MainActivity.manggiohang.size()>0){
                    boolean exists=false;
                    int sl=1;
                    for (int i=0;i<MainActivity.manggiohang.size();i++){
                        if (MainActivity.manggiohang.get(i).getIdSP()==id){
                            MainActivity.manggiohang.get(i).setSoLuongSP(MainActivity.manggiohang.get(i).getSoLuongSP()+sl);
                            if (MainActivity.manggiohang.size()>=10){
                                MainActivity.manggiohang.get(i).setSoLuongSP(10);
                            }
                            MainActivity.manggiohang.get(i).setGiaSP(GiaSP+MainActivity.manggiohang.get(i).getGiaSP());
                            exists=true;
                        }
                    }
                    if (exists==false){
                        int soluong=1;
                        int giamoi=soluong*GiaSP;
                        MainActivity.manggiohang.add(new GioHang(id,TenSP,giamoi,HinhSP,soluong));
                    }

                }else {
                    int soluong=1;
                    int giamoi=soluong*GiaSP;
                    MainActivity.manggiohang.add(new GioHang(id,TenSP,giamoi,HinhSP,soluong));
                }
                Intent intent=new Intent(ChitietsanphamActivity.this,GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getDataIntent() {
        Intent intent=getIntent();
        if (intent!=null){
            int idSP=intent.getIntExtra("idSP",0);
            getThongTinSanPham(idSP);

        }
    }

    private void getThongTinSanPham(int idSP) {
        beginLoadData();
        API.initRetrofit().create(API.ApiInterface.class).getIdSanPham(idSP).enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                if (response != null && response.body() != null) {
                    sanPhamArrayList.addAll(response.body());
                    linearLayoutChitietsanpham.setVisibility(View.VISIBLE);
                    if (sanPhamArrayList.get(position).getHinhSP().equals("")){
                        imgHinhSP.setImageResource(R.mipmap.noimage);
                    }else {
                        Picasso.with(getApplicationContext()).load(sanPhamArrayList.get(position).getHinhSP())
                                .placeholder(R.mipmap.noimage).error(R.mipmap.error).into(imgHinhSP);
                    }

                    tvTenSP.setText(sanPhamArrayList.get(position).getTenSP());
                    DecimalFormat formatter = new DecimalFormat("###,###,###");
                    tvGia.setText("Giá: "+formatter.format(sanPhamArrayList.get(position).getGiaSP())+" Đ");
                    tvMoTa.setText(sanPhamArrayList.get(position).getMoTa());
                    eventButtonClick();
                }
                String msg = "";
                if (sanPhamArrayList.size()==0) msg = getString(R.string.no_data);
                    finishLoadData(msg);

            }
            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                finishLoadData("Kiểm tra kết nối mạng ");
            }
        });
    }
    private void finishLoadData(String msg) {
        progressBar.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(msg)) {
            tvNotify.setVisibility(View.VISIBLE);
            tvNotify.setText(msg);
        } else {
            tvNotify.setVisibility(View.GONE);
        }
    }

    private void beginLoadData() {
        progressBar.setVisibility(View.VISIBLE);
        linearLayoutChitietsanpham.setVisibility(View.GONE);
    }
}
