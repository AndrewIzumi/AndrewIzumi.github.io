package view;

import model.Course;
import model.Model;

import java.util.List;

import controller.Controller;
import controller.StudentCourseController.ViewType;

public class UnregisterCourseView extends View {
	public UnregisterCourseView(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;
	}
	
	public void display() {
		while (true) {
			System.out.println("\nRegistered Courses for " + model.getUserName());
			List<Course> registeredCourses = model.getRegisteredCourses();
			if (registeredCourses.isEmpty()) {
				System.out.println("No courses registered.");
			}
			for (Course course : registeredCourses) {
				System.out.println("------------------------------------------");
				System.out.println(course);
			}
			
			boolean loop = true;
			while (loop) {
				System.out.println("\n1) Unregister course.");
				System.out.println("2) Return to main view");
				System.out.print("Enter selection:  ");
				
				String selection = controller.systemInput().nextLine();
				switch (selection) {
				case "1": {
					System.out.print("\nEnter Course Id:  ");
					String courseId = controller.systemInput().nextLine();
					loop = false;
					break;
				}
				case "2": {
					controller.setView(ViewType.MAIN_VIEW);
					return;
				}
				default:
					System.out.println("Invalid selection, try again.");
				}
			}
		}
	}
}
