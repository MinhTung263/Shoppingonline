package com.minhtung.muasamonline.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.minhtung.muasamonline.Adapter.GioHang_Adapter;
import com.minhtung.muasamonline.Adapter.SanPham_Adapter;
import com.minhtung.muasamonline.Model.GioHang;
import com.minhtung.muasamonline.Model.SanPham;
import com.minhtung.muasamonline.R;
import com.minhtung.muasamonline.Server.API;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GioHangActivity extends AppCompatActivity {
ListView lvGioHang;
TextView tvThongBao;
static TextView tvTongTien;
Button btnMuaNgay,btnTiepTucMua,btnMuaSamNgay;
Toolbar toolbar;
GioHang_Adapter gioHang_adapter;
SanPham_Adapter sanPham_adapter;
ArrayList<SanPham> sanPhamArrayList=new ArrayList<>();
RecyclerView recyclerViewSanPham;
LinearLayout linearlayoutKhongCoSanPham,linearlayoutCoSanPham;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        AnhXa();
        ActionToolbar();
        CheckData();
        tinhtien();
        EventClick();
        laysoluongsp();
        getDataSanPham();
        sanPham_adapter=new SanPham_Adapter(getApplicationContext(), sanPhamArrayList);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerViewSanPham.setLayoutManager(gridLayoutManager);
        recyclerViewSanPham.setAdapter(sanPham_adapter);
    }

    private void EventClick() {
        btnMuaSamNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        btnMuaNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ThongTinKhachHangActivity.class);
                startActivity(intent);
            }
        });
        btnTiepTucMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        lvGioHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(GioHangActivity.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if (MainActivity.manggiohang.size()<=0){
                                 setAn();
                        }else{
                            MainActivity.manggiohang.remove(position);
                            gioHang_adapter.notifyDataSetChanged();
                            tinhtien();
                            laysoluongsp();
                            if (MainActivity.manggiohang.size()<=0){
                                setHien();

                            }else {
                                setAn();
                                gioHang_adapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gioHang_adapter.notifyDataSetChanged();
                        tinhtien();
                        laysoluongsp();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void tinhtien() {
        int tongtien=0;
        for (int i=0;i<MainActivity.manggiohang.size();i++){
            tongtien+=MainActivity.manggiohang.get(i).getGiaSP();

        }
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        tvTongTien.setText(decimalFormat.format(tongtien)+" đ");
    }

    public static void laysoluongsp(){
        int soluong=0;
        for (int i=0;i<MainActivity.manggiohang.size();i++){
            soluong+=MainActivity.manggiohang.get(i).getSoLuongSP();
        }
        MainActivity.countTextView.setText(soluong+"");
    }


    private void CheckData() {
        if (MainActivity.manggiohang.size()<=0){
           setHien();
        }else {
            gioHang_adapter=new GioHang_Adapter(getApplicationContext(),MainActivity.manggiohang);
            lvGioHang.setAdapter(gioHang_adapter);
            gioHang_adapter.notifyDataSetChanged();
            setAn();

        }
    }

    private void setHien() {

    linearlayoutKhongCoSanPham.setVisibility(View.VISIBLE);
    linearlayoutCoSanPham.setVisibility(View.INVISIBLE);
    }

    private void setAn() {
        linearlayoutKhongCoSanPham.setVisibility(View.INVISIBLE);
        linearlayoutCoSanPham.setVisibility(View.VISIBLE);
    }

    private void AnhXa() {
        lvGioHang=findViewById(R.id.lvGioHang);
        tvThongBao=findViewById(R.id.tvThongBao);
        tvTongTien=findViewById(R.id.tvTongTien);
        btnMuaNgay=findViewById(R.id.btnMuaNgay);
        btnTiepTucMua=findViewById(R.id.btnTiepTucMuaHang);
        toolbar=findViewById(R.id.toolbarGioHang);
        btnMuaSamNgay=findViewById(R.id.btnMuaSamNgay);

        linearlayoutKhongCoSanPham=findViewById(R.id.linearlayoutKhongCoSanPham);
        linearlayoutCoSanPham=findViewById(R.id.linearlayoutCoSanPham);

        recyclerViewSanPham=findViewById(R.id.recyclerviewSanPham);
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



    private void getDataSanPham() {
        API.initRetrofit().create(API.ApiInterface.class).getSanPham().enqueue(new Callback<List<SanPham>>() {
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
