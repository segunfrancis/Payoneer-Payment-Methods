package com.segunfrancis.payoneerpaymentmethods.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

@Data
public class InputElementsItem implements Parcelable {
	private String name;
	private String type;

	protected InputElementsItem(Parcel in) {
		name = in.readString();
		type = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(type);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<InputElementsItem> CREATOR = new Creator<InputElementsItem>() {
		@Override
		public InputElementsItem createFromParcel(Parcel in) {
			return new InputElementsItem(in);
		}

		@Override
		public InputElementsItem[] newArray(int size) {
			return new InputElementsItem[size];
		}
	};
}