import model.Model;
import view.LoginView;
import view.MainView;
import view.RegisterCourseView;
import view.UnregisterCourseView;
import view.StudentProfileView;
import view.CourseView;
import controller.Controller;
import controller.Controller.ViewType;

public class StudentRegistration {
   private Model model;

   private Controller controller;

   public StudentRegistration() {
   }

   /**
    * Initializes model, view and controller objects to allow
    * displaying and switching of views.  Note that the order
    * of adding the views to the controller must match the
    * order of the Controller.ViewType enum.
    */
   public void initialize() {
      model = new Model();
      controller = new Controller();
      LoginView loginView = new LoginView(model, controller);
      MainView mainView = new MainView(model, controller);
      RegisterCourseView registerCourseView = new RegisterCourseView(model, controller);
      UnregisterCourseView unregisterCourseView = new UnregisterCourseView(model, controller);
      StudentProfileView studentProfileView = new StudentProfileView(model, controller);
      CourseView courseView = new CourseView(model, controller);
      controller.addView(loginView);
      controller.addView(mainView);
      controller.addView(registerCourseView);
      controller.addView(unregisterCourseView);
      controller.addView(studentProfileView);
      controller.addView(courseView);
      controller.setView(ViewType.LOGIN_VIEW);
   }

   public void start() {
      processEvents();
   }

   /**
    * Begin displaying the views starting with the login view
    */
   public void processEvents() {
      while (true) {
         controller.currentView().display();
      }
   }

   public static void main(String[] args) {
      StudentRegistration studentRegistration = new StudentRegistration();
      studentRegistration.initialize();
      studentRegistration.start();
   }
}