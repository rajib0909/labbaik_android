package com.labbaiik.app.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.labbaiik.app.api.NetworkService;
import com.labbaiik.app.model.duaList.DuaListResponse;
import com.labbaiik.app.model.fetchAllQueston.FetchAllQuestion;
import com.labbaiik.app.model.login.Login;
import com.labbaiik.app.model.privacyResponse.PrivacyResponse;
import com.labbaiik.app.model.questionCategory.QuestionCategoryResponse;
import com.labbaiik.app.model.termsCondition.TermsConditionResponse;
import com.labbaiik.app.model.videoList.VideoListResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ViewModel extends androidx.lifecycle.ViewModel {


    /**
     * only exposes immutable Auth LiveData objects to observe users
     */

    public MutableLiveData<FetchAllQuestion> fetchAllQuestionMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<QuestionCategoryResponse> questionCategory = new MutableLiveData<>();
    public MutableLiveData<Login> loginMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<PrivacyResponse> privacyResponseMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<TermsConditionResponse> termsConditionResponseData = new MutableLiveData<>();
    public MutableLiveData<DuaListResponse> duaListResponseMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<VideoListResponse> videoListResponseMutableLiveData = new MutableLiveData<>();



    /**
     * only exposes immutable Boolen LiveData objects to observe usersLoadError
     */
    public MutableLiveData<Boolean> fetchAllQuestionLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> loginLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> questionCategoryLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> privacyResponseLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> termsConditionResponseLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> duaListResponseLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> videoListResponseLoadError = new MutableLiveData<>();


    /**
     * only exposes immutable Boolen LiveData objects to observe loading
     */
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();
    public MutableLiveData<Boolean> noInternet = new MutableLiveData<>();

    /**
     * Call network service
     */
    private NetworkService networkService = NetworkService.getInstance();

    private CompositeDisposable disposable = new CompositeDisposable();


    public void getAllQuestion() {
        disposable.add(
                networkService.allQuestion()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<FetchAllQuestion>() {
                            @Override
                            public void onSuccess(@NonNull FetchAllQuestion fetchAllQuestion) {
                                fetchAllQuestionMutableLiveData.setValue(fetchAllQuestion);
                                fetchAllQuestionLoadError.setValue(false);
                                loading.setValue(false);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                fetchAllQuestionLoadError.setValue(true);
                                loading.setValue(false);
                            }
                        })
        );
    }

    public void allQuestionCategory() {
        disposable.add(
                networkService.allQuestionCategory()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<QuestionCategoryResponse>() {
                            @Override
                            public void onSuccess(@NonNull QuestionCategoryResponse questionCategoryResponse) {
                                questionCategory.setValue(questionCategoryResponse);
                                questionCategoryLoadError.setValue(false);
                                loading.setValue(false);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                questionCategoryLoadError.setValue(true);
                                loading.setValue(false);
                            }
                        })
        );
    }

    public void getTermsCondition() {
        disposable.add(
                networkService.getTermsCondition()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<TermsConditionResponse>() {
                            @Override
                            public void onSuccess(@NonNull TermsConditionResponse response) {
                                termsConditionResponseData.setValue(response);
                                termsConditionResponseLoadError.setValue(false);
                                loading.setValue(false);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                termsConditionResponseLoadError.setValue(true);
                                loading.setValue(false);
                            }
                        })
        );
    }

    public void getDuaList() {
        disposable.add(
                networkService.getDuaList()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<DuaListResponse>() {
                            @Override
                            public void onSuccess(@NonNull DuaListResponse response) {
                                duaListResponseMutableLiveData.setValue(response);
                                duaListResponseLoadError.setValue(false);
                                loading.setValue(false);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                duaListResponseLoadError.setValue(true);
                                loading.setValue(false);
                            }
                        })
        );
    }


    public void getVideoList() {
        disposable.add(
                networkService.getVideoList()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<VideoListResponse>() {
                            @Override
                            public void onSuccess(@NonNull VideoListResponse response) {
                                videoListResponseMutableLiveData.setValue(response);
                                videoListResponseLoadError.setValue(false);
                                loading.setValue(false);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                videoListResponseLoadError.setValue(true);
                                loading.setValue(false);
                            }
                        })
        );
    }

    public void getPrivacy() {
        disposable.add(
                networkService.getPrivacy()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<PrivacyResponse>() {
                            @Override
                            public void onSuccess(@NonNull PrivacyResponse privacyResponse) {
                                privacyResponseMutableLiveData.setValue(privacyResponse);
                                privacyResponseLoadError.setValue(false);
                                loading.setValue(false);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                privacyResponseLoadError.setValue(true);
                                loading.setValue(false);
                            }
                        })
        );
    }

    public void getLogin(String email, String password) {
        disposable.add(
                networkService.login(email, password)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Login>() {
                            @Override
                            public void onSuccess(@NonNull Login login) {
                                loginMutableLiveData.setValue(login);
                                loginLoadError.setValue(false);
                                Log.d("Tanvir", login.getData().getMessage());
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                loginLoadError.setValue(true);
                                Log.d("Tanvir", "opps");
                            }
                        })
        );
    }


    /**
     * Using clear CompositeDisposable, but can accept new disposable
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
