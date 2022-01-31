package com.segunfrancis.payoneerpaymentmethods.ui.applicable_items;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.segunfrancis.payoneerpaymentmethods.R;
import com.segunfrancis.payoneerpaymentmethods.data.remote.model.ApplicableItem;
import com.segunfrancis.payoneerpaymentmethods.data.remote.model.InputElementsItem;
import com.segunfrancis.payoneerpaymentmethods.databinding.FragmentApplicableBinding;

import java.util.List;

public class ApplicableFragment extends Fragment {

    private FragmentApplicableBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentApplicableBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ApplicableItem item = ApplicableFragmentArgs.fromBundle(getArguments()).getApplicableItem();
        List<InputElementsItem> inputElements = item.getInputElements();
        if (inputElements != null) {
            inputElements.stream().forEach(element -> {
                TextInputLayout layout = new TextInputLayout(new ContextThemeWrapper(getContext(), R.style.Widget_MaterialComponents_TextInputLayout_FilledBox));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(
                        getResources().getDimensionPixelSize(R.dimen.default_margin),
                        getResources().getDimensionPixelSize(R.dimen.small_margin),
                        getResources().getDimensionPixelSize(R.dimen.default_margin),
                        getResources().getDimensionPixelSize(R.dimen.small_margin)
                );
                layout.setLayoutParams(layoutParams);
                layout.setMinimumHeight(getResources().getDimensionPixelSize(R.dimen.edit_text_height));
                layout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED);
                layout.setBoxBackgroundColor(ContextCompat.getColor(layout.getContext(), R.color.box_background_color));
                TextInputEditText editText = new TextInputEditText(layout.getContext());
                editText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                editText.setHint(element.getName());
                layout.addView(editText);
                binding.getRoot().addView(layout);
            });
        }
    }
}
