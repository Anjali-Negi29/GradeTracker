public class Student {

    private String name;
    private int marks;

    public Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getGrade() {

        if(marks >= 90)
            return "A";

        else if(marks >= 80)
            return "B";

        else if(marks >= 70)
            return "C";

        else if(marks >= 60)
            return "D";

        else
            return "F";
    }
}