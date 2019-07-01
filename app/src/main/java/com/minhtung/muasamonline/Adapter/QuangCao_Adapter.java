package com.minhtung.muasamonline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.minhtung.muasamonline.Activity.ChitietsanphamActivity;
import com.minhtung.muasamonline.Model.QuangCao;
import com.minhtung.muasamonline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class QuangCao_Adapter extends BaseAdapter {
    Context context;
    ArrayList<QuangCao> quangCaoArrayList;

    public QuangCao_Adapter(Context context, ArrayList<QuangCao> quangCaoArrayList) {
        this.context = context;
        this.quangCaoArrayList = quangCaoArrayList;
    }

    @Override
    public int getCount() {
        return quangCaoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return quangCaoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.dong_quangcao,null);

        ImageView imgHinhQuangCao=view.findViewById(R.id.imgHinhQuangCao);

        if (quangCaoArrayList.get(position).getHinhQuangCao().equals("")){
            imgHinhQuangCao.setImageResource(R.mipmap.noimage);
        } else {
            Picasso.with(context).load(quangCaoArrayList.get(position).getHinhQuangCao()).error(R.mipmap.error).placeholder(R.mipmap.noimage).into(imgHinhQuangCao);
        }
        imgHinhQuangCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, ChitietsanphamActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("idSP",quangCaoArrayList.get(position).getIdSP());
                context.startActivity(intent);
            }
        });
        return view;

    }
}
