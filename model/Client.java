package model;

public class Client {

	private int idClient;
	private String name;
	private int age;
	private String address;
	private String phone;
	
	public Client(String name, int age, String address, String phone) {
		this.name = name;
		this.age = age;
		this.address = address;
		this.phone = phone;
	}
	
	public Client(int idClient,String name, int age, String address, String phone) {
		this.idClient = idClient;
		this.name = name;
		this.age = age;
		this.address = address;
		this.phone = phone;
	}
	
	
	public Client() {
		this.idClient=0;
	}
 
	public int getIdClient() {
		return idClient;
	}
	
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
