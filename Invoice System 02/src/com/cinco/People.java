package com.cinco;


import java.util.ArrayList;


public class People implements Cloneable{
	
	
	@SuppressWarnings("unused")
	private String personCode, name, street, city, zip, state, country;
	
	private String nullFix[] = {"na", "Non-Found","","","","", ""};
	
	
	private ArrayList<String> emailAddress = new ArrayList<String>();

	protected People(String input){
		input.trim();
		String temp[] = input.split(";");

		this.personCode = temp[0];
		this.name = temp[1];

		String address[]= temp[2].split(",");

		this.street=address[0];
		this.city = address[1];
		this.state = address[2];
		this.zip = address[3];
		this.country = address[4];


		if(temp.length == 4){
			String emailTemp[] = temp[3].split(",");
			for(int i = 0; i<emailTemp.length; i++){
				emailAddress.add(emailTemp[i]);
			}
			

		}

	}
	protected People(Boolean b){
		this.personCode = nullFix[0];
		this.name = nullFix[1];
		this.street=nullFix[2];
		this.city = nullFix[3];
		this.state = nullFix[4];
		this.zip = nullFix[5];
		this.country = nullFix[6];
	}
	
	
	protected String getPersonCode(){
		return this.personCode;
	}
	protected String getName(){
		return this.name;
	}
	protected String getAddress(){
		return this.street + "," + this.city + "," + this.country + "," + this.zip + "," + this.country;
	}
	protected String getEmailAddress(){
		return emailAddress.toString();
	}

	@Override 
	public String toString(){
		return String.format("Name: %s\n Person Code: %s\n Address: %s\n Email Addresses: %s\n", this.getName(), this.getPersonCode(), this.getAddress(), this.getEmailAddress());
	}
}