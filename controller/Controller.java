//Tuðçe Melike Koçak S007786

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Cook;
import model.Order;
import model.Restaurant;
import model.Waiter;

import view.OrderPanel;
import view.RestaurantPanel;

public class Controller implements ActionListener {
	Restaurant restaurant;
	OrderPanel orderpanel;
	RestaurantPanel restaurantpanel;

	DefaultTableModel employee = new DefaultTableModel();

	JTable table = new JTable(employee);
	Order order ;
	JButton AddWaiter = new JButton("Add");
	JButton AddCook = new JButton("Add");

	public Controller(Restaurant res, OrderPanel orderpanel, RestaurantPanel restaurantpanel) {
		this.restaurantpanel = restaurantpanel;
		this.orderpanel = orderpanel;
		this.restaurant = res;
	}

	public JButton getAddWaiter() {
		return AddWaiter;
	}

	public void addWaiter() {
		(restaurant).addWaiter(restaurantpanel.getWaiterName().getText());
		JOptionPane.showMessageDialog(null, "Waiter added successfully");
	}

	public Waiter assignedWaiter() {
		Waiter w = (restaurant).assignWaiter();
		return w;
	}

	public JButton getAddCook() {
		return AddCook;
	}

	public void addCook() {
		(restaurant).addCook(restaurantpanel.getCookName().getText(),
				Double.parseDouble(restaurantpanel.getSalary().getText()));
		JOptionPane.showMessageDialog(null, "Cook added successfully");
	}

	public void showWaiter() {
		JOptionPane.showMessageDialog(null,
				"Hi, I am " + assignedWaiter().getName() + ". \nWhat would you like to order?");
		this.order=new Order();
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public DefaultTableModel listEmployees() {
		employee.addColumn("ID");
		employee.addColumn("Name");
		employee.addColumn("Job");
		String[] a = { "<html><font color=\"red\">ID</font></html>", "<html><font color=\"red\">Name</font></html>",
				"<html><font color=\"red\">Job</font></html>" };
		employee.addRow(a);

		String job;
		for (int i = 0; i < (restaurant).getEmployees().size(); i++) {
			int id = (restaurant).getEmployees().get(i).getId();
			String name = (restaurant).getEmployees().get(i).getName();

			if ((restaurant).getEmployees().get(i) instanceof Cook) {
				job = "Cook";
			} else {
				job = "Waiter";
			}
			Object[] data = { id, name, job };
			employee.addRow(data);

		}
		return employee;
	}

	public double price() {
		double price = 0;
		double number = (int) orderpanel.getSpinner().getValue();
		if (number <= 0) {
			orderpanel.getSpinner().setValue(1);
			JOptionPane.showMessageDialog(null, "Select positive value!");
		} else {
			double productprice = Double.parseDouble(selectproductprice());
			price = number * productprice;
		}
		return price;
	}

	public void setInvisible() {
		restaurantpanel.removeAll();
		restaurantpanel.repaint();

		restaurantpanel.add(restaurantpanel.getEmployee());
		restaurantpanel.add(restaurantpanel.getCook());
		restaurantpanel.add(restaurantpanel.getWaiter());
		restaurantpanel.add(restaurantpanel.getCalc());
		restaurantpanel.revalidate();
	}

	public String selectproductprice() {
		String s = null;
		int i = orderpanel.getSelectproduct().getSelectedIndex() - 1;
		String selected = (String) orderpanel.getSelectproduct().getSelectedItem();
		if (selected == null) {
			JOptionPane.showMessageDialog(null, "Select a product!");
		} else {
			s = Double.toString((restaurant).getProducts().get(i).getSellingPrice());
			orderpanel.getPrice().setText(s + " TL");
		}
		return s;
	}

	int count = 2;

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("New Order")) {
			(orderpanel).getNewOrder().setVisible(false);
			orderpanel.add(orderpanel.topPanel());
			orderpanel.add(orderpanel.bottomPanel());

			showWaiter();

			(orderpanel.getSelectproduct()).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectproductprice();
				}
			});

			(orderpanel.getAddProduct()).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int i = orderpanel.getSelectproduct().getSelectedIndex();
					if (price() > 0) {
						orderpanel.getModel().addRow(orderpanel.Table());

						int number = (int) orderpanel.getSpinner().getValue();
						for (int j = 0; j < number; j++) {
							order.addProduct(restaurant.getProducts().get(i - 1));
						}
					}
				}

			});

			(orderpanel.getFinalize()).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					assignedWaiter().createOrder(order);
					JOptionPane.showMessageDialog(null,
							"Your order is finalized. Total price is " + restaurant.calculateRevenue() + " TL");
					showWaiter();
					orderpanel.getModel().setRowCount(0);
				}

			});

		}

		if (e.getActionCommand().equals("List Employees")) {
			if (count == 2) {
				setInvisible();
				employee.setRowCount(0);
				employee.setColumnCount(0);
				count--;
			}
			System.out.println(count);
			if (count == 1) {

				listEmployees();
				restaurantpanel.add(table);
				restaurantpanel.revalidate();

			}
			count++;

		}
		if (e.getActionCommand().equals("Add Waiter")) {
			setInvisible();

			restaurantpanel.add(restaurantpanel.waiterPanel());
			restaurantpanel.revalidate();

		 	(restaurantpanel.getAddWaiter()).addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					addWaiter();

				}
			});
		}
		if ((e.getActionCommand().equals("Add Cook"))) {
			setInvisible();

			restaurantpanel.add(restaurantpanel.cookPanel());
			restaurantpanel.revalidate();
			(restaurantpanel.getAddCook()).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addCook();
				}
			});
		}
		if ((e.getActionCommand().equals("Calculate Expenses")))

		{
			setInvisible();

			restaurantpanel.add(restaurantpanel.calculatePanel());
			restaurantpanel.revalidate();

		}
	}
}
