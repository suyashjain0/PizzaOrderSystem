package pizzaordersystem.controller;

import java.util.ArrayList;
import java.util.Scanner;

import pizzaordersystem.bean.LoginData;
import pizzaordersystem.bean.PizzaBean;
import pizzaordersystem.bean.RegistrationData;
import pizzaordersystem.database.CustomerDao;


public class CustomerController {
	
	int pizzaId;
	String pizzaName;
	String size;
	float price;
	String pizzaType;
	int count=0;
	static int id=0;
	PizzaBean[] pz1;
	PizzaBean pz;
	Scanner sc = new Scanner(System.in);
	public static CustomerDao cus=new CustomerDao(); 
	
	public boolean loginContoller(LoginData ld)
	{
		boolean b1=cus.isLogin(ld);
		if(b1==true)
		{	 
		System.out.println("Successfully login");
		return true;	
		}
		else
		{ 	
		System.out.println("login failed");
		return false;	 
		}
	}
	
	public boolean registerContoller(RegistrationData rd)
	{
		boolean b1=cus.isRegister(rd);
		if(b1==true)
		{	 
		System.out.println("Successfully Registered");
		return true;	
		}
		else
		{ 	
		System.out.println("Registration failed");
		return false;	 
		}
	}
	
	public int showAllPizza()
	{
	System.out.println("Pizza's list:");
	ArrayList <PizzaBean> pizzaList=cus.getAllPizza();
		for(int i=0;i<pizzaList.size();i++)
		{
		PizzaBean pizza=pizzaList.get(i);
		System.out.println(pizza);
		}
	return 0;	
	}
	
	public void placeOrder()
	{
		String pzname;
		String st;
		ArrayList <String> pizzaArr=new ArrayList<String>();
		System.out.println("Place Your Order: ");
		do
		{
		System.out.println("Enter the PizzaName you want to Order: ");
		pzname=sc.next();
		pizzaArr.add(pzname);
		System.out.println("DO you Want add More Pizza: (Y/N)");
		st=sc.next();
		}while(st.equals("Y") || st.equals("y"));	
		boolean b1=cus.makeOrder(pizzaArr);
		if(b1==true)
			System.out.println("Order is Placed");
		else
			System.out.println("Order not Placed");
	}
	
	public int viewOrder()
	{
	System.out.println("View your Order");
	ArrayList <PizzaBean> pizzaList=cus.getOrder();
	if(pizzaList!=null)
	{	
		for(int i=0;i<pizzaList.size();i++)
		{
		PizzaBean pizza=pizzaList.get(i);
		System.out.println(pizza);
		}
	}
	else
		System.out.println("Please place the order first");
	return 0;	
	}
	
	
	public void genBill()
	{
		
		float amount=cus.getBillAmount();
		if(amount!=0)
			System.out.println("Total Amount to be paid: "+amount);
		else
			System.out.println("No bill generated");
	}
	
	
	
}
