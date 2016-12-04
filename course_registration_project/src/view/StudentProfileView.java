package view;

import java.util.List;

import model.Model;
import model.Course;
import controller.Controller;
import controller.StudentCourseController.ViewType;

public class StudentProfileView extends View {
   public StudentProfileView(Model model, Controller controller) {
      this.model = model;
      this.controller = controller;
   }

   public void display() {
      System.out.println("\nLogged in as " + model.getUserName());
      System.out.println("Name: " + model.getUserFullName());
      System.out.println();
      System.out.println("Registered Courses");
      for (Course course : model.getRegisteredCourses()) {
         System.out.println("------------------------------------------");
         System.out.println(course);
      }
      System.out.println("\n1) Go to main view");
      System.out.println("2) Log out");
      System.out.print("Enter selection:  ");
      
      String selection = controller.systemInput().nextLine();
      switch (selection) {
      case "1":
    	  controller.setView(ViewType.MAIN_VIEW);
    	  break;
      case "2":
    	  model.clearCurrentStudent();
          controller.setView(ViewType.LOGIN_VIEW);
          System.out.println("Logged out of system.");
          break;
      default:
    	  System.out.println("Invalid selection, try again.");
      }
   }
}
