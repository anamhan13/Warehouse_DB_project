package data.access.object;

import model.*;

public class ProductDAO extends AbstractDAO<Product> {
	
	public ProductDAO() {
		super();
	}
	
	public Product findById(int id) {
		return super.findByAs(id,"idproduct");
	}
	
	public Product findByQuantity(int quant) {
		return super.findByAs(quant, "quantity");
	}
	
	public int insertProduct(Product product) {
		return insert(product);
	}
	
	public int updateProduct(Product product, int id) {
		OrderDAO orderDao = new OrderDAO();
		OrderT o = orderDao.findByIdProduct(id);
		Product p = this.findById(id);
		if (o!=null) {
			if (p.getIdProduct()!=id) {
				System.out.println("Cannot update product: currently involved in an order");
				return -1;
			} 
		}
		return update("idproduct",id,product);
	}
	
	public int deleteProduct(int id) {
		OrderDAO orderDao = new OrderDAO();
		OrderT o = orderDao.findByIdProduct(id);
		if (o!=null) {
			System.out.println("Cannot delete product: currently involved in an order");
			return -1;
		}
		return delete("idproduct",id);
	}
}

