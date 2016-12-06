package view;

import model.Course;
import model.Model;

import java.util.List;

import controller.Controller;
import controller.Controller.ViewType;

public class UnregisterCourseView extends View {
   public UnregisterCourseView(Model model, Controller controller) {
      this.model = model;
      this.controller = controller;
   }

   /**
    * Displays messages and prompts for unregistering course
    * Allows user the option to go to main view
    */
   @Override
   public void display() {
      boolean loop = true;
      while (loop) {
         System.out.println("\nRegistered Courses for " + model.getUserName() + '\n');
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

         System.out.println("1) Unregister course\n");
         System.out.println("2) Go to main view\n");
         System.out.print("Enter selection:  ");

         String selection = controller.systemInput().nextLine();
         switch (selection) {
         case "1": {
            System.out.print("\nEnter Course Id:  ");
            String courseId = controller.systemInput().nextLine();
            if (!model.registeredCourseExists(courseId)) {
               clearScreen();
               System.out.println("\nCourse " + courseId + " does not exist in schedule.");
            } else {
               model.unregisterCourse(courseId);
               clearScreen();
               System.out.println("\nCourse " + courseId + " has been removed from schedule.");
            }
            break;
         }
         case "2": {
            controller.setView(ViewType.MAIN_VIEW);
            loop = false;
            break;
         }
         default:
            clearScreen();
            System.out.println("Invalid selection, try again.");
         }
      }
   }
}
