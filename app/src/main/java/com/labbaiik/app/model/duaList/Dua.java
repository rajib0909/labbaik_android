
package com.labbaiik.app.model.duaList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dua {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("arabic")
    @Expose
    private String arabic;
    @SerializedName("bangla_pronounce")
    @Expose
    private String banglaPronounce;
    @SerializedName("bangla_meaning")
    @Expose
    private String banglaMeaning;
    @SerializedName("english_meaning")
    @Expose
    private String englishMeaning;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public String getBanglaPronounce() {
        return banglaPronounce;
    }

    public void setBanglaPronounce(String banglaPronounce) {
        this.banglaPronounce = banglaPronounce;
    }

    public String getBanglaMeaning() {
        return banglaMeaning;
    }

    public void setBanglaMeaning(String banglaMeaning) {
        this.banglaMeaning = banglaMeaning;
    }

    public String getEnglishMeaning() {
        return englishMeaning;
    }

    public void setEnglishMeaning(String englishMeaning) {
        this.englishMeaning = englishMeaning;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
