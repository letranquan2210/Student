
import Entities.Student;
import Functions.*;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<Student> studentList = new ArrayList<>();
        MainMenu menu = new MainMenu();
        Function func = new Function();
        boolean end = false;

        do {
            //PRINT OUT MAIN MENU AND GET USER CHOICE
            menu.printMainMenu();
            int choice = menu.getMMenuChoice();

            //FUNCTIONS
            switch (choice) {
                case 1: { //CREATE
                    studentList = func.createStudentList(studentList);
                    break;
                }
                case 2: { //FIND STUDENT
                    func.findStudent(studentList);
                    break;
                }
                case 3: { //UPDATE AND DELETE
                    studentList = func.updateDelete(studentList);
                    break;
                }
                case 4: { //REPORT
                    func.report(studentList);
                    break;
                }
                case 5: { //END PROGRAM
                    end = true;
                    break;
                }
            }
        } while (end == false);
    }

}
