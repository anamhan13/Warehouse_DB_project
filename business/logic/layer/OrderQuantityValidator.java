package business.logic.layer;

import model.OrderT;

public class OrderQuantityValidator implements Validator<OrderT>{
	private OrderT order;
	
	public OrderQuantityValidator(OrderT o) {
		this.order = o;
	}
	
	public void validate(OrderT o) {
		if (order.getQuantity()!= Math.floor(order.getQuantity())) {
			System.out.println("The quantity of the product in order must be an integer number!");
		}
	}

}
