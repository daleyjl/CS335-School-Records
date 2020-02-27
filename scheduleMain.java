import java.util.*;
import java.sql.DriverManager;

public class scheduleMain {
    public static void main (String args[]){
        Scanner input = new Scanner(System.in);
        ScheduleView schedule = new ScheduleView("tranat", "1858423");
        System.out.println("Insert semester: ");
        String sem = input.nextLine();
        System.out.println("Insert ID: ");
        String id = input.nextLine();
        schedule.listCourse(sem, id);
    }
}
