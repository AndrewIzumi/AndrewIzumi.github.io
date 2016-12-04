package model;

public class Course {	
   private String name;

   private String summary;

   private String startDate;

   private String endDate;

   private int enrollLimit;

   private int enrolledNum;

   private String id;

   public Course() {
      name = "";
      id = "";
      summary = "";
      startDate = "";
      endDate = "";
   }

   public Course(String name, String id, String summary, String startDate, String endDate, int enrollLimit, int enrolledNum) {
      this.name = name;
      this.summary = summary;
      this.startDate = startDate;
      this.endDate = endDate;
      this.enrollLimit = enrollLimit;
      this.enrolledNum = enrolledNum;
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getSummary() {
      return summary;
   }

   public void setSummary(String summary) {
      this.summary = summary;
   }

   public String getStartDate() {
      return startDate;
   }

   public void setStartDate(String startDate) {
      this.startDate = startDate;
   }

   public String getEndDate() {
      return endDate;
   }

   public void setEndDate(String endDate) {
      this.endDate = endDate;
   }

   public int getEnrolledNum() {
      return enrolledNum;
   }

   public void incrementEnrolledNum() {
      if (enrolledNum < enrollLimit) {
         enrolledNum++;
      }
   }

   public int getEnrollLimit() {
      return enrollLimit;
   }

   public void setEnrollLimit(int enrollLimit) {
      this.enrollLimit = enrollLimit;
   }

   public boolean isCourseAvailable() {
      return enrolledNum < enrollLimit;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getId() {
      return id;
   }

   public String toString() {
      return name + "  (" + id + ")\n" +
            summary + '\n' + 
            startDate + " - " + endDate + '\n' +
            "limit: " + enrollLimit +
            "  enrolled: " + enrolledNum;
   }
}
