/** Student Class
 * @author Theo
 */

public class Student {
    //attributes
    private String firstName;
    private String lastName;
    private String idNum;
    private String email;
    private double gpa;
    private String phoneNum;
    private Schedule schedule;

    public Student(){
        firstName="";
        lastName="";
        idNum=null;
        email="";
        gpa=0.0;
        phoneNum="";
        schedule=null;
    }

    public Student(String studentFirstName, String studentLastName, String student_id, String studentEmail, double gradepa, String studentphone){
        firstName=studentFirstName;
        lastName=studentLastName;
        idNum=student_id;
        email=studentEmail;
        gpa=gradepa;
        phoneNum=studentphone;
        schedule=null;
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

    public Schedule getSchedule(){
        return schedule;
    }

    public String getIdNum() {
        return idNum;
    }
}