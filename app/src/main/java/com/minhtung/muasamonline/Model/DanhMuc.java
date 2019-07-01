package com.minhtung.muasamonline.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DanhMuc {

@SerializedName("idDM")
@Expose
private int idDM;
@SerializedName("TenDM")
@Expose
private String tenDM;
@SerializedName("HinhDM")
@Expose
private String hinhDM;

public int getIdDM() {
return idDM;
}

public void setIdDM(int idDM) {
this.idDM = idDM;
}

public String getTenDM() {
return tenDM;
}

public void setTenDM(String tenDM) {
this.tenDM = tenDM;
}

public String getHinhDM() {
return hinhDM;
}

public void setHinhDM(String hinhDM) {
this.hinhDM = hinhDM;
}

}