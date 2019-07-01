package com.minhtung.muasamonline.Server;

import com.minhtung.muasamonline.Model.DanhMuc;
import com.minhtung.muasamonline.Model.KhachHang;
import com.minhtung.muasamonline.Model.LoaiSanPham;
import com.minhtung.muasamonline.Model.QuangCao;
import com.minhtung.muasamonline.Model.SanPham;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class API {
    public static Retrofit initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://luongtung263.000webhostapp.com/Muasamonline/FilePHP/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public interface ApiInterface {
        @POST("loaisanpham.php")
        public Call<List<LoaiSanPham>> getLoaiSP();

        @POST("quangcao.php")
        public Call<List<QuangCao>> getQuangCao();

        @POST("sanpham.php")
        public Call<List<SanPham>> getSanPham();

        @POST("danhmuc.php")
        public Call<List<DanhMuc>> getDM();

        @FormUrlEncoded
        @POST("sanpham.php")
        public Call<List<SanPham>> getIdSanPham(@Field("idSP") int idSP);

        @FormUrlEncoded
        @POST("sanpham.php")
        public Call<List<SanPham>> getIdLoaiSanPham(@Field("idLoaiSP") int idLoaiSP);

        @FormUrlEncoded
        @POST("sanpham.php")
        public Call<List<SanPham>> getIdDM(@Field("idDM") int idDM);

        @FormUrlEncoded
        @POST("khachhang.php")
        public Call<List<KhachHang>> getIdDonHang(@Field("user_id") String idDonHang);

        @FormUrlEncoded
        @POST("timkiem.php")
        Call<List<SanPham>>getTimKiemSanPham(@Field("tukhoa") String tukhoa);
    }
    public static String Donhang= initRetrofit().baseUrl()+"thongtinkhachhang.php";
    public static String Dangnhap=initRetrofit().baseUrl()+"dangnhap.php";
    public static String Dangky=initRetrofit().baseUrl()+"dangky.php";
    public static String Chitietdonhang=initRetrofit().baseUrl()+"chitietdonhang.php";
}
