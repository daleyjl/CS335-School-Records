import java.util.*;
import java.sql.DriverManager;

public class scheduleMain {
    public static void main (String args[]){
        Scanner input = new Scanner(System.in);
        ScheduleView schedule = new ScheduleView("daleyjl", "1768443");
        System.out.println("Insert semester: ");
        String sem = input.nextLine();
        System.out.println("Insert ID: ");
        int id = input.nextInt();
        schedule.listSchedule(sem, id);
    }
}
