package business.logic.layer;

import java.util.ArrayList;
import java.util.List;
import model.Client;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ClientBLL{
	private List<Validator<Client>> validators;

	@SuppressWarnings("unused") //FA-LE PE TOATE SA MEARGA DIN CONSTRUCTOR
	public ClientBLL(Client client) {
		validators = new ArrayList<Validator<Client>>();
		validators.add(new ClientPhoneValidator(client));
		ClientPhoneValidator hello= new ClientPhoneValidator(client);
		hello.validate(client);
		boolean add = validators.add(new ClientAgeValidator(client));
		ClientAgeValidator bye= new ClientAgeValidator(client);
		bye.validate(client);
	}

}