package com.labbaiik.app.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.labbaiik.app.R;
import com.labbaiik.app.databinding.ActivityUserImageSetBinding;

import java.io.File;

public class UserImageSetActivity extends AppCompatActivity {

    ActivityUserImageSetBinding binding;
    private File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_image_set);

        binding.editImage.setOnClickListener(l -> {
            ImagePicker.Companion.with(this)
                    .cropSquare()                    //Crop image(Optional), Check Customization for more option
                    .compress(2000)            //Final image size will be less than 1 MB(Optional)
                    //  .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                    .start();
        });

        binding.btnSave.setOnClickListener(l -> {
            startActivity(new Intent(this, WelcomeActivity.class));
            finishAffinity();
        });

        binding.btnSkip.setOnClickListener(l -> {
            startActivity(new Intent(this, WelcomeActivity.class));
            finishAffinity();
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
            binding.profileImage.setImageURI(uri);
            //You can get File object from intent
            file = ImagePicker.Companion.getFile(data);

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Log.d("UserImageSetActivity", "Task Cancelled");
        }
    }
}