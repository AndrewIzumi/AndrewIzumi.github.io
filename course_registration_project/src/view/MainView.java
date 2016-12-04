package view;

import model.Model;
import controller.Controller;
import controller.StudentCourseController.ViewType;

public class MainView extends View {
	public MainView(Model model, Controller controller) {
		this.model = model;
		this.controller = controller;
	}
	
	public void display() {
		boolean loop;
		do {
			System.out.println("\nMain Menu");
			System.out.println("Logged in as " + model.getUserName());
			System.out.println("1) Register new course");
			System.out.println("2) Unregister course");
			System.out.println("3) View registered courses");
			System.out.println("4) View available courses");
			System.out.println("5) Log out");
			System.out.println("6) Exit program");
			System.out.print("Enter selection:  ");
			
			loop = false;
			
			String selection = controller.systemInput().nextLine();
			switch (selection) {
			case "1":
				controller.setView(ViewType.REGISTER_VIEW);
				break;
			case "2":
				controller.setView(ViewType.UNREGISTER_VIEW);
				break;
			case "3":
				controller.setView(ViewType.PROFILE_VIEW);
				break;
			case "4":
				controller.setView(ViewType.COURSE_VIEW);
				break;
			case "5":
				model.clearCurrentStudent();
				controller.setView(ViewType.LOGIN_VIEW);
				System.out.println("Logged out of system.");
				break;
			case "6":
				System.out.println("Exited registration system.");
				System.exit(0);
			default:
				loop = false;
				System.out.println("Invalid selection, try again.");
			}
		} while (loop);
	}
}
