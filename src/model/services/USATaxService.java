package model.services;

public class USATaxService implements TaxService{
	
	public double tax(double amount) {
		if (amount <= 100.0) {
			return amount * 0.05;		
		}
		else {
			return amount * 0.10;
		}
	}

}
