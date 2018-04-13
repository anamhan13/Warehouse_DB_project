package data.access.object;

import model.*;

public class OrderDAO extends AbstractDAO<OrderT> {
	
	public OrderDAO() {
		super();
	}
	
	public OrderT findById(int id) {
		return super.findByAs(id,"idorder");
	}
	
	public OrderT findByIdProduct(int id) {
		return super.findByAs(id, "idproduct");
	}
	
	public OrderT findByIdClient(int id) {
		return super.findByAs(id, "idclient");
	}

	public int insertOrder(OrderT ordert) {
		int insertId = -1;
		ClientDAO cd =  new ClientDAO();	
		Client c = cd.findById(ordert.getIdClient());
		if (c!=null) {
			ProductDAO pd = new ProductDAO();
			Product p = pd.findById(ordert.getIdProduct());
			if (p!=null) {
				if (ordert.getQuantity()>p.getQuantity()) {
					System.out.println("Cannot place order due to under stock");
					return -1;
				}
				insertId = insert(ordert);			
				p.setQuantity(p.getQuantity()-ordert.getQuantity());
				int id = pd.updateProduct(p,ordert.getIdProduct());
				if (p.getQuantity()==0) {
					id = pd.deleteProduct(id);
				}
			} else {
				System.out.println("Couldn't find product");
				return -1;
			}
		} else {
			System.out.println("Couldn't find client");
			return -1;
		}
		System.out.println("The order was inserted successfully!");
		return insertId;
	}

	public int updateOrder(OrderT ordert,int id) {
		int updateId = -1;
		ClientDAO cd =  new ClientDAO();	
		Client c = cd.findById(ordert.getIdClient());
		if (c!=null) {
			ProductDAO pd = new ProductDAO();
			Product p = pd.findById(ordert.getIdProduct());
			if (p!=null) {
				updateId = update("idorder",id,ordert);
			} else {
				System.out.println("Couldn't find product");
				return -1;
			}
		} else {
			System.out.println("Couldn't find client");
			return -1;
		}
		if (updateId!=-1) {
			System.out.println("The order was updated successfully!");
		}
		return updateId;
	}
	
	public int deleteOrder(int id) {
		int deleteId = -1;
		OrderT o = findByAs(id,"idorder");
		if (o!= null) {
			deleteId = delete("idorder",id);
		} else {
			System.out.println("Inexistent order!");
		}
		return deleteId;
	}
}
