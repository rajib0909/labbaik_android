
package com.labbaiik.app.model.videoList;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Datum {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("videos")
    @Expose
    private List<Videos>  videos;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Videos> getVideos() {
        return videos;
    }

    public void setVideos(List<Videos> videos) {
        this.videos = videos;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
