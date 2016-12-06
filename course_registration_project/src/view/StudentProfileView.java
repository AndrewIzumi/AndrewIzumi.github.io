package view;

import java.util.List;

import model.Model;
import model.Course;
import controller.Controller;
import controller.Controller.ViewType;

public class StudentProfileView extends View {
   public StudentProfileView(Model model, Controller controller) {
      this.model = model;
      this.controller = controller;
   }

   /**
    * Displays student profile and registered courses
    * Allows user the option to go to main view or login view
    */
   @Override
   public void display() {
      boolean loop = true;
      while (loop) {
         System.out.println("\nLogged in as " + model.getUserName() + '\n');
         System.out.println("Name: " + model.getUserFullName() + '\n');
         System.out.println("Registered Courses\n");
         List<Course> registeredCourses = model.getRegisteredCourses();
         if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.\n");
         } else {
            for (Course course : registeredCourses) {
               System.out.println("------------------------------------------");
               System.out.println(course);
            }
            System.out.println("------------------------------------------\n");
         }
         System.out.println("1) Go to main view\n");
         System.out.println("2) Log out\n");
         System.out.print("Enter selection:  ");

         String selection = controller.systemInput().nextLine();
         switch (selection) {
         case "1":
            controller.setView(ViewType.MAIN_VIEW);
            loop = false;
            break;
         case "2":
            model.clearCurrentStudent();
            controller.setView(ViewType.LOGIN_VIEW);
            loop = false;
            System.out.println("Logged out of system.");
            break;
         default:
            clearScreen();
            System.out.println("Invalid selection, try again.");
         }
      }
   }
}
