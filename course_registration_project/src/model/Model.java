package model;

import java.util.List;
import java.util.ArrayList;
import java.io.File;

import dataio.DataInput;

public class Model {
   private List<Student> students = new ArrayList<>();

   private List<Course> openCourses = new ArrayList<>();

   private Student currentStudent;

   private static final String courseFilePath = "data" + File.separator + "courses.txt";
   
   // perhaps should be changed so each student has their own txt file?
   private static final String studentFilePath = "data" + File.separator + "students.txt";

   public Model() {
      if (!loadCourses(courseFilePath)) {
         System.out.println("Failed to load course data file " + courseFilePath);
      }
      if (!loadStudents(studentFilePath)) {
         System.out.println("Failed to load student data file " + studentFilePath);
      }
   }

   /**
    * Adds student to student list and sets new student to
    * be current logged in user.  Also writes student to
    * file for persistence.
    *
    * @param  firstName user's first name
    * @param  lastName  user's last name
    * @param  userName  unique system user name
    * @param  password  user's password
    */
   public void addStudent(String firstName, String lastName, String userName, String password) {
      Student newStudent = new Student(firstName, lastName, userName, password);
      students.add(newStudent);
      currentStudent = newStudent;
      // TODO:  Write result to file for persistence
   }

   /**
    * Adds student to student list and sets new student to
    * be current logged in user.  Also writes student to
    * file for persistence.
    *
    * @param  firstName user's first name
    * @param  lastName  user's last name
    * @param  userName  unique system user name
    * @param  password  user's password
    * @return true if user is registered;
    *         false otherwise
    */
   public boolean verifyAccount(String userName, String password) {
      for (Student student : students) {
         if (student.getUserName().equals(userName)) {
            if (student.getPassword().equals(password)) {
               currentStudent = student;
               return true;
            }
         }
      }
      return false;
   }

   public String getUserName() {
      return currentStudent != null ? currentStudent.getUserName() : "";
   }

   public String getUserFullName() {
      return currentStudent != null ? currentStudent.getFullName() : "";
   }

   public void clearCurrentStudent() {
      currentStudent = null;
   }

   /**
    * Registers open course for current logged in user.
    * Also increments enrolled number of open course.
    *
    * @param  courseId  course id of open course to be registered
    */
   public void registerCourse(String courseId) {
      if (currentStudent != null) {
         Course course = findOpenCourse(courseId);
         if (course != null && course.isCourseAvailable() && !currentStudent.courseExists(courseId)) {
            currentStudent.addCourse(course);
            course.incrementEnrolledNum();
         }
      }
      // TODO:  Write result to file for persistence
   }

   /**
    * Removes registered course for current logged in user.
    * Also decrements enrolled number of open course.
    *
    * @param  courseId  course id of open course to be unregistered
    */
   public void unregisterCourse(String courseId) {
      if (currentStudent != null) {
         Course course = findOpenCourse(courseId);
         if (course != null && currentStudent.courseExists(courseId)) {
            currentStudent.removeCourse(course);
            course.decrementEnrolledNum();
         }
      }
      // TODO:  Write result to file for persistence
   }

   /**
    * Get list of open courses
    *
    * @return  list of open courses
    */
   public List<Course> getOpenCourses() {
      return openCourses;
   }

   public Course findOpenCourse(String courseId) {
      for (Course course : openCourses) {
         if (course.getId().equals(courseId)) {
            return course;
         }
      }
      return null;
   }

   /**
    * Get list of registered courses for current logged in user
    *
    * @return  list of registered courses for current user
    */
   public List<Course> getRegisteredCourses() {
      return currentStudent != null ? currentStudent.getRegisteredCourses() : null;
   }
   
   public boolean registeredCourseExists(String courseId) {
      return currentStudent != null ? currentStudent.courseExists(courseId) : false;
   }

   public boolean openCourseExists(String courseId) {
      return findOpenCourse(courseId) != null;
   }

   /**
    * Check if open course is valid and is not full
    *
    * @return  true if open course is valid and not full;
    *          false otherwise
    */
   public boolean isOpenCourseAvailable(String courseId) {
      Course course = findOpenCourse(courseId);
      return course != null ? course.isCourseAvailable() : false;
   }

   /**
    * Load course data from file into course list
    *
    * @return  true if open course data is loaded from file;
    *          false otherwise
    */
   protected boolean loadCourses(String filePath) {
      DataInput dataInput = new DataInput(filePath);
      return dataInput.parseCourseData(openCourses);
   }
   
   protected boolean loadStudents(String filePath) {
      // TODO:  Load students and registered courses from file
      return true;
   }
}
