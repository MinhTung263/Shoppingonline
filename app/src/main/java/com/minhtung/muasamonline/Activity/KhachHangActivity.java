package com.minhtung.muasamonline.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.minhtung.muasamonline.Adapter.KhachHang_Adapter;
import com.minhtung.muasamonline.Model.KhachHang;
import com.minhtung.muasamonline.R;
import com.minhtung.muasamonline.Server.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KhachHangActivity extends AppCompatActivity {
    public static final String TAG = KhachHangActivity.class.getSimpleName();
    TextView tvTenKhach;
    TextView tvEmail;
    Toolbar toolbar;
    ProgressDialog progressDialog;
    ListView lvKhachHang;
    KhachHang_Adapter khachHang_adapter;
    int position=0;
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

        lvKhachHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                XacnhanhuyDonHang(khachHangArrayList.get(position)
                        .getTenSP(),khachHangArrayList.get(position)
                        .getIdSP(),khachHangArrayList.get(position).getUserId());
                return true;
            }
        });

    }

    private void huyDonHang(final int idSP, final String user_id) {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, API.Huydonhang, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
                if (response.equals("1")){
                    Toast.makeText(KhachHangActivity.this, "Hủy thành công", Toast.LENGTH_SHORT).show();
                    getDaTaKH(MainActivity.taiKhoanArrayList.get(0).getUser_id());
                }else{
                    Toast.makeText(KhachHangActivity.this, "Lỗi hủy", Toast.LENGTH_SHORT).show();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(KhachHangActivity.this, "Xảy ra lỗi", Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("idSP",String.valueOf(idSP));
                params.put("user_id",user_id);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


    private void XacnhanhuyDonHang(String tensp, final int idSP, final String user_id) {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(KhachHangActivity.this);
        alertDialog.setMessage("Bạn có muốn hủy đơn hàng "+tensp+" này không?");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                huyDonHang(idSP,user_id);
            }
        });
        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();

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
                khachHangArrayList.clear();
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
