package com.cinco;
import java.io.IOException;
import java.sql.SQLException;


public class Controller {

	String location[];
	static final Model data = new Model();
	
	protected Controller(String[] location){
		this.location = location;
	}
	
	protected void Start() throws IOException, SQLException{
		data.setPeopleData(location[0]);
		data.setConsumerData(location[1]);
		data.setProductData(location[2]);
		data.setInvoiceData(location[3]);
		data.printInvoice();
	}
	
}
