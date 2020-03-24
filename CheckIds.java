import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/** CheckIDs class and methods
 * @author Jamie
 */

public class CheckIds {

    private Connection connection;
    CheckIds(String user, String password) {
        //connection to DB
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://dany.simmons.edu:3306/33501sp20_daleyjl?characterEncoding=UTF-8",
                    user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int checkFaculty(int id) {
        int go=0;
        //go is set to 1 if their ID is valid
        try {
            Statement checkID = connection.createStatement();
            ResultSet rs = checkID.executeQuery(
                    "SELECT Faculty_ID, Name " +
                            "  FROM Faculty"+
                            " WHERE Faculty_ID= '" + id + "';");

            while (rs.next()){
                //is the ID in the database
                if (rs.getString(1).equals(Integer.toString(id))){
                    System.out.println("Hello "+rs.getString(2)+"! Welcome to AARC!");
                    go=1;
                }
            }
        }
        catch (Exception e) {
        }
        return go;
    }

    public int checkStudent(int id) {
        int go=0;
        //set to 1 if ID in DB
        try {
            Statement checkStuID = connection.createStatement();
            ResultSet rs = checkStuID.executeQuery(
                    "SELECT STUDENT_ID, LAST_NAME, FIRST_NAME " +
                            "  FROM STUDENTS"+
                            " WHERE STUDENT_ID= '" + id + "';");

            while (rs.next()){
                //checks if ID in DB
                System.out.println(rs.getString(1));
                if (rs.getString(1).equals(Integer.toString(id))){
                    System.out.println("Hello "+rs.getString(3)+" "+rs.getString(2)+"! Welcome to AARC!");
                    go=1;
                }
            }
        }
        catch (Exception e) {
        }
        return go;}
}
