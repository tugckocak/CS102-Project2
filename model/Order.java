package model;

import java.util.ArrayList;


public class Order  {
  private ArrayList<Product> products;
  
   public Order() {
		products= new ArrayList<Product>() ;
	}
	
	public void addProduct(Product product){
	  products.add( product);	
	}
	
	public void listOrder(){
		if(products.size()==0){
		System.out.println("You have not ordered anything yet!");	
		}else{
        for(int i=0;i<products.size();i++){
			System.out.println(products.get(i).getName() +": "+ products.get(i).getSellingPrice());
		}
		}
	}
	
	public ArrayList<Product> getOrderedProducts(){
		return products; 
	}
	
	public double getSellingPrice(){//this method is not in the uml, do they stated that I am allowed to add needed set or get or not
		double s=0;
		for (int j= 0; j<products.size();j++){
			s+=products.get(j).getSellingPrice();
			}
		return s;
	}
	
	public double calculateTotalPrice(){
		double totalPrice=0;
		for(int i=0;i<products.size();i++){
	totalPrice += products.get(i).calculateExpense();
		}
		return totalPrice;
	}

}
