package business.logic.layer;

import model.Client;

public class ClientAgeValidator  implements Validator<Client> {
	private static final int MIN_AGE = 14;
	private static final int MAX_AGE = 120;
	private Client client;
	
	public ClientAgeValidator(Client c) {
		this.client = c;
	}

	public void validate(Client t) {
		if (client.getAge() < MIN_AGE || client.getAge() > MAX_AGE) {
			System.out.println("The age limit is not respected!");
		}

	}
}
