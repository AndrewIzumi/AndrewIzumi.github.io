package view;

import model.Model;
import controller.Controller;
import controller.Controller.ViewType;

public class LoginView extends View {
   public LoginView(Model model, Controller controller) {
      this.model = model;
      this.controller = controller;
   }

   public void display() {
      boolean loop = true;
      while (loop) {
         System.out.println("\nCourse Registration System");
         System.out.println("1) Add new student");
         System.out.println("2) Login");
         System.out.println("3) Exit");
         System.out.print("Enter selection:  ");

         String selection = controller.systemInput().nextLine();
         switch (selection) {
         case "1": {
            System.out.println("\nCreating new account.");
            System.out.print("Enter first name:  ");
            String firstName = controller.systemInput().nextLine();
            System.out.print("Enter last name:  ");
            String lastName = controller.systemInput().nextLine();
            System.out.print("Enter user name:  ");
            String userName = controller.systemInput().nextLine();
            System.out.print("Enter password:  ");
            String password = controller.systemInput().nextLine();

            if (model.verifyAccount(userName, password)) {
               System.out.println("User '" + userName + "' already exists, try again.");
            } else {
               model.addStudent(firstName, lastName, userName, password);
               controller.setView(ViewType.MAIN_VIEW);
               loop = false;
            }
            break;
         }
         case "2": {
            System.out.println("\nLogging into system.");
            System.out.print("Enter user name:  ");
            String userName = controller.systemInput().nextLine();
            System.out.print("Enter password:  ");
            String password = controller.systemInput().nextLine();

            if (!model.verifyAccount(userName, password)) {
               System.out.println("Invalid user name or password, try again.");
            } else {
               System.out.println("User " + userName + " is authenticated.");
               controller.setView(ViewType.MAIN_VIEW);
               loop = false;
            }
            break;
         }
         case "3": {
            System.out.println("Exited registration system.\n");
            System.exit(0);
         }
         default:
            System.out.println("Invalid selection, try again.");
         }
      }
   }
}
