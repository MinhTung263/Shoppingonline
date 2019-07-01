package com.minhtung.muasamonline.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SanPham {

    @SerializedName("idSP")
    @Expose
    private int idSP;
    @SerializedName("TenSP")
    @Expose
    private String tenSP;
    @SerializedName("GiaSP")
    @Expose
    private int giaSP;
    @SerializedName("HinhSP")
    @Expose
    private String hinhSP;
    @SerializedName("MoTa")
    @Expose
    private String moTa;
    @SerializedName("idLoaiSP")
    @Expose
    private int idLoaiSP;
    @SerializedName("idDM")
    @Expose
    private int idDM;

    public int getIdSP() {
        return idSP;
    }

    public void setIdSP(int idSP) {
        this.idSP = idSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public String getHinhSP() {
        return hinhSP;
    }

    public void setHinhSP(String hinhSP) {
        this.hinhSP = hinhSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getIdLoaiSP() {
        return idLoaiSP;
    }

    public void setIdLoaiSP(int idLoaiSP) {
        this.idLoaiSP = idLoaiSP;
    }

    public int getIdDM() {
        return idDM;
    }

    public void setIdDM(int idDM) {
        this.idDM = idDM;
    }

}