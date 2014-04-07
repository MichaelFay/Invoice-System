package com.cinco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 * 
 */
public class InvoiceData {



	public static void removeAllEmails() {

		try {
			Connection conn = getConn();

			String sql = "SET SQL_SAFE_UPDATES = 0";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "SET FOREIGN_KEY_CHECKS=0";
			ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "DELETE FROM Emails";
			ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "SET FOREIGN_KEY_CHECKS=1";
			ps = conn.prepareStatement(sql);
			ps.execute();

			ps.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void removeAllInvoiceProducts() {
		try {
			Connection conn = getConn();

			String sql = "SET SQL_SAFE_UPDATES = 0";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "SET FOREIGN_KEY_CHECKS=0";
			ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "DELETE FROM InvoiceProducts";
			ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "SET FOREIGN_KEY_CHECKS=1";
			ps = conn.prepareStatement(sql);
			ps.execute();

			ps.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method that removes every person record from the database
	 */
	public static void removeAllPersons() {

		try {
			Connection conn = getConn();

			String sql = "SET SQL_SAFE_UPDATES = 0";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "SET FOREIGN_KEY_CHECKS=0";
			ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "DELETE FROM Persons";
			ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "SET FOREIGN_KEY_CHECKS=1";
			ps = conn.prepareStatement(sql);
			ps.execute();

			ps.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Removes the person record from the database corresponding to the provided
	 * <code>personCode</code>
	 * 
	 * @param personCode
	 */
	public void removePerson(String personCode) {

		try {
			Connection conn = getConn();

			String sql = "SET SQL_SAFE_UPDATES = 0";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "SET FOREIGN_KEY_CHECKS=0";
			ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "DELETE FROM Persons where PersonCode = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, personCode);
			ps.execute();

			getRequested("Persons", "FirstName");

			sql = "SET FOREIGN_KEY_CHECKS=1";
			ps = conn.prepareStatement(sql);
			ps.execute();

			ps.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Method to add a person record to the database with the provided data.
	 * 
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String firstName,
			String lastName, String street, String city, String state,
			String zip, String country) {

		if (checkUniqueness(personCode, "Persons", "PersonCode") == false
				|| personCode == null || firstName == null || lastName == null
				|| street == null || city == null || state == null
				|| zip == null || country == null)
			return;

		// INSERT INTO Persons (`FirstName`, `LastName`, `PersonCode`, `Street`,
		// `City`, `State`, `ZipCode`, `Country`)
		// VALUES ('Michael','Marsh','3456','326 17th
		// St.','Lincoln','Nebraksa','68508','USA');

		try {
			
			Connection conn = getConn();

			String sql = "INSERT INTO Persons (`FirstName`, `LastName`, `PersonCode`, `Street`, `City`, `State`, `ZipCode`, `Country`)"
					+ "VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, personCode);
			ps.setString(4, street);
			ps.setString(5, city);
			ps.setString(6, state);
			ps.setString(7, zip);
			ps.setString(8, country);

			ps.executeUpdate();

			getRequested("Persons", "LastName");

			ps.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {

		if (personCode == null || email == null
				|| checkUniqueness(email, "Emails", "Email") == false)
			return;

		try {
			
			Connection conn = getConn();

			String sql = "INSERT INTO Emails (`PersonID`, `Email`) "
					+ "VALUE ((SELECT PersonID FROM Persons WHERE PersonCode = ?), ?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, personCode);
			ps.setString(2, email);

			ps.executeUpdate();

			getRequested("Emails", "EmailID");

			ps.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Method that removes every customer record from the database
	 */
	public static void removeAllCustomers() {

		try {
			
			Connection conn = getConn();

			String sql = "SET SQL_SAFE_UPDATES = 0";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "SET FOREIGN_KEY_CHECKS=0";
			ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "DELETE FROM Customers";
			ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "SET FOREIGN_KEY_CHECKS=1";
			ps = conn.prepareStatement(sql);
			ps.execute();

			ps.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void addCustomer(String customerCode, String type,
			String primaryContactPersonCode, String name, String street,
			String city, String state, String zip, String country) {

		if (checkUniqueness(customerCode, "Customers", "CustomerCode") == false
				|| type == null || primaryContactPersonCode == null
				|| name == null || street == null || city == null
				|| state == null || zip == null || country == null)
			return;

		try {
			
			Connection conn = getConn();

			String sql = "INSERT INTO Customers (`CustomerCode`, `PrimaryContactCode`, `CompanyName`, `Street`, `City`, `State`, `ZipCode`, `Country`, `IsGov`)"
					+ "VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, customerCode);
			ps.setString(2, primaryContactPersonCode);
			ps.setString(3, name);
			ps.setString(4, street);
			ps.setString(5, city);
			ps.setString(6, state);
			ps.setString(7, zip);
			ps.setString(8, country);
			ps.setString(9, type);

			ps.executeUpdate();

			getRequested("Customers", "CompanyName");

			ps.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Removes all product records from the database
	 */
	public static void removeAllProducts() {
		try {
			Connection conn = getConn();

			String sql = "SET SQL_SAFE_UPDATES = 0";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "SET FOREIGN_KEY_CHECKS=0";
			ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "DELETE FROM Products";
			ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "SET FOREIGN_KEY_CHECKS=1";
			ps = conn.prepareStatement(sql);
			ps.execute();

			ps.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Removes a particular product record from the database corresponding to
	 * the provided <code>productCode</code>
	 * 
	 * @param assetCode
	 */
	public static void removeProduct(String productCode) {

		try {
			Connection conn = getConn();

			String sql = "SET SQL_SAFE_UPDATES = 0";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "SET FOREIGN_KEY_CHECKS=0";
			ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "DELETE FROM Products where ProductCode = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, productCode);
			ps.execute();

			getRequested("Products", "ProductName");

			sql = "SET FOREIGN_KEY_CHECKS=1";
			ps = conn.prepareStatement(sql);
			ps.execute();

			ps.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Adds an equipment record to the database with the provided data.
	 */
	public static void addEquipment(String productCode, String name,
			Double pricePerUnit) {

		if (checkUniqueness(productCode, "Products", "ProductCode") == false
				|| productCode == null || name == null || pricePerUnit == null
				|| pricePerUnit < 0)
			return;

		try {
			
			Connection conn = getConn();

			String sql = "INSERT INTO Products (`ProductCode`, `ProductName`, `ProductType`, `ServiceFee`, `PricePerUnit`) "
					+ "VALUES (?,?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, productCode);
			ps.setString(2, name);
			ps.setString(3, "Equipment");
			ps.setString(4, "0");
			ps.setString(5, String.valueOf(pricePerUnit));

			ps.executeUpdate();

			getRequested("Products", "ProductName");

			ps.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Adds an license record to the database with the provided data.
	 */
	public static void addLicense(String productCode, String name,
			double serviceFee, double annualFee) {
		if (checkUniqueness(productCode, "Products", "ProductCode") == false
				|| productCode == null || name == null || serviceFee < 0
				|| annualFee < 0)
			return;

		try {
			
			Connection conn = getConn();

			String sql = "INSERT INTO Products (`ProductCode`, `ProductName`, `ProductType`, `ServiceFee`, `PricePerUnit`) "
					+ "VALUES (?,?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, productCode);
			ps.setString(2, name);
			ps.setString(3, "License");
			ps.setString(4, String.valueOf(serviceFee));
			ps.setString(5, String.valueOf(annualFee));

			ps.executeUpdate();

			getRequested("Products", "ProductName");

			ps.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Adds an consultation record to the database with the provided data.
	 */
	public static void addConsultation(String productCode, String name,
			String consultantPersonCode, Double hourlyFee) {

		if (checkUniqueness(productCode, "Products", "ProductCode") == false
				|| productCode == null || name == null || hourlyFee < 0
				|| consultantPersonCode == null)
			return;

		try {
			
			Connection conn = getConn();

			String sql = "INSERT INTO Products (`ProductCode`, `ProductName`, `ConsultantPersonCode`,`ProductType`, `ServiceFee`, `PricePerUnit`) "
					+ "VALUES (?,?,?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, productCode);
			ps.setString(2, name);
			ps.setString(3, consultantPersonCode);
			ps.setString(4, "Consultant");
			ps.setString(5, String.valueOf(hourlyFee));
			ps.setString(6, "0");

			ps.executeUpdate();

			getRequested("Products", "ProductName");

			ps.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {
		
		Connection conn = getConn();

		try {

			String sql = "SET SQL_SAFE_UPDATES = 0";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "SET FOREIGN_KEY_CHECKS=0";
			ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "DELETE FROM Invoice";
			ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "DELETE FROM InvoiceProducts";
			ps = conn.prepareStatement(sql);
			ps.execute();

			sql = "SET FOREIGN_KEY_CHECKS=1";
			ps = conn.prepareStatement(sql);
			ps.execute();

			ps.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Removes the invoice record from the database corresponding to the
	 * provided <code>invoiceCode</code>
	 * 
	 * @param invoiceCode
	 */
	public static void removeInvoice(String invoiceCode) {

		int ID = 0;
		Connection conn = getConn();

		try {

			String Query = "SELECT InvoiceID FROM Invoice WHERE InvoiceCode = ?";
			PreparedStatement ps = conn.prepareStatement(Query);
			ps.setString(1, invoiceCode);
			ResultSet rs = null;

			rs = ps.executeQuery();

			while (rs.next()) {
				ID = rs.getInt("InvoiceID");
			}

			String sql = "DELETE FROM InvoiceProducts WHERE InvoiceID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ID);
			ps.execute();

			sql = "DELETE FROM Invoice WHERE InvoiceCode = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, invoiceCode);
			ps.execute();

			ps.close();
			rs.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Adds an invoice record to the database with the given data.
	 */
	public static void addInvoice(String invoiceCode, String customerCode,
			String salesPersonCode) {
		

		// INSERT INTO Invoice (`InvoiceCode`,`CustomerCode`,`SalesPersonCode`)
		// VALUES ('C001','2345','1278');

		if (checkUniqueness(invoiceCode, "Invoice", "InvoiceCode") == false
				|| invoiceCode == null || customerCode == null
				|| salesPersonCode == null)
			return;
		
		Connection conn = getConn();

		try {

			String sql = "INSERT INTO Invoice (`InvoiceCode`,`CustomerCode`,`SalesPersonCode`) "
					+ "VALUES (?,?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, invoiceCode);
			ps.setString(2, customerCode);
			ps.setString(3, salesPersonCode);

			ps.executeUpdate();

			getRequested("Invoice", "InvoiceCode");

			ps.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Adds a particular equipment (corresponding to <code>productCode</code> to
	 * an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of units
	 */
	public static void addEquipmentToInvoice(String invoiceCode,
			String productCode, int numUnits) {
	

		if (invoiceCode == null || productCode == null || numUnits < 0)
			return;

		try {
			
			Connection conn = getConn();

			String sql = "INSERT INTO InvoiceProducts (`InvoiceID`,`StartDate`,`EndDate`,`ProductCode`,`ProductCount`) "
					+ "VALUES ((SELECT InvoiceID FROM Invoice WHERE InvoiceCode = ?),?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, invoiceCode);
			ps.setString(2, "0");
			ps.setString(3, "0");
			ps.setString(4, productCode);
			ps.setString(5, String.valueOf(numUnits));

			ps.executeUpdate();

			getRequested("InvoiceProducts", "InvoiceID");

			ps.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Adds a particular equipment (corresponding to <code>productCode</code> to
	 * an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given begin/end dates
	 */
	public static void addLicenseToInvoice(String invoiceCode,
			String productCode, String startDate, String endDate) {
		

		if (invoiceCode == null || productCode == null || startDate == null
				|| endDate == null)
			return;

		try {

			Connection conn = getConn();
			
			String sql = "INSERT INTO InvoiceProducts (`InvoiceID`,`StartDate`,`EndDate`,`ProductCode`,`ProductCount`) "
					+ "VALUES ((SELECT InvoiceID FROM Invoice WHERE InvoiceCode = ?),?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, invoiceCode);
			ps.setString(2, startDate);
			ps.setString(3, endDate);
			ps.setString(4, productCode);
			ps.setString(5, "0");

			ps.executeUpdate();

			getRequested("InvoiceProducts", "InvoiceID");

			ps.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Adds a particular equipment (corresponding to <code>productCode</code> to
	 * an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of billable hours.
	 */
	public static void addConsultationToInvoice(String invoiceCode,
			String productCode, double numHours) {
		

		if (invoiceCode == null || productCode == null || numHours < 0)
			return;

		try {
			Connection conn = getConn();
			
			String sql = "INSERT INTO InvoiceProducts (`InvoiceID`,`StartDate`,`EndDate`,`ProductCode`,`ProductCount`) "
					+ "VALUES ((SELECT InvoiceID FROM Invoice WHERE InvoiceCode = ?),?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, invoiceCode);
			ps.setString(2, "0");
			ps.setString(3, "0");
			ps.setString(4, productCode);
			ps.setString(5, String.valueOf(numHours));

			ps.executeUpdate();

			getRequested("InvoiceProducts", "InvoiceID");

			ps.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void getRequested(String table, String col) {

		Connection conn = getConn();
		try {

			conn = DriverManager.getConnection(DatabaseInfo.url,
					DatabaseInfo.username, DatabaseInfo.password);

			String Query = "Select * FROM " + table.trim();
			PreparedStatement ps = conn.prepareStatement(Query);
			ResultSet rs = null;

			rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString(col));
			}

			ps.close();
			rs.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static boolean checkUniqueness(String ident, String table,
			String col) {
		
		Connection conn = getConn();

		try {

			String Query = "Select * FROM " + table.trim();
			PreparedStatement ps = conn.prepareStatement(Query);
			ResultSet rs = null;

			rs = ps.executeQuery();

			while (rs.next()) {
				if (rs.getString(col).toString().compareTo(ident) == 0)
					return false;
			}

			ps.close();
			rs.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
	
	private static Connection getConn(){
		
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
			conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return conn;
	}
}
