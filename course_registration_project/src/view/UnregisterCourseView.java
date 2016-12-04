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
            System.out.println("\n1) Unregister course");
            System.out.println("2) Go to main view");
            System.out.print("Enter selection:  ");

            String selection = controller.systemInput().nextLine();
            switch (selection) {
            case "1": {
               System.out.print("\nEnter Course Id:  ");
               String courseId = controller.systemInput().nextLine();
               if (!model.registeredCourseExists(courseId)) {
                  System.out.println("Course " + courseId + " does not exist in schedule.");
               } else {
                  model.unregisterCourse(courseId);
                  loop = false;
                  System.out.println("Course " + courseId + " has been removed from schedule.");
               }
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
