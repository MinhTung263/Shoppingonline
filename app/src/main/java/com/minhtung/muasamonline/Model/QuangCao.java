package com.minhtung.muasamonline.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuangCao {

@SerializedName("idQuangCao")
@Expose
private int idQuangCao;
@SerializedName("TenQuangCao")
@Expose
private String tenQuangCao;
@SerializedName("HinhQuangCao")
@Expose
private String hinhQuangCao;
@SerializedName("idSP")
@Expose
private int idSP;

public int getIdQuangCao() {
return idQuangCao;
}

public void setIdQuangCao(int idQuangCao) {
this.idQuangCao = idQuangCao;
}

public String getTenQuangCao() {
return tenQuangCao;
}

public void setTenQuangCao(String tenQuangCao) {
this.tenQuangCao = tenQuangCao;
}

public String getHinhQuangCao() {
return hinhQuangCao;
}

public void setHinhQuangCao(String hinhQuangCao) {
this.hinhQuangCao = hinhQuangCao;
}

public int getIdSP() {
return idSP;
}

public void setIdSP(int idSP) {
this.idSP = idSP;
}

}