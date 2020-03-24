import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/** TranscriptView Class and Methods
 * @author An
 */
public class TranscriptView {
    private Connection connection;
    TranscriptView(String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://dany.simmons.edu:3306/33501sp20_daleyjl?characterEncoding=UTF-8",
                    user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void transcript (String ID){
        try {
            Statement selectID = connection.createStatement();
            System.out.println("Here is your transcript:");
            //gets all of their grades in the system
            ResultSet rs = selectID.executeQuery(
                    "SELECT Student_ID, Semester, Last_Name, First_Name, " +
                            "Course_1, Grade_1, Absences_1, " +
                            "Course_2, Grade_2, Absences_2, " +
                            "Course_3, Grade_3, Absences_3, " +
                            "Course_4, Grade_4, Absences_4, " +
                            "Grade_Overall" +
                            " FROM SCHEDULES " +
                            "WHERE Student_ID ='" + ID + "';"

            );
            while(rs.next()){
                //outputs all of their info
                System.out.println("Student ID: " + rs.getString(1));
                System.out.println("Semesters attended: " + rs.getString(2));
                System.out.println(rs.getString(3) + ", " + rs.getString(4));
                System.out.println("Course: \t" + "Grade: \t" + "Absences: ");
                System.out.println(rs.getString(5) + "\t\t\t" + rs.getFloat(6) + "\t\t\t" + rs.getString(7) );
                System.out.println(rs.getString(8) + "\t\t\t" + rs.getFloat(9) + "\t\t\t" + rs.getString(10) +"\t");
                System.out.println(rs.getString(11) + "\t\t\t" + rs.getFloat(12) + "\t\t\t" + rs.getString(13) +"\t");
                System.out.println(rs.getString(14) + "\t\t\t" + rs.getFloat(15) + "\t\t\t" + rs.getString(16) +"\t");
                System.out.println("Grade overall");
                System.out.println(rs.getFloat(17));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
