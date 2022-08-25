package com.dbs.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bank {
	@Id
//	@Column(length=11)
	private String bic;
	private String bankName;
	
	public Bank(String bic, String bankName) {
		super();
		this.bic = bic;
		this.bankName = bankName;
	}

	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Override
	public String toString() {
		return "Bank [bic=" + bic + ", bankName=" + bankName + "]";
	}
	
}
