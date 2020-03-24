import java.util.*;

/** Command Line Interface main
 * @author Jamie
 */

public class CommandLineInterface {
    public static void main (String [] args){
        //sets variables
        int go=0;
        int student_id=-1;

        //make all objects for classes
        StudentRecords studentRec = new StudentRecords("daleyjl", "1768443");
        ScheduleView schedule = new ScheduleView("daleyjl", "1768443");
        TranscriptView transcript = new TranscriptView("daleyjl", "1768443");
        CheckIds check= new CheckIds("daleyjl", "1768443");
        CourseInfo courseActions= new CourseInfo("daleyjl", "1768443");

        //get info from user
        Scanner input= new Scanner(System.in);
        System.out.println("Enter 1 for Faculty or 2 for Student ");
        int status=input.nextInt();

        //if user is student:
        if (status==2){
            int tries=0;
            while (go==0){
                if (tries==0){
            System.out.println("Please enter your student ID Number: ");}
                else{
                    System.out.println("Invalid ID. Please enter your student ID Number");
                }
                student_id= input.nextInt();
          go=checkID(check, student_id, status);
            tries++;}
            String kg="yes";
            while(kg.equals("yes")){
                System.out.println("What would you like to do?\n1. View Schedule \n2. Add Class \n3. Drop Class \n4. View Transcript");
                int choice= input.nextInt();
                //choices for what to do
                if (choice==1){
                    viewSchedule(schedule, student_id);
                }
                else if (choice==2){
                    addClass(courseActions, studentRec, student_id);
                }
                else if (choice==3){
                    dropClass(studentRec, student_id);
                }
                else if (choice==4){
                    transcript.transcript(Integer.toString(student_id));
                }
                else {
                    System.out.println("Please enter a valid entry");
                    //they did not enter 1-4
                }

                System.out.println("Would you like to do something else? [yes/no] ");
                kg= input.next();
            }}

        //if user is faculty:
        else{
            int count=0;
            while (go==0){
                if (count==0){
            System.out.println("Please enter your faculty ID Number: ");}
                else{
                    System.out.println("Invalid ID. Please enter your faculty ID Number: ");
                }
            int faculty_id= input.nextInt();
            go=checkID(check, faculty_id, status);
            count++;}
            String cont="yes";
            while (cont.equals("yes")){

                System.out.println("What would you like to do?\n1. Input Grades \n2. Add a Course \n3. Record Attendance\n4. Add Student");
                int f_choice= input.nextInt();
                //choices
                if (f_choice==1){
                  inputGrade(studentRec, courseActions);
                }
                else if (f_choice==2){
                    System.out.println("Enter course details: ");
                    newCourse(courseActions);
                }
                else if (f_choice==3){
                    inputAttendance(studentRec, courseActions);
                }
                else if (f_choice==4){
                    newStud(studentRec);
                }
                else{
                    System.out.println("Not a valid entry ");
                }
                System.out.println("Would you like to do something else? ");
                cont=input.next();
            }


        }
    }

    /** checkID
     * @author Jamie
     */

    public static int checkID(CheckIds check, int id, int num){
        //makes sure the ID they entered is in the database as a faculty member or student
        int go=0;
        if (num==1){
            go=check.checkFaculty(id);
        }
        else{
            go=check.checkStudent(id);
        }
       return go;
    }

    /** viewSchedule
     * @author An
     */

    public static void viewSchedule(ScheduleView schedule, int student_id){
        //shows the student their schedule in ScheduleView.java
        Scanner input= new Scanner(System.in);
        System.out.println("Insert semester: ");
        String sem = input.nextLine();
        schedule.listSchedule(sem, student_id);

    }
    /** dropClass
     * @author Jamie
     */


    public static void dropClass(StudentRecords studentRec, int student_id){
        //allows students to drop a class in the current academic period (Fall 2016 for our project)
        System.out.println("**Note: You can only drop from your active schedule [Fall 2016] ");
        studentRec.dropCourse(student_id);

    }

    /** addClass
     * @author Jamie
     */

