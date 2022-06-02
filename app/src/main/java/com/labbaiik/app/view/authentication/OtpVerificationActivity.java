package com.labbaiik.app.view.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.labbaiik.app.R;
import com.labbaiik.app.databinding.ActivityOtpVerificationBinding;
import com.labbaiik.app.view.MainActivity;

public class OtpVerificationActivity extends AppCompatActivity {

    ActivityOtpVerificationBinding otpVerificationBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        otpVerificationBinding = DataBindingUtil.setContentView(this, R.layout.activity_otp_verification);
        setupOTPInputs();

        otpVerificationBinding.btnContiniue.setOnClickListener(l->{
            startActivity(new Intent(OtpVerificationActivity.this, MainActivity.class));
            finishAffinity();
        });
    }

     private void setupOTPInputs() {
         otpVerificationBinding.otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otpVerificationBinding.otp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
         otpVerificationBinding.otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otpVerificationBinding.otp3.requestFocus();
                } else
                    otpVerificationBinding.otp1.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
         otpVerificationBinding.otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otpVerificationBinding.otp4.requestFocus();
                } else
                    otpVerificationBinding.otp2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

         otpVerificationBinding.otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    hideKeyboard();
                } else
                    otpVerificationBinding.otp3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


}