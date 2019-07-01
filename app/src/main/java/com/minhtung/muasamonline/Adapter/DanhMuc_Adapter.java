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
import com.minhtung.muasamonline.Model.DanhMuc;
import com.minhtung.muasamonline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhMuc_Adapter extends RecyclerView.Adapter<DanhMuc_Adapter.ViewHolder> {

    Context context;
    ArrayList<DanhMuc> danhMucArrayList;

    public DanhMuc_Adapter(Context context, ArrayList<DanhMuc> danhMucArrayList) {
        this.context = context;
        this.danhMucArrayList = danhMucArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_danhmuc,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.tvTenDM.setText(danhMucArrayList.get(position).getTenDM());
        if (danhMucArrayList.get(position).getHinhDM().equals("")){
            viewHolder.imgDanhMuc.setImageResource(R.mipmap.noimage);
        }else{
            Picasso.with(context).load(danhMucArrayList.get(position).getHinhDM())
                    .error(R.mipmap.error)
                    .placeholder(R.mipmap.noimage)
                    .into(viewHolder.imgDanhMuc);
        }

    }


    @Override
    public int getItemCount() {
        return danhMucArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenDM;
        ImageView imgDanhMuc;
        public ViewHolder(@NonNull View view ){
            super(view);
            tvTenDM=view.findViewById(R.id.tvTenDM);
            imgDanhMuc=view.findViewById(R.id.imgDanhMuc);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, LoaiSPActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("idDM",danhMucArrayList.get(getAdapterPosition()).getIdDM());
                    context.startActivity(intent);
                }
            });
        }
    }
}
