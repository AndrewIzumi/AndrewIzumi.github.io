package view;

import model.Model;
import controller.Controller;
import controller.Controller.ViewType;

public class MainView extends View {
   public MainView(Model model, Controller controller) {
      this.model = model;
      this.controller = controller;
   }

   /**
    * Displays messages and prompts for main menu selections
    */
   @Override
   public void display() {
      boolean loop;
      do {
         System.out.println("\nMain Menu\n");
         System.out.println("Logged in as " + model.getUserName() + '\n');
         System.out.println("1) Register new course\n");
         System.out.println("2) Unregister course\n");
         System.out.println("3) View registered courses\n");
         System.out.println("4) View available courses\n");
         System.out.println("5) Log out\n");
         System.out.println("6) Exit program\n");
         System.out.print("Enter selection:  ");

         loop = false;

         String selection = controller.systemInput().nextLine();
         switch (selection) {
         case "1":
            controller.setView(ViewType.REGISTER_VIEW);
            break;
         case "2":
            controller.setView(ViewType.UNREGISTER_VIEW);
            break;
         case "3":
            controller.setView(ViewType.PROFILE_VIEW);
            break;
         case "4":
            controller.setView(ViewType.COURSE_VIEW);
            break;
         case "5":
            model.clearCurrentStudent();
            controller.setView(ViewType.LOGIN_VIEW);
            System.out.println("Logged out of system.");
            break;
         case "6":
            System.out.println("\nExited registration system.");
            System.exit(0);
         default:
            loop = true;
            clearScreen();
            System.out.println("Invalid selection, try again.");
         }
      } while (loop);
   }
}
