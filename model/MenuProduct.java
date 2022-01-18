package model;

import java.util.ArrayList;

public class MenuProduct extends Product {

	private ArrayList<Product> products = new ArrayList<>();

	public MenuProduct(String name ,ArrayList<Product> products) {
		super(name);
		this.products=products;
	}

	public double calculateExpense() {
		double expense=0;
		for(int i=0;i<products.size();i++){
			expense+=products.get(i).calculateExpense();
		}
	    return expense;
	}
	
	public double getSellingPrice(){ //not in the uml
		return calculateSellingPrice();
	}

	
	private double calculateSellingPrice(){
		double total=0;
        for(int i=0;i<products.size();i++){
           if(products.get(i) instanceof Beverage){
    	total+=products.get(i).getSellingPrice()*0.5;
           }
    	 else if( products.get(i) instanceof MainDish ){
    		 total+=products.get(i).getSellingPrice()*0.9;
    	}
    	 else{
    		 total+=products.get(i).getSellingPrice()*0.8;
    	 }
    	}
		
       return total;
	}
	
}
