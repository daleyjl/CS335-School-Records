import java.util.*;

public class CommandLineInterface {
    public static void main (String [] args){
        StudentRecords studentRec = new StudentRecords("daleyjl", "1768443");
        Scanner input= new Scanner(System.in);
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
                }
                else if (choice==2){
                    addClass(studentRec);
                }
                else if (choice==3){
                    System.out.println("Select the class you would like to drop: ");
                }
                else if (choice==4){
                    System.out.println("Here are your transcripts: ");
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
                    System.out.println("Which course? ");
                }
                else if (f_choice==2){
                    System.out.println("Enter course details: ");
                }
                else if (f_choice==3){
                    System.out.println("Which course? ");
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

    public static void addClass(StudentRecords studentRec) {
        Scanner input = new Scanner(System.in);
        studentRec.listCourses();
        System.out.println("Which class would you like to add: ");
        int classChoice=input.nextInt();
    }

    public static void newStud(StudentRecords studentRec){
        Scanner input= new Scanner(System.in);
        System.out.println("Enter the following information: ");
        System.out.println("Student ID: ");
        String student_id= input.next();
        System.out.println("Last Name: ");
        String l_name=input.next();
        System.out.println("First Name: ");
        String f_name=input.next();
        System.out.println("Email: ");
        String email=input.next();
        System.out.println("Phone Number: ");
        String phoneNum= input.next();
        System.out.println("GPA: ");
        String gpa= input.next();

        System.out.println(student_id+"\n"+f_name+" "+l_name+"\n"+email+"\n"+phoneNum+"\n"+gpa+"\n\n\nAdd Student? [yes]/[no]");

        String answer=input.next();

        String[] student_info= new String[]{student_id, l_name, f_name, email, phoneNum, gpa};

        if (answer.equals("yes")){
       /* Student newStudent= new Student(student_id, l_name, f_name, phoneNum, email, gpa);
        newStudent.addStudent();*/
            System.out.println("Student added!");
            studentRec.save(student_info);}
    }
}