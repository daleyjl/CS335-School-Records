import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CourseInfo {
    private Connection connection;
    CourseInfo(String user, String password) {
        //makes connection to DB
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://dany.simmons.edu:3306/33501sp20_daleyjl?characterEncoding=UTF-8",
                    user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /** listCourses
     * @author Jamie
     */

    public void listCourses() {
        //lists all courses
        try {
            Statement selectBooks = connection.createStatement();
            ResultSet rs = selectBooks.executeQuery(
                    "SELECT COURSE_ID, START_TIME, END_TIME, COURSE_NAME, DAYS," +
                            " SEMESTER, INSTRUCTOR, CAPACITY"+" FROM COURSES");
            // Iterate over result set and print each book description.
            int count=1;
            while (rs.next()) {
                //lists all attibutes of the courses
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
                //keeps count of how many classes
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** listStudentsInCourse
     * @author Caitlin
     * @author Jamie
     */

    public void listStudentsInCourse(String course_id){
        try {
            Statement selectStudents = connection.createStatement();
            //selects all student IDs and courses
            ResultSet rs = selectStudents.executeQuery(
                    "SELECT Student_ID, Last_Name, First_Name, Course_1, Course_2, Course_3, Course_4"+" FROM SCHEDULES");
            int count=1;
            String class1;
            String class2;
            String class3;
            String class4;

            //determines which classes are empty or sets them equal to course id
            while (rs.next()) {
                if (rs.getString(4)==null){
                    class1="empty class";
                }
                else{
                    class1=rs.getString(4);
                }
                if (rs.getString(5)==null){
                    class2="empty class";
                }
                else{
                    class2=rs.getString(5);
                }
                if (rs.getString(6)==null){
                    class3="empty class";
                }
                else{
                    class3=rs.getString(6);
                }
                if (rs.getString(7)==null){
                    class4="empty class";
                }
                else{
                    class4=rs.getString(7);
                }

                //lists students in the class if one of their classes matches the course id
                if(class1.equals(course_id)||class2.equals(course_id)||class3.equals(course_id)||
                        class4.equals(course_id)){
                    System.out.println(count+": "+rs.getString(3)+", "+rs.getString(2)+"    Student ID: "+rs.getInt(1));
                    count++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
