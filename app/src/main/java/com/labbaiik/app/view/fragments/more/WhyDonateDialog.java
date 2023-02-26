package com.labbaiik.app.view.fragments.more;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.labbaiik.app.R;
import com.labbaiik.app.view.SignUpMuftiActivity;
import com.labbaiik.app.view.SignUpUserActivity;

public class WhyDonateDialog extends BottomSheetDialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.popup_why_donate,
                container, false);
        return v;
    }
}