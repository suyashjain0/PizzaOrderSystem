package pizzaordersystem.database;

import pizzaordersystem.bean.PizzaBean;

public class Database {
	private static PizzaBean pizza[]=new PizzaBean[4];
	public static int count,flag,i,index;
	
	//to keep count of pizza
	public String addPizzaCount(PizzaBean pizza_1)
	{
	if(count>=4)
	{
	return ("Can't add more pizza");
	}
	else
	{
	pizza[count]=pizza_1;
	//System.out.println("Pizza Name added :"+pizza[count].getPizzaName());
	count++;

	return ("Pizza "+count+" added Successfully");
	}
	}
	
	public boolean deletePizzaCount(int pzId)
	{	
		flag=0;
		for(i=0;i<count;i++)
		{
			if(pizza[i]!=null && pizza[i].getPizzaId()==pzId)
			{
				flag=1;
				index=i;
				//System.out.println("id found for deletion having index : "+index+" and count :"+count);
				break;
			}			
		}
		if(flag==0)
		{
			return false;
		}
		else
		{	if(index==count-1)
			{
				pizza[index]=null;
				count--;
				//System.out.println("count :"+count);
				return true;
			}
		    else
		    {
		    	pizza[index]=null;			
		    	for(i=index;i<count-1;i++)
		    	{	
		    		pizza[i]=pizza[i+1];
		    	}
		    	pizza[i]=null;
		    	count=i;
		    	System.out.println("count :"+count);
		    	return true;
		    }
		}
	}
	
	public PizzaBean[] displayAllPizza()
	{	
		System.out.println(pizza);
		return pizza;
	}
	
	public boolean updatePizzaData(PizzaBean pz,int pzId)
	{	
		for(int i=0;i<pizza.length;i++)
		{
			if(pizza[i]!=null && pizza[i].getPizzaId()==pzId)
			{
			pizza[i].setPizzaName(pz.getPizzaName());
			pizza[i].setPizzaType(pz.getPizzaType());
			pizza[i].setSize(pz.getSize());
			pizza[i].setPrice(pz.getPrice());
			return true;
			}
		}	
		return false;
		
	}
	
	public boolean checkPizzaName(int pzId)
	{	
		for(int i=0;i<pizza.length;i++)
		{
			if(pizza[i]!=null && pizza[i].getPizzaId()==pzId)
			{
			return true;
			}
		}
		return false;
	}

	public PizzaBean getObject(int pzId)
	{
		for(int i=0;i<pizza.length;i++)
		{
			if(pizza[i]!=null && pizza[i].getPizzaId()==pzId)
			{
			return pizza[i];
			}
		}	
		return null;
	}

}
