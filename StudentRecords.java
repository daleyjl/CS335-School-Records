import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


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
                System.out.println("Start Time: " + rs.getTime(2)+" End Time: "+rs.getTime(3));       // Category
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
}
