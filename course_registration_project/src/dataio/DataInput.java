package dataio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import model.Course;

public class DataInput {
   private String filePath;

   public DataInput(String filePath) {
      this.filePath = filePath;
   }

   /**
    * Parse a single line of data to initialize a Course object
    *
    * @param  line single line of data for a given course from file
    * @return      Course object initialized from a single line of
    *              course data from file
    */
   private Course parseLine(String line) {
      String[] args = line.split("\\|");
      if (args.length != 7) {
         return null;
      }
      String id = args[0];
      String name = args[1];
      String startDate = args[2];
      String endDate = args[3];
      String summary = args[4];
      try {
         int enrollLimit = Integer.parseInt(args[5]);
         int enrolledNum = Integer.parseInt(args[6]);
         return new Course(name, id, summary, startDate, endDate, enrollLimit, enrolledNum);
      } catch (NumberFormatException ex) {
         return null;
      }
   }

   /**
    * Parse course data from file into course list
    *
    * @param  courseList  course list to be appended with course objects
    *                     initialized with course data from file
    * @return             true if course data read from file successfully
    *                     false otherwise
    */
   public boolean parseCourseData(List<Course> courseList) {
      Scanner input = null;
      try {
         File file = new File(filePath);
         input = new Scanner(file);
         input.useDelimiter("\\|");
         while (input.hasNext()) {
            String line = input.nextLine();
            if (line.isEmpty() || line.charAt(0) == '#') {
               continue;
            }
            Course course = parseLine(line);
            if (course == null) {
               return false;
            }
            courseList.add(course);
         }
      } catch (FileNotFoundException ex) {
         return false;
      } finally {
         input.close();
      }
      return true;
   }
   
   /**
    * Parse a single line of data to initialize a Student object
    *
    * @param  line single line of data for a given student from students.txt file
    * @return      Course object initialized from a single line of
    *              student data from file
    */
   private Student parseStudentLine(String line) {
      String[] args = line.split("\\|");
      if (args.length != 4) {
         return null;
      }
      String username = args[0];
      String firstName = args[1];
      String lastName = args[2];
      String password = args[3];
      return new Student(firstName, lastName, username, password);
   }
   
  /**
    * Parse student data from file into student list
    *
    * @param  studentList student list to be appended with Student objects
    *                     initialized with student data from file
    * @return             true if student data read from file successfully
    *                     false otherwise
    */
   public boolean parseStudentData(List<Course> studentList) {
      Scanner input = null;
      try {
         File file = new File(filePath);
         input = new Scanner(file);
         input.useDelimiter("\\|");
         while (input.hasNext()) {
            String line = input.nextLine();
            // TODO: make sure students can't have 'username' as their username
            if (line.isEmpty() || line.startsWith("username")) {
               continue;
            }
            Student student = parseStudentLine(line);
            if (course == null) {
               return false;
            }
            studentList.add(student);
         }
      } catch (FileNotFoundException ex) {
         return false;
      } finally {
         input.close();
      }
      return true;
   }
   
   /**
    * Parse student course data from file into student course ID list
    * Similar structure as others for ease of use
    *
    * @param  studentCourseIdList list to be appended with course Id strings
    *                             initialized from student course data from file
    * @return                     true if course data read from file successfully
    *                             false otherwise
    */
   public boolean parseStudentCourseData(List<String> studentCourseIdList) {
      Scanner input = null;
      try {
         File file = new File(filePath);
         input = new Scanner(file);
         input.useDelimiter("\\|");
         while (input.hasNext()) {
            String courseId = input.next();
            if (courseId.isEmpty()) {
               continue;
            }
            studentCourseIdList.add(courseId);
         }
      } catch (FileNotFoundException ex) {
         return false;
      } finally {
         input.close();
      }
      return true;
   }
}
