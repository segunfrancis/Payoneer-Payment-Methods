package com.segunfrancis.payoneerpaymentmethods.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

@Data
public class Links implements Parcelable {
	private String self;
	private String lang;
	private String logo;
	private String operation;
	private String validation;

	protected Links(Parcel in) {
		self = in.readString();
		lang = in.readString();
		logo = in.readString();
		operation = in.readString();
		validation = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(self);
		dest.writeString(lang);
		dest.writeString(logo);
		dest.writeString(operation);
		dest.writeString(validation);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Links> CREATOR = new Creator<Links>() {
		@Override
		public Links createFromParcel(Parcel in) {
			return new Links(in);
		}

		@Override
		public Links[] newArray(int size) {
			return new Links[size];
		}
	};
}