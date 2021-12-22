package com.segunfrancis.payoneerpaymentmethods.data.remote.model;

import java.util.List;
import lombok.Data;

@Data
public class ApplicableItem{
	private String recurrence;
	private boolean redirect;
	private String code;
	private String method;
	private String registration;
	private Links links;
	private String operationType;
	private String label;
	private String grouping;
	private boolean selected;
	private List<InputElementsItem> inputElements;
	private ContractData contractData;
}