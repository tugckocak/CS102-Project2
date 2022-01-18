//Tuðçe Melike Koçak-S007786
package model;

public class Beverage extends Product {
  
	public Beverage(String name, double sellingPrice, double purchasePrice) {
		super(name, sellingPrice, purchasePrice, purchasePrice);	
	}

	
	public double calculateExpense() {
		return getPurchasePrice();
	}

}
