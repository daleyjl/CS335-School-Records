import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class StudentRecords {

    private Connection connection;
    StudentRecords(String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://dany.simmons.edu:3306/33501sp20_daleyjl?characterEncoding=UTF-8",
                    user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Adds a book to the Book table.
    public void save(String[] cols) {
        try {
            Statement insertBook = connection.createStatement();
            insertBook.execute(
                    "INSERT INTO STUDENTS " +
                            "(STUDENT_ID, LAST_NAME, FIRST_NAME, PHONE_NUMBER, EMAIL, GPA)" +
                            " VALUES ('" + cols[0] + "', '" + cols[1] + "', '" + cols[2] + "', '" + cols[3] + "', '" +
                            cols[4] + "', " + Float.parseFloat(cols[5])+ ") ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void saveCourse(String[] cols) {
        try {
            Statement insertBook = connection.createStatement();
            insertBook.execute(
                    "INSERT INTO COURSES " +
                            "(COURSE_ID, START_TIME, END_TIME, COURSE_NAME, DAYS, SEMESTER, INSTRUCTOR, CAPACITY)" +
                            " VALUES ('" + cols[0] + "', '" + cols[1] + "', '" + cols[2] + "', '" + cols[3] + "', '" +
                            cols[4] + "', '" + cols[5]+ "', '"+ cols[6]+ "', "+ Integer.parseInt(cols[7])+ ") ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveClass(int student_id, String course_id) {
        try {
            String time1="";
            String time2="";
            String day="";
            Statement insertClass = connection.createStatement();
            Statement selectBooks = connection.createStatement();
            ResultSet rs = selectBooks.executeQuery(
                    "SELECT Course_1, Course_2, Course_3, Course_4 '" +
                            " ' FROM SCHEDULES"+
                            " WHERE Student_ID= '" + student_id + "' AND Semester='Fall 2016';");

            // Iterate over result set and print each book description.
            int count=1;
            boolean work=false;
            ArrayList<String> classes= new ArrayList<>();
            ArrayList<String> emptyClasses= new ArrayList<>();


            while (rs.next()) {
                classes.add(rs.getString(1));
                classes.add(rs.getString(2));
                classes.add(rs.getString(3));
                classes.add(rs.getString(4));

            }

            for(String x:classes){

                if (x==null){
                    emptyClasses.add(Integer.toString(classes.indexOf(x)+1));
                    work=true;
                }
                if(x.equals(course_id)){
                    System.out.println("You already have this course in your schedule");
                    work=false;
                }
            }



            if (work){
                ResultSet rs2 = selectBooks.executeQuery(
                        "SELECT Semester, START_TIME, END_TIME, DAYS " +
                                " FROM COURSES"+
                        " WHERE COURSE_ID= '"+course_id+"';");
                while (rs2.next()){
                    time1= rs2.getString(2);
                    time2= rs2.getString(3);
                    day= rs2.getString(4);

                }


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

    public void dropCourse(int student_id){
        try {
            Scanner input= new Scanner(System.in);
            Statement selectBooks = connection.createStatement();
            Statement insertClass = connection.createStatement();
            ResultSet rs = selectBooks.executeQuery(
                    "SELECT Course_1, Course_2, Course_3, Course_4" +
                            " FROM SCHEDULES " +
                            " WHERE Student_ID= '" + student_id + "' AND Semester='Fall 2016';");
            System.out.println("Select the class ID for the course that you would like to drop: ");
            while (rs.next()){
                System.out.println("Course 1: "+rs.getString(1));
                System.out.println("Course 2: "+rs.getString(2));
                System.out.println("Course 3: "+rs.getString(3));
                System.out.println("Course 4 "+rs.getString(4));

            }
            String choice= input.next();
            System.out.println("Select the class number you would like to drop: [1,2,3,4] ");
            String courseIndex=input.next();
            String course="Course_"+courseIndex;

            insertClass.execute(
                    " UPDATE SCHEDULES" +
                            " SET " + course + "= NULL" +
                            " WHERE Student_ID= '" + student_id + "' AND Semester='Fall 2016';");

           /* insertClass.execute(
                    "  DELETE FROM SCHEDULES" +
                            " WHERE " + course + "='" + choice + "';");*/
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    // Reads and prints all rows in the Book table.
    public void listCourses() {
        try {
            Statement selectBooks = connection.createStatement();
            ResultSet rs = selectBooks.executeQuery(
                    "SELECT COURSE_ID, START_TIME, END_TIME, COURSE_NAME, DAYS," +
                            " SEMESTER, INSTRUCTOR, CAPACITY"+" FROM COURSES");
            // Iterate over result set and print each book description.
            int count=1;
            while (rs.next()) {
                System.out.println(count);
                System.out.println("ID: " + rs.getString(1));       // Title
                System.out.println("Start Time: " + rs.getString(2)+" End Time: "+rs.getString(3));       // Category
                System.out.println("Course Name: " + rs.getString(4));      // Author first+middle+last
                System.out.println("Days: " + rs.getString(5));     // Edition
                System.out.println("Semester: " + rs.getString(6));     // Edition
                System.out.println("Instructor: " + rs.getString(7));  // Pages
                System.out.println("Capacity: " + rs.getInt(8));  // Pages
                System.out.println("\n");
                count++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void listStudentsInCourse(String course_id){
        try {
            Statement selectStudents = connection.createStatement();
            ResultSet rs = selectStudents.executeQuery(
                    "SELECT Student_ID, Last_Name, First_Name, Course_1, Course_2, Course_3, Course_4"+" FROM SCHEDULES");
            // Iterate over result set and print each book description.
            int count=1;
            while (rs.next()) {
                if(rs.getString(4).equals(course_id)||rs.getString(5).equals(course_id)||rs.getString(6).equals(course_id)||
                        rs.getString(7).equals(course_id)){
                    System.out.println(count+": "+rs.getString(3)+", "+rs.getString(2)+"    Student ID: "+rs.getInt(1));
                    count++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //selects the courses inline with the correct student ID in SQL, finds which one matches, inserts grade for that course (1, 2, 3, or 4) - connection with SQL stopped working before this worked
    public void insertGrade(int student_id,String course_id,double grade){
        try {
            String classNumber = "0";
            Statement selectStudents = connection.createStatement();
            ResultSet rs = selectStudents.executeQuery(
                    "SELECT Course_1, Course_2, Course_3, Course_4 "+
                            "FROM SCHEDULES "+
                            "WHERE Student_ID = '"+student_id+"';");
            //sSystem.out.println(rs.getString(1));
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

    //finds correct course with the same method used in insertGrade, then inserts attendance instead (if grade and attendance weren't different data types, these methods could be combined) - my connection isn't working, so this has not been tested
    public void insertAttendance(int student_id,String course_id,String attendance){
        try {
            String classNumber = "0";


            Statement selectStudents = connection.createStatement();
            ResultSet rs = selectStudents.executeQuery(
                    "SELECT Course_1, Course_2, Course_3, Course_4 "+
                            "FROM SCHEDULES "+
                            "WHERE Student_ID = '"+student_id+"';");

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
