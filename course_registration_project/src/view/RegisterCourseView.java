package view;

import java.util.List;

import model.Course;
import model.Model;
import controller.Controller;
import controller.Controller.ViewType;

public class RegisterCourseView extends View {
   public RegisterCourseView(Model model, Controller controller) {
      this.model = model;
      this.controller = controller;
   }

   /**
    * Displays messages and prompts for registering course
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

         System.out.println("1) Add new course\n");
         System.out.println("2) Go to main view\n");
         System.out.print("Enter selection:  ");

         String selection = controller.systemInput().nextLine();
         switch (selection) {
         case "1": {
            System.out.print("\nEnter Course Id:  ");
            String courseId = controller.systemInput().nextLine();
            if (model.registeredCourseExists(courseId)) {
               clearScreen();
               System.out.println("\nCourse " + courseId + " already exists in schedule.");
            } else if (!model.openCourseExists(courseId)) {
               clearScreen();
               System.out.println("\nCourse " + courseId + " is not an existing open course.");
            } else if (!model.isOpenCourseAvailable(courseId)) {
               clearScreen();
               System.out.println("\nCourse " + courseId + " is full.");
            } else {
               model.registerCourse(courseId);
               clearScreen();
               System.out.println("\nCourse " + courseId + " added to schedule.");
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
