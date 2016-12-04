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
            System.out.println("\n1) Add new course.");
            System.out.println("2) Go to main view");
            System.out.print("Enter selection:  ");

            String selection = controller.systemInput().nextLine();
            switch (selection) {
            case "1": {
               System.out.print("\nEnter Course Id:  ");
               String courseId = controller.systemInput().nextLine();
               if (model.registeredCourseExists(courseId)) {
                  System.out.println("Course " + courseId + " already exists in schedule.");
               } else if (!model.openCourseExists(courseId)) {
                  System.out.println("Course " + courseId + " is not an existing open course.");
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
