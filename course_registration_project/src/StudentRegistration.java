import model.Model;
import view.LoginView;
import view.MainView;
import view.RegisterCourseView;
import view.UnregisterCourseView;
import view.StudentProfileView;
import view.CourseView;
import controller.StudentCourseController;
import controller.StudentCourseController.ViewType;

public class StudentRegistration {
	private Model model;
	
	private StudentCourseController controller;
	
	public StudentRegistration() {
		
	}
	
	public void initialize() {
		model = new Model();
		controller = new StudentCourseController();
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