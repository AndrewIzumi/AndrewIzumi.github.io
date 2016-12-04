package model;

import java.util.List;
import java.util.ArrayList;

public class Student {
   private String firstName;

   private String lastName;

   private String userName;

   private String password;

   private List<Course> registeredCourses = new ArrayList<>();

   public Student() {
      userName = "";
      password = "";
      firstName = "";
      lastName = "";
   }

   public Student(String userName, String password) {
      this.userName = userName;
      this.password = password;
      firstName = "";
      lastName = "";
   }

   public Student(String firstName, String lastName, String user, String password) {
      this(user, password);
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public boolean courseExists(String courseId) {
      for (Course course : registeredCourses) {
         if (course.getId().equals(courseId)) {
            return true;
         }
      }
      return false;
   }

   public void addCourse(Course course) {
      if (!courseExists(course.getId())) {
         registeredCourses.add(course);
      }
   }
   
   public void removeCourse(Course course) {
      if (courseExists(course.getId())) {
         registeredCourses.remove(course);
      }
   }

   public List<Course> getRegisteredCourses() {
      return registeredCourses;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getFullName() {
      return firstName + ' ' + lastName;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }
}
