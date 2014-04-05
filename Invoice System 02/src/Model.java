import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

	protected Model(){


	}

	protected void setPeopleData(String location) throws IOException{
		peopleData = new ArrayList<People>();

		ArrayList<String> temp = getFile(location);

		for(int i = 0; i<temp.size();i++ ){
			people = new People(temp.get(i));
			peopleData.add(people);
			//System.out.println(people.toString());
		}

	}

	protected void setConsumerData(String location) throws IOException{
		consumerData = new ArrayList<Consumer>();

		ArrayList<String> temp = getFile(location);

		for(int i = 0; i<temp.size();i++ ){
			temp.get(i).trim();
			consumer = new Consumer(temp.get(i));
			consumer.setPrimaryContact(peopleData);	
			consumerData.add(consumer);
			//System.out.println(consumer.toString());
		}

	}

	protected void setProductData(String location) throws IOException{
		productData = new ArrayList<Products>();
		ArrayList<String> temp = getFile(location);

		for(int i = 0; i<temp.size(); i++){
			temp.get(i).trim();
			product = new Products(temp.get(i));
			product.setConsult(peopleData);
			productData.add(product);
		}
	}

	protected void setInvoiceData(String location) throws IOException{
		invoiceData = new ArrayList<Invoice>();

		ArrayList<String> temp = getFile(location);

		for(int i = 0; i<temp.size(); i++){
			temp.get(i).trim();
			invoice = new Invoice(temp.get(i));

			try {
				invoice.setInvoiceData(peopleData, consumerData, productData);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			invoiceData.add(invoice);
		}

	}

	private ArrayList<String> getFile(String location) throws IOException{

			ArrayList<String> file = new ArrayList<String>();

			BufferedReader br = new BufferedReader(new FileReader(location));

			String line = null;
			int lineNumber = 0;

			while((line = br.readLine()) != null){
				line.trim();

				if(lineNumber != 0 && line.compareTo(" ") !=0)
					file.add(line);

				lineNumber++;
			}


			return file;
		}


	protected void printInvoice(){
		for(Invoice i : invoiceData){
			i.printInvoice();
			System.out.println();
			System.out.println();
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		ArrayList<String> invoiceMaster = new ArrayList<String>();
		Double total = 0.0, subTotal = 0.0, fees = 0.0, taxes = 0.0;

		System.out.println(String.format("Executive Sumary Report \n========================="));

		System.out.println(String.format("%-10s %-40s %-40s %-15s %-15s %-20s  $%-15s", "Invoice", "Customer", "Salesperson", "Subtotal", "Fees", "Taxes", "Total"));





		for(Invoice i : invoiceData){

			System.out.println(String.format("%-10s %-40s %-40s $%-15.2f $%-15.2f $%-15.2f  $%-15.2f", i.getInvoiceCode(), i.getConsumerName(), i.getSalesPersonName(), 
					i.getSubtotal(), i.getComplianceFees(), i.getTaxes(), i.getGrandTotal()));
			total += i.getGrandTotal();
			subTotal += i.getSubtotal();
			fees += i.getFees();
			taxes += i.getTaxes();
		}
		System.out.println("============================================================================================================================================================");
		System.out.println(String.format("%-92s $%-15.2f $%-15.2f $%-15.2f  $%-15.2f", "Totals", subTotal, fees, taxes, total));






	}


	protected void prepSQL() {
		sql.removeAllCustomers();
		sql.removeAllPersons();
		sql.removeAllProducts();
		sql.removeAllInvoices();
		sql.removeAllInvoiceProducts();
		sql.removeAllEmails();
	}

}