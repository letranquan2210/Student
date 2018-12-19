package Functions;

import Entities.*;
import Validate_GetInput.*;
import java.io.*;
import java.util.*;

public class Function {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    GetInput get = new GetInput();
    Validate va = new Validate();

    //---------CREATE STUDENT LIST---------//
    public ArrayList createStudentList(ArrayList<Student> studentList) throws IOException {
        //add student to list (atleast 10 students)
        boolean addMore, dupID, sameInfo;
        do {
            do {
                Student x = new Student();
                x.setId(get.getStudentID());
                dupID = va.checkDupID(x.getId(), studentList); //check for duplicate id
                if (dupID == false) {
                    x.setName(get.getStudentName());
                    x.setSemester(get.getStudentSemester());
                    x.setCourse(get.getStudentCourse());
                    studentList.add(x);
                    System.out.println("Student added\n");
                } else {
                    String dupName = get.getDupName(x.getId(), studentList);    //set the name to be the dup one
                    x.setName(dupName);
                    System.out.println(x.getId() + "    |    " + x.getName() + "\n");
                    x.setSemester(get.getStudentSemester());
                    x.setCourse(get.getStudentCourse());
                    sameInfo = va.checkSameInfo(x.getId(), x.getSemester(), x.getCourse(), studentList); //check if all info are the same
                    if (sameInfo == false) {
                        studentList.add(x);
                        System.out.println("Student added\n");
                    } else {
                        System.out.println("Info already been added. Please try again\n");
                    }
                }
            } while (studentList.size() < 4);
            addMore = va.ynVal();
        } while (addMore == true);
        //print out student list
        printStudentList(studentList);
        return studentList;
    }

    //---------FIND STUDENT---------//
    public void findStudent(ArrayList<Student> studentList) throws IOException {
        if (studentList.isEmpty() == true) {
            System.out.println("There are no students in student list\n");
        } else {
            ArrayList<Student> foundList = new ArrayList<>();
            //get search name
            String searchName = get.getSearchName();
            //search in the array
            for (int i = 0; i < studentList.size(); i++) {
                if (studentList.get(i).getName().trim().toLowerCase().contains(searchName.trim().toLowerCase()) == true) {
                    foundList.add(studentList.get(i));
                }
            }
            //display result
            Comparator<Student> nameSort = new Comparator<Student>() {
                @Override
                public int compare(Student t, Student t1) {
                    return t.getName().compareToIgnoreCase(t1.getName());
                }
            };
            Collections.sort(foundList, nameSort); //sort the result list and print out
            if (foundList.isEmpty()) {
                System.out.println("No student found");
            } else {
                System.out.println("Name    |    Semester    |    Course name");
                for (int i = 0; i < foundList.size(); i++) {
                    System.out.println(foundList.get(i).getName() + "    |    " + foundList.get(i).getSemester() + "    |    " + foundList.get(i).getCourse() + "\n");
                }
            }
        }
    }

    //---------UPDATE/DELETE STUDENT---------//
    public ArrayList updateDelete(ArrayList<Student> studentList) throws IOException {
        if (studentList.isEmpty() == true) {
            System.out.println("There are no students in student list\n");
        } else {
            boolean idExist;
            //get search id
            String searchID = get.getSearchID();
            //check if search id exist
            idExist = va.checkSearchID(searchID, studentList);
            if (idExist == false) {
                System.out.println("ID do not exist\n");
            } else {
                ArrayList<Student> foundList = new ArrayList<>();
                //add student with search id to found list
                for (int i = 0; i < studentList.size(); i++) {
                    if (studentList.get(i).getId().equalsIgnoreCase(searchID) == true) {
                        foundList.add(studentList.get(i));
                    }
                }
                //show found list
                System.out.println("------- Found List -------");
                System.out.println("Index    |    Name    |    Semester    |    Course");
                for (int j = 0; j < foundList.size(); j++) {
                    System.out.println("    " + (j + 1) + "    |    " + foundList.get(j).getName() + "    |    " + foundList.get(j).getSemester() + "    |    " + foundList.get(j).getCourse() + "\n");
                }
                //get selected student
                int selectedIndex = get.getSelectedIndex(foundList);
                Student selectedStudent = get.getSelectedStudent(selectedIndex, foundList);
                //get the found student inside student list
                Student foundStudent = get.getFoundStudent(selectedStudent, studentList);
                System.out.println("ID    |    Name    |    Semester    |    Course");
                System.out.println(foundStudent.getId() + "    |    " + foundStudent.getName() + "    |    " + foundStudent.getSemester() + "    |    " + foundStudent.getCourse());

                //check if user want to delete or update
                String udChoice = get.udCheck().toLowerCase();
                switch (udChoice) {
                    case "u": { //update student
                        boolean dupID, sameInfo;
                        //update student
                        String newID = get.getStudentID();
                        dupID = va.checkDupID(newID, studentList); //check if the new id already exist in student list
                        if (dupID == false) {
                            foundStudent.setId(newID);
                            foundStudent.setName(get.getStudentName());
                            foundStudent.setSemester(get.getStudentSemester());
                            foundStudent.setCourse(get.getStudentCourse());
                            System.out.println("Student updated\n");
                        } else {
                            String dupName = get.getDupName(newID, studentList);
                            String newSemester = get.getStudentSemester();
                            String newCourse = get.getStudentCourse();
                            sameInfo = va.checkSameInfo(newID, newSemester, newCourse, studentList); //check if all info are the same
                            if (sameInfo == true) {
                                System.out.println("Info already been added. Please try again\n");
                            } else {
                                foundStudent.setId(newID);
                                foundStudent.setName(dupName);
                                foundStudent.setSemester(newSemester);
                                foundStudent.setCourse(newCourse);
                                System.out.println("Student updated\n");
                            }
                        }

                        //print out student list
                        printStudentList(studentList);
                        break;
                    }
                    case "d": { //delete student
                        //delete the found student in student list
                        studentList.remove(foundStudent);
                        //print out student list
                        printStudentList(studentList);
                        break;
                    }
                }
            }
        }
        return studentList;
    }

