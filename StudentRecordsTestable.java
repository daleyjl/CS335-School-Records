/*All code shown below is written by me, Theo Ciccarelli. However, original author tags have been preserved as an acknowledgement of the work done
  by those people and the foundation on which this refactoring rests */

import java.util.ArrayList;
import java.util.HashMap;

public class StudentRecordsTestable{
        Student student = new Student("John","Smith","007","johnsmith@email.com",3.1,"6034973123");
        HashMap<String,Course> allClasses = new HashMap<String, Course>();
        ArrayList<String> newDays=new ArrayList<String>();
        newDays.add("Wednesday");
       Course newClass= new Course ("012","Art History",newDays,"9:00","10:30","Emily Monet","Fall 2017",15);

    public void saveClass(String student_id, String course_id) {
        /*
         * @author Theo
         * @author Jamie
         */
        //pull wanted class from DB
         Course newClass=allClasses.get(course_id);
            //ensures class can be added
            if(student.getIdNum().equals(student_id)&&student.getSchedule().getClasses().size()<4&&!(student.getSchedule().getClasses().contains(newClass))){
                    //put the class in the right spot in their schedule
                    student.getSchedule().addClass(newClass);
                   System.out.println("Class added!");
                } else {
                    System.out.println("Drop a class first");
                }
        }

    public void dropCourse(String student_id, int dropNum){
        /*
         * @author Theo
         * @author Jamie
         */
            if(student.getIdNum().equals(student_id)) {
                //displays all of their classes
                System.out.println(student.getSchedule().toString());
                //picks the appropriate columns in the DB to update, sets appropriate columns to null
                Course course = student.getSchedule().getClasses().get(dropNum);
                student.getSchedule().dropClass(course);
            }
    }


    //selects the courses inline with the correct student ID, finds which one matches, inserts grade for that course (1, 2, 3, or 4)
    public void insertGrade(String student_id,String course_id,double newGrade){
        /*
         * @author Theo
         * @author Caitlin
         * @author Jamie
         */
        if(student.getIdNum().equals(student_id)) {
            //display schedule
            System.out.println(student.getSchedule().toString());
            //updates the grade for the correct class
            Course course = allClasses.get(course_id);
            if(student.getSchedule().getClasses().contains(course)){
                course.setGrade(newGrade);
            }
        }
    }


    //finds correct course with the same method used in insertGrade, then inserts attendance instead (if grade and attendance weren't different data types, these methods could be combined) - my connection isn't working, so this has not been tested
    public void insertAttendance(String student_id,String course_id,String attendance){
        /*
         * @author Theo
         * @author Caitlin
         * @author Jamie
         */
        if(student.getIdNum().equals(student_id)){
           //display schedule
           System.out.println(student.getSchedule().toString());
            //figures out which course matches the course id
            Course course = allClasses.get(course_id);
            //if there's a match update attendance
            if(student.getSchedule().getClasses().contains(course)){
                course.setAbsences(attendance);
            }
        }
    }
}