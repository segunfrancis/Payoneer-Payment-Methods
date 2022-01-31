package com.segunfrancis.payoneerpaymentmethods.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.segunfrancis.payoneerpaymentmethods.R;
import com.segunfrancis.payoneerpaymentmethods.data.remote.model.ApplicableItem;
import com.segunfrancis.payoneerpaymentmethods.data.remote.model.InputElementsItem;
import com.segunfrancis.payoneerpaymentmethods.databinding.ItemPaymentMethodBinding;
import com.segunfrancis.payoneerpaymentmethods.util.GlideUtils;

import java.util.List;

public class PaymentMethodAdapter extends ListAdapter<ApplicableItem, PaymentMethodAdapter.PaymentMethodViewHolder> {

    private OnItemClickListener listener;

    protected PaymentMethodAdapter(OnItemClickListener listener) {
        super(PaymentMethodAdapter.DIFF_UTIL);
        this.listener = listener;
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

     class PaymentMethodViewHolder extends RecyclerView.ViewHolder {

        private final ItemPaymentMethodBinding binding;

        public PaymentMethodViewHolder(@NonNull ItemPaymentMethodBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(@NonNull ApplicableItem applicableItem) {
            binding.paymentOptionLabel.setText(applicableItem.getLabel());
            GlideUtils.loadImage(binding.paymentOptionImage, applicableItem.getLinks().getLogo());
            binding.getRoot().setOnClickListener(v -> { listener.onClick(applicableItem); });
        }
    }

    public static final DiffUtil.ItemCallback<ApplicableItem> DIFF_UTIL = new DiffUtil.ItemCallback<ApplicableItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull ApplicableItem oldItem, @NonNull ApplicableItem newItem) {
            return oldItem.getCode().equals(newItem.getCode());
        }

        @Override
        public boolean areContentsTheSame(@NonNull ApplicableItem oldItem, @NonNull ApplicableItem newItem) {
            return oldItem.equals(newItem);
        }
    };

    interface OnItemClickListener {
        void onClick(ApplicableItem applicableItem);
    }
}
