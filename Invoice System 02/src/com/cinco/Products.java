package com.cinco;
import java.util.ArrayList;


public class Products {
	private String code;
	private Character type; 
	private String name;
	private Equipment equip;
	private Consultions consult;
	private License lic;
	
	private InvoiceData sql = new InvoiceData();

	public Products(String input){
		 input.trim();
		 String temp[] = input.split(";");
		 if(temp[1].compareTo("E")==0)
			 setEquipment(temp);
		 else if(temp[1].compareTo("C")==0)
			 setConsultions(temp);
		 else if(temp[1].compareTo("L")==0)
			 setLicense(temp);

	 }

	private void setEquipment(String input[]){
		 this.code = input[0];
		 this.type = 'E';
		 this.name = input[2];
		 this.equip = new Equipment(input[3]); 
		 
//		 sql.addEquipment(this.code, this.name, this.equip.getPricePerUnit());
//		 
//		 try {
//			sql.conn.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	 }
	private void setConsultions(String input[]){
		 this.code = input[0];
		 this.type = 'C';
		 this.name = input[2];
		 this.consult = new Consultions(input[3], input[4]);
		 
//		 sql.addConsultation(this.code, this.name, this.consult.getConsultPersonCode(), this.consult.getHourlyFee());
//		 
//		 try {
//				sql.conn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	}
	private void setLicense(String input[]){
		this.code = input[0];
		this.type = 'L';
		this.name = input[2];
		this.lic = new License(input[3], input[4]);
		
//		sql.addLicense(this.code, this.name, this.lic.getServiceFee(), this.lic.getAnnualFee());
//		
//		 try {
//				sql.conn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		
	}

	protected String getType(){
		if(type.compareTo('E')==0)
			return "Equipment";
		else if (type.compareTo('C')==0)
			return "Consultions";
		else if(type.compareTo('L')==0)
			return "License";
		else
			return null;
	}
	protected void setConsult(ArrayList<People> peopleData){
		if(type.compareTo('C')==0)
			consult.setConsult(peopleData);
	}

	@Override
	public String toString(){
		if(type.compareTo('E')==0)
			return String.format(" Name: %s\t Code: %s\n Type: %s\n PPU: %s\n",
					this.name, this.code, getType(), equip.getPricePerUnit());

		else if (type.compareTo('C')==0)
			return String.format(" Name: %s\t Code: %s\n Type: %s\n Consult: %s       Consult Code: %s\n HourlyFee: %f\n", 
					this.name, this.code, getType(), consult.getConsult().getName(), consult.getConsultPersonCode(), consult.getHourlyFee());

		else if(type.compareTo('L')==0)
			return String.format(" Name: %s\t Code: %s\n Type: %s\n ServiceFee: %f\n Annual Fee: %f\t Daily Fee: %f\n",
					this.name, this.code, getType(), lic.getServiceFee(), lic.getAnnualFee(), lic.getDailyFee());

		else
			return null;
	}

	protected String getAmount(){
		if(type.compareTo('E')==0)
			return equip.getPricePerUnit() + "";
		else if (type.compareTo('C')==0)
			return "" + consult.getHourlyFee();
		else if(type.compareTo('L')==0)
			return "" + lic.getDailyFee();
		else
			return null;
	}

	protected String getName(){
		return this.name;
	}

	protected Double getServiceFee(){
		if(type.compareTo('L')==0)
			return lic.getServiceFee();
		else
			return null;
	}

	protected String getCode(){
		return this.code;
	}

	protected Double getPrice(){

		if(type.compareTo('E')==0)
			return equip.getPricePerUnit();
		else if (type.compareTo('C')==0)
			return consult.getHourlyFee();
		else if(type.compareTo('L')==0)
			return lic.getDailyFee();
		else
			return null;
	}
}