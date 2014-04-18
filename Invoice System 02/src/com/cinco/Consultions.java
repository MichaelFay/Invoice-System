package com.cinco;

import java.util.ArrayList;



public class Consultions {

	private String consultPersonCode;
	private People consult;
	private Double hourlyFee;
	
	public Consultions(String personCode, String hourlyFee){
		this.consultPersonCode = personCode;
		this.hourlyFee = Double.parseDouble(hourlyFee);
	}
	protected void setConsult(ArrayList<People> peopleData){
		
		for(int i = 1; i<peopleData.size(); i++){
			if(peopleData.get(i).getPersonCode().compareTo(consultPersonCode)==0)
				this.consult = peopleData.get(i);
		}
		
		
	}
	protected void setConsult(People p){
		this.consult = p;
		
	}

	protected Double getHourlyFee(){
		return this.hourlyFee;
	}
	protected People getConsult(){
		return this.consult;
	}
	protected String getConsultPersonCode(){
		return this.consultPersonCode;
	}

}
