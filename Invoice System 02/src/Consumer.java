
import java.sql.SQLException;
import java.util.ArrayList;



public class Consumer {

	private String customerCode, primaryContactCode, name, street, city, state, zip, country;
	private boolean isGov;
	private People primaryContact;
	
	protected InvoiceData sql = new InvoiceData();

	public Consumer(String input){
		input.trim();

		String temp[] = input.split(";");


		String address[] = temp[4].split(",");

		this.customerCode = temp[0];

		
		String temps = "D";
		
		if(temp[1].compareTo("G")==0){
			this.isGov = true;
			temps = "G";
		}
		else{
			this.isGov = false;
			temps = "C";
		}


		this.primaryContactCode = temp[2];
		this.name = temp[3];

		this.street = address[0];
		this.city = address[1];
		this.state = address[2];
		this.zip = address[3];
		this.country = address[4];
		
//		
//		String customerCode, String type,
//		String primaryContactPersonCode, String name, String street,
//		String city, String state, String zip, String country

		
		sql.addCustomer(this.customerCode, temps, this.primaryContactCode, this.name, this.street, this.city, this.state, this.zip, this.country);
		
		try {
			sql.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected String getCustomerCode(){
		return this.customerCode;
	}
	protected String getPrimaryContactCode(){
		return this.primaryContactCode;
	}
	protected String getName(){
		return this.name;
	}
	protected String getAddress(){
		return this.street + "," + this.city + "," + this.country + "," + this.zip + "," + this.country;
	}
	protected String getType(){
		if(isGov == true)
			return "G";
		else
			return "C";
	}

	protected void setPrimaryContact(ArrayList<People> input){
		for(int i = 0; i < input.size(); i++){
			if(this.primaryContactCode.compareTo(input.get(i).getPersonCode())==0){
				this.primaryContact = input.get(i);
			}
		}
	}
	protected People getPrimaryContact(){
		return this.primaryContact;
	}
	protected Boolean getGov(){
		return this.isGov;
	}

	@Override
	public String toString(){
		return String.format(" Name: %s\n Customer Code: %s\n Primary Contact Code %s\n Primary Contact: %s\n Type: %s\n Address: %s\n  ", 
				this.getName(), this.customerCode, this.getPrimaryContactCode(), this.getPrimaryContact().getName(),  this.getType(), this.getAddress());
	}
}