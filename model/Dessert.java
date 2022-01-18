
//Tuðçe Melike Koçak-S007786
package model;

public class Dessert extends Product {

	public Dessert(String name, double sellingPrice, double purchasePrice, double utilityCost) {
		super(name, sellingPrice, purchasePrice, utilityCost);
		
	}

	
	public double calculateExpense() {
		
		return getPurchasePrice()+getUtilityCost();
	}

}
