package pizzaordersystem.service;

import java.util.ArrayList;

import pizzaordersystem.bean.LoginData;
import pizzaordersystem.bean.PizzaBean;
import pizzaordersystem.database.AdminDao;
import pizzaordersystem.exception.InvalidIdException;
import pizzaordersystem.exception.InvalidNameException;
import pizzaordersystem.exception.InvalidSizeException;
import pizzaordersystem.exception.InvalidTypeException;


public class AdminService
{		
	private static AdminDao adao=new AdminDao();

	
	public boolean validLogin(LoginData ld)
	{
		return adao.isLogin(ld);
	}
	
	
	public int validAdd(PizzaBean pz) 
	{	
		int i=0;
		try
		{
			if(pz==null)
			{
			throw new NullPointerException();
			}	
			else if(pz.getPizzaId()<=0)
			{
				throw new InvalidIdException();
			}
			else if(pz.getPizzaName().equals(null) || pz.getPizzaName().equals(" ") || pz.getPizzaName().matches(".*\\d.*"))
			{
				throw new InvalidNameException();
			}
			else if(!(pz.getPizzaType().equals("veg")) && !(pz.getPizzaType().equals("non-veg")))
			{
				throw new InvalidTypeException();
			}
			else if(!(pz.getSize().equals("R")) && !(pz.getSize().equals("M")) && !(pz.getSize().equals("L")))
			{
				throw new InvalidSizeException();
			}
			else
				i=adao.addPizzaDB(pz);	
		}
		catch(NullPointerException npe)
		{
			return (0);
		}
		catch(InvalidIdException ine)
		{
			ine.idInvalid();
		}
		catch(InvalidNameException ine)
		{
			ine.nameInvalid();
		}
		catch(InvalidTypeException ine)
		{
			ine.typeInvalid();
		}
		catch(InvalidSizeException ine)
		{
			ine.sizeInvalid();
		}
		return i;
	}
	
	
	public ArrayList validDisplay()
	{
		return adao.getAllPizza();
		 
	}
	
	public boolean validUpdate(PizzaBean pz,int pzId)
	{
		try
		{
			if(pz==null)
			{
			throw new NullPointerException();
			}	
			return adao.updatePizzaDataDB(pz,pzId);
		}
		catch(NullPointerException npe)
		{
			return false;
		}		 
	}
	
	public int checkPizza(int pzId)
	{
		return adao.checkPizzaId(pzId);
		 
	}
	
	
	
	public int validDelete(int pzId)
	{
		
		try
		{
			PizzaBean pz=adao.getPizza(pzId);
			if(pz==null)
			{
			throw new NullPointerException();
			}	
			return adao.deletePizzaCountDB(pzId);
			 
		}
		catch(NullPointerException npe)
		{
			return 0;
		}
	
	}
}
