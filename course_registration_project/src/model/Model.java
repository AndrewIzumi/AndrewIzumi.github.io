package model;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

import dataio.DataInput;

public class Model {
   private List<Student> students = new ArrayList<>();

   private List<Course> openCourses = new ArrayList<>();

   private Student currentStudent;

   private static final String courseFilePath = "data" + File.separator + "courses.txt";
   
   private static final String studentFilePath = "data" + File.separator + "students.txt";
   
   private String currentStudentFilePath = "";

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
      currentStudentFilePath = "data" + File.separator + "student_" + currentStudent.getUserName() + ".txt";
      saveStudentsChanges();
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
      currentStudentFilePath = "";
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
      saveCourseChanges();
      saveStudentCourseChanges();
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
      saveCourseChanges();
      saveStudentCourseChanges();
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
      DataInput dataInput = new DataInput(filePath);
      return dataInput.parseStudentData(students);
   }
   
   /**
    * Saves the current openCourses list to the courses.txt file.
    * Returns true if successful, false if not
    */
   private boolean saveCourseChanges() {
      // Write temporary file with changes
      String tmpFileName = "data" + File.separator + "coursesTMP.txt";
      File newFile = new File(tmpFileName);
      PrintWriter printwriter = null;
      try {
         printwriter = new PrintWriter(newFile);
         printwriter.println("# id | name | startDate | endDate | summary | limit | enrolled");
         for(Course c : openCourses) {
            printwriter.println(c.getId() + '|' + c.getName() + '|' + c.getStartDate() + '|' + c.getEndDate() + '|' + c.getSummary() +
                                '|' + c.getEnrollLimit() + '|' + c.getEnrolledNum());
         }
      }
      catch (IOException ex) {
         return false;
      }
      finally {
         printwriter.close();
      }
      
      // delete old file
      File oldFile = new File(courseFilePath);
      oldFile.delete();
      
      // rename tmp file to old file name
      if(newFile.renameTo(oldFile)) {
         return true;
      }
      return false;
   }
   
  /**
    * Saves the current students list to the students.txt file.
    * Returns true if successful, false if not
    */
   private boolean saveStudentsChanges() {
      // Write temporary file with changes
      String tmpFileName = "data" + File.separator + "studentsTMP.txt";
      File newFile = new File(tmpFileName);
      PrintWriter printwriter = null;
      try {
         printwriter = new PrintWriter(newFile);
         printwriter.println("username | firstname | lastname | password");
         for(Student s : students) {
            printwriter.println(s.getUserName() + '|' + s.getFirstName() + '|' + s.getLastName() + '|' + s.getPassword());
         }
      }
      catch (IOException ex) {
         return false;
      }
      finally {
         printwriter.close();
      }
      
      // delete old file
      File oldFile = new File(studentFilePath);
      oldFile.delete();
      
      // rename tmp file to old file name
      if(newFile.renameTo(oldFile)) {
         return true;
      }
      return false;
   }
   
  /**
    * Saves the current students courses to the student_username.txt file.
    * Returns true if successful, false if not
    */
   private boolean saveStudentCourseChanges() {
      // Write temporary file with changes
      String tmpFileName = "data" + File.separator + "studentCoursesTMP.txt";
      File newFile = new File(tmpFileName);
      PrintWriter printwriter = null;
      try {
         printwriter = new PrintWriter(newFile);
         List<Course> registeredCourses = currentStudent.getRegisteredCourses();
         if(!registeredCourses.isEmpty()) {
            printwriter.print(registeredCourses.get(0).getId());
            for(int i = 1; i < registeredCourses.size(); i++) {
               printwriter.print('|' + registeredCourses.get(i).getId());
            }
         }
      }
      catch (IOException ex) {
         return false;
      }
      finally {
         printwriter.close();
      }
      
      // delete old file
      File oldFile = new File(currentStudentFilePath);
      oldFile.delete();
      
      // rename tmp file to old file name
      if(newFile.renameTo(oldFile)) {
         return true;
      }
      return false;
   }
   
  /**
    * load student course data from student_username.txt file.
    * used after login of known user
    */
   public void loadStudentCourses() {
      List<String> studentCourses = new ArrayList<>();
      currentStudentFilePath = "data" + File.separator + "student_" + currentStudent.getUserName() + ".txt";
      DataInput dataInput = new DataInput(currentStudentFilePath);
      
      if(dataInput.parseStudentCourseData(studentCourses)) {
         for(String courseId : studentCourses) {
            Course c = findOpenCourse(courseId);
               if(c != null) {
                  currentStudent.addCourse(c);
               }
         }
      }
   }
   
}
