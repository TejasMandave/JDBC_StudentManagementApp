package com.tejas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

class Operations {

	Scanner sc = null;
	Connection connection = null;
	PreparedStatement pstm = null;
	ResultSet resultset = null;
	
	
	public void addnewrecord() {
		try {
			connection = JdbcUtil.getJdbcConnection();
			sc = new Scanner(System.in);

			connection = JdbcUtil.getJdbcConnection();

			String sqlquery = "insert into student (`sname`,`sage`,`saddress`) values(?,?,?) ";

			pstm = connection.prepareStatement(sqlquery);

			System.out.println("Please Enter the Name of Student");
			String sname = sc.next();

			System.out.println("Please Enter the Age of Student");

			int age = Integer.parseInt(sc.next());

			System.out.println("Please Enter the Address of Student");
			String address = sc.next();

			pstm.setString(1, sname);
			pstm.setInt(2, age);
			pstm.setString(3, address);

			int row = pstm.executeUpdate();
			System.out.println("Record Added Sucessfully Affected Rows: " + row);

			ConsoleApplication.main(null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	public void viewrecords() {
		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null) {

				
				
				
				String sqlquery = "select * from student where sid=?";
				
				
				
				pstm = connection.prepareStatement(sqlquery);

				sc = new Scanner(System.in);

				System.out.println("Please Enter the id of student to view correspondig Record.");
				int id = sc.nextInt();
				
				
				pstm.setInt(1, id);

				resultset = pstm.executeQuery();
				if (resultset!=null) {

					System.out.println("ID      Name      age      Address");

					while (resultset.next()) {
						int id2 = resultset.getInt(1);
						String name = resultset.getString(2);
						int age = resultset.getInt(3);
						String address = resultset.getString(4);

						System.out.println(id2 + "      " + name + "      " + age + "      " + address + "");
					}
				}
				else {
					System.out.println("No Record feched ,Record Dosen't Present with Correspondig Id:"+id);
				}
					ConsoleApplication.main(null);

				}

			
			
			} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	
	public void deleterecord() {
		
		
		try {
			
			connection=JdbcUtil.getJdbcConnection();
			
			String sqlquery="delete from student where sid=?;";
			
			sc=new Scanner(System.in);
			System.out.println("Enter the id of Student to Deleted the Record");
			int id=sc.nextInt();
			
			pstm=connection.prepareStatement(sqlquery);
			
			pstm.setInt(1, id);
			
			int row=pstm.executeUpdate();
			
			if(row!=0)
			System.out.println("Record Deleted Sucessfully ,Affected rows are: "+row);
			else 
				System.out.println("No Record deleted ,Record Dosen't Present with Correspondig Id:"+id);
			ConsoleApplication.main(null);
		}catch (Exception e) {

e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	
	
	public void updaterecord() {
		
		try {
			
		String sqlquery="update student set sname=?,sage=?,saddress=? where sid=?";
		connection =JdbcUtil.getJdbcConnection();
		pstm=connection.prepareStatement(sqlquery);
		
		sc=new Scanner(System.in);
		
		System.out.println("Enter the ID to Update the record.");
		int id=sc.nextInt();
		
		System.out.println("Enter the new name.");
		String name=sc.next();
		
		System.out.println("Enter the age to update.");
		int age=sc.nextInt();
		
		System.out.println("Enter the new address");
		String address=sc.next();
		
		
		pstm.setString(1, name);
		pstm.setInt(2, age);
		pstm.setString(3, address);
		pstm.setInt(4,id);
		
		int row=pstm.executeUpdate();
		
		System.out.println("Data Successfully Updated Rows Affected: "+row);
		
		
	ConsoleApplication.main(null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
}

public class ConsoleApplication {

	public static void main(String[] args) {

		Operations op = new Operations();
System.out.println();
		System.out.println("         ::Welcome to Student Management Portal::     ");

		System.out.println();
		System.out.println("Select a  Option below to perform a Operation.");
		
		System.out.println("Press 1 to Add a new A Student Record.");
		System.out.println("Press 2 to Update existing Student Record.");
		System.out.println("Press 3 to Delete Student Record.");
		System.out.println("Press 4 to View Student Record.");
		System.out.println("Press 5 to Exit !.");
		Scanner sc = new Scanner(System.in);

		String option = sc.next();

		if (option.equals("1")) {

			op.addnewrecord();
		} 
		else if (option.equals("2")) {
			op.updaterecord();
		}

		else if (option.equals("3")) {
			op.deleterecord();
		}

		else if (option.equals("4")) {
			op.viewrecords();
		}
		
		  else if (option.equals("5")) {
			System.out.println("Logged Off...!");
			
			
			System.exit(0);
		}  
		   
		   else {

			System.out.println(option);

			System.out.println("You have Entered Wrong Option..! Please Try Again.!");
			
			ConsoleApplication.main(null);
		}

	}

}
