package model;

import java.util.List;
import java.util.ArrayList;
import java.io.File;

import dataio.DataInput;

public class Model {
	private List<Student> students = new ArrayList<>();
	
	private List<Course> openCourses = new ArrayList<>();
	
	private Student currentStudent;
	
	private static final String courseFilePath = "data" + File.separator + "courses.txt";
	
	public Model() {
		if (!loadCourses(courseFilePath)) {
			System.out.println("Failed to load course file " + courseFilePath);
		}
	}
	
	public void addStudent(String firstName, String lastName, String userName, String password) {
		Student newStudent = new Student(firstName, lastName, userName, password);
		students.add(newStudent);
		currentStudent = newStudent;
	}
	
	public boolean verifyAccount(String userName, String password) {
		for (Student student : students) {
			if (student.getUserName().equals(userName)) {
				if (student.getPassword().equals(password)) {
					currentStudent = student;
					return true;
				}
			}
		}
		return false;
	}
	
	public String getUserName() {
		return currentStudent != null ? currentStudent.getUserName() : "";
	}
	
	public String getUserFullName() {
		return currentStudent != null ? currentStudent.getFullName() : "";
	}
	
	public void clearCurrentStudent() {
		currentStudent = null;
	}
	
	public void registerCourse(String courseId) {
		if (currentStudent != null) {
			Course course = findOpenCourse(courseId);
			if (course != null) {
				currentStudent.addCourse(course);
				course.incrementEnrolledNum();
			}
		}
	}
	
	public List<Course> getOpenCourses() {
		return openCourses;
	}
	
	public Course findOpenCourse(String courseId) {
		for (Course course : openCourses) {
			if (course.getId().equals(courseId)) {
				return course;
			}
		}
		return null;
	}
	
	public List<Course> getRegisteredCourses() {
		return currentStudent != null ? currentStudent.getRegisteredCourses() : null;
	}
	
	public boolean registeredCourseExists(String courseId) {
		return currentStudent != null ? currentStudent.courseExists(courseId) : false;
	}
	
	public boolean openCourseExists(String courseId) {
		return findOpenCourse(courseId) != null;
	}
	
	public boolean isOpenCourseAvailable(String courseId) {
		Course course = findOpenCourse(courseId);
		return course != null ? course.isCourseAvailable() : false;
	}
	
	protected boolean loadCourses(String filePath) {
		DataInput dataInput = new DataInput(filePath);
		return dataInput.parseCourseData(openCourses);
	}
}
