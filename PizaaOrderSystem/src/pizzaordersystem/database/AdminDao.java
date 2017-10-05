package pizzaordersystem.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzaordersystem.bean.LoginData;
import pizzaordersystem.bean.PizzaBean;

public class AdminDao extends BaseDAO {
	private int pizzaId;
	private String pizzaName;
	private String size;
	private float price;
	private String pizzaType;
	
	
	public int addPizzaDB(PizzaBean pizza_1)
	{
		PreparedStatement pstmt=null;
		try 
		{
			Connection conn1=conn;
			pstmt=conn1.prepareStatement("insert into pizza values(?,?,?,?,?)");
			pizzaId=pizza_1.getPizzaId();
			pizzaName=pizza_1.getPizzaName();
			pizzaType=pizza_1.getPizzaType();
			size=pizza_1.getSize();
			price=pizza_1.getPrice();
			pstmt.setInt(1, pizzaId);
			pstmt.setString(2, pizzaName);
			pstmt.setString(3, pizzaType);
			pstmt.setString(4, size);
			pstmt.setFloat(5, price);
			
			int i = pstmt.executeUpdate();
			return i;
			
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
	
	
	public boolean updatePizzaDataDB(PizzaBean pz,int pzId)
	{
		PreparedStatement pstmt=null;
		
		try {
			Connection conn1=conn;
			pstmt=conn1.prepareStatement("update pizza set pizzaName = ?, pizzaType = ?, pizzaSize = ?, pizzaPrice = ? where pizzaId = ?");
			pizzaId=pzId;
			pizzaName=pz.getPizzaName();
			pizzaType=pz.getPizzaType();
			size=pz.getSize();
			price=pz.getPrice();
		
			
			pstmt.setString(1, pizzaName);
			pstmt.setString(2, pizzaType);
			pstmt.setString(3, size);
			pstmt.setFloat(4, price);
			pstmt.setInt(5, pizzaId);
			
			int i = pstmt.executeUpdate();
			System.out.println(i);
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
	
	
	public int deletePizzaCountDB(int pzId)
	{
		PreparedStatement pstmt=null;
		try 
		{
			Connection conn1=conn;
			pstmt=conn1.prepareStatement("delete from pizza where pizzaId=?");
			
			pstmt.setInt(1, pzId);
			int i = pstmt.executeUpdate();
			return i;
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
	
	public ArrayList getAllPizza()
	{
	PreparedStatement pstmt=null;
	ArrayList<PizzaBean> pizzaList;
	try {
		pizzaList = new ArrayList<PizzaBean>();
		Connection conn1=conn;
		pstmt=conn1.prepareStatement("select * from pizza order by pizzaid");
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
	


	public int checkPizzaId(int pzId)
	{	
		PreparedStatement pstmt=null;
		try 
		{
			Connection conn1=conn;
			pstmt=conn1.prepareStatement("select pizzaName from pizza where pizzaId=?");
			
			pstmt.setInt(1, pzId);
			int i = pstmt.executeUpdate();
			return i;
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

	public PizzaBean getPizza(int pzId)
	{
		PreparedStatement pstmt=null;
		try 
		{
			Connection conn1=conn;
			pstmt=conn1.prepareStatement("select * from pizza");
			ResultSet rs=pstmt.executeQuery();
			PizzaBean pz=new PizzaBean();
				while(rs.next())
				{
					PizzaBean pizza=new PizzaBean();
					pizza.setPizzaId(rs.getInt(1));
					pizza.setPizzaName(rs.getString(2));
					pizza.setPizzaType(rs.getString(3));
					pizza.setSize(rs.getString(4));
					pizza.setPrice(rs.getFloat(5));
				}
				return pz;
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
	
	
	public Boolean isLogin(LoginData data)
	{  
		String user=data.getUsername();
		String pass=data.getPassword();
		String tablePassword="";
		PreparedStatement pstmt=null;
			try {
				Connection conn1=conn;
				pstmt=conn1.prepareStatement("select password from AdminData where username = ?");
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

}
