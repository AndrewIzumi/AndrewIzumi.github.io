package view;

import model.Model;
import controller.Controller;

public abstract class View {
   protected Model model;

   protected Controller controller;

   private static final int SCROLL_LEN = 200;

   public void display() {
   }

   public void clearScreen() {
      for (int i = 0; i < SCROLL_LEN; i++) {
         System.out.println();
      }
   }
}
