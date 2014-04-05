import java.io.IOException;
import java.sql.SQLException;


public class Controller {

	String location[];
	Model data;
	InvoiceData sql = new InvoiceData();
	protected Controller(String[] location){
		this.location = location;
		data = new Model();
	}
	protected void Start() throws IOException, SQLException{
		
		//data.prepSQL();
		
		data.setPeopleData(location[0]);
		
		data.setConsumerData(location[1]);
		data.setProductData(location[2]);
		data.setInvoiceData(location[3]);
		
		
		data.printInvoice();
		//data.printSQL();
		
		
		
		sql.conn.close();
		
		
	}
	
}
