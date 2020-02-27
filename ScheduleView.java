import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/*
* connect to mySQL
* list the selected columns using ID and semester
* @param1: Simmons username: tranat
* @param2: Password to database
* @param3: Semester
* @param4: student ID
* */
public class ScheduleView {
    private Connection connection;
    ScheduleView(String user, String password){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://dany.simmons.edu:3306/33501sp20_tranat?characterEncoding=UTF-8",
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
                    "SELECT Student_ID, Semester, Last_Name, First_Name, " +
                            "COURSE_1, Start_Time_1, End_Time_1, Days_1," +
                            "COURSE_2, Start_Time_2, End_Time_2, Days_2," +
                            "COURSE_3, Start_Time_3, End_Time_3, Days_3," +
                            "COURSE_4, Start_Time_4, End_Time_4, Days_4," + " FROM Schedule," +
                            "WHERE Student_ID = 'ID'," + "AND WHERE Semester = 'sem'"
            );
            while (rs.next()){
                System.out.println("Student ID: " + rs.getString(1)); //ID
                System.out.println("Semester: " + rs.getString(2)); //semester
                System.out.println(rs.getString(3)
                    + ", " + rs.getString(4)); //Student last and first name
                System.out.println("Course 1: " + rs.getString(5) + //course 1
                        "\t Start time: " + rs.getTime(6) +
                        "\t End time: " + rs.getTime(7)+
                        "\t Days: " + rs.getString(8));
                System.out.println("Course 2: " + rs.getString(9) + //course 2
                        "\t Start time: " + rs.getTime(10) +
                        "\t End time: " + rs.getTime(11)+
                        "\t Days: " + rs.getString(12));
                System.out.println("Course 3: " + rs.getString(13) + //course 3
                        "\t Start time: " + rs.getTime(14) +
                        "\t End time: " + rs.getTime(15) +
                        "\t Days: " + rs.getString(16));
                System.out.println("Course 4: " + rs.getString(17) + //course 4
                        "\t Start time: " + rs.getTime(18) +
                        "\t End time: " + rs.getTime(19) +
                        "\t Days: " + rs.getString(20));
            }
        }

        catch (Exception e){
            e.printStackTrace();
        }
    }
}
