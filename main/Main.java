package main;

import java.util.logging.Logger;

import model.OrderT;

public class Main implements Cloneable{
	protected static final Logger LOGGER = Logger.getLogger(Main.class.getName());	
	
	public void cloneObj(){
		try {
			Object a = this.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		OrderT ord[]  = new OrderT[10];
		System.out.println(ord.getClass().getComponentType());
		try {
			OrderT o = (OrderT)ord[0].getClass().newInstance();
			System.out.println(o.getClass());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
