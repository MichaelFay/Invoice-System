package com.cinco;

import java.util.ArrayList;

public class Consumer {

	@SuppressWarnings("unused")
	private String customerCode, primaryContactCode, name, street, state, city,
			zip, country;
	private boolean isGov;
	private People primaryContact;

	protected InvoiceData sql = new InvoiceData();

	public Consumer(String input) {
		input.trim();

		String temp[] = input.split(";");

		String address[] = temp[4].split(",");

		this.customerCode = temp[0];

		if (temp[1].compareToIgnoreCase("1") == 0) {
			this.isGov = true;
		} else {
			this.isGov = false;
		}

		this.primaryContactCode = temp[2];
		this.name = temp[3];

		this.street = address[0];
		this.city = address[1];
		this.state = address[2];
		this.zip = address[3];
		this.country = address[4];

	}

	public Consumer() {
		// TODO Auto-generated constructor stub
	}

	protected String getCustomerCode() {
		return this.customerCode;
	}

	protected String getPrimaryContactCode() {
		return this.primaryContactCode;
	}

	protected String getName() {
		return this.name;
	}

	protected String getAddress() {
		return this.street + "," + this.city + "," + this.country + ","
				+ this.zip + "," + this.country;
	}

	protected String getType() {
		if (isGov == true)
			return "G";
		else
			return "C";
	}

	protected void setPrimaryContactForce(People code) {
		this.primaryContact = code;

	}

	protected void setPrimaryContact(ArrayList<People> peopleData) {
		for (int i = 0; i < peopleData.size(); i++) {
			if (this.primaryContactCode.compareTo(peopleData.get(i)
					.getPersonCode()) == 0) {
				this.primaryContact = peopleData.get(i);
			}
		}
	}

	protected People getPrimaryContact() {
		return this.primaryContact;
	}

	/**
	 * 
	 * @return 
	 * Returns true if consumer is public sector
	 * Returns false if consumer is private sector
	 */
	protected Boolean getGov() {
		return this.isGov;
	}

	@Override
	public String toString() {
		if (primaryContact == null) {
			primaryContact = new People(true);
		}

		return String
				.format(" Name: %s\n Customer Code: %s\n Primary Contact Code %s\n Primary Contact: %s\n Type: %s\n Address: %s\n  ",
						this.getName(), this.customerCode, this
								.getPrimaryContactCode(), this
								.getPrimaryContact().getName(), this.getType(),
						this.getAddress());
	}

}