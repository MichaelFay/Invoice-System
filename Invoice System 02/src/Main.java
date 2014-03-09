import java.io.IOException;


public class Main {

	public static void Main(String args[]) throws IOException{
		
		
		
		final String location[] = new String[4];
		location[0]= "data/Persons.dat"; //People Data 
		location[1]= "data/Customers.dat"; //Consumer Data
		location[2]= "data/Products.dat"; //Product Data
		location[3]= "data/Invoices.dat"; //Invoice Data
		
		final Controller control = new Controller(location);
		
		control.Start();
		
		//System.out.println("End of Program");
	}
}
