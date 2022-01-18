//Tuðçe Melike Koçak S007786


package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

import model.Restaurant;

public class RestaurantPanel extends JPanel {
	private Restaurant restaurant;
	private OrderPanel orderpanel;
	private Controller controller;
	
	private JButton employee;
	private JButton cook;
	private JButton waiter;
	private JButton calc;

	JTextField waiterName = new JTextField("Enter Name ");
	JTextField cookName = new JTextField("Enter Name ");
	JTextField salary = new JTextField("Enter Salary ");
	JButton AddCook=new JButton("Add");
	JButton AddWaiter=new JButton("Add");
	

	public RestaurantPanel(Restaurant restaurant) {
		controller=new Controller(restaurant, orderpanel, this);
       this.restaurant=restaurant;
		this.setLayout(new FlowLayout());
		employee = new JButton("List Employees");
		this.add(employee);
		cook = new JButton("Add Cook");
		this.add(cook);
		waiter = new JButton("Add Waiter");
		this.add(waiter);
		calc = new JButton("Calculate Expenses");
		this.add(calc);
		this.setVisible(true);
        
		employee.addActionListener(controller);
		waiter.addActionListener(controller);
		cook.addActionListener(controller);
		calc.addActionListener(controller);
	}
	
	public JPanel cookPanel() {
		JPanel panel2 = new JPanel();
		panel2.add(new JLabel("Name: "));
		panel2.add(cookName);
		panel2.add(new JLabel("Salary: "));
		panel2.add(salary);
		panel2.add(AddCook);
		panel2.setVisible(true);
		return panel2;
	}
	
	public JPanel waiterPanel() {
		JPanel panel1 = new JPanel();
		
		panel1.add(new JLabel("Name: "));
		panel1.add(waiterName);
		panel1.add(AddWaiter);
		panel1.setVisible(true);
		
		return panel1;
	}
	
	public JPanel calculatePanel() {
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(3,2));
		
		panel3.add(new JLabel("Expenses:          "));
		JLabel expenses=new JLabel();
		String e=Double.toString(restaurant.calculateExpenses());
		expenses.setText(e +" TL");
		panel3.add(expenses );
		
		panel3.add(new JLabel("Revenue:            "));
		JLabel revenue=new JLabel();
		String r=Double.toString(restaurant.calculateRevenue());
		revenue.setText(r +" TL");
		panel3.add(revenue);
		
		panel3.add(new JLabel("Profit:             "));
		JLabel profit=new JLabel();
		double pro=0;
		pro-=restaurant.calculateExpenses()-restaurant.calculateRevenue();
		String p=Double.toString(pro);
		profit.setText(p +" TL");
		panel3.add(profit);
		
		panel3.setVisible(true);
		return panel3;
	}

	public JTextField getWaiterName() {
		return waiterName;
	}

	public JButton getEmployee() {
		return employee;
	}

	public JButton getCook() {
		return cook;
	}

	public JButton getWaiter() {
		return waiter;
	}

	public JButton getCalc() {
		return calc;
	}
	
	public JButton getAddWaiter() {
		return AddWaiter;
	}

	public JTextField getCookName() {
		return cookName;
	}

	public JTextField getSalary() {
		return salary;
	}

	public JButton getAddCook() {
		return AddCook;
	}

}
