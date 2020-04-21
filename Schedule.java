/**Schedule Class
 * @author Theo
 */

import java.util.ArrayList;

public class Schedule {
    private ArrayList<Course> classes;

    public Schedule() {
        classes = new ArrayList<Course>();
    }

    public ArrayList<Course> getClasses() {
        return classes;
    }

    public void addClass(Course course) {
        classes.add(course);
    }

    public void dropClass(Course course){
        classes.remove(course);
    }

    public String toString(){
        String printNice ="Your schedule is: ";
        for (Course aClass : classes) {
            String className = aClass.getName();
            printNice = printNice + className + "\r\n";
        }
        return printNice;
     }
}
