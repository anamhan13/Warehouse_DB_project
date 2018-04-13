package business.logic.layer;

import model.Product;

public class ProductQuantityValidator implements Validator<Product>{
	private Product product;
	
	public ProductQuantityValidator(Product p) {
		this.product = p;
	}
	
	public void validate(Product o) {
		if (product.getQuantity()!= Math.floor(product.getQuantity())) {
			System.out.println("The quantity of a product must be an integer number!");
		}
	}
}
