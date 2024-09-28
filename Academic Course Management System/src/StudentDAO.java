import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class StudentDAO implements IDAO<Student> {
    @Override
    public Student getByID(int id) {
        try(BufferedReader br = new BufferedReader(new FileReader("student.txt"))){
            Student student = null;
            String currentLine;
            while ((currentLine = br.readLine()) != null) {

                String[] parts = currentLine.split("\t");
                int lineID = Integer.parseInt(parts[0]);
                String[] nameSurname = parts[1].split(" ");
                if(lineID == id){
                    student = new Student(Integer.parseInt(parts[0]),nameSurname[0],nameSurname[1],parts[2],parts[3]);
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true))) {
                        writer.write(currentLine + "\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }

            }
            return student;
        }
          catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByID(int id) {
        Student student = null;
        try(BufferedReader br = new BufferedReader(new FileReader("student.txt"))){
            File tempFile = new File("temp.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
            String currentLine;

            while ((currentLine = br.readLine()) != null) {

                String[] parts = currentLine.split("\t");
                int lineID = Integer.parseInt(parts[0]);
                if (lineID != id) {
                    bw.write(currentLine + "\n");
                }
                else {
                    String[] nameSurname = parts[1].split(" ");
                    student = new Student(Integer.parseInt(parts[0]),nameSurname[0],nameSurname[1],parts[2],parts[3]);
                }
            }
            bw.close();

        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File originalFile = new File("student.txt");
        File tempFile = new File("temp.txt");
        if (originalFile.delete()){
            tempFile.renameTo(originalFile);
        }

        String string = "Student " + student.getId() + " " + student.getName() + " removed\n";
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt",true))){
            bw.write(string);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void add(Student s) {
        ArrayList<Student> students = new ArrayList<>();
        String currentLine;
        try (BufferedReader br = new BufferedReader(new FileReader("student.txt"))) {
            while((currentLine = br.readLine()) != null){
                String[] parts = currentLine.split("\t");
                String[] nameSurname = parts[1].split(" ");
                students.add(new Student(Integer.parseInt(parts[0]),nameSurname[0], nameSurname[1], parts[2], parts[3]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        students.add(s);
        Collections.sort(students);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("student.txt"))) {
            for(Student st : students){
                writer.write(st.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt",true))){
            bw.write("Student " + s.getId() + " " + s.getName() + " added\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Student> getAll() {
        ArrayList<Student> studentArrayList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("student.txt"));
            String currentLine;
            while ((currentLine = br.readLine()) != null){
                String[] parts = currentLine.split("\t");
                String[] nameSurname = parts[1].split(" ");
                studentArrayList.add(new Student(Integer.parseInt(parts[0]),nameSurname[0], nameSurname[1],parts[2],parts[3]));
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Collections.sort(studentArrayList);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt",true))){
            bw.write("Student List:\n");
            for (Student s : studentArrayList){
                bw.write(s.toString());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return studentArrayList;
    }

}