    public static void addClass(CourseInfo courseActions, StudentRecords studentRec, int student_id) {
        //allows student to add a class to their schedules
        Scanner input = new Scanner(System.in);
        courseActions.listCourses();
        System.out.println("Enter the ID of the class you would like to add: ");
        String classChoice=input.next();

        studentRec.saveClass(student_id, classChoice);

    }

    /** newStud
     * @author Jamie
     */

    public static void newStud(StudentRecords studentRec){
        //creates a new student object
        Scanner input= new Scanner(System.in);
        System.out.println("Student ID: ");
        String id= input.nextLine();
        int student_id=Integer.valueOf(id);
        System.out.println("Last Name: ");
        String l_name=input.nextLine();
        System.out.println("First Name: ");
        String f_name=input.nextLine();
        System.out.println("Email: ");
        String email=input.nextLine();
        System.out.println("Phone Number: ");
        String phoneNum= input.nextLine();
        System.out.println("GPA: ");
        String gpa= input.nextLine();

        System.out.println(student_id+"\n"+f_name+" "+l_name+"\n"+email+"\n"+phoneNum+"\n"+gpa+"\n\n\nAdd Student? [yes]/[no]");

        String answer=input.next();

        if (answer.equals("yes")){
       Student newStudent= new Student(f_name, l_name, Integer.toString(student_id), email, Double.valueOf(gpa), phoneNum);
            System.out.println("Student added!");
            newStudent.addStudent();}
        else{
            System.out.println("Student not added");
        }
    }

    /** newCourse
     * @author Jamie
     */

    public static void newCourse(CourseInfo courseActions){
        //creates a new course object
        Scanner input= new Scanner(System.in);
        System.out.println("Enter the following information: ");
        System.out.println("Course ID: ");
        String course_id= input.nextLine();
        System.out.println("Start Time: ");
        String start_time=input.nextLine();
        System.out.println("End Time: ");
        String end_time=input.nextLine();
        System.out.println("Course Name: ");
        String course_name=input.nextLine();
        System.out.println("Days: ");
        String days= input.nextLine();
        System.out.println("Semester: ");
        String sem= input.nextLine();
        System.out.println("Instructor: ");
        String instructor= input.nextLine();
        System.out.println("Capacity: ");
        int capacity= input.nextInt();

        System.out.println(course_id+"\n"+start_time+"\n"+end_time+"\n"+course_name+"\n"+days+"\n"+sem+"\n"+instructor+"\n"+capacity+"\n\nAdd Course? [yes]/[no]");

        String answer=input.next();


        if (answer.equals("yes")){
            Course newCourse= new Course(course_id, start_time, end_time, course_name, days, sem, instructor, capacity);
            System.out.println("Course added!");
            newCourse.addCourse();}
        else{
            System.out.println("Course not added");
        }
    }

    /** inputGrade
     * @author Caitlin
     */
    public static void inputGrade(StudentRecords studentRec, CourseInfo courseActions){
        //allows faculty members to input grades
        Scanner input= new Scanner(System.in);
        System.out.println("Enter Course ID: ");
        String course_id = input.nextLine();
        courseActions.listStudentsInCourse(course_id);
        System.out.println("Enter student ID to input that student's grade: "); //switch ID to an int, then add to print statement
        int student_id = input.nextInt();
        System.out.println("Enter student's grade (out of 4.0): ");
        double grade = input.nextDouble(); //input checks for user error (try catch?)
        studentRec.insertGrade(student_id,course_id,grade);

    }

    /** inputAttendance
     * @author Caitlin
     */

    public static void inputAttendance(StudentRecords studentRec, CourseInfo courseActions){
        //allows faculty members to input attendance
        Scanner input= new Scanner(System.in);
        System.out.println("Enter Course ID: ");
        String course_id = input.next();
        courseActions.listStudentsInCourse(course_id);
        System.out.println("Enter student ID to input attendance: "); //switch ID to an int, then add to print statement
        int student_id = input.nextInt();
        System.out.println("Enter attendance: ");
        String attendance = input.next(); //input checks for user error (try catch?)
        studentRec.insertAttendance(student_id,course_id,attendance);
    }
}