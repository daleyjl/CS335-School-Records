import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Student {
    private String firstName;
    private String lastName;
    public String idNum;
    private String email;
    private double gpa;
    private String phoneNum;

    public Student(){
        firstName="F";
        lastName="L";
        idNum=null;
        email="nope@nope.com";
        gpa=0.0;
        phoneNum="None";
    }

    public Student(String f, String l, String id, String em, double gradepa, String p){
        firstName=f;
        lastName=l;
        idNum=id;
        email=em;
        gpa=gradepa;
        phoneNum=p;
    }

    // This initializes database connection. We yoinked this directly from the Books project example.
    Connection recordConnector() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://dany.simmons.edu:3306/33501sp20_ciccareg?characterEncoding=UTF-8",
                    "ciccareg", "1690812");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void addStudent(String idNum, String lastName, String firstName, String email, String phoneNum, double gpa){
        try {
            Connection connection = recordConnector();
            Statement newStudent = connection.createStatement();
            newStudent.execute(
                    "INSERT INTO STUDENT" + "(STUDENT_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, GPA)" + " VALUES ('" + idNum +
                            "', '" + lastName + "', '" + firstName + "', '" + email + "', '" + phoneNum + "', '" + gpa + "')"
            );
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void getStudent(String idNumTry){
        try {
            Connection connection= recordConnector();
            Statement getStudent = connection.createStatement();
            ResultSet stu = getStudent.executeQuery(
                    "SELECT LAST_NAME, FIRST_NAME, EMAIL, PHONE_NUMBER, GPA FROM STUDENT WHERE " +
                    idNumTry + " = STUDENT_ID");
        idNum=idNumTry;
        lastName=stu.getString(1);
        firstName=stu.getString(2);
        email=stu.getString(3);
        phoneNum=stu.getString(4);
        gpa=stu.getInt(5);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

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
