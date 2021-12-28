package com.segunfrancis.payoneerpaymentmethods.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.segunfrancis.payoneerpaymentmethods.databinding.FragmentHomeBinding;
import com.segunfrancis.payoneerpaymentmethods.util.ErrorDialog;
import com.segunfrancis.payoneerpaymentmethods.util.ErrorUtils;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    private ErrorDialog dialog = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider((requireActivity())).get(HomeViewModel.class);
        setupObservers();
    }

    private void setupObservers() {
        viewModel.state.observe(getViewLifecycleOwner(), listPaymentMethodState -> {
            switch (listPaymentMethodState) {
                case SUCCESS:
                    handleSuccess();
                    break;
                case IN_PROGRESS:
                    handleLoading();
                    break;
                case ERROR:
                    handleError();
            }
        });
    }

    private void handleSuccess() {
        viewModel.response.observe(getViewLifecycleOwner(), paymentMethods -> {
            setupAdapter(paymentMethods);
            binding.progressIndicator.setVisibility(View.GONE);
        });
    }

    private void handleLoading() {
        binding.progressIndicator.setVisibility(View.VISIBLE);
    }

    private void handleError() {
        viewModel.error.observe(getViewLifecycleOwner(), error -> {
            binding.progressIndicator.setVisibility(View.GONE);
            if (dialog == null) {
                dialog = new ErrorDialog(requireContext(), ErrorUtils.handleThrowable(error), () -> viewModel.loadPaymentMethods());
            }
            dialog.show();
        });
    }

    private void setupAdapter(List<PaymentMethod> paymentMethods) {
        PaymentMethodAdapter adapter = new PaymentMethodAdapter();
        binding.paymentMethodRecyclerView.setAdapter(adapter);
        binding.paymentMethodRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter.submitList(paymentMethods);
    }
}
