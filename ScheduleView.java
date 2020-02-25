import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ScheduleView {
    private Connection connection;
    ScheduleView(String user, String password){
        try{
            Class.forName("com.mysql.jbdc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://dany.simmons.edu:3306/33501sp20_tranat?characterEncoding=UTF-8",
                    user, password);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void listCourse (){
        Scanner input = new Scanner(System.in);
        System.out.println("Insert semester: ");
        String sem = input.next();
        System.out.println("Insert ID: ");
        String ID = input.next();
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
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
