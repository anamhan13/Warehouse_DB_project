package model;

public class OrderT {

	private int idOrder;
	private int quantity;
	private int idProduct;
	private int idClient;

	public OrderT(int quantity, int idClient, int idProduct) {
		this.quantity = quantity;
		this.idClient = idClient;
		this.idProduct = idProduct;
	}
	
	public OrderT(int idOrder,int quantity,int idClient, int idProduct) {
		this.idOrder = idOrder;
		this.quantity = quantity;
		this.idProduct = idProduct;
		this.idClient = idClient;
	}
	
	public OrderT(){
		
	}

	public int getIdOrder() {
		return idOrder;
	}
	
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getIdProduct() {
		return idProduct;
	}
	
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	
}
