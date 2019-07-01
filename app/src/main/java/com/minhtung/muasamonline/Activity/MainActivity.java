package com.minhtung.muasamonline.Activity;

import android.accounts.Account;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.minhtung.muasamonline.Adapter.DanhMuc_Adapter;
import com.minhtung.muasamonline.Adapter.LoaiSP_Adapter;
import com.minhtung.muasamonline.Adapter.QuangCao_Adapter;
import com.minhtung.muasamonline.Adapter.SanPham_Adapter;
import com.minhtung.muasamonline.Adapter.TimKiem_Adapter;
import com.minhtung.muasamonline.Model.DanhMuc;
import com.minhtung.muasamonline.Model.GioHang;
import com.minhtung.muasamonline.Model.LoaiSanPham;
import com.minhtung.muasamonline.Model.QuangCao;
import com.minhtung.muasamonline.Model.SanPham;
import com.minhtung.muasamonline.Model.TaiKhoan;
import com.minhtung.muasamonline.R;
import com.minhtung.muasamonline.Server.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MainActivity extends AppCompatActivity {
    private TaiKhoan taiKhoan;
    public static   ArrayList<TaiKhoan> taiKhoanArrayList=new ArrayList<>();
    Toolbar toolbar;
    AdapterViewFlipper adapterViewFlipper;
    QuangCao_Adapter quangCao_adapter;
    ArrayList<QuangCao> quangCaoArrayList=new ArrayList<>();
    NestedScrollView scrollView;
    RecyclerView recyclerViewThuongHieu,recyclerViewSanPham,recyclerViewTimKiem,recyclerViewDM;;
    SanPham_Adapter sanPham_adapter;
    ArrayList<SanPham> sanPhamArrayList=new ArrayList<>();
    ArrayList<DanhMuc> danhMucArrayList=new ArrayList<>();
    DanhMuc_Adapter danhMuc_adapter;
    TimKiem_Adapter timKiem_adapter;
    public static ProgressDialog progressDialog;
    SwipeRefreshLayout refreshLayoutMainActivity;
    RelativeLayout relativeLayoutView;
    public  static  Context context;
    TextView tvThongBao;
    public static  ArrayList<GioHang> manggiohang;

    ArrayList<LoaiSanPham> mangloaisanpham=new ArrayList<>();
    LoaiSP_Adapter loaiSP_adapter;

    FrameLayout rootView,redCircle;
    public  static  TextView countTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog=new ProgressDialog(this,R.style.StyledDialog);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Đang tải dữ liệu...");

        AnhXa();
        getIntentDangNhap();
        ActionToolbar();
        getDataQuangCao();
        getDataDM();
        getdataLoaiSP();
        getDataSanPham();

        adapterViewFlipper.setFlipInterval(7000);
        adapterViewFlipper.setAutoStart(true);
        adapterViewFlipper.setInAnimation(getApplicationContext(),R.animator.slide_in_right);
        adapterViewFlipper.setOutAnimation(getApplicationContext(),R.animator.slide_out_right);

        GridLayoutManager manager=new GridLayoutManager(getApplicationContext(),2,GridLayoutManager.HORIZONTAL,false);
        recyclerViewDM.setLayoutManager(manager);
        recyclerViewDM.setHasFixedSize(true);

        GridLayoutManager manager2=new GridLayoutManager(getApplicationContext(),2);
//        manager2.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int i) {
//                return (3-i%2);
//            }
//        });
        recyclerViewSanPham.setLayoutManager(manager2);


        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollView.getScrollY();
                if(scrollY == 0) refreshLayoutMainActivity.setEnabled(true);
                else refreshLayoutMainActivity.setEnabled(false);
            }
        });
        refreshLayoutMainActivity.setColorScheme(R.color.colorAccent,R.color.colorApp,R.color.colorOrange,R.color.colorGreen);
        refreshLayoutMainActivity.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataQuangCao();
                quangCaoArrayList=new ArrayList<>();
                quangCao_adapter=new QuangCao_Adapter(getApplicationContext(),quangCaoArrayList);
                adapterViewFlipper.setAdapter(quangCao_adapter);
                getDataSanPham();
                sanPhamArrayList=new ArrayList<>();
                sanPham_adapter=new SanPham_Adapter(getApplicationContext(), sanPhamArrayList);
                recyclerViewSanPham.setAdapter(sanPham_adapter);

                getDataDM();
                danhMucArrayList=new ArrayList<>();
                danhMuc_adapter=new DanhMuc_Adapter(getApplicationContext(),danhMucArrayList);
                recyclerViewDM.setAdapter(danhMuc_adapter);

                getdataLoaiSP();
                mangloaisanpham=new ArrayList<>();
                loaiSP_adapter=new LoaiSP_Adapter(getApplicationContext(),mangloaisanpham);
                recyclerViewThuongHieu.setAdapter(loaiSP_adapter);

                refreshLayoutMainActivity.setRefreshing(false);
            }
        });
    }

    private void getIntentDangNhap() {
        Intent intent=getIntent();
        taiKhoan=new TaiKhoan();
        if (intent!=null){
            if (intent.hasExtra("login")){
                taiKhoan= (TaiKhoan) intent.getSerializableExtra("login");
                if (taiKhoan!=null&&!taiKhoan.getUserName().equals("")&&!taiKhoan.getEmail().equals("")){
                    String iduser=taiKhoan.getUser_id();
                    String tenkhachhang=taiKhoan.getUserName();
                    String email=taiKhoan.getEmail();
                    taiKhoanArrayList.add(new TaiKhoan(iduser,tenkhachhang,email));
                }

            }
        }

    }
    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.icon_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (taiKhoanArrayList.size()>0){
                    Intent intent=new Intent(getApplicationContext(),KhachHangActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent=new Intent(getApplicationContext(),DangNhapActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
    private void getDataDM() {
        progressDialog.show();
        relativeLayoutView.setVisibility(View.INVISIBLE);
        API.initRetrofit().create(API.ApiInterface.class).getDM().enqueue(new Callback<List<DanhMuc>>() {
            @Override
            public void onResponse(Call<List<DanhMuc>> call, Response<List<DanhMuc>> response) {
                danhMucArrayList.addAll(response.body());
                relativeLayoutView.setVisibility(View.VISIBLE);
                String msg = "";
                if ( danhMucArrayList.size() == 0) msg = getString(R.string.no_data);
                for (int i=0;i<danhMucArrayList.size();i++){
                    danhMuc_adapter=new DanhMuc_Adapter(getApplicationContext(),danhMucArrayList);
                    recyclerViewDM.setAdapter(danhMuc_adapter);
                    danhMuc_adapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                }

            }
            @Override
            public void onFailure(Call<List<DanhMuc>> call, Throwable t) {
            }
        });
    }

    private void getDataQuangCao() {
        progressDialog.show();
        relativeLayoutView.setVisibility(View.INVISIBLE);
        API.initRetrofit().create(API.ApiInterface.class).getQuangCao().enqueue(new Callback<List<QuangCao>>() {
            @Override
            public void onResponse(Call<List<QuangCao>> call, Response<List<QuangCao>> response) {
                quangCaoArrayList.addAll(response.body());
                relativeLayoutView.setVisibility(View.VISIBLE);
                String msg = "";
                if ( quangCaoArrayList.size() == 0) msg = getString(R.string.no_data);
                quangCao_adapter=new QuangCao_Adapter(getApplicationContext(),quangCaoArrayList);
                adapterViewFlipper.setAdapter(quangCao_adapter);
                quangCao_adapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }
            @Override
            public void onFailure(Call<List<QuangCao>> call, Throwable t) {
            }
        });
    }

    private void getDataSanPham() {
        progressDialog.show();
        relativeLayoutView.setVisibility(View.INVISIBLE);
        API.initRetrofit().create(API.ApiInterface.class).getSanPham().enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                sanPhamArrayList.addAll(response.body());
                relativeLayoutView.setVisibility(View.VISIBLE);
                String msg = "";
                if ( sanPhamArrayList.size() == 0) msg = getString(R.string.no_data);
                sanPham_adapter=new SanPham_Adapter(getApplicationContext(), sanPhamArrayList);
                recyclerViewSanPham.setAdapter(sanPham_adapter);
                sanPham_adapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }
            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
            }
        });
    }
    private void getdataLoaiSP() {
        progressDialog.show();
        relativeLayoutView.setVisibility(View.INVISIBLE);
        API.initRetrofit().create(API.ApiInterface.class).getLoaiSP().enqueue(new Callback<List<LoaiSanPham>>() {
            @Override
            public void onResponse(Call<List<LoaiSanPham>> call, Response<List<LoaiSanPham>> response) {
                mangloaisanpham.addAll(response.body());
                relativeLayoutView.setVisibility(View.VISIBLE);
                String msg = "";
                if ( mangloaisanpham.size() == 0) msg = getString(R.string.no_data);
                loaiSP_adapter=new LoaiSP_Adapter(getApplicationContext(),mangloaisanpham);
                recyclerViewThuongHieu.setAdapter(loaiSP_adapter);
                StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
                recyclerViewThuongHieu.setLayoutManager(gridLayoutManager);
                loaiSP_adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<List<LoaiSanPham>> call, Throwable t) {
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_giohang,menu);
        getMenuInflater().inflate(R.menu.menu,menu);
        final MenuItem item=menu.findItem(R.id.action_search);

        final SearchView searchView= (SearchView) item.getActionView();
        ImageView icon = searchView.findViewById(android.support.v7.appcompat.R.id.search_button);
        icon.setColorFilter(Color.RED);
        SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(getResources().getColor(android.R.color.black));
        searchAutoComplete.setTextColor(getResources().getColor(android.R.color.black));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Tìm sản phẩm...");
        //Nút tìm kiếm thành mũi tên
        searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshLayoutMainActivity.setEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                menu.findItem(R.id.menugiohang).setVisible(false);
                relativeLayoutView.setVisibility(View.INVISIBLE);
            }
        });


        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                refreshLayoutMainActivity.setEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                toolbar.setNavigationIcon(R.drawable.icon_menu);
                menu.findItem(R.id.menugiohang).setVisible(true);
                recyclerViewTimKiem.setVisibility(View.INVISIBLE);
                relativeLayoutView.setVisibility(View.VISIBLE);
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                refreshLayoutMainActivity.setEnabled(false);
                TimKiem(query);
                recyclerViewTimKiem.setVisibility(View.VISIBLE);
                relativeLayoutView.setVisibility(View.INVISIBLE);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                if (taiKhoanArrayList.size()>0){
                    Intent intent=new Intent(getApplicationContext(),GioHangActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent=new Intent(getApplicationContext(),DangNhapActivity.class);
                    startActivity(intent);
                }
                return  false;
        }
        return true;
    }


    @Override
    public  boolean onPrepareOptionsMenu(Menu menu) {

        final MenuItem alertMenuItem = menu.findItem(R.id.menugiohang);
        rootView = (FrameLayout) alertMenuItem.getActionView();
        redCircle = (FrameLayout) rootView.findViewById(R.id.view_alert_red_circle);
        countTextView = rootView.findViewById(R.id.view_alert_count_textview);
        GioHangActivity.laysoluongsp();
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(alertMenuItem);
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }
    private  void TimKiem(String query){
        API.initRetrofit().create(API.ApiInterface.class).getTimKiemSanPham(query).enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                ArrayList<SanPham> danhsachsanpham=(ArrayList<SanPham>)response.body();
                if (danhsachsanpham.size()>0){
                    timKiem_adapter=new TimKiem_Adapter(MainActivity.this,danhsachsanpham);
                    StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    recyclerViewTimKiem.setLayoutManager(gridLayoutManager);
                    recyclerViewTimKiem.setAdapter(timKiem_adapter);
                    tvThongBao.setVisibility(View.INVISIBLE);
                }else{
                    tvThongBao.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {

            }
        });
    }

    private void AnhXa() {
        toolbar=findViewById(R.id.toolbarTrangChu);
        scrollView=findViewById(R.id.scrollview);
        adapterViewFlipper=findViewById(R.id.viewflipperTrangChu);
        recyclerViewSanPham=findViewById(R.id.recyclerviewSanPham);

        recyclerViewThuongHieu=findViewById(R.id.recyclerviewThuongHieu);
        recyclerViewDM=findViewById(R.id.recyclerviewDanhMuc);
        relativeLayoutView=findViewById(R.id.relativelayoutView);
        tvThongBao=findViewById(R.id.tvThongBao);
        refreshLayoutMainActivity=findViewById(R.id.activity_main_refreshlayout);
        recyclerViewTimKiem=findViewById(R.id.recyclerviewTimKiem);


        if (manggiohang!=null ){
        }
        else {
            manggiohang=new ArrayList<>();
        }
    }


}
