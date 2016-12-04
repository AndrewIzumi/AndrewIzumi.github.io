package dataio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import model.Course;

public class DataInput {
	private String filePath;
	
	public DataInput(String filePath) {
		this.filePath = filePath;
	}
	
	private Course parseLine(String line) {
		String[] args = line.split("\\|");
		if (args.length != 7) {
			return null;
		}
		String id = args[0];
        String name = args[1];
        String startDate = args[2];
        String endDate = args[3];
        String summary = args[4];
        try {
    		int enrollLimit = Integer.parseInt(args[5]);
    		int enrolledNum = Integer.parseInt(args[6]);
    		return new Course(name, id, summary, startDate, endDate, enrollLimit, enrolledNum);
        } catch (NumberFormatException ex) {
        	return null;
        }
	}
	
	public boolean parseCourseData(List<Course> courseList) {
		Scanner input = null;
		try {
			File file = new File(filePath);
			input = new Scanner(file);
			input.useDelimiter("\\|");
	        while (input.hasNext()) {
	            String line = input.nextLine();
	            Course course = parseLine(line);
	            if (course == null) {
	            		return false;
	            }
	            courseList.add(course);
	        }
        } catch (FileNotFoundException ex) {
        		return false;
        } finally {
        		input.close();
        }
		return true;
	}
}
