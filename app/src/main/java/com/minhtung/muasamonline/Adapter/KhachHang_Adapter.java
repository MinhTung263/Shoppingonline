package com.minhtung.muasamonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.minhtung.muasamonline.Activity.MainActivity;
import com.minhtung.muasamonline.Model.GioHang;
import com.minhtung.muasamonline.Model.KhachHang;
import com.minhtung.muasamonline.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class KhachHang_Adapter extends BaseAdapter {

    Context context;
    ArrayList<KhachHang> khachHangArrayList;

    public KhachHang_Adapter(Context context, ArrayList<KhachHang> khachHangArrayList) {
        this.context = context;
        this.khachHangArrayList = khachHangArrayList;
    }

    @Override
    public int getCount() {
        return khachHangArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return khachHangArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ImageView HinhSP;
        TextView TenSP,GiaSP,SoLuong;
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.dong_khach_hang,null);
        HinhSP=view.findViewById(R.id.HinhSP);
        TenSP=view.findViewById(R.id.TenSP);
        GiaSP=view.findViewById(R.id.GiaSP);
        SoLuong=view.findViewById(R.id.SoLuong);
        KhachHang khachHang= (KhachHang) getItem(position);

        Picasso.with(context).load(khachHang.getHinhSP()).placeholder(R.mipmap.noimage).error(R.mipmap.error).into(HinhSP);
        TenSP.setText(khachHang.getTenSP());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        GiaSP.setText("Tổng thanh toán: "+decimalFormat.format(khachHang.getGiaSP())+"đ");
        SoLuong.setText("Số lượng: "+khachHang.getSoLuong()+"");

        return view;
    }
}
