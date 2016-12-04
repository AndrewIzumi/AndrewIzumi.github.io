package view;

import java.util.List;

import model.Model;
import model.Course;
import controller.Controller;

public class StudentProfileView extends View {
	public StudentProfileView(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;
	}
	
	public void display() {
		System.out.println("Logged in as " + model.getUserName());
		System.out.println("Name: " + model.getUserFullName());
		System.out.println();
		System.out.println("Registered Courses");
		for (Course course : model.getRegisteredCourses()) {
			System.out.println("------------------------------------------");
			System.out.println(course);
		}
	}
}
