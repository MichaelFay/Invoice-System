package com.cinco;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class Model {

	private People people;
	private Consumer consumer;
	private Products product;
	private Invoice invoice;
	protected InvoiceData sql = new InvoiceData();

	private ArrayList<People> peopleData;
	private ArrayList<Consumer> consumerData;
	private ArrayList<Products> productData;
	private ArrayList<Invoice> invoiceData;

	protected void setPeopleData(String location) throws IOException {
		peopleData = new ArrayList<People>();
		ArrayList<String> temp = getFile(location);
		for (int i = 0; i < temp.size(); i++) {
			people = new People(temp.get(i));
			peopleData.add(people);
			// System.out.println(people.toString());
		}
	}

	protected void setConsumerData(String location) throws IOException {
		consumerData = new ArrayList<Consumer>();
		ArrayList<String> temp = getFile(location);
		for (int i = 0; i < temp.size(); i++) {
			temp.get(i).trim();
			consumer = new Consumer(temp.get(i));
			consumer.setPrimaryContact(peopleData);
			consumerData.add(consumer);
			// System.out.println(consumer.toString());
		}
	}

	protected void setProductData(String location) throws IOException {
		productData = new ArrayList<Products>();
		ArrayList<String> temp = getFile(location);
		for (int i = 0; i < temp.size(); i++) {
			temp.get(i).trim();
			product = new Products(temp.get(i));
			product.setConsult(peopleData);
			productData.add(product);
		}
	}

	protected void setInvoiceData(String location) throws IOException {
		invoiceData = new ArrayList<Invoice>();

		ArrayList<String> temp = getFile(location);

		for (int i = 0; i < temp.size(); i++) {
			temp.get(i).trim();
			invoice = new Invoice(temp.get(i));

			try {
				invoice.setInvoiceData(peopleData, consumerData, productData);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			invoiceData.add(invoice);
		}

	}

	private ArrayList<String> getFile(String location) throws IOException {

		if (location.compareTo("data/Persons.dat") == 0)
			return getPersonSQL();
		else if (location.compareTo("data/Customers.dat") == 0)
			return getConsumerSQL();
		else if (location.compareTo("data/Products.dat") == 0)
			return getProductsSQL();
		else if (location.compareTo("data/Invoices.dat") == 0)
			return getInvoiceSQL();
		
		else 
			return null;

	}

	protected void printInvoice() {
		
		
		System.out.println(invoiceData.size());
		for (Invoice i : invoiceData) {
			i.printInvoice();
			System.out.println();
			System.out.println();
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

		Double total = 0.0, subTotal = 0.0, fees = 0.0, taxes = 0.0;

		System.out.println(String
				.format("Executive Sumary Report \n========================="));

		System.out.println(String
				.format("%-10s %-40s %-40s %-15s %-15s %-20s  $%-15s",
						"Invoice", "Customer", "Salesperson", "Subtotal",
						"Fees", "Taxes", "Total"));

		for (Invoice i : invoiceData) {

			System.out.println(String.format(
					"%-10s %-40s %-40s $%-15.2f $%-15.2f $%-15.2f  $%-15.2f",
					i.getInvoiceCode(), i.getConsumerName(),
					i.getSalesPersonName(), i.getSubtotal(),
					i.getComplianceFees(), i.getTaxes(), i.getGrandTotal()));
			total += i.getGrandTotal();
			subTotal += i.getSubtotal();
			fees += i.getFees();
			taxes += i.getTaxes();
		}
		System.out
				.println("============================================================================================================================================================");
		System.out.println(String.format(
				"%-92s $%-15.2f $%-15.2f $%-15.2f  $%-15.2f", "Totals",
				subTotal, fees, taxes, total));

	}

	protected void fileToSQL(String location) {
		ArrayList<String> file = new ArrayList<String>();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(location));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String line = null;
		int lineNumber = 0;

		try {
			while ((line = br.readLine()) != null) {
				line.trim();

				if (lineNumber != 0 && line.compareTo(" ") != 0)
					file.add(line);

				lineNumber++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < file.size(); i++){
			if (location.compareTo("data/Persons.dat") == 0){
				
			}
			else if (location.compareTo("data/Customers.dat") == 0){
				
			}
				
			else if (location.compareTo("data/Products.dat") == 0){
				
			}
				
			else if (location.compareTo("data/Invoices.dat") == 0){
				
			}
				
		}
		
	}

	private ArrayList<String> getPersonSQL() {
		ArrayList<String> file = new ArrayList<String>();
		Connection conn = getConn();
		try {
			String sql = "SELECT * FROM Persons";
			ResultSet rs = null;
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				StringBuilder sb = new StringBuilder();

				sb.append(rs.getString("PersonCode") + ";");
				sb.append(rs.getString("LastName") + ", ");
				sb.append(rs.getString("FirstName") + ";");
				sb.append(rs.getString("Street") + ",");
				sb.append(rs.getString("City") + ",");
				sb.append(rs.getString("State") + ",");
				sb.append(rs.getString("ZipCode") + ",");
				sb.append(rs.getString("Country") + ";");

				ResultSet emails = null;

				sql = "SELECT FirstName, PersonCode, Email FROM Emails JOIN Persons ON Emails.PersonID =  Persons.PersonID WHERE Persons.PersonCode = ?";

				ps = conn.prepareStatement(sql);

				ps.setString(1, rs.getString("PersonCode"));

				emails = ps.executeQuery();
				while (emails.next()) {
					sb.append(emails.getString("Email") + ",");

				}
				InvoiceData.closeConnections(null, null, emails);
				file.add(sb.toString());
			}
			InvoiceData.closeConnections(conn, ps, rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return file;
	}

	private ArrayList<String> getConsumerSQL() {

		ArrayList<String> file = new ArrayList<String>();

		Connection conn = getConn();

		try {
			String sql = "SELECT * FROM Customers";
			ResultSet rs = null;
			PreparedStatement ps = conn.prepareStatement(sql);

			rs = ps.executeQuery(sql);

			// C002;C;944c;Stark Industries;184 Marvel Way,New York,NY,10453,USA

			while (rs.next()) {

				StringBuilder sb = new StringBuilder();

				sb.append(rs.getString("CustomerCode") + ";");
				sb.append(rs.getString("IsGov") + ";");
				sb.append(rs.getString("PrimaryContactCode") + ";");
				sb.append(rs.getString("CompanyName") + ";");
				sb.append(rs.getString("Street") + ",");
				sb.append(rs.getString("City") + ",");
				sb.append(rs.getString("State") + ",");
				sb.append(rs.getString("ZipCode") + ",");
				sb.append(rs.getString("Country"));

				file.add(sb.toString());
			}
			
			InvoiceData.closeConnections(conn, ps, rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return file;
	}

	private ArrayList<String> getProductsSQL() {
		ArrayList<String> file = new ArrayList<String>();

		Connection conn = getConn();

		try {
			String sql = "SELECT * FROM Products";
			ResultSet rs = null;
			PreparedStatement ps = conn.prepareStatement(sql);

			rs = ps.executeQuery(sql);

			while (rs.next()) {

				StringBuilder sb = new StringBuilder();

				sb.append(rs.getString("ProductCode") + ";");

				if (rs.getString("ServiceFee").compareToIgnoreCase("0") == 0) {

					sb.append("E;");
					sb.append(rs.getString("ProductName") + ";");
					sb.append(rs.getString("PricePerUnit"));

				} else if (rs.getString("PricePerUnit")
						.compareToIgnoreCase("0") == 0) {
					sb.append("C;");
					sb.append(rs.getString("ProductName") + ";");
					sb.append(rs.getString("ConsultantPersonCode")+ ";");
					sb.append(rs.getString("ServiceFee"));

				} else if (rs.getString("ServiceFee").compareToIgnoreCase("0") != 0
						&& rs.getString("PricePerUnit")
								.compareToIgnoreCase("0") != 0) {

					sb.append("L;");
					sb.append(rs.getString("ProductName") + ";");
					sb.append(rs.getString("ServiceFee") + ";");
					sb.append(rs.getString("PricePerUnit"));

				} else
					sb.append("");

				file.add(sb.toString());
			}
			
			InvoiceData.closeConnections(conn, ps, rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return file;

	}

	private ArrayList<String> getInvoiceSQL() {

		ArrayList<String> file = new ArrayList<String>();

		Connection conn = getConn();

		try {
			String sql = "SELECT * FROM Invoice";
			ResultSet rs = null;
			PreparedStatement ps = conn.prepareStatement(sql);

			rs = ps.executeQuery(sql);

			while (rs.next()) {

				StringBuilder sb = new StringBuilder();

				sb.append(rs.getString("InvoiceCode") + ";");
				sb.append(rs.getString("CustomerCode") + ";");
				sb.append(rs.getString("SalesPersonCode") + ";");


				ResultSet products = null;

				sql = "SELECT StartDate, EndDate, ProductCode, ProductCount FROM InvoiceProducts JOIN Invoice ON Invoice.InvoiceID = InvoiceProducts.InvoiceID WHERE InvoiceCode = ?;";

				ps = conn.prepareStatement(sql);

				ps.setString(1, rs.getString("InvoiceCode"));

				products = ps.executeQuery();

				while (products.next()) {

					if (products.getString("StartDate").compareToIgnoreCase("0") != 0
							&& products.getString("StartDate").compareToIgnoreCase(
									"0") != 0) {

						sb.append(products.getString("ProductCode") + ":");
						sb.append(products.getString("StartDate") + ":");
						sb.append(products.getString("EndDate") + ",");

					}
					else if (products.getString("StartDate").compareToIgnoreCase("0") == 0 && products.getString("StartDate").compareToIgnoreCase("0") == 0){
						
						sb.append(products.getString("ProductCode") + ":");
						sb.append(products.getString("ProductCount") + ",");
						
					}
				

				}

				products.close();
				sb.replace(sb.length()-1, sb.length(), "");
				
				file.add(sb.toString());
				
				
//				System.out.println(sb.toString());

			}
			
			InvoiceData.closeConnections(conn, ps, rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		
		return file;

	}

	private static Connection getConn() {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			System.out.println("InstantiationException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.url,
					DatabaseInfo.username, DatabaseInfo.password);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return conn;
	}

}