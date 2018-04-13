package business.logic.layer;

import java.util.ArrayList;
import java.util.List;
import model.OrderT;

public class OrderBLL{
	private List<Validator<OrderT>> validators;
	
	public OrderBLL(OrderT order) {
		validators = new ArrayList<Validator<OrderT>>();
		validators.add(new OrderQuantityValidator(order));
	}

}
