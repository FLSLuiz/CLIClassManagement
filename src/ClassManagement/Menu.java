package ClassManagement;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private final StudentsCRUD _crudOp;
    private final Scanner scanner;

    public Menu(StudentsCRUD crudOp) {
        _crudOp = crudOp;
        scanner = new Scanner(System.in);
    }

    public void ShowMenu() throws SQLException {
        while (true) {
            System.out.println("Welcome to the ClassManagement.Students Database!\n");
            System.out.println("==== Class ClassManagement.Menu ====");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Modify Student Data");
            System.out.println("4. Show Every Student");
            System.out.println("5. Search Student By ID");
            System.out.println("6. Show Class Average");
            System.out.println("7. Show Student(s) with highest grade(s)");
            System.out.println("8. Show Student(s) with lowest grade(s)");
            System.out.println("9. Leave");
            System.out.print("Pick your poison: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    AddStudent();
                    break;
                case 2:
                    RemoveStudent();
                    break;
                case 3:
                    UpdateStudent();
                    break;
                case 4:
                    ShowAllStudents();
                    break;
                case 5:
                    GetStudentById();
                    break;
                case 6:
                    ShowClassAvg();
                    break;
                case 7:
                    ShowHighestGrade();
                    break;
                case 8:
                    ShowLowestGrade();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Invalid option! Press any key to continue...");
                    scanner.nextLine();
                    break;
            }
        }
    }

    private void AddStudent() throws SQLException {
        Students Student = new Students();
        System.out.println("Add Student!");

        System.out.print("Student ID: ");
        Student.setId_Student(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Name: ");
        Student.setName(scanner.nextLine());

        System.out.print("Phone Number: ");
        Student.setPhoneNr(scanner.nextLine());

        System.out.print("Birthday (dd/MM/yyyy): ");
        Student.setBirthDate(scanner.nextLine());

        System.out.print("Final Grade: ");
        Student.setFinalGrade(Float.parseFloat(scanner.nextLine()));

        _crudOp.Add(Student);
        System.out.println("Student added successfully! Press any key to continue...");
        scanner.nextLine();
    }

    private void RemoveStudent() throws SQLException {
        System.out.println("Remove Student");
        System.out.print("Insert student ID: ");
        scanner.nextLine(); // Consume newline character

        int id;
        try {
            id = Integer.parseInt(scanner.nextLine()); // Read student ID
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for student ID!");
            return;
        }

        _crudOp.Remove(id);
        System.out.println("Student removed successfully! Press any key to continue...");
        scanner.nextLine(); // Consume newline character
    }



    private void UpdateStudent() throws SQLException {
        System.out.println("Modify Student Data");
        System.out.print("Insert Student ID: ");
        int id_student = Integer.parseInt(scanner.nextLine());
        Students Student = _crudOp.GetById(id_student);

        if (Student == null) {
            System.out.println("Student not found! Press any key to continue...");
            scanner.nextLine();
            return;
        }

        System.out.println("Current Name: " + Student.getName());
        System.out.print("New Name (Leave this empty to keep the current): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) Student.setName(newName);

        System.out.println("Current Phone Number: " + Student.getPhoneNr());
        System.out.print("New Phone Number (Leave this empty to keep the current): ");
        String newPhoneNr = scanner.nextLine();
        if (!newPhoneNr.isEmpty()) Student.setPhoneNr(newPhoneNr);

        System.out.println("Current birthdate: " + Student.getBirthDate());
        System.out.print("New birthdate (Leave this empty to keep the current): ");
        String newBirthDate = scanner.nextLine();
        if (!newBirthDate.isEmpty()) Student.setBirthDate(newBirthDate);

        System.out.println("Current Final Grade: " + Student.getFinalGrade());
        System.out.print("New Final Grade (Leave this empty to keep the current): ");
        String newGrade = scanner.nextLine();
        if (!newGrade.isEmpty()) Student.setFinalGrade(Float.parseFloat(newGrade));

        _crudOp.Update(Student);
        System.out.println("Student data has been updated successfully! Press any key to continue...");
        scanner.nextLine();
    }

    private void ShowAllStudents() throws SQLException {
        System.out.println("Every Student:");
        _crudOp.GetAll().forEach(Student -> {
            System.out.printf("Id_Student: %d, Name: %s, PhoneNr: %s, BirthDate: %s, FinalGrade: %.2f%n",
                    Student.getId_Student(), Student.getName(), Student.getPhoneNr(), Student.getBirthDate(), Student.getFinalGrade());
        });
        System.out.println("Press any key to continue");
        scanner.nextLine();
    }

    private void GetStudentById() throws SQLException {
        System.out.println("Search Student By ID");
        System.out.print("Insert the student's ID: ");
        int id_student = Integer.parseInt(scanner.nextLine());

        Students Student = _crudOp.GetById(id_student);

        if (Student != null)
        {
            System.out.printf("Id_student: %d, Name: %s, PhoneNr: %s, BirthDate: %s, FinalGrade: %.2f%n",
                    Student.getId_Student(), Student.getName(), Student.getPhoneNr(), Student.getBirthDate(), Student.getFinalGrade());
        }
        else
        {
            System.out.println("Student not found!");
        }
        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }

    private void ShowClassAvg() throws SQLException {
        float avg = (float) _crudOp.ClassAvg();
        System.out.printf("Class Average: %.2f%n", avg);
        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }

    private void ShowHighestGrade() throws SQLException {
        System.out.println("Student with highest Grade:");
        _crudOp.GetHighestGrade().forEach(Student -> {
            System.out.printf("Id_student: %d, Name: %s, PhoneNr: %s, BirthDate: %s, FinalGrade: %.2f%n",
                    Student.getId_Student(), Student.getName(), Student.getPhoneNr(), Student.getBirthDate(), Student.getFinalGrade());
        });
        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }

    private void ShowLowestGrade() throws SQLException {
        System.out.println("Student with lowest Grade:");
        _crudOp.GetLowestGrade().forEach(Student -> {
            System.out.printf("Id_student: %d, Name: %s, PhoneNr: %s, BirthDate: %s, FinalGrade: %.2f%n",
                    Student.getId_Student(), Student.getName(), Student.getPhoneNr(), Student.getBirthDate(), Student.getFinalGrade());
        });
        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }
}
