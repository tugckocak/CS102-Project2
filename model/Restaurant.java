package model;

import java.util.ArrayList;
import java.util.Random;


public class Restaurant {

	private ArrayList<Employee> employees = new ArrayList<>();
	private ArrayList<Product> products = new ArrayList<>();
	private Random rgen = new Random();

	public Restaurant() {
		initEmployees();
		initProducts();
	}

	private void initEmployees() {

		addCook("Monica", 100);
		addWaiter("Ross");
		addWaiter("Phobe");
		addWaiter("Rachel");
	}

	private void initProducts() {

		// Parameters for Product Constructor:
		// Product Name, Selling Price, Purchase Price and Utility Cost

		products.add(new MainDish("Pizza", 6, 2, 2));
		products.add(new MainDish("Burger", 5, 1.5, 2));

		products.add(new Beverage("Coke", 2, 0.5));
		products.add(new Beverage("Lemonade", 2, 0.3));

		products.add(new Dessert("Tiramusu", 4, 1, 1));
		products.add(new Dessert("Cake", 3, 0.5, 1));
		products.add(new Dessert("Ice Cream", 3, 0.5, 0.5));

		ArrayList<Product> HGproducts = new ArrayList<>();
		HGproducts.add(new MainDish("Pizza", 6, 2, 2));
		HGproducts.add(new Beverage("Coke", 2, 0.5));
		HGproducts.add(new Dessert("Tiramusu", 4, 1, 1));
		products.add(new MenuProduct("Hunger Games Menu", HGproducts));

		ArrayList<Product> Kidsproducts = new ArrayList<>();
		Kidsproducts.add(new MainDish("Burger", 5, 1.5, 2));
		Kidsproducts.add(new Beverage("Lemonade", 2, 0.3));
		Kidsproducts.add(new Dessert("Ice Cream", 3, 0.5, 0.5));
		products.add(new MenuProduct("Kids Menu", Kidsproducts));
	}

	public void listEmployees() {
		for (int i = 0; i < employees.size(); i++) {
            System.out.println(employees.get(i));
		}
		
	}
	
	
	public void addCook(String name, double salary) {
		Cook cook = new Cook(employees.size() + 1, name, salary);
		employees.add(cook);
	}
	

	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	
	public double[] price(){
		double[] array = new double[products.size()];
		for(int i = 0; i < array.length; i++) {
			array[i] =  products.get(i).getSellingPrice();
		}
		return array;
		}


	public void addWaiter(String name) {

		Waiter waiter = new Waiter(employees.size() + 1, name);
		employees.add(waiter);

	}
	
	

	public Waiter assignWaiter() {
		int randomNumber = rgen.nextInt(employees.size());
		if (employees.get(randomNumber) instanceof Waiter)
			return (Waiter) employees.get(randomNumber);
		else
			return assignWaiter();
	}

	public double calculateExpenses() {
		double employeeExpenses = 0;
		double orderExpenses = 0;
		double totalExpenses = 0;
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i) instanceof Cook) {
				employeeExpenses += employees.get(i).calculateExpense();
			} else {
				employeeExpenses += employees.get(i).calculateExpense();
			}
		}
		System.out.println("Employee Expense: " + employeeExpenses);

		//
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i) instanceof Waiter) {
              Waiter temp = (Waiter) employees.get(i);
				for (int o = 0; o < temp.getOrdersReceived().size(); o++) {

					orderExpenses += temp.getOrdersReceived().get(o).calculateTotalPrice();

				}
			}

		}
		totalExpenses += orderExpenses + employeeExpenses;
		System.out.println("Order Expense: " + orderExpenses);

		return totalExpenses;
	}

	public double calculateRevenue() {
		double rev=0;
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i) instanceof Waiter) {
              Waiter temp = (Waiter) employees.get(i);
				for (int o = 0; o < temp.getOrdersReceived().size(); o++) {
                   rev += temp.getOrdersReceived().get(o).getSellingPrice();
	
				}
			}

		}
	    return rev;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

}
