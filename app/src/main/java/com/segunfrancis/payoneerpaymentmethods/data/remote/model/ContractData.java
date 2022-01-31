package com.segunfrancis.payoneerpaymentmethods.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

@Data
public class ContractData implements Parcelable {
	private String pAGEENVIRONMENT;
	private String jAVASCRIPTINTEGRATION;
	private String pAGEBUTTONLOCALE;

	protected ContractData(Parcel in) {
		pAGEENVIRONMENT = in.readString();
		jAVASCRIPTINTEGRATION = in.readString();
		pAGEBUTTONLOCALE = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(pAGEENVIRONMENT);
		dest.writeString(jAVASCRIPTINTEGRATION);
		dest.writeString(pAGEBUTTONLOCALE);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<ContractData> CREATOR = new Creator<ContractData>() {
		@Override
		public ContractData createFromParcel(Parcel in) {
			return new ContractData(in);
		}

		@Override
		public ContractData[] newArray(int size) {
			return new ContractData[size];
		}
	};
}