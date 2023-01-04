package com.labbaiik.app.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.labbaiik.app.R;
import com.labbaiik.app.view.SignUpMuftiActivity;
import com.labbaiik.app.view.SignUpUserActivity;

public class SignUpSelectionDialog extends BottomSheetDialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.signup_selection,
                container, false);

        MaterialCardView userCard = v.findViewById(R.id.user_card);
        MaterialCardView muftiCard = v.findViewById(R.id.mufti_card);
        MaterialButton btnContinue = v.findViewById(R.id.btn_continue);

        userCard.setOnClickListener(l ->{
            if (userCard.isChecked())
                userCard.setChecked(false);
            else
                userCard.setChecked(true);
            if (muftiCard.isChecked())
                muftiCard.setChecked(false);
        });

        muftiCard.setOnClickListener(l ->{
            if (muftiCard.isChecked())
                muftiCard.setChecked(false);
            else
                muftiCard.setChecked(true);
            if (userCard.isChecked())
                userCard.setChecked(false);
        });

        btnContinue.setOnClickListener(l -> {
            if (userCard.isChecked())
                startActivity(new Intent(getActivity(), SignUpUserActivity.class));
            else if (muftiCard.isChecked())
                startActivity(new Intent(getActivity(), SignUpMuftiActivity.class));
            else
                Toast.makeText(getContext(), "Please choose user type.", Toast.LENGTH_SHORT).show();
        });

        return v;
    }
}