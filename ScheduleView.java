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
                    "jdbc:mysql://dany.simmons.edu:3306/33501sp20_daleyjl?characterEncoding=UTF-8",
                    user, password);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void listSchedule (String sem, int ID){
        try {
            Statement selectID = connection.createStatement();
            System.out.println("Here are the courses that you applied");
            ResultSet rs = selectID.executeQuery(
                    "SELECT Student_ID, Semester, Last_Name, First_Name, " +
                            "COURSE_1, Start_Time1, End_Time1, Days1," +
                            "COURSE_2, Start_Time2, End_Time2, Days2," +
                            "COURSE_3, Start_Time3, End_Time3, Days3," +
                            "COURSE_4, Start_Time4, End_Time4, Days4" +
                            " FROM SCHEDULES " +
                            "WHERE Semester ='"+ sem  + "'AND Student_ID ='"+ ID +"';"

            );
            while (rs.next()){
                System.out.println("Student ID: " + rs.getInt(1));
                System.out.println("Semester: " + rs.getString(2));
                System.out.println(rs.getString(3)
                        + ", " + rs.getString(4));
                System.out.println("Course 1: " + rs.getString(5) +
                        "\t Start time: " + rs.getString(6) +
                        "\t End time: " + rs.getString(7)+
                        "\t Days: " + rs.getString(8));
                System.out.println("Course 2: " + rs.getString(9) +
                        "\t Start time: " + rs.getString(10) +
                        "\t End time: " + rs.getString(11)+
                        "\t Days: " + rs.getString(12));
                System.out.println("Course 3: " + rs.getString(13) +
                        "\t Start time: " + rs.getString(14) +
                        "\t End time: " + rs.getString(15) +
                        "\t Days: " + rs.getString(16));
                System.out.println("Course 4: " + rs.getString(17) +
                        "\t Start time: " + rs.getString(18) +
                        "\t End time: " + rs.getString(19) +
                        "\t Days: " + rs.getString(20));
            }
        }

        catch (Exception e){
            e.printStackTrace();
        }
    }
}
