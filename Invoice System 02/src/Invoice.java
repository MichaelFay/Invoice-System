
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;



public class Invoice {

	private String invoiceCode, customerCode, salesPersonCode;
	private People salesPerson;
	private Consumer consumer;
	private InvoiceProductData product;
	
	private ArrayList<String> productTemp;
	private HashMap<Products, String> products;
	
	private Double subtotal, complianceFee, taxes, fees, grandTotal; 
	
	public Invoice(String input){
		input.trim();
		String temp[] = input.split(";");
		this.invoiceCode = temp[0];
		this.customerCode = temp[1];
		this.salesPersonCode = temp[2];
		productTemp = new ArrayList<String>();
		setProductTemp(temp[3]);
		
		
		this.subtotal = 0.0;
		this.complianceFee = 0.0;
		this.taxes = 0.0;
		this.fees = 0.0;
		this.grandTotal = 0.0;
	}
	
	private void setProductTemp(String input){
		String temp[] = input.split(",");
		for (int i = 0; i < temp.length; i++) {
			productTemp.add(temp[i]);
		}
	}
	
	protected void setInvoiceData(ArrayList<People> peopleData, ArrayList<Consumer> consumerData, ArrayList<Products> productData) throws ParseException{
		
		setSalesPerson(peopleData);
		setConsumer(consumerData);
		setProducts(productData);
	}
	
	private void setSalesPerson(ArrayList<People> peopleData){
		
		for(int i = 0; i<peopleData.size(); i++){
			if(peopleData.get(i).getPersonCode().compareTo(salesPersonCode)==0)
				this.salesPerson = peopleData.get(i);
		}
		
	}
	
	private void setConsumer(ArrayList<Consumer> consumerData){
		
		for(int i = 0; i<consumerData.size(); i++){
			if(consumerData.get(i).getCustomerCode().compareTo(customerCode)==0)
				this.consumer = consumerData.get(i);
		}
		
	}
	
	private void setProducts(ArrayList<Products> productData) throws ParseException{
		products = new HashMap<Products, String>();
		
		for(int i = 0; i< productTemp.size(); i++){
			String temp[] = productTemp.get(i).split(":");
			
			if(temp.length == 3){
				 products.put(getProduct(temp[0], productData), Double.toString(calculateDays(temp[1], temp[2])));
				 
			}
			else if(temp.length == 2){
				products.put(getProduct(temp[0], productData), temp[1]);
			}
			
			
		}
		
	}
	
	protected void printInvoice(){
		toString();
		for (Products key : products.keySet()) {
			printResults(key);
		}
		
		printFinalResults();
	}

	private int calculateDays(String date1, String date2) throws ParseException{
    
	    return Days.daysBetween(new LocalDate(date1), new LocalDate(date2)).getDays();
	}

	private Products getProduct(String productCode, ArrayList<Products> productData){
		
		for(int i = 0; i<productData.size(); i++){
			if(productData.get(i).getCode().compareTo(productCode)==0)
				return productData.get(i);
		}
			
		
		return null;
	}

	@Override
	public String toString(){
		
		Double fee, total; 
		
		System.out.println("Invoice " + this.invoiceCode);
		System.out.println("==============");
		
		System.out.println("Salesperson: " + salesPerson.getName());
		System.out.println(String.format("Customer Info:\n\t %s (%s) \n\t %s\n\t %s\n\t ", consumer.getName(), consumer.getCustomerCode(), consumer.getPrimaryContact().getName(), consumer.getAddress()));

		
		System.out.println("------------------------------------------------------------");
		
		System.out.println(String.format("%-4s \t %-60s %-4s  %-16s  %-8s %-8s", "Code", "Item", "Amount", "Price-Per-Unit", "Fee", "Total" ));
		
		
		return null;
	}
	
	private void printResults(Products key) {
		double amount = Double.parseDouble(products.get(key));
		double price =  (key.getPrice()*amount);
		
		double fee = 0;
		
		if(key.getType().compareTo("Consultions")!=0)
			 fee = 0.0;
		else
			 this.fees += 150;
		
		if(key.getType().compareTo("Equipment")==0)
			this.taxes += price * 0.07;
		else 
			this.taxes += price * 0.0425;
		
		String s = new String();
		s = String.format("%-4s \t %-60s %-4d  $%10.2f  $%10.2f $%10.2f", key.getCode(), key.getName(), (int) amount, key.getPrice(), fee,  price);
		
		this.subtotal += price;
		//this.fees += fee ;
		
		if(this.consumer.getGov() == true){
			this.complianceFee =150.0;
			System.out.println(key.getType());
			this.taxes = 0.0;
		}
		
		
		System.out.println(s);
		
	}

	private void printFinalResults(){
		this.taxes += 0;
		
		this.grandTotal = this.subtotal + this.complianceFee + this.fees + this.taxes;
		
		String s = new String();
		s = String.format("%-8s  %102s", "","==================");
		System.out.println(s);
		s = String.format("%-8s  %90s $%10.2f", "Sub-Total", "", this.subtotal);
		System.out.println(s);
		s = String.format("%-8s  %85s $%10.2f", "Compliance Fee", "", this.complianceFee);
		System.out.println(s);
		s = String.format("%-8s  %90s $%10.2f", "Taxes", "", this.taxes);
		System.out.println(s);
		s = String.format("%-8s  %90s $%10.2f", "Fees", "", this.fees);
		System.out.println(s);
		s = String.format("%-8s  %90s $%10.2f", "Total", "", this.grandTotal);
		System.out.println(s);
	}

	
	protected String getInvoiceCode() {
		return this.invoiceCode;
	}

	
	protected String getSalesPersonName() {
		return this.salesPerson.getName();
	}

	protected String getConsumerName() {
		return this.consumer.getName();
	}

	
	protected Double getSubtotal() {
		return this.subtotal;
	}

	protected Double getTaxes() {
		return this.taxes;
	}

	
	protected Double getFees() {
		return this.fees;
	}
	protected Double getComplianceFees(){
		return this.complianceFee;
	}

	protected Double getGrandTotal() {
		return this.grandTotal;
	}

	
	
	
}





