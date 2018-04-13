package business.logic.layer;

import model.Client;

public class ClientPhoneValidator implements Validator<Client>{
	private Client client;

	public ClientPhoneValidator(Client c){
		this.client = c;
	}
	
	public void validate(Client t) {
		int len = client.getPhone().length();
		
		if (len>19) {
			System.out.println("The phone number is too long!");
		}
		if (client.getPhone().charAt(0)!='+') {
			System.out.println("The phone number has a wrong format!");
		} else {
			boolean rightFormat = true;
			for (int i=0; i<len;i++) {
				if (i!=0) {
					if (!Character.isDigit(client.getPhone().charAt(i))) {
						rightFormat = false;
					}
				}
			}
			if (!rightFormat) {
				System.out.println("The phone number has a wrong format!");
			}
		}
		
	}

}
