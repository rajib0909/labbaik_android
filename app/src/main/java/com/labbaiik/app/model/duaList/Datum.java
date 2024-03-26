
package com.labbaiik.app.model.duaList;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("dua")
    @Expose
    private List<Dua> dua;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Dua> getDua() {
        return dua;
    }

    public void setDua(List<Dua> dua) {
        this.dua = dua;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
