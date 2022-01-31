package com.segunfrancis.payoneerpaymentmethods.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import lombok.Data;

@Data
public class ApplicableItem implements Parcelable {

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

	protected ApplicableItem(Parcel in) {
		recurrence = in.readString();
		redirect = in.readByte() != 0;
		code = in.readString();
		method = in.readString();
		registration = in.readString();
		operationType = in.readString();
		label = in.readString();
		grouping = in.readString();
		selected = in.readByte() != 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(recurrence);
		dest.writeByte((byte) (redirect ? 1 : 0));
		dest.writeString(code);
		dest.writeString(method);
		dest.writeString(registration);
		dest.writeString(operationType);
		dest.writeString(label);
		dest.writeString(grouping);
		dest.writeByte((byte) (selected ? 1 : 0));
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<ApplicableItem> CREATOR = new Creator<ApplicableItem>() {
		@Override
		public ApplicableItem createFromParcel(Parcel in) {
			return new ApplicableItem(in);
		}

		@Override
		public ApplicableItem[] newArray(int size) {
			return new ApplicableItem[size];
		}
	};
}
