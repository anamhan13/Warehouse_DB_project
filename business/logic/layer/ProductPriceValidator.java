package business.logic.layer;

import model.Product;

public class ProductPriceValidator implements Validator<Product>{
	private Product product;
	
	public ProductPriceValidator(Product p) {
		this.product = p;
	}
	
	public void validate(Product t) {	
		if (product.getPrice()<=0) {
			System.out.println("The price of a product must be a positive number!");
		}
	}
}
