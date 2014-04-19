package com.cinco;

public class Equipment {

	private Double pricePerUnit;
	
	public Equipment(String input){
		this.pricePerUnit = Double.parseDouble(input);
	}
	
	protected Double getPricePerUnit(){
		return this.pricePerUnit;
	}
	
}
