package com.dbs.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TransferTypes {
	@Id
	private String transferTypeCode;
	private String transferTypeDescription;
	
	public TransferTypes(String transferTypeCode, String transferTypeDescription) {
		super();
		this.transferTypeCode = transferTypeCode;
		this.transferTypeDescription = transferTypeDescription;
	}

	public TransferTypes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTransferTypeCode() {
		return transferTypeCode;
	}

	public void setTransferTypeCode(String transferTypeCode) {
		this.transferTypeCode = transferTypeCode;
	}

	public String getTransferTypeDescription() {
		return transferTypeDescription;
	}

	public void setTransferTypeDescription(String transferTypeDescription) {
		this.transferTypeDescription = transferTypeDescription;
	}

	@Override
	public String toString() {
		return "TransferTypes [transferTypeCode=" + transferTypeCode + ", transferTypeDescription="
				+ transferTypeDescription + "]";
	}
	
}
