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

   public enum ViewType {
      LOGIN_VIEW,
      MAIN_VIEW,
      REGISTER_VIEW,
      UNREGISTER_VIEW,
      PROFILE_VIEW,
      COURSE_VIEW
   }

   public void addView(View view) {
      views.add(view);
   }

   public void setView(ViewType viewType) {
      currentView = views.get(viewType.ordinal());
   }

   public View currentView() {
      return currentView;
   }

   public Scanner systemInput() {
      return scanner;
   }
}
