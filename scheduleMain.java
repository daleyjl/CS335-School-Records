import java.util.*;
import java.sql.DriverManager;

public class scheduleMain {
    public static void main (String args[]){
        Scanner input = new Scanner(System.in);
        System.out.println("User: ");
        String user = input.next();
        System.out.println("Password: ");
        String pass = input.next();
        ScheduleView schedule = new ScheduleView(user, pass);
        System.out.println("Insert semester: ");
        String sem = input.next();
        System.out.println("Insert ID: ");
        String ID = input.next();
        schedule.listCourse(sem, ID);
    }
}
