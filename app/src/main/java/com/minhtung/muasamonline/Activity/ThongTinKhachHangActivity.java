package com.minhtung.muasamonline.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.minhtung.muasamonline.Model.TaiKhoan;
import com.minhtung.muasamonline.R;
import com.minhtung.muasamonline.Server.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongTinKhachHangActivity extends AppCompatActivity {
    public static final String TAG = DangNhapActivity.class.getSimpleName();
EditText edtSDT,edtDiaChi;
TextView txtTenKhachHang,txtEmail;
Button btnXacNhan,btnHuy;
private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang);
        AnhXa();
        txtTenKhachHang.setText(MainActivity.taiKhoanArrayList.get(0).getUserName());
        txtEmail.setText(MainActivity.taiKhoanArrayList.get(0).getEmail());
        eventOnClick();
    }

    private void eventOnClick() {
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String SDT=edtSDT.getText().toString().trim();
                final String DiaChi=edtDiaChi.getText().toString().trim();
                if (SDT.length()>0&&DiaChi.length()>0){
                    pDialog.show();
                    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, API.Donhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String iddonhang) {
                            if (Integer.parseInt(iddonhang)>0){
                                RequestQueue   queue=Volley.newRequestQueue(getApplicationContext());
                                StringRequest request=new StringRequest(Request.Method.POST, API.Chitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Log.d(TAG, response);
                                            if (response.equals("1")) {
                                                MainActivity.manggiohang.clear();
                                                Toast.makeText(getApplicationContext(), "Bạn đã mua hàng thành công!", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                startActivity(intent);
                                                Toast.makeText(getApplicationContext(), "Mời bạn tiếp tục mua hàng!", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(getApplicationContext(), "Mua hàng thất bại", Toast.LENGTH_LONG).show();
                                            }
                                        pDialog.dismiss();
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                                        pDialog.dismiss();
                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray=new JSONArray();
                                        for (int i=0;i<MainActivity.manggiohang.size();i++){
                                            JSONObject jsonObject=new JSONObject();
                                            try {
                                                jsonObject.put("idDonHang",iddonhang);
                                                jsonObject.put("user_id",MainActivity.taiKhoanArrayList.get(0).getUser_id());
                                                jsonObject.put("idSP",MainActivity.manggiohang.get(i).getIdSP());
                                                jsonObject.put("TenSP",MainActivity.manggiohang.get(i).getTenSP());
                                                jsonObject.put("HinhSP",MainActivity.manggiohang.get(i).getHinhSP());
                                                jsonObject.put("GiaSP",MainActivity.manggiohang.get(i).getGiaSP());
                                                jsonObject.put("SoLuong",MainActivity.manggiohang.get(i).getSoLuongSP());

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                            pDialog.dismiss();
                                        }
                                        HashMap<String,String> hashMap=new HashMap<String, String>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(request);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            VolleyLog.d(TAG, "Error: " + error.getMessage());
                            pDialog.dismiss();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String>hashMap=new HashMap<String, String> ();
                                hashMap.put("user_id",MainActivity.taiKhoanArrayList.get(0).getUser_id());
                                hashMap.put("TenKhachHang",MainActivity.taiKhoanArrayList.get(0).getUserName());
                                hashMap.put("SDT",SDT);
                                hashMap.put("Email",MainActivity.taiKhoanArrayList.get(0).getEmail());
                                hashMap.put("DiaChi",DiaChi);
                            return hashMap;
                        }
                    };

                    requestQueue.add(stringRequest);
                }else{
                    Toast.makeText(ThongTinKhachHangActivity.this, "Bạn hãy kiểm tra dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void AnhXa() {
        txtTenKhachHang=findViewById(R.id.txtTenKhachHang);
        edtSDT=findViewById(R.id.edtSDT);
        txtEmail=findViewById(R.id.txtEmail);
        edtDiaChi=findViewById(R.id.edtDiaChi);
        btnXacNhan=findViewById(R.id.btnXacNhan);
        btnHuy=findViewById(R.id.btnHuy);
        pDialog = new ProgressDialog(this,R.style.StyledDialog);
        pDialog.setMessage("Đang xử lý...");
        pDialog.setCanceledOnTouchOutside(false);
    }
}
