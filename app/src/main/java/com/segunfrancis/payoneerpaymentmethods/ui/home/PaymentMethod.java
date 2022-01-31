package com.segunfrancis.payoneerpaymentmethods.ui.home;

import com.segunfrancis.payoneerpaymentmethods.data.remote.model.InputElementsItem;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class PaymentMethod {
    private String code;
    private String method;
    private String label;
    private String grouping;
    private String logo;
    private List<InputElementsItem> inputElementsItems;

    public PaymentMethod(String code, String method, String label, String grouping, String logo, List<InputElementsItem> inputElementsItems) {
        this.code = code;
        this.method = method;
        this.label = label;
        this.grouping = grouping;
        this.logo = logo;
        this.inputElementsItems = inputElementsItems;
    }
}
