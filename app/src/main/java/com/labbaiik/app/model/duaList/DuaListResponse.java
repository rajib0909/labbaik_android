
package com.labbaiik.app.model.duaList;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DuaListResponse {

    @SerializedName("data")
    @Expose
    private List<Datum> data;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}
