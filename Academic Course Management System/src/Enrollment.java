import java.util.ArrayList;

public class Enrollment implements Comparable<Enrollment>{
    private int id;
    private int studentID;
    private ArrayList<IAssessment> assessments = new ArrayList<>();
    private static ArrayList<Enrollment> enrollments = new ArrayList<>();
    public Enrollment(int id, int studentID) {
        this.id = id;
        this.studentID = studentID;
        enrollments.add(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public ArrayList<IAssessment> getAssessments() {
        return assessments;
    }

    public void setAssessment(IAssessment assessment) {
        this.assessments.add(assessment);
    }

    public static ArrayList<Enrollment> getEnrollments() {
        return enrollments;
    }

    public static void setEnrollments(ArrayList<Enrollment> enrollments) {
        Enrollment.enrollments = enrollments;
    }

    @Override
    public int compareTo(Enrollment o) {
        return id - o.getId();
    }

    @Override
    public String toString() {
        String s = getId() + "\t" + getStudentID() + "\n";
        if(!getAssessments().isEmpty()){
            for (IAssessment a : getAssessments()){
                s += a.getTasks() + "\n";
            }
        }
        return s;
    }
}
