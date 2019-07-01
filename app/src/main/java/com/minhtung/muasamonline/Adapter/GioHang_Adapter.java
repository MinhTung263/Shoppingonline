package com.minhtung.muasamonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.minhtung.muasamonline.Activity.ChitietsanphamActivity;
import com.minhtung.muasamonline.Activity.GioHangActivity;
import com.minhtung.muasamonline.Activity.MainActivity;
import com.minhtung.muasamonline.Model.GioHang;
import com.minhtung.muasamonline.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class GioHang_Adapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> gioHangArrayList;

    public GioHang_Adapter(Context context, ArrayList<GioHang> gioHangArrayList) {
        this.context = context;
        this.gioHangArrayList = gioHangArrayList;
    }

    @Override
    public int getCount() {
        return gioHangArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return gioHangArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View view, ViewGroup parent) {
            final TextView tvTenSP,tvGia;
            ImageView imgHinhSPGioHang;
            final Button btnTru,btnSoLuong,btnCong;
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.dong_giohang,null);
            imgHinhSPGioHang=(ImageView) view.findViewById(R.id.imgHinhSPGioHang);
            tvTenSP=(TextView) view.findViewById(R.id.tvTenSPGioHang);
            tvGia=(TextView)view.findViewById(R.id.tvGia);

            btnTru=(Button) view.findViewById(R.id.btnTru);
            btnSoLuong=(Button)view.findViewById(R.id.btnSoLuong);
            btnCong=(Button)view.findViewById(R.id.btnCong);




        GioHang gioHang=(GioHang)getItem(position);
        if (gioHang.getHinhSP().equals("")){
            imgHinhSPGioHang.setImageResource(R.mipmap.noimage);
        }else{
            Picasso.with(context).load(gioHang.getHinhSP()).placeholder(R.mipmap.noimage).error(R.mipmap.error).into(imgHinhSPGioHang);
        }
        tvTenSP.setText(gioHang.getTenSP());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        tvGia.setText(decimalFormat.format(gioHang.getGiaSP())+" đ");
        btnSoLuong.setText(gioHang.getSoLuongSP()+"");
        int sl=Integer.parseInt(btnSoLuong.getText().toString());
        if (sl>10){
            btnCong.setVisibility(View.INVISIBLE);
            btnTru.setVisibility(View.VISIBLE);
        }
        else if(sl<=1){
            btnCong.setVisibility(View.VISIBLE);
            btnTru.setVisibility(View.INVISIBLE);
        }else  if (sl>=1){
            btnCong.setVisibility(View.VISIBLE);
            btnTru.setVisibility(View.VISIBLE);
        }
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    int slMoi = Integer.parseInt(btnSoLuong.getText().toString()) + 1;
                    int slhientai = MainActivity.manggiohang.get(position).getSoLuongSP();
                    long giahientai = MainActivity.manggiohang.get(position).getGiaSP();
                    MainActivity.manggiohang.get(position).setSoLuongSP(slMoi);
                    long giamoinhat = (giahientai * slMoi) / slhientai;
                    MainActivity.manggiohang.get(position).setGiaSP(giamoinhat);
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    tvGia.setText(decimalFormat.format(giamoinhat) + " đ");
                    GioHangActivity.laysoluongsp();
                    GioHangActivity.tinhtien();
                    if (slMoi>9){
                        btnCong.setVisibility(View.INVISIBLE);
                        btnTru.setVisibility(View.VISIBLE);
                        btnSoLuong.setText(String.valueOf(slMoi));
                    }else {
                        btnCong.setVisibility(View.VISIBLE);
                        btnTru.setVisibility(View.VISIBLE);
                        btnSoLuong.setText(String.valueOf(slMoi));
                    }
            }
        });
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slMoi=Integer.parseInt(btnSoLuong.getText().toString())-1;
                int slhientai=MainActivity.manggiohang.get(position).getSoLuongSP();
                long giahientai= MainActivity.manggiohang.get(position).getGiaSP();
                MainActivity.manggiohang.get(position).setSoLuongSP(slMoi);
                long giamoinhat = (giahientai * slMoi) / slhientai;
                MainActivity.manggiohang.get(position).setGiaSP(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                tvGia.setText(decimalFormat.format(giamoinhat) + " đ");
                GioHangActivity.laysoluongsp();
                GioHangActivity.tinhtien();
                if (slMoi<2){
                    btnTru.setVisibility(View.INVISIBLE);
                    btnCong.setVisibility(View.VISIBLE);
                    btnSoLuong.setText(String.valueOf(slMoi));
                }else {
                    btnCong.setVisibility(View.VISIBLE);
                    btnTru.setVisibility(View.VISIBLE);
                    btnSoLuong.setText(String.valueOf(slMoi));
                }
            }
        });
        return view;
    }
}
