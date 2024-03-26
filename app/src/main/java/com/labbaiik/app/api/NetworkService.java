package com.labbaiik.app.api;

import com.labbaiik.app.model.duaList.DuaListResponse;
import com.labbaiik.app.model.fetchAllQueston.FetchAllQuestion;
import com.labbaiik.app.model.login.Login;
import com.labbaiik.app.model.privacyResponse.PrivacyResponse;
import com.labbaiik.app.model.questionCategory.QuestionCategoryResponse;
import com.labbaiik.app.model.termsCondition.TermsConditionResponse;
import com.labbaiik.app.model.videoList.VideoListResponse;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static final String BASE_URL = "https://ijaba.org/api/";

    private static NetworkService instance;

    private Api api = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(Api.class);


    private NetworkService() {
    }

    public static NetworkService getInstance() {
        if (instance == null) {
            instance = new NetworkService();
        }
        return instance;
    }

    public Single<FetchAllQuestion> allQuestion() {
        return api.allQuestion();
    }

    public Single<QuestionCategoryResponse> allQuestionCategory() {
        return api.allQuestionCategory();
    }

    public Single<PrivacyResponse> getPrivacy() {
        return api.getPrivacy();
    }

    public Single<TermsConditionResponse> getTermsCondition() {
        return api.getTermsCondition();
    }

    public Single<DuaListResponse> getDuaList() {
        return api.getDuaList();
    }
    public Single<VideoListResponse> getVideoList() {
        return api.getVideoList();
    }

    public Single<Login> login(String email, String password) {
        return api.userLogin(email, password);
    }
}
