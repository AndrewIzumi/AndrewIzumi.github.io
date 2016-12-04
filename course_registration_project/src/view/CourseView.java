package view;

import model.Model;
import controller.Controller;
import controller.StudentCourseController.ViewType;
import model.Course;

public class CourseView extends View {
   public CourseView(Model model, Controller controller) {
      this.model = model;
      this.controller = controller;
   }

   public void display() {
      System.out.println("\nOpen Registration Courses");
      for (Course course : model.getOpenCourses()) {
         System.out.println("------------------------------------------");
         System.out.println(course);
      }
      boolean loop;
      do {
         System.out.println("\n1) Go to Profile View");
         System.out.println("2) Go to Main View");
         System.out.print("Enter Selection:  ");

         loop = false;

         String selection = controller.systemInput().nextLine();
         switch (selection) {
         case "1":
            controller.setView(ViewType.PROFILE_VIEW);
            break;
         case "2":
            controller.setView(ViewType.MAIN_VIEW);
            break;
         default:
            loop = false;
            System.out.println("Invalid selection, try again.");
         }
      } while (loop);
   }
}
