import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class StudentRecords {

    private Connection connection;
    StudentRecords(String user, String password) {
        //connection to database
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://dany.simmons.edu:3306/33501sp20_daleyjl?characterEncoding=UTF-8",
                    user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** saveClass
     * @author Jamie
     */
    public void saveClass(int student_id, String course_id) {
        //puts existing class into a student schedule
        try {
            //start time
            String time1="";
            //end time
            String time2="";
            //days
            String day="";
            Statement insertClass = connection.createStatement();
            Statement selectClass = connection.createStatement();
            //selects the classes from schedules
            ResultSet rs = selectClass.executeQuery(
                    "SELECT Course_1, Course_2, Course_3, Course_4 '" +
                            " ' FROM SCHEDULES"+
                            " WHERE Student_ID= '" + student_id + "' AND Semester='Fall 2016';");


           //work will determine if the course add works
            boolean work=false;
            //arraylist of all classes
            ArrayList<String> classes= new ArrayList<>();
            //arraylist with just empty classes
            ArrayList<String> emptyClasses= new ArrayList<>();


            while (rs.next()) {
                //adds classes 1-4 to classes arraylist
                classes.add(rs.getString(1));
                classes.add(rs.getString(2));
                classes.add(rs.getString(3));
                classes.add(rs.getString(4));

            }

            for(String x:classes){

                if (x==null){
                //if x is null, add to emptyClasses
                    emptyClasses.add(Integer.toString(classes.indexOf(x)+1));
                    //if work=true then there is an empty spot in their schedule
                    work=true;
                }
                else if(x.equals(course_id)){
                    //if a class equals the class they want to add it won't let them add it again
                    System.out.println("You already have this course in your schedule");
                    work=false;
                }
            }



            if (work){
                //if empty spot put the new class in that spot
                //pull the class they want from Courses in DB
                ResultSet rs2 = selectClass.executeQuery(
                        "SELECT Semester, START_TIME, END_TIME, DAYS " +
                                " FROM COURSES"+
                        " WHERE COURSE_ID= '"+course_id+"';");
                while (rs2.next()){
                    time1= rs2.getString(2);
                    time2= rs2.getString(3);
                    day= rs2.getString(4);

                }

            //put the class in the right spot in their schedule
            insertClass.execute(
                    "UPDATE SCHEDULES" +
                            " SET Course_" + emptyClasses.get(0)+"= '" + course_id + "', Start_time" + emptyClasses.get(0) + "= '" + time1 + "', End_time" +
                            emptyClasses.get(0) + "='"+ time2 + "', Days"+emptyClasses.get(0)+"='" + day+
                            "' WHERE Student_ID= '" + student_id + "' AND Semester='Fall 2016';");
            System.out.println("Class added!");}
            else{
                System.out.println("Drop a class first");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** dropCourse
     * @author Jamie
     */

    public void dropCourse(int student_id){
        try {
            Scanner input= new Scanner(System.in);
            Statement selectClass = connection.createStatement();
            Statement updateClass = connection.createStatement();
            ResultSet rs = selectClass.executeQuery(
                    "SELECT Course_1, Course_2, Course_3, Course_4" +
                            " FROM SCHEDULES " +
                            " WHERE Student_ID= '" + student_id + "' AND Semester='Fall 2016';");
            //displays all of their classes
            System.out.println("Select the class ID for the course that you would like to drop: ");
            while (rs.next()){
                System.out.println("Course 1: "+rs.getString(1));
                System.out.println("Course 2: "+rs.getString(2));
                System.out.println("Course 3: "+rs.getString(3));
                System.out.println("Course 4 "+rs.getString(4));

            }

            System.out.println("Select the class number you would like to drop: [1,2,3,4] ");
            String courseIndex=input.next();
            //picks the appropriate columns in the DB to update
            String course="Course_"+courseIndex;
            String days= "Days"+courseIndex;
            String start="Start_time"+courseIndex;
            String end="End_time"+courseIndex;
            String grade="Grade_"+courseIndex;
            String absences="Absences_"+courseIndex;

            //sets appropriate columns to null
            updateClass.execute(
                    " UPDATE SCHEDULES" +
                            " SET " + course + "= NULL, " + days+"=NULL,"+start+"=NULL, "+end+"=NULL,"
                    +grade+"=NULL,"+absences+"=NULL"+
                            " WHERE Student_ID= '" + student_id + "' AND Semester='Fall 2016';");

        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }


    /** insertGrade
     * @author Caitlin

     */

    //selects the courses inline with the correct student ID in SQL, finds which one matches, inserts grade for that course (1, 2, 3, or 4) - connection with SQL stopped working before this worked
    public void insertGrade(int student_id,String course_id,double grade){
        try {
            String classNumber = "0";
            Statement selectStudents = connection.createStatement();
            //selects courses for the student
            ResultSet rs = selectStudents.executeQuery(
                    "SELECT Course_1, Course_2, Course_3, Course_4 "+
                            "FROM SCHEDULES "+
                            "WHERE Student_ID = '"+student_id+"';");
            //sSystem.out.println(rs.getString(1));
            //tests which classes matches the course id
            while (rs.next()){
            if(rs.getString(1).equals(course_id)){
                classNumber = "1";
            }
            else if(rs.getString(2).equals(course_id)){
                classNumber = "2";
            }
            else if(rs.getString(3).equals(course_id)){
                classNumber = "3";
            }
            else if(rs.getString(4).equals(course_id)){
                classNumber = "4";
            }}
            System.out.println(classNumber);
            //updates the grade for the correct class
            if(!classNumber.equals("0")){
                Statement insertGrade = connection.createStatement();
                insertGrade.execute(
                        "UPDATE SCHEDULES " +
                                " SET Grade_"+classNumber+"='" +grade+
                                "' WHERE Student_ID='"+student_id+"';");


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** insertAttendance
     * @author Caitlin
     */

    //finds correct course with the same method used in insertGrade, then inserts attendance instead (if grade and attendance weren't different data types, these methods could be combined) - my connection isn't working, so this has not been tested
    public void insertAttendance(int student_id,String course_id,String attendance){
        try {
            String classNumber = "0";
            //selects all the courses for the right student
            Statement selectStudents = connection.createStatement();
            ResultSet rs = selectStudents.executeQuery(
                    "SELECT Course_1, Course_2, Course_3, Course_4 "+
                            "FROM SCHEDULES "+
                            "WHERE Student_ID = '"+student_id+"';");
            //figures out which course matches the course id
            while (rs.next()){
            if(rs.getString(1).equals(course_id)){
                classNumber = "1";
            }
            else if(rs.getString(2).equals(course_id)){
                classNumber = "2";
            }
            else if(rs.getString(3).equals(course_id)){
                classNumber = "3";
            }
            else if(rs.getString(4).equals(course_id)){
                classNumber = "4";
            }}
            //if there's a match update attendance
            if(!classNumber.equals("0")){
                Statement insertAttendance = connection.createStatement();
                insertAttendance.execute(
                        "UPDATE SCHEDULES " +
                                " SET Absences_"+ classNumber+ "='"+attendance+
                                "' WHERE Student_ID='"+student_id+"';");


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
