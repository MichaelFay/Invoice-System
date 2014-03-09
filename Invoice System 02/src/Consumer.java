import java.util.ArrayList;


public class Consumer {

	private String customerCode, primaryContactCode, name, street, city, state, zip, country;
	private boolean isGov;
	private People primaryContact;
	
	public Consumer(String input){
		input.trim();
		
		String temp[] = input.split(";");
		
		
		String address[] = temp[4].split(",");
		
		this.customerCode = temp[0];
		
		
		
		if(temp[1].compareTo("G")==0)
			this.isGov = true;
		else
			this.isGov = false;
		
		
		this.primaryContactCode = temp[2];
		this.name = temp[3];
		
		this.street = address[0];
		this.city = address[1];
		this.state = address[2];
		this.zip = address[3];
		this.country = address[4];
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
