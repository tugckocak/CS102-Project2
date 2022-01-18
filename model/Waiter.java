package model;

import java.util.ArrayList;



public class Waiter extends Employee {
	private double orderRate=0.1;
    private ArrayList<Order> ordersReceived = new ArrayList<Order>();
  
	public Waiter(int id, String name) {
		super(id, name);
	}

	public void createOrder(Order order){
		ordersReceived.add(order);
	}

		
	public ArrayList<Order> getOrdersReceived(){
		return ordersReceived;
	}
	
	public double calculateExpense() {
		double d = 0;
		if(ordersReceived.size()==0){
			 d=0;
		}
		else{
			for(int i=0;i<ordersReceived.size();i++){
				
				
				d+=ordersReceived.get(i).getSellingPrice()*orderRate;
			}
				
		}
		return d;
	}
	
	
}
