package com.labbaiik.app.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.labbaiik.app.api.NetworkService;
import com.labbaiik.app.model.fetchAllQueston.FetchAllQuestion;
import com.labbaiik.app.model.login.Login;

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
    public MutableLiveData<Login> loginMutableLiveData = new MutableLiveData<>();



    /**
     * only exposes immutable Boolen LiveData objects to observe usersLoadError
     */
    public MutableLiveData<Boolean> fetchAllQuestionLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> loginLoadError = new MutableLiveData<>();


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
