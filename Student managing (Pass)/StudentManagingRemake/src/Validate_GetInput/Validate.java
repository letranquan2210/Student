package Validate_GetInput;

import Entities.*;
import java.io.*;
import java.util.*;

public class Validate {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    //VALIDATE ID
    public boolean idVal(String id) {
        boolean invalidId;
        id = id.trim();
        if (id == null || id.equals("")) {      //check for blank input
            System.out.println("Blank input. Please enter again");
            System.out.println();
            invalidId = true;
        } else if (!id.matches("[a-zA-Z0-9]*")) { //check if ID having special characters
            System.out.println("No special character allowed");
            System.out.println();
            invalidId = true;
        } else {
            invalidId = false;
        }
        return invalidId;
    }

    //VALIDATE NAME
    public boolean nameVal(String name) {
        boolean invalidName;
        name = name.trim();
        if (name == null || name.equals("")) {  //check for blank input
            System.out.println("Blank input. Please enter name");
            System.out.println();
            invalidName = true;
        } else if (!name.matches("[a-zA-Z\\s]*")) {  //check for name to be character only
            System.out.println("No number or special character allowed");
            System.out.println();
            invalidName = true;
        } else {
            invalidName = false;
        }
        return invalidName;
    }

    //VALIDATE COURSE
    public boolean courseVal(String course) {
        boolean invalidCourse;
        course = course.trim();
        if (course == null || course.equals("")) {  //check for blank input
            System.out.println("Blank input. Please enter name");
            System.out.println();
            invalidCourse = true;
        } else if (!course.equalsIgnoreCase("Java") && !course.equalsIgnoreCase(".Net") && !course.equalsIgnoreCase("C/C++")) {
            System.out.println("Invalid course. Please enter Java, .Net or C/C++ correctly");
            System.out.println();
            invalidCourse = true;
        } else {
            invalidCourse = false;
        }
        return invalidCourse;
    }

    //VALIDATE Y/N QUESTION
    public boolean ynVal() throws IOException {
        boolean ynCheck;
        boolean addMore = false;
        do {
            System.out.print("Do you want to continue (Y/N) ? ");
            String check = in.readLine().trim();
            if (check.equalsIgnoreCase("y")) {          //if y then return to adding student
                ynCheck = true;
                addMore = true;
            } else if (check.equalsIgnoreCase("n")) {   //if n then end the function
                ynCheck = true;
                addMore = false;
                System.out.println();
            } else {
                System.out.println("Wrong input. Please enter Y or N only");
                ynCheck = false;
            }
        } while (ynCheck == false);
        return addMore;
    }

    //CHECK DUPLICATE ID
    public boolean checkDupID(String id, ArrayList<Student> studentList) {
        boolean dupID = false;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equalsIgnoreCase(id) == true) {
                dupID = true;
                break;
            } else {
                dupID = false;
            }
        }
        return dupID;
    }

    //CHECK DUPLICATE INFOMATION (ALL THE INFOMATION ARE THE SAME AS THE OTHER)
    public boolean checkSameInfo(String id, String semester, String course, ArrayList<Student> studentList) {
        boolean samePerson = false;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equalsIgnoreCase(id) == true) { //if same id, semester and course
                if (studentList.get(i).getSemester().equalsIgnoreCase(semester) == true 
                        && studentList.get(i).getCourse().equalsIgnoreCase(course) == true) {
                    samePerson = true;
                    break;
                } else {
                    samePerson = false;
                }
            }
        }
        return samePerson;
    }

    //CHECK IF SEARCH ID EXIST
    public boolean checkSearchID(String searchID, ArrayList<Student> studentList) {
        boolean idExist = false;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equalsIgnoreCase(searchID) == true) {
                idExist = true;
                break;
            } else {
                idExist = false;
            }
        }
        return idExist;
    }

    //CHECK FOR SAME PERSON IN REPORT LIST
    public boolean checkRPList(String id, ArrayList<StudentRP> reportList) {
        boolean dupPer = false;
        for (int i = 0; i < reportList.size(); i++) {
            if (id.equalsIgnoreCase(reportList.get(i).getId()) == true) {
                dupPer = true;
                break;
            } else {
                dupPer = false;
            }
        }
        return dupPer;
    }
}
