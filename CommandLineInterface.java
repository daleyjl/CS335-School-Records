import java.util.*;

public class CommandLineInterface {
    public static void main (String [] args){

        StudentRecords studentRec = new StudentRecords("daleyjl", "1768443");
        Scanner input= new Scanner(System.in);
        ScheduleView schedule = new ScheduleView("daleyjl", "1768443");
        TranscriptView transcript = new TranscriptView("daleyjl", "1768443");
        System.out.println("First Name: ");
        String f_name=input.next();
        System.out.println("Last Name: ");
        String l_name=input.next();
        System.out.println("Enter 1 for Faculty or 2 for Student ");
        int status=input.nextInt();
        if (status==2){
            System.out.println("Hello "+f_name+" "+l_name+"! Welcome to AARC 2.0.\nPlease enter your student ID Number: ");
            int student_id= input.nextInt();
            String kg="yes";
            while(kg.equals("yes")){
                System.out.println("What would you like to do?\n1. View Schedule \n2. Add Class \n3. Drop Class \n4. View Transcript");
                int choice= input.nextInt();
                if (choice==1){
                    System.out.println("Here is your Schedule: ");
                    viewSchedule(schedule, student_id);
                }
                else if (choice==2){
                    addClass(studentRec, student_id, l_name, f_name);
                }
                else if (choice==3){
                    dropClass(studentRec, student_id);
                }
                else if (choice==4){

                    System.out.println("Here are your transcripts: ");
                    transcript.transcript(Integer.toString(student_id));
                }
                else {
                    System.out.println("Please enter a valid entry");
                }

                System.out.println("Would you like to do something else? [yes/no] ");
                kg= input.next();
            }}
        else{
            System.out.println("Hello "+f_name+" "+l_name+"! Welcome to AARC 2.0.\nPlease enter your faculty ID Number: ");
            int faculty_id= input.nextInt();
            String cont="yes";
            while (cont.equals("yes")){

                System.out.println("What would you like to do?\n1. Input Grades \n2. Add a Course \n3. Record Attendance\n4. Add Student");
                int f_choice= input.nextInt();
                if (f_choice==1){
                  inputGrade(studentRec);
                }
                else if (f_choice==2){
                    System.out.println("Enter course details: ");
                    newCourse(studentRec);
                }
                else if (f_choice==3){
                    inputAttendance(studentRec);
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

    public static void viewSchedule(ScheduleView schedule, int student_id){
        Scanner input= new Scanner(System.in);
        System.out.println("Insert semester: ");
        String sem = input.nextLine();
        schedule.listSchedule(sem, student_id);

    }

    public static void dropClass(StudentRecords studentRec, int student_id){
        System.out.println("**Note: You can only drop from your active schedule [Fall 2016] ");
        studentRec.dropCourse(student_id);

    }

    public static void addClass(StudentRecords studentRec, int student_id, String l_name, String f_name) {
        Scanner input = new Scanner(System.in);
        studentRec.listCourses();
        System.out.println("Enter the ID of the class you would like to add: ");
        String classChoice=input.next();

        studentRec.saveClass(student_id, classChoice);

    }

    public static void newStud(StudentRecords studentRec){
        Scanner input= new Scanner(System.in);
        System.out.println("Student ID: ");
        String student_id= input.nextLine();
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

        String[] student_info= new String[]{student_id, l_name, f_name, email, phoneNum, gpa};

        if (answer.equals("yes")){
       /* Student newStudent= new Student(student_id, l_name, f_name, phoneNum, email, gpa);
        newStudent.addStudent();*/
            System.out.println("Student added!");
            studentRec.save(student_info);}
    }

    public static void newCourse(StudentRecords studentRec){
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
        String capacity= input.nextLine();

        System.out.println(course_id+"\n"+start_time+"\n"+end_time+"\n"+course_name+"\n"+days+"\n"+sem+"\n"+instructor+"\n"+capacity+"\n\nAdd Course? [yes]/[no]");

        String answer=input.next();

        String[] course_info= new String[]{course_id, start_time, end_time, course_name, days, sem, instructor, capacity};

        if (answer.equals("yes")){
            System.out.println("Course added!");
            studentRec.saveCourse(course_info);}
    }
    public static void inputGrade(StudentRecords studentRec){
        Scanner input= new Scanner(System.in);
        System.out.println("Enter Course ID: ");
        String course_id = input.nextLine();
        studentRec.listStudentsInCourse(course_id);
        System.out.println("Enter student ID to input that student's grade: "); //switch ID to an int, then add to print statement
        int student_id = input.nextInt();
        System.out.println("Enter student's grade (out of 4.0): ");
        double grade = input.nextDouble(); //input checks for user error (try catch?)
        studentRec.insertGrade(student_id,course_id,grade);
        /* TODO:  make method that inserts grade into sql database, give user the option to insert more grades, recursive?*/
    }

    public static void inputAttendance(StudentRecords studentRec){
        Scanner input= new Scanner(System.in);
        System.out.println("Enter Course ID: ");
        String course_id = input.next();
        studentRec.listStudentsInCourse(course_id);
        System.out.println("Enter student ID to input attendance: "); //switch ID to an int, then add to print statement
        int student_id = input.nextInt();
        System.out.println("Enter attendance: ");
        String attendance = input.next(); //input checks for user error (try catch?)
        studentRec.insertAttendance(student_id,course_id,attendance);
    }
}