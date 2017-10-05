package pizzaordersystem.controller;

import java.util.ArrayList;
import java.util.Scanner;

import pizzaordersystem.bean.LoginData;
import pizzaordersystem.bean.PizzaBean;
import pizzaordersystem.service.AdminService;

public class AdminController {
	int pizzaId;
	String pizzaName;
	String size;
	float price;
	String pizzaType;
	static int id=0;
	AdminService adm = new AdminService();
	PizzaBean[] pz1;
	PizzaBean pz;
	Scanner sc = new Scanner(System.in);
	
	
	
	public boolean loginContoller(LoginData ld)
	{
		boolean b1=adm.validLogin(ld);
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
	
	public int addPizza()
	{
	System.out.println("Add a Pizza");
	
	pz=new PizzaBean();
	pz.setPizzaId(id);
	System.out.println("Enter the Details of Pizza \n:");
	System.out.println("Enter the PizzaId: ");
	pizzaId=sc.nextInt();
	System.out.println("Enter the PizzaName: ");
	pizzaName=sc.next();
	System.out.println("Enter the PizzaType: (veg/non-veg)");
	pizzaType=sc.next();
	System.out.println("Enter the size : (R/M/L)");
	size=sc.next();
	System.out.println("Enter the price: ");
	price=sc.nextInt();
	pz.setPizzaId(pizzaId);
	pz.setPizzaName(pizzaName);
	pz.setPizzaType(pizzaType);
	pz.setSize(size);
	pz.setPrice(price);
	int i =adm.validAdd(pz);
	if(i!=0)
	System.out.println("Total rows inserted: "+i);
	else
	System.out.println("Object can not be null");	
	return 0;
	}
	
	
	public int updatePizza()
	{
	System.out.println("Update a Pizza");
	System.out.println("Enter the pizza Id you want to update");
	int pizId=sc.nextInt();
	int b1=adm.checkPizza(pizId);
	if(b1!=0)
	{
		System.out.println("Enter the New Details of Pizza having id = "+pizId);
		System.out.println("Enter the PizzaName");
		pizzaName=sc.next();
		System.out.println("Enter the PizzaType");
		pizzaType=sc.next();
		System.out.println("Enter the size : (R/M/L)");
		size=sc.next();
		System.out.println("Enter the price");
		price=sc.nextInt();
		pz=new PizzaBean();
		pz.setPizzaName(pizzaName);
		pz.setPizzaType(pizzaType);
		pz.setSize(size);
		pz.setPrice(price);
		boolean str=adm.validUpdate(pz,pizId);
		if(str==(true))
			System.out.println("Update successfull");
		else
			System.out.println("Object can not be null");
	}
	else
		System.out.println("Invalid pizzaId");
	return 0;
	}
	
	public int deletePizza()
	{
	System.out.println("Delete a Pizza");
	System.out.println("Enter the pizza Id you want to Delete");
	int pizId=sc.nextInt();
	int b1=adm.checkPizza(pizId);
	if(b1!=0)
	{
		System.out.println("Id found in Table");
		int b2=adm.validDelete(pizId);
		if(b2!=0)
			System.out.println("Delete successfull");
		else
			System.out.println("Object can not be null");	
	}
	else
		System.out.println("Invalid pizzaId or No Pizza Present");
		
	return 0;
	}
	
	public int showAllPizza()
	{
	System.out.println("Show All Pizza");
	ArrayList <PizzaBean> pizzaList=adm.validDisplay();
		for(int i=0;i<pizzaList.size();i++)
		{
		PizzaBean pizza=pizzaList.get(i);
		System.out.println(pizza);
		}
	return 0;	
	}

}
