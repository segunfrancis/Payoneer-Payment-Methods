package com.segunfrancis.payoneerpaymentmethods.ui.home;

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

    public PaymentMethod(String code, String method, String label, String grouping, String logo) {
        this.code = code;
        this.method = method;
        this.label = label;
        this.grouping = grouping;
        this.logo = logo;
    }
}
