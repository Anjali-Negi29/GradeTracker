import java.util.*;
import java.io.*;

public class StudentGradeTracker {

    static ArrayList<Student> students = new ArrayList<>();
    static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {

        loadFromFile();

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n========== STUDENT GRADE TRACKER ==========");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Average Marks");
            System.out.println("7. Highest Marks");
            System.out.println("8. Lowest Marks");
            System.out.println("9. Sort Students");
            System.out.println("10. Rank Students");
            System.out.println("11. Summary Report");
            System.out.println("12. Save Data");
            System.out.println("13. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {

                case 1:
                    addStudent(sc);
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent(sc);
                    break;

                case 4:
                    updateStudent(sc);
                    break;

                case 5:
                    deleteStudent(sc);
                    break;

                case 6:
                    averageMarks();
                    break;

                case 7:
                    highestMarks();
                    break;

                case 8:
                    lowestMarks();
                    break;

                case 9:
                    sortStudents();
                    break;

                case 10:
                    rankStudents();
                    break;

                case 11:
                    summaryReport();
                    break;

                case 12:
                    saveToFile();
                    break;

                case 13:
                    saveToFile();
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while(choice != 13);

        sc.close();
    }

    static void addStudent(Scanner sc) {

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Marks: ");
        int marks = sc.nextInt();

        students.add(new Student(name, marks));

        System.out.println("Student Added Successfully");
    }

    static void viewStudents() {

        if(students.isEmpty()) {
            System.out.println("No Records Found");
            return;
        }

        System.out.println("\nName\tMarks\tGrade");

        for(Student s : students) {

            System.out.println(
                    s.getName() + "\t"
                    + s.getMarks() + "\t"
                    + s.getGrade());
        }
    }

    static void searchStudent(Scanner sc) {

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        for(Student s : students) {

            if(s.getName().equalsIgnoreCase(name)) {

                System.out.println("Found");
                System.out.println("Marks: " + s.getMarks());
                System.out.println("Grade: " + s.getGrade());

                return;
            }
        }

        System.out.println("Student Not Found");
    }

    static void updateStudent(Scanner sc) {

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        for(Student s : students) {

            if(s.getName().equalsIgnoreCase(name)) {

                System.out.print("Enter New Marks: ");
                int marks = sc.nextInt();

                s.setMarks(marks);

                System.out.println("Updated Successfully");

                return;
            }
        }

        System.out.println("Student Not Found");
    }

    static void deleteStudent(Scanner sc) {

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        Iterator<Student> it = students.iterator();

        while(it.hasNext()) {

            Student s = it.next();

            if(s.getName().equalsIgnoreCase(name)) {

                it.remove();

                System.out.println("Deleted Successfully");

                return;
            }
        }

        System.out.println("Student Not Found");
    }

    static void averageMarks() {

        int sum = 0;

        for(Student s : students)
            sum += s.getMarks();

        System.out.println(
                "Average Marks = "
                + ((double)sum/students.size()));
    }

    static void highestMarks() {

        Student top = Collections.max(
                students,
                Comparator.comparingInt(Student::getMarks));

        System.out.println(
                "Highest = "
                + top.getName()
                + " ("
                + top.getMarks()
                + ")");
    }

    static void lowestMarks() {

        Student low = Collections.min(
                students,
                Comparator.comparingInt(Student::getMarks));

        System.out.println(
                "Lowest = "
                + low.getName()
                + " ("
                + low.getMarks()
                + ")");
    }

    static void sortStudents() {

        students.sort(
                (a,b) ->
                b.getMarks() - a.getMarks());

        System.out.println("Sorted Successfully");
    }

    static void rankStudents() {

        sortStudents();

        int rank = 1;

        System.out.println("\nRank\tName\tMarks");

        for(Student s : students) {

            System.out.println(
                    rank + "\t"
                    + s.getName()
                    + "\t"
                    + s.getMarks());

            rank++;
        }
    }

    static void summaryReport() {

        System.out.println("\n========== REPORT ==========");

        viewStudents();

        averageMarks();

        highestMarks();

        lowestMarks();

        System.out.println(
                "Total Students = "
                + students.size());
    }

    static void saveToFile() {

        try(PrintWriter pw =
                    new PrintWriter(FILE_NAME)) {

            for(Student s : students) {

                pw.println(
                        s.getName()
                        + ","
                        + s.getMarks());
            }

            System.out.println("Saved Successfully");

        } catch(Exception e) {

            System.out.println(e.getMessage());
        }
    }

    static void loadFromFile() {

        try(BufferedReader br =
                    new BufferedReader(
                            new FileReader(FILE_NAME))) {

            String line;

            while((line = br.readLine()) != null) {

                String[] data = line.split(",");

                students.add(
                        new Student(
                                data[0],
                                Integer.parseInt(data[1])));
            }

        } catch(Exception e) {

        }
    }
}
