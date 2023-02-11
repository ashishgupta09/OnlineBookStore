package Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BookStore {
	
	Scanner sc=new Scanner(System.in);

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		
		BookStore Bk =new BookStore();
		
		int ch;
		do
		{
			System.out.println("Ente the your choice");
			
			System.out.println("1)Insert The Record");
			System.out.println("2)Display the Records");
			System.out.println("3)Upadate The Record");
			System.out.println("4)Delete The Record");
			System.out.println("5)Exit");
			
			ch=sc.nextInt();
			
			switch (ch) {
			
			case 1:
				 
				  Bk.insertData();
				  
			case 2:
				
				  Bk.displayData();
				
			case 3:
				
				  Bk.updateData();
				  
			case 4:
				
				  Bk.deleteData();
				  
			case 5:
				
				  System.out.println("thank u for visiting us");
				
				break;
				
				default:
					break;
			
			}
			
			
		}
		while(ch!=5);
    }
	
	
	   public Connection getConnect(){
		   
		    try {
		    	
		    	DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		    	
		    	Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","@9b8c7D6E5F4");
		    	
		    	return con;
		    	
		    }catch(Exception e) {
		    	e.printStackTrace();
		    }
		   return null;
		   
	   }
	   
	   
        
	    public void insertData() {
	    	
	     Book b= new Book();

         System.out.println("Enter the id");
	     int Id=sc.nextInt();
         b.setId(Id);
	     
	     System.out.println("Enter the Book name");
	     String Bname=sc.next();
	     b.setBName(Bname);
	     
	     System.out.println("Enter the Book Author name");
	     String AutName=sc.next();
	     b.setAutName(AutName);
	     
	     System.out.println("Enter the Book price");
	     double price=sc.nextDouble();
	     b.setPrice(price);
	     
	     try {
	    	 Connection con=getConnect();
	    	 
	    	 PreparedStatement pst=con.prepareStatement("insert into bookstore(id,Bname,AutName,Price) values(?,?,?,?)");
	    	 pst.setInt(1,b.getId());
	    	 pst.setString(2,b.getBName());
	    	 pst.setString(3,b.getAutName());
	    	 pst.setDouble(4,b.getPrice());
	    	 
	    	 int x=pst.executeUpdate();
	    	 pst.close();
	    	 con.close();
	    	 
	    	 if(x==1) {
	    		 System.out.println("Record Inserted Successfully");
	    	 }
	    	 
	    	 
	     }catch (Exception e) {
	    	 e.printStackTrace();
	     }
	    }
	

	    
		public void displayData() {
	    	try {
	    		
	    	Connection con=getConnect();
	    	
	    	PreparedStatement pst=con.prepareStatement("select * from bookstore");

	    	ResultSet rs=pst.executeQuery();
	    	
	    	while(rs.next())
	    	{
	    		
	    	System.out.println( rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
	    	
	    	}
	    	rs.close();
	    	pst.close();
	    	con.close();
		
	    	
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	   }
	    
		

	     public void updateData() {
			
	    	 Book b=new Book();
	    	 
	    	 System.out.println("Enter the id");
	    	 int Id = sc.nextInt();
	    	 b.setId(Id);
	    	 
	    	 System.out.println("Enter the BName");
	    	 String BName=sc.next();
	    	 b.setBName(BName);
	    	 
	    	 System.out.println("Enter the AutName");
	    	 String AutName=sc.next();
	    	 b.setAutName(AutName);
	    	 
	    	 System.out.println("Enter the price");
	    	 double Price=sc.nextDouble();
	    	 b.setPrice(Price);
	    	 
	    	 try {
	    		 Connection con=getConnect();
	    		 String str="update bookstore set BName=?,AutName=?,Price=? where id=?";
	    		 PreparedStatement pst=con.prepareStatement(str);
	    		 
	    		
	    		 pst.setString(1,b.getBName());
	    		 pst.setString(2,b.getAutName());
	    		 pst.setDouble(3,b.getPrice());
	    		 pst.setInt(4,b.getId());
	    		
	    		 
	    		 int x=pst.executeUpdate();
	    		 
	    		 pst.close();
	    		 pst.close();
	    		 
	    		 if(x==1) {
	    			 System.out.print("Update done");
	    		 }
	    		 else{
	    			 System.out.print("Update not done");
	    		 }
	    	 } catch(Exception e) {
	    		 e.printStackTrace();
	    	 }
	    	 
		}
	     
	  
	
		public void deleteData() {
			
			try {
				int Id;
				System.out.println("Enter the record number to delete");
				
				Id=sc.nextInt();
				Book b =new Book();
				b.setId(Id);
				Connection con=getConnect();
				PreparedStatement pst=con.prepareStatement("delete from bookstore where Id=?");
				
				pst.setInt(1, Id);
				
				int x=pst.executeUpdate();
				
				pst.close();
				con.close();
				if(x==1) {
					System.out.println("record deleted");
				}
				else {
					System.out.println("record not found");
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}	
}
