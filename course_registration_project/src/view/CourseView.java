package view;

import java.util.List;

import model.Model;
import controller.Controller;
import controller.Controller.ViewType;
import model.Course;

public class CourseView extends View {
   public CourseView(Model model, Controller controller) {
      this.model = model;
      this.controller = controller;
   }

   /**
    * Displays prompts for displaying all open courses
    */
   @Override
   public void display() {
      System.out.println("\nOpen Registration Courses");
      System.out.println("------------------------------------------");
      List<Course> courses = model.getOpenCourses();
      for (int i = 0; i < courses.size(); i++) {
         System.out.println(courses.get(i));
         System.out.println("------------------------------------------");
         if ((i + 1) % 3 == 0) {
            System.out.println("Press enter to continue...");
            controller.systemInput().nextLine();
            if (i < courses.size() - 1) {
               clearScreen();
            }
         }
      }
      boolean loop = true;
      while (loop) {
         System.out.println("1) Go to Profile View\n");
         System.out.println("2) Go to Main View\n");
         System.out.println("3) Top of list\n");
         System.out.print("Enter Selection:  ");

         String selection = controller.systemInput().nextLine();
         switch (selection) {
         case "1":
            // Set next view to be displayed to student profile view
            controller.setView(ViewType.PROFILE_VIEW);
            loop = false;
            break;
         case "2":
            // Set next view to be displayed to main menu view
            controller.setView(ViewType.MAIN_VIEW);
            loop = false;
            break;
         case "3":
            loop = false;
            break;
         default:
            clearScreen();
            System.out.println("Invalid selection, try again.\n");
         }
      }
   }
}
