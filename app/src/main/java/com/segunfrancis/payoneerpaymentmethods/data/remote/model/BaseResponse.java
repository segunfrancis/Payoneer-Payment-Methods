package com.segunfrancis.payoneerpaymentmethods.data.remote.model;

import lombok.Data;

@Data
public class BaseResponse {
    private String resultCode;
    private Networks networks;
    private String resultInfo;
    private ReturnCode returnCode;
    private Identification identification;
    private String integrationType;
    private Interaction interaction;
    private Links links;
    private String operationType;
    private Style style;
    private Payment payment;
    private String operation;
    private String timestamp;
    private Status status;
}