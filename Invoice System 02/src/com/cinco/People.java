package com.cinco;
import java.sql.SQLException;
import java.util.ArrayList;


public class People implements Cloneable{
	private String personCode, name, street, city, zip, state, country;

	private InvoiceData sql = new InvoiceData();
	
	private ArrayList<String> emailAddress = new ArrayList<String>();

	protected People(String input){
		input.trim();
		String temp[] = input.split(";");

		this.personCode = temp[0];
		this.name = temp[1];
		
		String subname[] = this.name.split(",");

		String address[]= temp[2].split(",");

		this.street=address[0];
		this.city = address[1];
		this.state = address[2];
		this.zip = address[3];
		this.country = address[4];
		
		//sql.addPerson(this.personCode, subname[1].trim(), subname[0].trim(), this.street, this.city, this.state, this.zip, this.country);
		
		
//		String personCode, String firstName,
//		String lastName, String street, String city, String state,
//		String zip, String country

		if(temp.length == 4){
			String emailTemp[] = temp[3].split(",");
			for(int i = 0; i<emailTemp.length; i++){
				emailAddress.add(emailTemp[i]);
				//sql.addEmail(this.personCode, emailAddress.get(i));
				
				
			}
			

		}
		
		 try {
				sql.conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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