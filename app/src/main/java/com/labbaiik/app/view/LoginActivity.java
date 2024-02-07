package com.labbaiik.app.view;

import static com.labbaiik.app.utill.SharedPrefUtil.setSetting;
import static com.labbaiik.app.utill.Util.isOnline;
import static com.labbaiik.app.utill.Util.showToastMessage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.labbaiik.app.R;
import com.labbaiik.app.databinding.ActivityLoginBinding;
import com.labbaiik.app.view.activity.SignUpSelectionDialog;
import com.labbaiik.app.viewModel.ViewModel;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    private String  password, email;
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
//
//        Window window = getWindow();
//        WindowManager.LayoutParams winParams = window.getAttributes();
//        winParams.flags &= ~WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//        window.setAttributes(winParams);
//        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

//        Glide.with(this)
//                .load(R.drawable.signin_draw)
//                .into(loginBinding.loginDrawable);



        binding.btnSignUp.setOnClickListener(l -> {
            SignUpSelectionDialog signUpSelectionDialog = new SignUpSelectionDialog();
            signUpSelectionDialog.show(getSupportFragmentManager(),
                    "ModalBottomSheet");
           // startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        });
        binding.btnSignIn.setOnClickListener(l -> {

            email  = binding.etEmail.getText().toString().trim();
            password = binding.etPassword.getText().toString().trim();

            if (email.isEmpty()) {
                showToastMessage(this, getString(R.string.email_is_required));
                binding.etEmail.requestFocus();
                return;
            }
            if (password.isEmpty()) {
                showToastMessage(this, getString(R.string.password_is_required));
                binding.etEmail.requestFocus();
                return;
            }
            if (isOnline(this)){
                binding.loading.setVisibility(View.VISIBLE);
                binding.btnSignIn.setEnabled(false);
                viewModel.getLogin(email, password);
                observeViewModel();

            }else {
                showToastMessage(this, getString(R.string.no_internet_connection));
            }
        });


    }

    private void observeViewModel() {
        viewModel.loginMutableLiveData.observe(
                this,
                loginResponse -> {
                    if (loginResponse.getData().getSuccess()) {
                        showToastMessage(this, "Successfully login");

                        setSetting(this, "userId", loginResponse.getData().getUserId());
                        setSetting(this, "token", "Bearer " + loginResponse.getData().getAccessToken());

                        setSetting(this, "isLoggedIn", true);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finishAffinity();
                    } else {
                        binding.btnSignIn.setEnabled(true);
                        showToastMessage(this, loginResponse.getData().getMessage());
                    }
                    viewModel.loginMutableLiveData = new MutableLiveData<>();
                }
        );
        viewModel.loginLoadError.observe(
                this, isError -> {
                    if (isError != null) {
                        if (isError) {
                            binding.btnSignIn.setEnabled(true);
                            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                        viewModel.loginLoadError = new MutableLiveData<>();
                    }
                }
        );

        viewModel.loading.observe(
                this, loading -> {
                    if (loading != null) {
                        if (!loading) {
                            binding.btnSignIn.setEnabled(true);
                            binding.loading.setVisibility(View.GONE);
                        }
                        viewModel.loading = new MutableLiveData<>();
                    }
                }
        );
    }
}