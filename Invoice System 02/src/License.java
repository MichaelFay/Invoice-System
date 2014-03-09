
public class License {

	private Double serviceFee, annualFee, dailyFee;
	
	public License(String serviceFee, String annualFee){
		this.serviceFee = Double.parseDouble(serviceFee);
		this.annualFee = Double.parseDouble(annualFee);
		this.dailyFee = this.annualFee/365.0;		
	}
	
	protected Double getServiceFee(){
		return this.serviceFee;
	}
	protected Double getAnnualFee(){
		return this.annualFee;
	}
	protected Double getDailyFee(){
		return this.dailyFee;
	}

}
