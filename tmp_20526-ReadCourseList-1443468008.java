import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.Scanner;

	public class ReadCourseList {
		public static void main(String[] args) throws Exception {
			// Create a File instance
			java.io.File file = new java.io.File("storage/emulated/0/CourseList.txt");

			// Create a Scanner for the file
			Scanner input = new Scanner(file);
			input.useDelimiter("[,\n]");

			// Read data from a file

			//ID,Name,Dates,Description,Limit,Enrolled
			while (input.hasNext()) {
				String id = input.next();
				String name = input.next();
				String dates = input.next();
				String description = input.next();
				String limit = input.next();
				String enrolled = input.next();

				System.out.println(
					id + "|" + name + "|" + dates + "|" + description +
					"|" + limit + "|" + enrolled + "||");
			}


			// Close the file
			input.close();
		}

	
}
