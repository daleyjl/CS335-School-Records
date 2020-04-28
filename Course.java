/**Course Class
 * @author Theo
 */

import java.util.*;

public class Course {
    private String course_id;
    private String name;
    private ArrayList<String> days;
    private String startTime;
    private String endTime;
    private String faculty;
    private String semester;
    private int capacity;
    private double grade;
    private String absences;

    public Course(){
        course_id=null;
        name="";
        days=null;
        startTime="";
        endTime="";
        faculty="";
        semester="";
        capacity=0;
        grade=0;
        absences="0";
    }

    public Course(String newCourse_id, String courseName, ArrayList<String> courseDays, String newStartTime, String newEndTime,
                  String facultyName, String newSemester, int newCapacity){
        course_id=newCourse_id;
        name=courseName;
        days=courseDays;
        startTime=newStartTime;
        endTime=newEndTime;
        faculty=facultyName;
        semester=newSemester;
        capacity=newCapacity;
        grade=0;
        absences="0";
    }

    public ArrayList getDays() {
        return days;
    }

    public double getGrade() {
        return grade;
    }

    public String getAbsences() {
        return absences;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getCourse_id() {
        return course_id;
    }

    public String getName() {
        return name;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getSemester() {
        return semester;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setAbsences(String newAbsences) {
        this.absences = newAbsences;
    }

    public void setGrade(double newGrade) {
        this.grade = newGrade;
    }
}

