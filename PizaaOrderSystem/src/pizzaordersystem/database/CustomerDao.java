package pizzaordersystem.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pizzaordersystem.bean.LoginData;
import pizzaordersystem.bean.PizzaBean;
import pizzaordersystem.bean.RegistrationData;

public class CustomerDao extends BaseDAO{
	
	private int pizzaId;
	private String pizzaName;
	private String size;
	private float price;
	private String pizzaType;
	private float amt=0;
	private int count=0;
	
	
	

	public boolean isLogin(LoginData data)
	{  
		String user=data.getUsername();
		String pass=data.getPassword();
		String tablePassword="";
		PreparedStatement pstmt=null;
			try {
				Connection conn1=conn;
				pstmt=conn1.prepareStatement("select password from CustomerData where username = ?");
				pstmt.setString(1,user);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next())
				{
					tablePassword=rs.getString(1);	
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		finally
		{
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			if(pass.equals(tablePassword))
			{
				//System.out.println(tablePassword);
				System.out.println("Match found");
				return true;
			}
			else
			{
				//System.out.println(tablePassword);
				System.out.println("Match not Found");
				return false;
			}
	}
	
	
	
	 public boolean isRegister(RegistrationData rd)
	    {
	     
	        String user=rd.getUsername();
	        int contact=rd.getContact();
	        String pass=rd.getPassword();
	        String conpass=rd.getConfrimPassword();
	        PreparedStatement pstmt=null;
	        
	        try {
				Connection conn1=conn;
				pstmt=conn1.prepareStatement("insert into CustomerData values(?,?,?,?)");
				pstmt.setString(1, user);
				pstmt.setInt(2, contact);
				pstmt.setString(3, pass);
				pstmt.setString(4, conpass);
				
				int i = pstmt.executeUpdate();
				if(i==1)
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	finally
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	        return false;
	    }
	 
	 public ArrayList getAllPizza()
		{
		PreparedStatement pstmt=null;
		ArrayList<PizzaBean> pizzaList;
		try {
			pizzaList = new ArrayList<PizzaBean>();
			Connection conn1=conn;
			pstmt=conn1.prepareStatement("select * from pizza");
			ResultSet rs=pstmt.executeQuery();
				while(rs.next())
				{
					PizzaBean pizza=new PizzaBean();
					pizza.setPizzaId(rs.getInt(1));
					pizza.setPizzaName(rs.getString(2));
					pizza.setPizzaType(rs.getString(3));
					pizza.setSize(rs.getString(4));
					pizza.setPrice(rs.getFloat(5));
					pizzaList.add(pizza);
				}
				return pizzaList;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			finally
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				return null;
		}
	 
	 
	 public boolean makeOrder(ArrayList ar)
	 {
		 PreparedStatement pstmt=null,pstmt1=null;
		 PizzaBean pizza=new PizzaBean();
		 int id=0,j=0;
		 for(int i=0;i<ar.size();i++)
			{
				 pizzaName=(String)ar.get(i);
				// System.out.println(pizzaName);
				 Connection conn1=conn;
				 try {
					pstmt=conn1.prepareStatement("select * from pizza where pizzaname=?");
					 pstmt.setString(1, pizzaName);
					 ResultSet rs=pstmt.executeQuery();
					 if(rs.next()) 
					 {
						 id=(rs.getInt(1));
						 //System.out.println(id);
					 }
					 
				
					if(id!=0)
					{
					pstmt1=conn1.prepareStatement("insert into placedorder values(?,?,?,?,?)");
					pizza=getPizzaId(id);
					pstmt1.setInt(1, pizza.getPizzaId());
					pstmt1.setString(2, pizza.getPizzaName());
					pstmt1.setString(3, pizza.getPizzaType());
					pstmt1.setString(4, pizza.getSize());
					pstmt1.setFloat(5, pizza.getPrice());
					
					j = pstmt1.executeUpdate();
					j++;
					}	 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					finally
					{
						try {
							pstmt.close();
							pstmt1.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			}
		 if(j!=0)
		 {
			 return true;
		 }
		 else
			 return false;
	 }
	 
		public PizzaBean getPizzaId(int pzId)
		{
			PreparedStatement pstmt=null;
			try 
			{
				Connection conn1=conn;
				pstmt=conn1.prepareStatement("select * from pizza where pizzaId=?");
				PizzaBean pizza=null;
				pstmt.setInt(1, pzId);
				ResultSet rs=pstmt.executeQuery();
					if(rs.next())
					{
						pizza=new PizzaBean();
						pizza.setPizzaId(rs.getInt(1));
						pizza.setPizzaName(rs.getString(2));
						pizza.setPizzaType(rs.getString(3));
						pizza.setSize(rs.getString(4));
						pizza.setPrice(rs.getFloat(5));
					}
					return pizza;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			finally
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				return null;
		}
		
		 public ArrayList getOrder()
			{
			PreparedStatement pstmt=null;
			ArrayList<PizzaBean> pizzaList=null;
			try {
				
				Connection conn1=conn;
				pstmt=conn1.prepareStatement("select * from placedorder");
				ResultSet rs=pstmt.executeQuery();
				pizzaList = new ArrayList<PizzaBean>();	
					while(rs.next())
					{
						PizzaBean pizza=new PizzaBean();
						pizza.setPizzaId(rs.getInt(1));
						pizza.setPizzaName(rs.getString(2));
						pizza.setPizzaType(rs.getString(3));
						pizza.setSize(rs.getString(4));
						pizza.setPrice(rs.getFloat(5));
						pizzaList.add(pizza);
					}
					return pizzaList;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				finally
				{
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					return null;
			}
		 

		 public float getBillAmount()
		 {
			 PreparedStatement pstmt=null,pstmt1=null;
				try 
				{
					count=0;
					Connection conn1=conn;
					pstmt=conn1.prepareStatement("select count(pizzaid), sum(pizzaprice) from placedorder");
					ResultSet rs=pstmt.executeQuery();
						if(rs.next())
						{
							count=rs.getInt(1);
							amt=rs.getInt(2);
						}
						System.out.println("Total Pizza Ordered are: "+count);
						
						if(amt!=0)
						{
							/*pstmt1=conn1.prepareStatement("delete placedorder");
							pstmt.executeUpdate();
							pstmt1=conn1.prepareStatement("commit");
							pstmt.executeUpdate();
							*/
							Statement st=conn1.createStatement();
							st.execute("delete placedorder");
							return amt;	
						}
						
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				finally
				{
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			return 0;
		 }
}



