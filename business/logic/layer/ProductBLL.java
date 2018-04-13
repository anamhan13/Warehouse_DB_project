package business.logic.layer;

import java.util.ArrayList;
import java.util.List;
import model.Product;

public class ProductBLL{
	private List<Validator<Product>> validators;
	
	public ProductBLL(Product product) {
		validators = new ArrayList<Validator<Product>>();
		validators.add(new ProductPriceValidator(product));
		validators.add(new ProductQuantityValidator(product));
	}

}
