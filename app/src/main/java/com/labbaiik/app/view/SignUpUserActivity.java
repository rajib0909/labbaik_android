package com.labbaiik.app.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.labbaiik.app.R;
import com.labbaiik.app.databinding.ActivitySignUpUserBinding;
import com.labbaiik.app.view.authentication.OtpVerificationActivity;

import java.io.File;

public class SignUpUserActivity extends AppCompatActivity {

    ActivitySignUpUserBinding signUpUserBinding;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpUserBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up_user);

        Window window = getWindow();
        WindowManager.LayoutParams winParams = window.getAttributes();
        winParams.flags &= ~WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        window.setAttributes(winParams);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        file = new File("");
        String[] genderArray = {"Male", "Female", "Other"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>
                (this, android.R.layout.select_dialog_item, genderArray);

        signUpUserBinding.spinnerGender.setAdapter(genderAdapter);

        signUpUserBinding.spinnerGender.setOnItemClickListener((parent, arg1, pos, id) -> {
            if (pos == 0)
                signUpUserBinding.btnChangeImage.setVisibility(View.VISIBLE);
            else
                signUpUserBinding.btnChangeImage.setVisibility(View.GONE);
        });

        signUpUserBinding.btnLogin.setOnClickListener(l -> {
                    startActivity(new Intent(SignUpUserActivity.this, LoginActivity.class));
                    finishAffinity();
                }
        );

        signUpUserBinding.btnSignUp.setOnClickListener(l -> startActivity(new Intent(SignUpUserActivity.this, WelcomeActivity.class))
        );

        signUpUserBinding.btnChangeImage.setOnClickListener(l -> {
            ImagePicker.Companion.with(this)
                    .cropSquare()                    //Crop image(Optional), Check Customization for more option
                    .compress(2000)            //Final image size will be less than 1 MB(Optional)
                    //  .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                    .start();
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            //  selectPhoto = true;
            Intent fileUri = data;

            Uri uri = data.getData();
            signUpUserBinding.profileImage.setImageURI(uri);
            //You can get File object from intent
            file = ImagePicker.Companion.getFile(data);

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Log.d("tanvir", "Task Cancelled");
        }
    }
}