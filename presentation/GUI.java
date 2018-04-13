package presentation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import data.access.object.ClientDAO;
import data.access.object.OrderDAO;
import data.access.object.ProductDAO;
import main.ReflectionClass;
import model.Client;
import model.OrderT;
import model.Product;

public class GUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JPanel contentPane_1;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private static int noOfBills;
	private static int noOfOP; //number of ordered products
	private static double priceOfOP; //price of ordered products
	private static int noPStock; //number of products in stock
	private static double pricePStock; //price of products in stock
		
	/**
	 * Launch the application.
	 * @param <T>
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		GUI.noOfBills=0;
		GUI.noOfOP=0;
		GUI.noPStock=0;
		GUI.priceOfOP=0;
		GUI.pricePStock=0;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1050, 622);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Font font = new Font("",1,15);
		
		contentPane_1 = new JPanel();
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setLayout(null);
		
		/*
		 * CLIENT TABLE
		 */
		Object[] columns = {"idclient","name","age","address","phone"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1050, 599);
		frame.setContentPane(contentPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(507, 285, 484, 126);
		contentPane_1.add(scrollPane);
		
		JTable table = new JTable();
		scrollPane.setViewportView(table);
		
        table.setModel(model);
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        table.setFont(font);
        table.setRowHeight(30);
        table.setVisible(true);
		
		/*
		 * PRODUCT TABLE
		 */
        Object[] columns2 = {"idProduct","name","price","quantity","producer"};
        DefaultTableModel model2 = new DefaultTableModel();
        model2.setColumnIdentifiers(columns2);
     
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1050, 599);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(507, 148, 484, 126);
		contentPane_1.add(scrollPane_1);
		
		JTable table2 = new JTable();
		scrollPane_1.setViewportView(table2);
		
        table2.setModel(model2);
        table2.setBackground(Color.LIGHT_GRAY);
        table2.setForeground(Color.black);
        table2.setFont(font);
        table2.setRowHeight(30);
        table2.setVisible(true);
        
        /*
         * ORDER TABLE 
         */
        Object[] columns3 = {"idorder","quantity","idproduct","idclient"};
        DefaultTableModel model3 = new DefaultTableModel();
        model3.setColumnIdentifiers(columns3);
     
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1050, 599);
		
        JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(507, 11, 484, 126);
		contentPane_1.add(scrollPane_2);
		
		JTable table3 = new JTable();
		scrollPane_2.setViewportView(table3);
		
        table3.setModel(model3);
        table3.setBackground(Color.LIGHT_GRAY);
        table3.setForeground(Color.black);
        table3.setFont(font);
        table3.setRowHeight(30);
        table3.setVisible(true);
        /*
         * 
         */
		
		JTextArea txtrClientName = new JTextArea();
		txtrClientName.setEditable(false);
		txtrClientName.setFont(new Font("Arial", Font.PLAIN, 13));
		txtrClientName.setText("Client name:");
		txtrClientName.setBounds(10, 11, 77, 22);
		frame.getContentPane().add(txtrClientName);
		
		textField = new JTextField();
		textField.setBounds(112, 11, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextArea txtrClientAge = new JTextArea();
		txtrClientAge.setEditable(false);
		txtrClientAge.setFont(new Font("Arial", Font.PLAIN, 13));
		txtrClientAge.setText("Client age:");
		txtrClientAge.setBounds(10, 41, 77, 22);
		frame.getContentPane().add(txtrClientAge);
		
		textField_1 = new JTextField();
		textField_1.setBounds(112, 41, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JTextArea txtrClientAddress = new JTextArea();
		txtrClientAddress.setEditable(false);
		txtrClientAddress.setFont(new Font("Arial", Font.PLAIN, 13));
		txtrClientAddress.setText("Client address:");
		txtrClientAddress.setBounds(10, 74, 91, 22);
		frame.getContentPane().add(txtrClientAddress);
		
		textField_2 = new JTextField();
		textField_2.setBounds(112, 74, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JTextArea txtrClientPhone = new JTextArea();
		txtrClientPhone.setEditable(false);
		txtrClientPhone.setFont(new Font("Arial", Font.PLAIN, 13));
		txtrClientPhone.setText("Client phone:");
		txtrClientPhone.setBounds(10, 110, 91, 22);
		frame.getContentPane().add(txtrClientPhone);
		
		textField_3 = new JTextField();
		textField_3.setBounds(112, 110, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JTextArea txtrProductName = new JTextArea();
		txtrProductName.setEditable(false);
		txtrProductName.setFont(new Font("Arial", Font.PLAIN, 13));
		txtrProductName.setText("Product name:");
		txtrProductName.setBounds(6, 148, 91, 22);
		frame.getContentPane().add(txtrProductName);
		
		textField_4 = new JTextField();
		textField_4.setBounds(112, 148, 86, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JTextArea txtrProductPrice = new JTextArea();
		txtrProductPrice.setEditable(false);
		txtrProductPrice.setFont(new Font("Arial", Font.PLAIN, 13));
		txtrProductPrice.setText("Product price:");
		txtrProductPrice.setBounds(6, 171, 81, 19);
		frame.getContentPane().add(txtrProductPrice);
		
		textField_5 = new JTextField();
		textField_5.setBounds(112, 171, 86, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JTextArea txtrProductQuantity = new JTextArea();
		txtrProductQuantity.setEditable(false);
		txtrProductQuantity.setFont(new Font("Arial", Font.PLAIN, 13));
		txtrProductQuantity.setText("Product quantity:");
		txtrProductQuantity.setBounds(6, 194, 103, 22);
		frame.getContentPane().add(txtrProductQuantity);
		
		textField_6 = new JTextField();
		textField_6.setBounds(112, 194, 86, 20);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		JTextArea txtrProductProducer = new JTextArea();
		txtrProductProducer.setEditable(false);
		txtrProductProducer.setFont(new Font("Arial", Font.PLAIN, 13));
		txtrProductProducer.setText("Product producer:");
		txtrProductProducer.setBounds(6, 225, 106, 22);
		frame.getContentPane().add(txtrProductProducer);
		
		textField_7 = new JTextField();
		textField_7.setBounds(112, 225, 86, 20);
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);
		
		
		JLabel lblClient = new JLabel("Order :");
		lblClient.setBounds(459, 11, 46, 14);
		contentPane_1.add(lblClient);
		
		JLabel lblProduct = new JLabel("Product:");
		lblProduct.setBounds(443, 148, 54, 14);
		contentPane_1.add(lblProduct);
		
		JLabel lblOrder = new JLabel("Client:");
		lblOrder.setBounds(459, 286, 46, 14);
		contentPane_1.add(lblOrder);
		
		JLabel lblMessagesToBe = new JLabel("Messages to be displayed:");
		lblMessagesToBe.setBounds(507, 439, 216, 14);
		contentPane_1.add(lblMessagesToBe);
		
		JLabel lblClientidForOrder = new JLabel("Client to order:");
		lblClientidForOrder.setBounds(10, 332, 103, 14);
		contentPane_1.add(lblClientidForOrder);
		
		JLabel lblProductidForOrde = new JLabel("Product to be ordered:");
		lblProductidForOrde.setBounds(10, 357, 141, 14);
		contentPane_1.add(lblProductidForOrde);
		
		JLabel lblQuantityForOrder = new JLabel("Quantity for order:");
		lblQuantityForOrder.setBounds(10, 382, 103, 14);
		contentPane_1.add(lblQuantityForOrder);
		
		textField_9 = new JTextField();
		textField_9.setBounds(152, 329, 46, 20);
		contentPane_1.add(textField_9);
		textField_9.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setBounds(153, 354, 45, 20);
		contentPane_1.add(textField_10);
		textField_10.setColumns(10);
		
		textField_11 = new JTextField();
		textField_11.setBounds(152, 379, 46, 20);
		contentPane_1.add(textField_11);
		textField_11.setColumns(10);
		
		JLabel lblCid = new JLabel("CID:");
		lblCid.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCid.setBounds(39, 260, 46, 14);
		contentPane_1.add(lblCid);
		
		textField_12 = new JTextField();
		textField_12.setBounds(97, 254, 43, 20);
		contentPane_1.add(textField_12);
		textField_12.setColumns(10);
		
		JLabel lblOid = new JLabel("OID:");
		lblOid.setFont(new Font("Arial", Font.PLAIN, 13));
		lblOid.setBounds(313, 260, 46, 14);
		contentPane_1.add(lblOid);
		
		textField_13 = new JTextField();
		textField_13.setBounds(369, 254, 43, 20);
		contentPane_1.add(textField_13);
		textField_13.setColumns(10);
		
		table.setFont(new Font("Arial", Font.BOLD, 12));
		table.setRowHeight(14);
		
		table2.setFont(new Font("Arial", Font.BOLD, 12));
		table2.setRowHeight(14);
		
		table3.setFont(new Font("Arial", Font.BOLD, 12));
		table3.setRowHeight(14);

		JTextArea txtrId = new JTextArea();
		txtrId.setFont(new Font("Arial", Font.PLAIN, 13));
		txtrId.setText("PID:");
		txtrId.setBounds(171, 259, 47, 16);
		frame.getContentPane().add(txtrId);
		
		textField_8 = new JTextField();
		textField_8.setBounds(233, 254, 43, 20);
		frame.getContentPane().add(textField_8);
		textField_8.setColumns(10);
		
		JButton btnInsertClient = new JButton("INSERT CLIENT");
		Object[] rows = new Object[5];
		
		btnInsertClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClientDAO clientDao = new ClientDAO();
				Client c = new Client(textField.getText(),Integer.parseInt(textField_1.getText()),textField_2.getText(),textField_3.getText());
				clientDao.insertClient(c);
			}
		});
		btnInsertClient.setBounds(220, 11, 139, 23);
		frame.getContentPane().add(btnInsertClient);
		
		JButton btnInsertProduct = new JButton("INSERT PRODUCT");
		btnInsertProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductDAO productDao = new ProductDAO();
				Product p = new Product(textField_4.getText(),Double.parseDouble(textField_5.getText()),Integer.parseInt(textField_6.getText()),textField_7.getText());
				productDao.insertProduct(p);
			}
		});
		btnInsertProduct.setBounds(220, 148, 139, 23);
		frame.getContentPane().add(btnInsertProduct);
		
		JButton btnInsertOrder = new JButton("INSERT ORDER");
		btnInsertOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderDAO orderDao = new OrderDAO();
				OrderT o = new OrderT(Integer.parseInt(textField_11.getText()),Integer.parseInt(textField_9.getText()),Integer.parseInt(textField_10.getText()));
				orderDao.insertOrder(o);
			}
		});
		btnInsertOrder.setBounds(13, 407, 137, 23);
		contentPane_1.add(btnInsertOrder);
		
		JButton btnUpdateClient = new JButton("UPDATE CLIENT");
		btnUpdateClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientDAO clientDao = new ClientDAO();
				Client c = new Client(textField.getText(),Integer.parseInt(textField_1.getText()),textField_2.getText(),textField_3.getText());
				int id = Integer.parseInt(textField_12.getText());
				clientDao.updateClient(c,id);
			}
		});
		btnUpdateClient.setBounds(220, 40, 139, 23);
		frame.getContentPane().add(btnUpdateClient);
		
		JButton btnUpdateProduct = new JButton("UPDATE PRODUCT");
		btnUpdateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductDAO productDao = new ProductDAO();
				Product p = new Product(textField_4.getText(),Double.parseDouble(textField_5.getText()),Integer.parseInt(textField_6.getText()),textField_7.getText());
				int id = Integer.parseInt(textField_8.getText());
				productDao.updateProduct(p,id);
			}
		});
		btnUpdateProduct.setBounds(220, 182, 139, 23);
		frame.getContentPane().add(btnUpdateProduct);
		
		JButton btnUpdateOrder = new JButton("UPDATE ORDER");
		btnUpdateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderDAO orderDao = new OrderDAO();
				OrderT o = new OrderT(Integer.parseInt(textField_11.getText()),Integer.parseInt(textField_9.getText()),Integer.parseInt(textField_10.getText()));
				int id = Integer.parseInt(textField_13.getText());
				orderDao.updateOrder(o,id);
			}
		});
		btnUpdateOrder.setBounds(12, 441, 138, 23);
		contentPane_1.add(btnUpdateOrder);
		
		JButton btnPlaceOrder = new JButton("CREATE BILL");
		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				   
				    OrderDAO orderDao = new OrderDAO();
				    OrderT o = orderDao.findById(Integer.parseInt(textField_13.getText()));
				    if (o!=null) {
				    	
					    ClientDAO clientDao = new ClientDAO();
					    Client client = clientDao.findById(o.getIdClient());
					    if (client!=null) {
						    ProductDAO productDao = new ProductDAO();
						    Product product = productDao.findById(o.getIdProduct());
						    if (product!=null) {
							    PrintWriter writer = new PrintWriter("bill"+noOfBills+".txt", "UTF-8");
							    writer.println("Bill no.: "+noOfBills);
							    writer.println("Client no."+client.getIdClient()+" bought "+product.getName());
							    writer.println("Quantity: "+o.getQuantity()+" Price: "+o.getQuantity()*product.getPrice());
							    writer.println("Thank you for buying from our store!");
							    noOfBills++;
							    writer.close();
						    } else {
						    	System.out.println("Cannot create bill: inexistent product");
						    }
					    } else {
					    	System.out.println("Cannot create bill: inexistent client");
					    }
				    } else {
				    	System.out.println("Cannot create bill: inexistent order");
				    }
				    
				} catch (IOException ex) {
				    System.out.println("There was a problem while printing the bill. Please try again!");
				}
			}
		});
		btnPlaceOrder.setBounds(11, 475, 139, 23);
		contentPane_1.add(btnPlaceOrder);
		
		JButton btnDeleteClient = new JButton("DELETE CLIENT");
		btnDeleteClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientDAO clientDao = new ClientDAO();
				int id = Integer.parseInt(textField_12.getText());
				clientDao.deleteClient(id);
			}
		});
		btnDeleteClient.setBounds(220, 73, 139, 23);
		frame.getContentPane().add(btnDeleteClient);
		
		JButton btnDeleteClient_1 = new JButton("DELETE PRODUCT");
		btnDeleteClient_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductDAO productDao = new ProductDAO();
				//Client c = new Client(textField.getText(),Integer.parseInt(textField_1.getText()),textField_2.getText(),textField_3.getText());
				int id = Integer.parseInt(textField_8.getText());
				productDao.deleteProduct(id);
			}
		});
		btnDeleteClient_1.setBounds(220, 216, 139, 23);
		frame.getContentPane().add(btnDeleteClient_1);
		
		JButton btnDeleteOrder = new JButton("DELETE ORDER");
		btnDeleteOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OrderDAO orderDao = new OrderDAO();
				//Client c = new Client(textField.getText(),Integer.parseInt(textField_1.getText()),textField_2.getText(),textField_3.getText());
				int id = Integer.parseInt(textField_13.getText());
				orderDao.deleteOrder(id);
			}
		});
		btnDeleteOrder.setBounds(10, 509, 140, 23);
		contentPane_1.add(btnDeleteOrder);
		
		JButton btnFindClient = new JButton("FIND CLIENT");
		btnFindClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClientDAO clientDao = new ClientDAO();
				int id = Integer.parseInt(textField_12.getText());
				clientDao.findById(id);
			}
		});
		btnFindClient.setBounds(39, 277, 111, 23);
		frame.getContentPane().add(btnFindClient);
		
		JButton btnFindProduct = new JButton("FIND ORDER");
		btnFindProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OrderDAO orderDao = new OrderDAO();
				int id = Integer.parseInt(textField_13.getText());
				orderDao.findById(id);
			}
		});
		btnFindProduct.setBounds(309, 277, 111, 23);
		frame.getContentPane().add(btnFindProduct);
		
		JButton btnFindOrder = new JButton("FIND PRODUCT");
		btnFindOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductDAO productDao = new ProductDAO();
				int id = Integer.parseInt(textField_8.getText());
				productDao.findById(id);
			}
		});
		btnFindOrder.setBounds(169, 277, 121, 23);
		frame.getContentPane().add(btnFindOrder);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(507, 464, 484, 86);
		contentPane_1.add(scrollPane_3);
		
		JTextArea textArea = new JTextArea();
		scrollPane_3.setViewportView(textArea);
		PrintStream log1 = new PrintStream(new CustomOutputStream(textArea));
		System.setOut(log1);
		
		JLabel lblUtilities = new JLabel("UTILITIES:");
		lblUtilities.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUtilities.setBounds(272, 307, 103, 14);
		contentPane_1.add(lblUtilities);
		
		JLabel lblTotalNoOf = new JLabel("Total no. of products in stock:");
		lblTotalNoOf.setBounds(228, 329, 184, 14);
		contentPane_1.add(lblTotalNoOf);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(413, 327, 54, 19);
		contentPane_1.add(textArea_1);
		
		
		JLabel lblTotalPriceOf = new JLabel("Total price of products in stock:");
		lblTotalPriceOf.setBounds(228, 354, 184, 14);
		contentPane_1.add(lblTotalPriceOf);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(413, 352, 54, 20);
		contentPane_1.add(textArea_2);
	
		
		JLabel lblTotalNoOf_1 = new JLabel("Total no. of ordered products:");
		lblTotalNoOf_1.setBounds(228, 379, 184, 14);
		contentPane_1.add(lblTotalNoOf_1);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setBounds(413, 377, 54, 19);
		contentPane_1.add(textArea_3);

		
		JLabel lblTotalNoOf_2 = new JLabel("Total no. of bills:");
		lblTotalNoOf_2.setBounds(228, 395, 103, 16);
		contentPane_1.add(lblTotalNoOf_2);
		
		JTextArea textArea_4 = new JTextArea();
		textArea_4.setBounds(413, 394, 54, 17);
		contentPane_1.add(textArea_4);
	
		
		JLabel lblTotalPriceOf_1 = new JLabel("Total price of ordered products:");
		lblTotalPriceOf_1.setBounds(228, 418, 184, 14);
		contentPane_1.add(lblTotalPriceOf_1);
		
		JTextArea textArea_5 = new JTextArea();
		textArea_5.setBounds(413, 417, 54, 19);
		contentPane_1.add(textArea_5);
		
		JLabel lblFilters = new JLabel("FILTERS:");
		lblFilters.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFilters.setBounds(272, 438, 74, 14);
		contentPane_1.add(lblFilters);
		
		textField_14 = new JTextField();
		textField_14.setBounds(443, 510, 47, 20);
		contentPane_1.add(textField_14);
		textField_14.setColumns(10);
		
		textField_15 = new JTextField();
		textField_15.setBounds(443, 530, 47, 20);
		contentPane_1.add(textField_15);
		textField_15.setColumns(10);
		
		textField_16 = new JTextField();
		textField_16.setBounds(443, 453, 50, 20);
		contentPane_1.add(textField_16);
		textField_16.setColumns(10);
		
		textField_17 = new JTextField();
		textField_17.setBounds(443, 487, 47, 20);
		contentPane_1.add(textField_17);
		textField_17.setColumns(10);
		
		JButton btnDisplayAll = new JButton("DISPLAY ALL");
		btnDisplayAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReflectionClass<Client> rfc = new ReflectionClass<Client>();
				ReflectionClass<Product> rfp = new ReflectionClass<Product>();
				ReflectionClass<OrderT> rfo = new ReflectionClass<OrderT>();
				
				ClientDAO cd = new ClientDAO();
				ProductDAO pd = new ProductDAO();
				OrderDAO od = new OrderDAO();
				List<Client> clients= new ArrayList<Client>();
				try {
					clients = cd.findAll();
				} catch (IllegalArgumentException | IllegalAccessException e1) {
					e1.printStackTrace();
				}
				List<Product> products = new ArrayList<Product>();
				try {
					products = pd.findAll();
				} catch (IllegalArgumentException | IllegalAccessException e1) {
					e1.printStackTrace();
				}
				List<OrderT> orders = new ArrayList<OrderT>();
				try {
					orders = od.findAll();
				} catch (IllegalArgumentException | IllegalAccessException e1) {
					e1.printStackTrace();
				}
				List<Object> list;
			
				while(model.getRowCount() > 0){
					   for(int i = 0 ; i < model.getRowCount();i++){
					      model.removeRow(i);
					   }
				}
				while(model2.getRowCount() > 0){
					   for(int i = 0 ; i < model2.getRowCount();i++){
					      model2.removeRow(i);
					   }
				}
				while(model3.getRowCount() > 0){
					   for(int i = 0 ; i < model3.getRowCount();i++){
					      model3.removeRow(i);
					   }
				}
				
				model.setRowCount(0);
				model2.setRowCount(0);
				model3.setRowCount(0);
				
		        for (Client client : clients) {
					list = rfc.retrieveProperties(client);
					rows[0] = list.get(0);
					rows[1] = list.get(1);
					rows[2] = list.get(2);
					rows[3] = list.get(3);
					rows[4] = list.get(4);
					
					model.addRow(rows);
				}
				
				for (Product product : products) {
					list = rfp.retrieveProperties(product);
					rows[0] = list.get(0);
					rows[1] = list.get(1);
					rows[2] = list.get(2);
					rows[3] = list.get(3);
					rows[4] = list.get(4);
					model2.addRow(rows);
					noPStock++;
					pricePStock += product.getPrice()*product.getQuantity();
				}
				
				
				for (OrderT order : orders) {
					list = rfo.retrieveProperties(order);
					rows[0] = list.get(0);
					rows[1] = list.get(1);
					rows[2] = list.get(2);
					rows[3] = list.get(3);
					model3.addRow(rows);
					noOfOP+=order.getQuantity();
					Product product = pd.findById(order.getIdProduct());
					priceOfOP += product.getPrice()*order.getQuantity();
				}
				
				textArea_1.setText(Integer.toString(noPStock));
				textArea_2.setText(Double.toString(pricePStock));
				textArea_3.setText(Integer.toString(noOfOP));
				textArea_4.setText(Integer.toString(noOfBills));
				textArea_5.setText(Double.toString(priceOfOP));
			}
		});
		btnDisplayAll.setBounds(822, 422, 111, 23);
		contentPane_1.add(btnDisplayAll);
		
		JButton btnProductsWithPrice = new JButton("Products with price higher than");
		btnProductsWithPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for( int i = model2.getRowCount() - 1; i >= 0; i-- ) {
			        model2.removeRow(i);
			    }
				
				ReflectionClass<Product> rfp = new ReflectionClass<Product>();
				ProductDAO pd = new ProductDAO();
				List<Product> products = new ArrayList<Product>();
				List<Object> list;
				model2.setRowCount(0);
				double price = Double.parseDouble(textField_14.getText());
				try {
					products = pd.findAll();
				} catch (IllegalArgumentException | IllegalAccessException e1) {
					e1.printStackTrace();
				}
				
				for (Product product : products) {
					list = rfp.retrieveProperties(product);
					if (product.getPrice()>price) {
						rows[0] = list.get(0);
						rows[1] = list.get(1);
						rows[2] = list.get(2);
						rows[3] = list.get(3);
						rows[4] = list.get(4);
						model2.addRow(rows);
					}
				}
			}
		});
		btnProductsWithPrice.setBounds(188, 509, 250, 23);
		contentPane_1.add(btnProductsWithPrice);
		
		JButton btnNewButton = new JButton("Orders with more than...products");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for( int i = model3.getRowCount() - 1; i >= 0; i-- ) {
			        model3.removeRow(i);
			    }
				
				ReflectionClass<OrderT> rfo = new ReflectionClass<OrderT>();
				OrderDAO od = new OrderDAO();
				List<OrderT> orders = new ArrayList<OrderT>();
				List<Object> list;
				model3.setRowCount(0);
				int np = Integer.parseInt(textField_15.getText());
				try {
					orders = od.findAll();
				} catch (IllegalArgumentException | IllegalAccessException e1) {
					e1.printStackTrace();
				}
				
				for (OrderT order : orders) {
					list = rfo.retrieveProperties(order);
					if (order.getQuantity()>=np) {
						rows[0] = list.get(0);
						rows[1] = list.get(1);
						rows[2] = list.get(2);
						rows[3] = list.get(3);
						model3.addRow(rows);
					}
			}
			}
		});
		btnNewButton.setBounds(188, 531, 250, 23);
		contentPane_1.add(btnNewButton);
		
		JButton btnClientsOlderThan = new JButton("Clients older than");
		btnClientsOlderThan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for( int i = model.getRowCount() - 1; i >= 0; i-- ) {
			        model.removeRow(i);
			    }
				
				ReflectionClass<Client> rfc = new ReflectionClass<Client>();
				ClientDAO cd = new ClientDAO();
				List<Client> clients = new ArrayList<Client>();
				List<Object> list;
				model2.setRowCount(0);
				int age = Integer.parseInt(textField_16.getText());
				try {
					clients = cd.findAll();
				} catch (IllegalArgumentException | IllegalAccessException e1) {
					e1.printStackTrace();
				}
				
				for (Client client : clients) {
					list = rfc.retrieveProperties(client);
					if (client.getAge()>age) {
						rows[0] = list.get(0);
						rows[1] = list.get(1);
						rows[2] = list.get(2);
						rows[3] = list.get(3);
						rows[4] = list.get(4);
						model.addRow(rows);
					}
				}
			}
		});
		btnClientsOlderThan.setBounds(188, 452, 250, 23);
		contentPane_1.add(btnClientsOlderThan);
		
		JButton btnProduct = new JButton("Products  with quantity smaller than ");
		btnProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for( int i = model2.getRowCount() - 1; i >= 0; i-- ) {
			        model2.removeRow(i);
			    }
				
				ReflectionClass<Product> rfp = new ReflectionClass<Product>();
				ProductDAO pd = new ProductDAO();
				List<Product> products = new ArrayList<Product>();
				List<Object> list;
				model2.setRowCount(0);
				int quantity = Integer.parseInt(textField_17.getText());
				try {
					products = pd.findAll();
				} catch (IllegalArgumentException | IllegalAccessException e1) {
					e1.printStackTrace();
				}
				
				for (Product product : products) {
					list = rfp.retrieveProperties(product);
					if (product.getQuantity()<=quantity) {
						rows[0] = list.get(0);
						rows[1] = list.get(1);
						rows[2] = list.get(2);
						rows[3] = list.get(3);
						rows[4] = list.get(4);
						model2.addRow(rows);
					}
				}
			}
		});
		btnProduct.setBounds(188, 486, 250, 23);
		contentPane_1.add(btnProduct);
		
		JButton btnClearAll = new JButton("CLEAR ALL");
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for( int i = model.getRowCount() - 1; i >= 0; i-- ) {
			        model.removeRow(i);
			    }
				
				for( int i = model2.getRowCount() - 1; i >= 0; i-- ) {
			        model2.removeRow(i);
			    }
				
				for( int i = model3.getRowCount() - 1; i >= 0; i-- ) {
			        model3.removeRow(i);
			    }
				
				textArea.setText(null);
				textArea_1.setText(null);
				textArea_2.setText(null);
				textArea_3.setText(null);
				textArea_4.setText(null);
				textArea_5.setText(null);
			}
		});
		btnClearAll.setBounds(679, 422, 111, 23);
		contentPane_1.add(btnClearAll);
		
	}
}