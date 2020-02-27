import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
*Connect to mySQL
*View only selected column from the database using the password 
*/

public class ScheduleView {
    private Connection connection;
    ScheduleView(String user, String password){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://dany.simmons.edu:3306/33501sp20_tranat?useUnicode=yes&characterEncoding=UTF-8",
                    user, password);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void listCourse (String sem, String ID){
        try {
            Statement selectID = connection.createStatement();
            ResultSet rs = selectID.executeQuery(
                    "SELECT STUDENT_ID, SEMESTER, LAST_NAME, FIRST_NAME, " +
                            "COURSE_1, Start_Time_1, End_Time_1, Days_1," +
                            "COURSE_2, Start_Time_2, End_Time_2, Days_2," +
                            "COURSE_3, Start_Time_3, End_Time_3, Days_3," +
                            "COURSE_4, Start_Time_4, End_Time_4, Days_4," + " FROM SCHEDULE," +
                            "WHERE STUDENT_ID LIKE ID AND WHERE SEMESTER LIKE sem"
            );
            int course = 1;
            while (rs.next()){
                System.out.println("Student ID: " + rs.getString(1)); 
                System.out.println("Semester: " + rs.getString(2)); 
                System.out.println(rs.getString(3)
                    + ", " + rs.getString(4)); 
                System.out.println("Course 1: " + rs.getString(5) + 
                        "\t Start time: " + rs.getTime(6) +
                        "\t End time: " + rs.getTime(7));
                System.out.println("Course 2: " + rs.getString(8) +
                        "\t Start time: " + rs.getTime(9) +
                        "\t End time: " + rs.getTime(10));
                System.out.println("Course 3: " + rs.getString(11) + 
                        "\t Start time: " + rs.getTime(12) +
                        "\t End time: " + rs.getTime(13));
                System.out.println("Course 4: " + rs.getString(14) + 
                        "\t Start time: " + rs.getTime(15) +
                        "\t End time: " + rs.getTime(16));
            }
        }

        catch (Exception e){
            e.printStackTrace();
        }
    }
}
