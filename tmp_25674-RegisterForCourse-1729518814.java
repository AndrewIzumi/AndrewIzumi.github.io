import java.util.Date;
import java.util.Scanner;
import java.lang.Double;
import java.security.*;
import java.math.*;
import java.util.*;
import java.io.*;

public class RegisterForCourse extends ReadCourseList
{
		public static void main(String[] args) throws Exception{
			
			 Scanner input = new Scanner(System.in);
			 input.useDelimiter("[,\n]");
			
			 Register id1 = new Register();
			 id1.setId(1234);
			 System.out.println("Enter StudentID:  ");
			 id1.setStudentID(input.nextInt());
			 System.out.println("Enter first name:  ");
			 id1.getFirstName(input.next());
			 System.out.println("Enter last name:  ");
			 id1.getLastName(input.next());
			 System.out.println("Enter middle initial:  ");
			 id1.getMI(input.next());
			 id1.setStudentName();
			 System.out.println("What course would you like to register for:  ");	
			 id1.setCourseID(input.next());
			 System.out.println(id1.toString());
			 
			java.io.File file = new java.io.File("1234.txt");
			if (file.exists()) {
				System.out.println("File already exists");
				System.exit(0);
			}

			// Create a file
			java.io.PrintWriter output = new java.io.PrintWriter(file);

			// Write formatted output to the file
			output.print(id1.toString());
			
			// Close the file
			output.close();
		}
		
	}
	class Register
	{	
		private int id = 0;
		private Date dateCreated = new Date();
		private String lastName = "";
		private String firstName = "";
		private String mi = "";
		private String studentName = lastName + ", " + firstName + mi;
		private int studentID;

		public String courseID = "";

		public Register(){
		}
		;public int getId(){
			return id;
		}	
		public void setId(int id){
			this.id = id;
		}
		public String getCourseID(){
			return courseID;
		}
		public void setCourseID(String courseID){
			this.courseID = courseID;
		}
		public int getStudentID(){
			return studentID;
		}
		public void setStudentID(int studentID){
			this.studentID = studentID;
		}
		public String studentName(){
			return studentName;
		}
		public Date getDateCreated(){
			return dateCreated;
		}
		public void setStudentName(){
			this.studentName = lastName + ", " + firstName + mi;
		}
		public String getFirstName(String firstName){
			this.firstName = firstName;
			return firstName;
		}
		public String getLastName(String lastName){
			this.lastName = lastName;
			return lastName;
		}
		public String getMI(String mi){
			this.mi = mi;
			return mi;
	}
		
		public String toString(){
			return "Registration Confirmation No.:  " + id + "\n"
				+ "Student Name: " + studentName + "\n"
				+ "Student ID:  " + studentID + "\n"
				+ "Date Created:  " + dateCreated + "\n"
				+ "Course ID:  " + courseID + "\n"
				+ "Course Details:" + id + "\n";

		}
	
}
