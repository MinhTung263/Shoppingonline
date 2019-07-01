package com.minhtung.muasamonline.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.minhtung.muasamonline.Activity.ChitietsanphamActivity;
import com.minhtung.muasamonline.Activity.MainActivity;
import com.minhtung.muasamonline.Model.SanPham;
import com.minhtung.muasamonline.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class SanPham_Adapter extends RecyclerView.Adapter<SanPham_Adapter.ViewHolder> {

    Context context;
    ArrayList<SanPham> sanPhamArrayList;

    public SanPham_Adapter(Context context, ArrayList<SanPham> sanPhamArrayList) {
        this.context = context;
        this.sanPhamArrayList = sanPhamArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_sanpham,parent,false);
        ViewHolder holder=new ViewHolder(view);
        context=parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        if (sanPhamArrayList.get(position).getHinhSP().equals("")){
            viewHolder.imgHinhSanPham.setImageResource(R.mipmap.noimage);
        }else{
            Picasso.with(context).load(sanPhamArrayList.get(position).getHinhSP()).error(R.mipmap.error).placeholder(R.mipmap.noimage).into(viewHolder.imgHinhSanPham);
        }

        viewHolder.tvTenSP.setText(sanPhamArrayList.get(position).getTenSP());
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        viewHolder.tvGiaSP.setText(formatter.format(sanPhamArrayList.get(position).getGiaSP())+" đ");
        viewHolder.tvMoTa.setText(sanPhamArrayList.get(position).getMoTa());

    }

    @Override
    public int getItemCount() {
        return sanPhamArrayList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenSP,tvGiaSP,tvMoTa;
        ImageView imgHinhSanPham;
        public ViewHolder(@NonNull View view) {
            super(view);
            tvTenSP=view.findViewById(R.id.tvTenSP);
            tvGiaSP=view.findViewById(R.id.tvGiaSP);
            tvMoTa=view.findViewById(R.id.tvMoTa);
            imgHinhSanPham=view.findViewById(R.id.imgHinhSP);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(context, ChitietsanphamActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("idSP",sanPhamArrayList.get(getAdapterPosition()).getIdSP());
                    context.startActivity(intent);
                }
            });
        }
    }
}
