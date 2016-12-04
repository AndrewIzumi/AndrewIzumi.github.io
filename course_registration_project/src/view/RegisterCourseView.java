package view;

import java.util.List;

import model.Course;
import model.Model;
import controller.Controller;
import controller.StudentCourseController.ViewType;

public class RegisterCourseView extends View {
   public RegisterCourseView(Model model, Controller controller) {
      this.model = model;
      this.controller = controller;
   }

   public void display() {
      while (true) {
         System.out.println("\nRegistered Courses for " + model.getUserName());
         List<Course> availableCourses = model.getRegisteredCourses();
         if (availableCourses.isEmpty()) {
            System.out.println("No courses registered.");
         }
         for (Course course : availableCourses) {
            System.out.println("------------------------------------------");
            System.out.println(course);
         }

         boolean loop = true;
         while (loop) {
            System.out.println("\n1) Add new course.");
            System.out.println("2) Return to main view");
            System.out.print("Enter selection:  ");

            String selection = controller.systemInput().nextLine();
            switch (selection) {
            case "1": {
               System.out.print("\nEnter Course Id:  ");
               String courseId = controller.systemInput().nextLine();
               if (model.registeredCourseExists(courseId)) {
                  System.out.println("Course " + courseId + " already exists.");
               } else if (!model.openCourseExists(courseId)) {
                  System.out.println("Course " + courseId + " does not exist.");
               } else if (!model.isOpenCourseAvailable(courseId)) {
                  System.out.println("Course " + courseId + " is full.");
               } else {
                  model.registerCourse(courseId);
                  loop = false;
                  System.out.println("Course " + courseId + " added to schedule.");
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
