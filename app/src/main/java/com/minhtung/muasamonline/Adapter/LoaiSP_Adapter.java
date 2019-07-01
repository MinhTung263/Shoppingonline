package com.minhtung.muasamonline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.minhtung.muasamonline.Activity.LoaiSPActivity;
import com.minhtung.muasamonline.Model.LoaiSanPham;
import com.minhtung.muasamonline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaiSP_Adapter extends RecyclerView.Adapter<LoaiSP_Adapter.ViewHolder> {

    Context context;
    ArrayList<LoaiSanPham> loaiSanPhamArrayList;

    public LoaiSP_Adapter(Context context, ArrayList<LoaiSanPham> loaiSanPhamArrayList) {
        this.context = context;
        this.loaiSanPhamArrayList = loaiSanPhamArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_loaisp,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.tvTenLoaiSP.setText(loaiSanPhamArrayList.get(position).getTenLoaiSP());
        if (loaiSanPhamArrayList.get(position).getHinhLoaiSP().equals("")){
            viewHolder.imgHinhLoaiSP.setImageResource(R.mipmap.noimage);
        }else{
            Picasso.with(context).load(loaiSanPhamArrayList.get(position).getHinhLoaiSP())
                    .error(R.mipmap.error)
                    .placeholder(R.mipmap.noimage)
                    .into(viewHolder.imgHinhLoaiSP);
        }

    }


    @Override
    public int getItemCount() {
        return loaiSanPhamArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenLoaiSP;
        ImageView imgHinhLoaiSP;
        public ViewHolder(@NonNull View view ){
            super(view);
            tvTenLoaiSP=view.findViewById(R.id.tvTenLoaiSP);
            imgHinhLoaiSP=view.findViewById(R.id.imgHinhLoaiSP);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, LoaiSPActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("idLoaiSP",loaiSanPhamArrayList.get(getAdapterPosition()).getIdLoaiSP());
                    context.startActivity(intent);
                }
            });
        }
    }
}
