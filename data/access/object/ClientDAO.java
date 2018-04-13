package data.access.object;

import model.*;

public class ClientDAO extends AbstractDAO<Client> {

	public ClientDAO() {
		super();
	}
		
	/*
	 * Tries to find an object of class Client by the id given as a parameter
	 * @param: int id - specifies the id the Client should be searched after
	 * @return:  Client object 
	 */
	public Client findById(int id) {
		return super.findByAs(id,"idclient");
	}
	
	/*
	 * Tries to find an object of class Client by the age given as a parameter
	 * @param: int age - specifies the id the Client should be searched after
	 * @return:  Client object 
	 */
	public Client findByAge(int age) {
		return super.findByAs(age, "age");
	}
	
	/*
	 * Tries to insert an object of class Client in the table client
	 * @param: Client client - specifies the id the Client should be searched after
	 * @return:  int id -  returns the id of the newly inserted client or -1 if the operation couldn't be done 
	 */
	public int insertClient(Client client) {
		return insert(client);
	}
	
	/*
	 * Tries to update an object of class Client in the table client if they do not belong to an order
	 * @param: Client client - specifies the id the Client should be searched after
	 * @return:  int id -  returns the id of the newly updated client or -1 if the operation couldn't be done 
	 */
	public int updateClient(Client client, int id) {
		OrderDAO orderDao = new OrderDAO();
		OrderT o = orderDao.findByIdClient(id);
		if (o!=null) {
			System.out.println("Cannot update client: currently involved in an order");
			return -1;
		}
		return update("idclient",id,client);
	}
	
	/*
	 * Tries to delete an object of class Client in the table client if they do not belong to an order
	 * @param: Client client - specifies the id the Client should be searched after
	 * @return:  int id -  returns the id of the newly deleted client or -1 if the operation couldn't be done 
	 */
	public int deleteClient(int id) {
		OrderDAO orderDao = new OrderDAO();
		OrderT o = orderDao.findByIdClient(id);
		if (o!=null) {
			System.out.println("Cannot delete client: currently involved in an order");
			return -1;
		}
		return delete("idclient",id);
	}
}