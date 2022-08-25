package com.dbs.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Currency {
	@Id
	private String currencyCode;
	private String currencyName;
	private double conversionRate;
	
	public Currency(String currencyCode, String currencyName, double conversionRate) {
		super();
		this.currencyCode = currencyCode;
		this.currencyName = currencyName;
		this.conversionRate = conversionRate;
	}

	public Currency() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public double getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(double conversionRate) {
		this.conversionRate = conversionRate;
	}

	@Override
	public String toString() {
		return "Currency [currencyCode=" + currencyCode + ", currencyName=" + currencyName + ", conversionRate="
				+ conversionRate + "]";
	}
	
}
