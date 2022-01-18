//Tuðçe Melike Koçak-S007786
package model;

public class Cook extends Employee {
	
	private double salary;
	private double taxRate=0.18;

	public Cook(int id, String name, double salary) { 
		super(id, name);
		this.salary=salary;
	}


	public double calculateExpense() {
	   return	salary+salary*taxRate;
	}


	public double getSalary() {
		return salary;
	}


	public double getTaxRate() {
		return taxRate;
	}


	
}
