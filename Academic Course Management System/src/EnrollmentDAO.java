import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class EnrollmentDAO implements IDAO<Enrollment>{
    public EnrollmentDAO(){
        Enrollment.setEnrollments(getAll());
    }

    @Override
    public Enrollment getByID(int id) {
        Enrollment enrollment = null;
        for (Enrollment e : getAll()){
            if (e.getId() == id){
                enrollment = e;
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            bw.write(enrollment.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return enrollment;
    }

    public Enrollment getFromArrayListByID(int id) {
        Enrollment enrollment = null;
        for(Enrollment e : Enrollment.getEnrollments()){
            if (e.getId()==id){
                enrollment = e;
            }
        }
        return enrollment;
    }

    public IAssessment addAssessment(int enrollmentID, IAssessment a) {
        Enrollment updatedEnrollment = getFromArrayListByID(enrollmentID);
        updatedEnrollment.setAssessment(a);
        Enrollment.getEnrollments().remove(getFromArrayListByID(enrollmentID));
        Enrollment.getEnrollments().add(updatedEnrollment);
        Collections.sort(Enrollment.getEnrollments());
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt",true))){
            bw.write(a.getTasks().split("\t")[0] + " assessment added to enrollment " + updatedEnrollment.getId() + "\n");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("courseEnrollment.txt"))){
            for(Enrollment e : Enrollment.getEnrollments()){writer.write(e.toString());}
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return a;
    }

    public int getFee(Enrollment e){
        int totalFee = 0;

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt",true))){
            writer.write("TotalFee for enrollment " + e.getId() + "\n");
            for (IAssessment a : e.getAssessments()){
                totalFee+=a.getFee();
                writer.write("\t" + a.getTasks().replace("\t"," ") + a.getFee() + "$\n");
            }
            writer.write("\tTotal: " + totalFee + "$\n");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return totalFee;
    }

    public String getTasks(Enrollment enrollment){
        String s = null;

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt",true))){
            for (IAssessment a : enrollment.getAssessments()){
                String[] tasks = a.getTasks().split("\t");
                if (tasks.length<2){s=tasks[0];writer.write(s.trim() + "\n");}
                else {s = tasks[1]; writer.write(s.trim() + "\n");}
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return s.trim();
    }

    @Override
    public void deleteByID(int id) {
        Enrollment toBeDeleted = getFromArrayListByID(id);
        Enrollment.getEnrollments().remove(toBeDeleted);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("courseEnrollment.txt"))){
            for (Enrollment e : Enrollment.getEnrollments()){
                bw.write(e.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String string = "Enrollment " + toBeDeleted.getId() + " removed\n";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt",true))){
            writer.write(string);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void add(Enrollment enrollment) {
        Collections.sort(Enrollment.getEnrollments());

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt",true))){
            writer.write("CourseEnrollment " + enrollment.getId() + " created\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("courseEnrollment.txt"))){
            for (Enrollment e : Enrollment.getEnrollments()){
                bw.write(e.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ArrayList<Enrollment> getAll() {
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        ArrayList<String> lines = new ArrayList<>();
        String currentLine;
        try(BufferedReader br = new BufferedReader(new FileReader("courseEnrollment.txt"))) {
            while ((currentLine = br.readLine()) != null){
                lines.add(currentLine);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(int i=0;i<lines.size();i++){
            String[] parts = lines.get(i).split("\t");
            if(parts[0].length()<3){
                enrollments.add(new Enrollment(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
            }
            else {
                IAssessment assessment = null;
                if (!(parts.length<2)){
                    String[] tasks = parts[1].split(" ");
                    if(parts[0].equalsIgnoreCase("multiplechoice")) {
                    assessment = new MultipleChoiceAssessment();
                    for(int j=0;j<tasks.length;j++){
                        if(tasks[j].equalsIgnoreCase("questionset")){
                            assessment = new QuestionSet(assessment);
                        }
                        else if(tasks[j].equalsIgnoreCase("analysis")){
                            assessment = new Analysis(assessment);
                        }
                        else if(tasks[j].equalsIgnoreCase("additionaltasks")){
                            assessment = new AdditionalTasks(assessment);
                        }
                        else if(tasks[j].equalsIgnoreCase("literaturereview")){
                            assessment = new LiteratureReview(assessment);
                        }
                    }
                }
                    else if(parts[0].equalsIgnoreCase("essaybased")) {
                    assessment = new EssayBasedAssessment();
                    for(int j=0;j<tasks.length;j++){
                        if(tasks[j].equalsIgnoreCase("questionset")){
                            assessment = new QuestionSet(assessment);
                        }
                        else if(tasks[j].equalsIgnoreCase("analysis")){
                            assessment = new Analysis(assessment);
                        }
                        else if(tasks[j].equalsIgnoreCase("additionaltasks")){
                            assessment = new AdditionalTasks(assessment);
                        }
                        else if(tasks[j].equalsIgnoreCase("literaturereview")){
                            assessment = new LiteratureReview(assessment);
                        }
                    }
                }}
                else{
                    if (parts[0].equalsIgnoreCase("multiplechoice")){
                        assessment = new MultipleChoiceAssessment();
                    }
                    else if(parts[0].equalsIgnoreCase("essaybased")){
                        assessment = new EssayBasedAssessment();
                    }
                }



                enrollments.get(enrollments.size()-1).setAssessment(assessment);
            }
        }

        return enrollments;
    }
}
