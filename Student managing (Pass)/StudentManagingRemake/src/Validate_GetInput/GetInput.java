package Validate_GetInput;

import Entities.Student;
import java.io.*;
import java.util.*;

public class GetInput {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    Validate va = new Validate();

    //GET ID (CREATE FUNCTION)
    public String getStudentID() throws IOException {
        boolean invalidID;
        String id;
        do {
            System.out.print("Enter id: ");
            id = in.readLine();
            invalidID = va.idVal(id); //validate no special char
        } while (invalidID == true);
        return id;
    }

    //GET NAME (CREATE FUNCTION)
    public String getStudentName() throws IOException {
        boolean invalidName;
        String name;
        do {
            System.out.print("Enter name: ");
            name = in.readLine();
            invalidName = va.nameVal(name); //validate name (char only)
        } while (invalidName == true);
        return name;
    }

    //GET SEMESTER (CREATE FUNCTION)
    public String getStudentSemester() throws IOException {
        boolean invalidSemester;
        String semester;
        do {
            System.out.print("Enter semester: ");
            semester = in.readLine();
            invalidSemester = va.idVal(semester); //validate no special char
        } while (invalidSemester == true);
        return semester;
    }

    //GET STUDENT COURSE (CREATE FUNCTION)
    public String getStudentCourse() throws IOException {
        boolean invalidCourse;
        String course;
        do {
            System.out.print("Enter course: ");
            course = in.readLine();
            invalidCourse = va.courseVal(course); //validate for correct course (No special char, number or not exist course)
        } while (invalidCourse == true);
        return course.toLowerCase();
    }

    //GET SEARCH NAME (SEARCH FUNCTION)
    public String getSearchName() throws IOException {
        boolean invalidName;
        String name;
        do {
            System.out.print("Enter search name: ");
            name = in.readLine();
            invalidName = va.nameVal(name); //validate name (char only)
        } while (invalidName == true);
        return name;
    }

    //GET DUPLICATE NAME (SEARCH FUNCTION)
    public String getDupName(String id, ArrayList<Student> studentList) {
        String dupName = null;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equalsIgnoreCase(id) == true) {
                dupName = studentList.get(i).getName();
                break;
            }
        }
        return dupName;
    }

    //GET SEARCH ID (UPDATE/DELETE FUNCTION)
    public String getSearchID() throws IOException {
        boolean invalidID;
        String searchID;
        do {
            System.out.print("Enter search id: ");
            searchID = in.readLine();
            invalidID = va.idVal(searchID); //validate no special char
        } while (invalidID == true);
        return searchID;
    }

    //CHECK FOR UPDATE OR DELETE (UPDATE/DELETE FUNCTION)
    public String udCheck() throws IOException {
        boolean UDCheck;
        String udInput;
        do {
            System.out.print("Do you want to update (U) or delete (D) student ? ");
            udInput = in.readLine().trim();
            if (udInput.equalsIgnoreCase("u")) {        //check if user input u or d
                UDCheck = true;
            } else if (udInput.equalsIgnoreCase("d")) {
                UDCheck = true;
            } else {
                System.out.println("Wrong input. Please enter U or D only");
                UDCheck = false;
            }
        } while (UDCheck == false);
        return udInput;
    }

    //GET SELECTED INDEX (UPDATE/DELETE FUNCTION)
    public int getSelectedIndex(ArrayList<Student> foundList) throws IOException {
        boolean invalidIndex;
        int selectIndex = 0;
        do {
            try {
                System.out.print("Enter index: ");
                selectIndex = Integer.parseInt(in.readLine().trim());
                if (selectIndex <= 0 || selectIndex > foundList.size()) { //check if index exist
                    System.out.println("There arent any index like that. Please enter again");
                    System.out.println();
                    invalidIndex = true;
                } else {
                    invalidIndex = false;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage() + "is not a number. Please enter again"); //check if input not a number
                invalidIndex = true;
            }
        } while (invalidIndex == true);
        return (selectIndex - 1);
    }

    //GET SELECTED STUDENT (UPDATE/DELETE FUNCTION)
    public Student getSelectedStudent(int selectedIndex, ArrayList<Student> foundList) {
        Student selectedStudent = new Student();
        selectedStudent.setId(foundList.get(selectedIndex).getId());
        selectedStudent.setName(foundList.get(selectedIndex).getName());
        selectedStudent.setSemester(foundList.get(selectedIndex).getSemester());
        selectedStudent.setCourse(foundList.get(selectedIndex).getCourse());
        return selectedStudent;
    }

    //GET FOUND STUDENT (UPDATE/DELETE FUNCTION)
    public Student getFoundStudent(Student selectedStudent, ArrayList<Student> studentList) {
        Student foundStudent = new Student();
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equalsIgnoreCase(selectedStudent.getId()) == true
                    && studentList.get(i).getName().equalsIgnoreCase(selectedStudent.getName()) == true
                    && studentList.get(i).getSemester().equalsIgnoreCase(selectedStudent.getSemester()) == true
                    && studentList.get(i).getCourse().equalsIgnoreCase(selectedStudent.getCourse()) == true) {
                foundStudent = studentList.get(i);
            }
        }
        return foundStudent;
    }
}
