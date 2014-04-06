package com.cinco;

public class InvoiceProductData {

	private String startDate, endDate, type, name, productCode;
	private Double fees, price, complianceFee, totalCharge;
	private Integer amount;
	
	public InvoiceProductData(String startDate, String endDate, Integer amount, Products product){
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = product.getType();
		this.name = product.getName();
		this.productCode = product.getCode();
		this.complianceFee = product.getServiceFee();
		
		this.price = product.getPrice();
		this.amount = amount;
		
		this.totalCharge = this.amount * this.price;
		
	}
	public InvoiceProductData(Integer amount, Products product){
		if(product.getType().compareToIgnoreCase("Equipment")==0)
			setEquip(amount,product);
		else if(product.getType().compareToIgnoreCase("Consultions")==0)
			setConsult(amount,product);
		
	}
	
	private void setEquip(Integer amount, Products product){
		this.name = product.getName();
		this.type = product.getType();
		this.price =(Double)product.getPrice();
		this.amount = amount;
		this.totalCharge = this.price * this.amount;
	}
	private void setConsult(Integer amount, Products product){
		this.name = product.getName();
		this.type = product.getType();
		
	}
	

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InvoiceProductData [startDate=" + startDate + ", endDate="
				+ endDate + ", type=" + type + ", name=" + name
				+ ", productCode=" + productCode + ", fees=" + fees
				+ ", price=" + price + ", complianceFee=" + complianceFee
				+ ", totalCharge=" + totalCharge + ", amount=" + amount + "]";
	}
	
}
