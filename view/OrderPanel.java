//Tuðçe Melike Koçak S007786


package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.Restaurant;

public class OrderPanel extends JPanel {
	private Restaurant restaurant;
	private RestaurantPanel restaurantpanel;
	private Controller controller;

	private DefaultTableModel model;
	private JButton NewOrder;
	private JPanel toppanel = new JPanel();
	private JPanel bottompanel = new JPanel();
	private JButton addProduct;
	private JButton finalize;
	private JTable table;
	private JComboBox selectproduct;
	private JLabel price;
	private JSpinner spinner;

	

	public OrderPanel(Restaurant restaurant) {
		this.restaurant = restaurant;
		NewOrder = new JButton("New Order");
		add(NewOrder);
		this.setVisible(true);
		controller = new Controller(restaurant, this, restaurantpanel);
		NewOrder.addActionListener(controller);
	}

	public JPanel topPanel() {

		toppanel.setName("Add Product");
		toppanel.setSize(650, 250);

		selectproduct = new JComboBox(toArray());
		toppanel.setLayout(new GridLayout(4, 2));
		toppanel.add(new JLabel("Product: "));
		toppanel.add(selectproduct);
		toppanel.add(new JLabel("Count: "));
		spinner = new JSpinner();
		spinner.setValue(1);
		toppanel.add(spinner);
		toppanel.add(new JLabel("Price: "));
		price = new JLabel("0.00 TL");
		toppanel.add(price);
		addProduct = new JButton("Add Product");
		toppanel.add(addProduct);
		toppanel.setVisible(true);
		return toppanel;

	}

	
	public JPanel bottomPanel() {
		bottompanel.setSize(200, 100);
		String[] colNames = { "Product Name", "Count", "Price" };
		model = new DefaultTableModel(colNames, 0);
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		bottompanel.add(scrollPane);
		finalize = new JButton("Finalize");
		bottompanel.add(finalize);
		return bottompanel;
	}
	
	public String[] toArray() {
		String[] array = new String[(restaurant).getProducts().size() + 1];
		array[0] = null;
		for (int i = 1; i < array.length; i++) {
			array[i] = (restaurant).getProducts().get(i - 1).getName();
		}
		return array;
	}

	public String[] Table() {

		String name = (String) selectproduct.getSelectedItem();

		int c = (int) spinner.getValue();
		String count = Integer.toString(c);
		String price = Double.toString(controller.price());

		String[] data = { name, count, price };

		return data;

	}
	
	public JSpinner getSpinner() {
		return spinner;
	}

	public JButton getFinalize() {
		return finalize;
	}

	public DefaultTableModel getModel() {
		return model;
	}
	
	public JButton getAddProduct() {
		return addProduct;
	}

	public JButton getNewOrder() {
		return NewOrder;
	}

	public JComboBox getSelectproduct() {
		return selectproduct;
	}

	public JLabel getPrice() {
		return price;
	}

}