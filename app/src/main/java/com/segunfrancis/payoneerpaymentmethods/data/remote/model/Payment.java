package com.segunfrancis.payoneerpaymentmethods.data.remote.model;

import lombok.Data;

@Data
public class Payment{
	private String reference;
	private int amount;
	private String currency;
}