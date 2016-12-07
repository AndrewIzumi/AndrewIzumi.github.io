package view;

import model.Model;
import controller.Controller;
import controller.Controller.ViewType;

public class LoginView extends View {
   public LoginView(Model model, Controller controller) {
      this.model = model;
      this.controller = controller;
   }

   /**
    * Displays prompts for adding new student, login and exit
    */
   @Override
   public void display() {
      boolean loop = true;
      while (loop) {
         System.out.println("\nCourse Registration System\n");
         System.out.println("1) Add new student\n");
         System.out.println("2) Login\n");
         System.out.println("3) Exit\n");
         System.out.print("Enter selection:  ");

         String selection = controller.systemInput().nextLine();
         switch (selection) {
         case "1": {
            System.out.println("\nCreating new account.");
            System.out.print("\nEnter first name:  ");
            String firstName = controller.systemInput().nextLine();
            System.out.print("\nEnter last name:  ");
            String lastName = controller.systemInput().nextLine();
            System.out.print("\nEnter user name:  ");
            String userName = controller.systemInput().nextLine();
            System.out.print("\nEnter password:  ");
            String password = controller.systemInput().nextLine();

            // Check to see if user is registered
            if (model.verifyAccount(userName, password)) {
               System.out.println("\nUser '" + userName + "' already exists, try again.");
            }
            else if(userName.equals("username")) {
               // because our parseStudentData ignores the username line
               System.out.println("\n\"username\" is not a valid username, try again.");
            }
            else {
               // Add new student in memory and also to file
               model.addStudent(firstName, lastName, userName, password);
               // Set next view to be displayed to main menu view
               controller.setView(ViewType.MAIN_VIEW);
               loop = false;
            }
            break;
         }
         case "2": {
            System.out.println("\nLogging into system.");
            System.out.print("\nEnter user name:  ");
            String userName = controller.systemInput().nextLine();
            System.out.print("\nEnter password:  ");
            String password = controller.systemInput().nextLine();

            // Check to see if user is registered
            if (!model.verifyAccount(userName, password)) {
               System.out.println("\nInvalid user name or password, try again.");
            } else {
               model.loadStudentCourses();
               // Set next view to be displayed to main menu view
               controller.setView(ViewType.MAIN_VIEW);
               loop = false;
            }
            break;
         }
         case "3": {
            System.out.println("\nExited registration system.");
            System.exit(0);
         }
         default:
            clearScreen();
            System.out.println("\nInvalid selection, try again.");
         }
      }
   }
}
