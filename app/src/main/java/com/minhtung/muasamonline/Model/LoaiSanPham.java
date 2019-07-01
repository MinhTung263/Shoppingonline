package com.minhtung.muasamonline.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoaiSanPham {

@SerializedName("idLoaiSP")
@Expose
private int idLoaiSP;
@SerializedName("TenLoaiSP")
@Expose
private String tenLoaiSP;
@SerializedName("HinhLoaiSP")
@Expose
private String hinhLoaiSP;

public int getIdLoaiSP() {
return idLoaiSP;
}

public void setIdLoaiSP(int idLoaiSP) {
this.idLoaiSP = idLoaiSP;
}

public String getTenLoaiSP() {
return tenLoaiSP;
}

public void setTenLoaiSP(String tenLoaiSP) {
this.tenLoaiSP = tenLoaiSP;
}

public String getHinhLoaiSP() {
return hinhLoaiSP;
}

public void setHinhLoaiSP(String hinhLoaiSP) {
this.hinhLoaiSP = hinhLoaiSP;
}

}