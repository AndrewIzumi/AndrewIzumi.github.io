package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Model;
import view.View;

public class Controller {
   protected Model registration;

   protected List<View> views = new ArrayList<>();

   private View currentView;

   private Scanner scanner = new Scanner(System.in);

   /**
    * Must match the order that the views are added to controller.
    */
   public enum ViewType {
      LOGIN_VIEW,
      MAIN_VIEW,
      REGISTER_VIEW,
      UNREGISTER_VIEW,
      PROFILE_VIEW,
      COURSE_VIEW
   }

   /**
    * Add view to controller so it can control which view gets displayed.
    */
   public void addView(View view) {
      views.add(view);
   }

   /**
    * Sets the next view to be display
    *
    * @param  viewType type of view (see Controller.ViewType) to be displayed
    */
   public void setView(ViewType viewType) {
      currentView = views.get(viewType.ordinal());
   }

   /**
    * View object that is to be displayed
    *
    * @return  View object that is to be displayed
    */
   public View currentView() {
      return currentView;
   }

   /**
    * Must use same Scanner object throughout session, since
    * closing a Scanner object flushes all text from stdin 
    *
    * @return  Scanner object for stdin input
    */
   public Scanner systemInput() {
      return scanner;
   }
}
