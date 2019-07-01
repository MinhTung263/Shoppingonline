package com.minhtung.muasamonline.Model;

public class GioHang {
    public  int idSP;
    public String TenSP;
    public long GiaSP;
    public String HinhSP;
    public int SoLuongSP;

    public GioHang(int idSP, String tenSP, long giaSP, String hinhSP, int soLuongSP) {
        this.idSP = idSP;
        TenSP = tenSP;
        GiaSP = giaSP;
        HinhSP = hinhSP;
        SoLuongSP = soLuongSP;
    }

    public int getIdSP() {
        return idSP;
    }

    public void setIdSP(int idSP) {
        this.idSP = idSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public long getGiaSP() {
        return GiaSP;
    }

    public void setGiaSP(long giaSP) {
        GiaSP = giaSP;
    }

    public String getHinhSP() {
        return HinhSP;
    }

    public void setHinhSP(String hinhSP) {
        HinhSP = hinhSP;
    }

    public int getSoLuongSP() {
        return SoLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        SoLuongSP = soLuongSP;
    }
}