    //---------REPORT---------//
    public void report(ArrayList<Student> studentList) {
        boolean dupPer;
        if (studentList.isEmpty() == true) {
            System.out.println("There are no students in student list\n");
        } else {
            System.out.println("-----------Report-----------");
            ArrayList<StudentRP> reportList = new ArrayList<>();
            //calculate total course
            for (int i = 0; i < studentList.size(); i++) {
                StudentRP x = new StudentRP();
                x.setId(studentList.get(i).getId());
                dupPer = va.checkRPList(x.getId(), reportList); //check if the id is already exist in report list
                if (dupPer == false) { //if that id is completely new the add it
                    x.setName(studentList.get(i).getName());
                    x.setCourse(studentList.get(i).getCourse());
                    x.setSemester(studentList.get(i).getSemester());
                    x.setTotalJava(0);
                    x.setTotalNet(0);
                    x.setTotalC(0);
                    if (x.getCourse().equalsIgnoreCase("Java") == true) {
                        x.setTotalJava(x.getTotalJava() + 1);
                    } else if (x.getCourse().equalsIgnoreCase(".Net") == true) {
                        x.setTotalNet(x.getTotalNet() + 1);
                    } else {
                        x.setTotalC(x.getTotalC() + 1);
                    }
                    reportList.add(x);
                } else { //if not then update the total count
                    x.setCourse(studentList.get(i).getCourse());
                    for (int j = 0; j < reportList.size(); j++) {
                        if (x.getId().equalsIgnoreCase(reportList.get(j).getId()) == true) {
                            if (x.getCourse().equalsIgnoreCase("Java") == true) {
                                reportList.get(j).setTotalJava(reportList.get(j).getTotalJava() + 1);
                                break;
                            } else if (x.getCourse().equalsIgnoreCase(".Net") == true) {
                                reportList.get(j).setTotalNet(reportList.get(j).getTotalNet() + 1);
                                break;
                            } else {
                                reportList.get(j).setTotalC(reportList.get(j).getTotalC() + 1);
                                break;
                            }
                        }
                    }
                }
            }
            //print out result
            for (int i = 0; i < reportList.size(); i++) {
                System.out.println("Student: " + reportList.get(i).getName());
                System.out.println("Java: " + reportList.get(i).getTotalJava());
                System.out.println(".Net: " + reportList.get(i).getTotalNet());
                System.out.println("C/C++: " + reportList.get(i).getTotalC());
                System.out.println();
            }
        }
    }

    //PRINT STUDENT LIST
    public void printStudentList(ArrayList<Student> studentList) {
        System.out.println("\n---------Student list---------");
        System.out.println("ID    |    Name    |    Semester    |    Course");
        for (int i = 0; i < studentList.size(); i++) {
            System.out.println(studentList.get(i).getId() + "    |    " + studentList.get(i).getName() + "    |    " + studentList.get(i).getSemester() + "    |    " + studentList.get(i).getCourse() + "\n");
        }
        System.out.println();
    }
}
