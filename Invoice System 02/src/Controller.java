import java.io.IOException;


public class Controller {

	String location[];
	Model data;
	
	protected Controller(String[] location){
		this.location = location;
		data = new Model();
	}
	protected void Start() throws IOException{
		data.setPeopleData(location[0]);
		data.setConsumerData(location[1]);
		data.setProductData(location[2]);
		data.setInvoiceData(location[3]);
		data.printInvoice();
	}
	
}
