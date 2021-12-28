package com.segunfrancis.payoneerpaymentmethods.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.segunfrancis.payoneerpaymentmethods.databinding.DialogErrorLayoutBinding;

public class ErrorDialog extends Dialog {

    private final OnCallbackClickListener listener;
    private final String errorDescription;

    public ErrorDialog(@NonNull Context context, String errorDescription, OnCallbackClickListener listener) {
        super(context);
        this.listener = listener;
        this.errorDescription = errorDescription;
    }

    private DialogErrorLayoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DialogErrorLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCanceledOnTouchOutside(false);
        setCancelable(true);

        setupClickListeners();
        setupUI();
    }

    private void setupClickListeners() {
        binding.actionButton.setOnClickListener(v -> {
            listener.onClick();
            dismiss();
        });
        binding.crossForeground.setOnClickListener(v -> dismiss());
    }

    private void setupUI() {
        binding.errorDescription.setText(errorDescription);
    }

    public interface OnCallbackClickListener {
        void onClick();
    }
}
