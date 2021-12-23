package com.segunfrancis.payoneerpaymentmethods.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.segunfrancis.payoneerpaymentmethods.R;
import com.segunfrancis.payoneerpaymentmethods.databinding.ItemPaymentMethodBinding;
import com.segunfrancis.payoneerpaymentmethods.util.GlideUtils;

public class PaymentMethodAdapter extends ListAdapter<PaymentMethod, PaymentMethodAdapter.PaymentMethodViewHolder> {

    protected PaymentMethodAdapter() {
        super(PaymentMethodAdapter.DIFF_UTIL);
    }

    @NonNull
    @Override
    public PaymentMethodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PaymentMethodViewHolder(
                ItemPaymentMethodBinding.bind(LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_payment_method, parent, false))
        );
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class PaymentMethodViewHolder extends RecyclerView.ViewHolder {

        private final ItemPaymentMethodBinding binding;

        public PaymentMethodViewHolder(@NonNull ItemPaymentMethodBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(@NonNull PaymentMethod method) {
            binding.paymentOptionLabel.setText(method.getLabel());
            GlideUtils.loadImage(binding.paymentOptionImage, method.getLogo());
            binding.getRoot().setOnClickListener(v -> {});
        }
    }

    public static final DiffUtil.ItemCallback<PaymentMethod> DIFF_UTIL = new DiffUtil.ItemCallback<PaymentMethod>() {
        @Override
        public boolean areItemsTheSame(@NonNull PaymentMethod oldItem, @NonNull PaymentMethod newItem) {
            return oldItem.getCode().equals(newItem.getCode());
        }

        @Override
        public boolean areContentsTheSame(@NonNull PaymentMethod oldItem, @NonNull PaymentMethod newItem) {
            return oldItem.equals(newItem);
        }
    };
}
