package com.labbaiik.app.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.labbaiik.app.R;
import com.labbaiik.app.databinding.ActivitySignUpMuftiBinding;
import com.labbaiik.app.view.activity.MuftiImageSetActivity;
import com.labbaiik.app.view.authentication.OtpVerificationActivity;

import java.io.File;

public class SignUpMuftiActivity extends AppCompatActivity {

    ActivitySignUpMuftiBinding signUpMuftiBinding;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpMuftiBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up_mufti);

//        Window window = getWindow();
//        WindowManager.LayoutParams winParams = window.getAttributes();
//        winParams.flags &= ~WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//        window.setAttributes(winParams);
//        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);


        file = new File("");

        signUpMuftiBinding.btnSignIn.setOnClickListener(l -> {
                    startActivity(new Intent(SignUpMuftiActivity.this, LoginActivity.class));
                    finishAffinity();
                }
        );

        signUpMuftiBinding.btnNextCreate.setOnClickListener(l -> {
                   signUpMuftiBinding.createLayout.setVisibility(View.GONE);
                   signUpMuftiBinding.detailsLayout.setVisibility(View.VISIBLE);
                   signUpMuftiBinding.cardCreate.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.theme_color)));
                   signUpMuftiBinding.textCreate.setTextColor(getColor(R.color.theme_color));
                   signUpMuftiBinding.textDetails.setTextColor(getColor(R.color.color_signup_selection_text_bg_active));
                }
        );


        signUpMuftiBinding.btnSaveDetails.setOnClickListener(l -> {
                   signUpMuftiBinding.detailsLayout.setVisibility(View.GONE);
                   signUpMuftiBinding.qualificationLayout.setVisibility(View.VISIBLE);
                   signUpMuftiBinding.cardDetails.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.theme_color)));
                   signUpMuftiBinding.textDetails.setTextColor(getColor(R.color.theme_color));
                   signUpMuftiBinding.textQualifications.setTextColor(getColor(R.color.color_signup_selection_text_bg_active));
                }
        );

        signUpMuftiBinding.btnSkip.setOnClickListener(l->{
            signUpMuftiBinding.detailsLayout.setVisibility(View.GONE);
            signUpMuftiBinding.qualificationLayout.setVisibility(View.VISIBLE);
            signUpMuftiBinding.cardDetails.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.theme_color)));
            signUpMuftiBinding.textDetails.setTextColor(getColor(R.color.theme_color));
            signUpMuftiBinding.textQualifications.setTextColor(getColor(R.color.color_signup_selection_text_bg_active));
        });

        signUpMuftiBinding.btnSubmitToReview.setOnClickListener(l->{
            startActivity(new Intent(SignUpMuftiActivity.this, MuftiImageSetActivity.class));
            finishAffinity();
        });

//        signUpMuftiBinding.btnChangeImage.setOnClickListener(l -> {
//            ImagePicker.Companion.with(this)
//                    .cropSquare()                    //Crop image(Optional), Check Customization for more option
//                    .compress(2000)            //Final image size will be less than 1 MB(Optional)
//                    //  .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
//                    .start();
//        });
    }

    //    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK) {
//            //Image Uri will not be null for RESULT_OK
//            //  selectPhoto = true;
//            Intent fileUri = data;
//
//            Uri uri = data.getData();
//            signUpMuftiBinding.profileImage.setImageURI(uri);
//            //You can get File object from intent
//            file = ImagePicker.Companion.getFile(data);
//
//        } else if (resultCode == ImagePicker.RESULT_ERROR) {
//            Toast.makeText(this, ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
//        } else {
//            Log.d("tanvir", "Task Cancelled");
//        }
//    }
}