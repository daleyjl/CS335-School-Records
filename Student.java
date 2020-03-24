import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/** Student Class and Methods.
 * @author Theo
 * @author Jamie
 */

public class Student {
    private Connection connection;
    //attributes
    private String firstName;
    private String lastName;
    public String idNum;
    private String email;
    private double gpa;
    private String phoneNum;

    public Student(){
        firstName="";
        lastName="";
        idNum=null;
        email="";
        gpa=0.0;
        phoneNum="";
    }

    public Student(String f, String l, String id, String em, double gradepa, String p){
        firstName=f;
        lastName=l;
        idNum=id;
        email=em;
        gpa=gradepa;
        phoneNum=p;
    }

    // This initializes database connection. We took this directly from the Books project example.
    Connection recordConnector() {
        //connection to DB
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://dany.simmons.edu:3306/33501sp20_daleyjl?characterEncoding=UTF-8",
                    "daleyjl", "1768443");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    public void addStudent(){
        //adds student attributes to DB
        try {
            Connection connection = recordConnector();
            Statement newStudent = connection.createStatement();
            newStudent.execute(
                    "INSERT INTO STUDENTS" + "(STUDENT_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE_NUMBER, GPA)" + " VALUES ('" + idNum +
                            "', '" + lastName + "', '" + firstName + "', '" + email + "', '" + phoneNum + "', '" + gpa + "')"
            );
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    //get student attribute values

    public double getGPA() {
        return gpa;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

}
