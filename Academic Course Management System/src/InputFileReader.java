import java.io.*;

public class InputFileReader {
    public static void readInputFile(String fileName){
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        StudentDAO studentDAO = new StudentDAO();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;

        while ((line = br.readLine()) != null){
            String[] inputLine = line.split(" ");

            if (inputLine[0].equalsIgnoreCase("addstudent")){
                String address = line.split(" ", 6)[5];
                Student student = new Student(Integer.parseInt(inputLine[1]),inputLine[2],inputLine[3],inputLine[4],address);
                studentDAO.add(student);
            }
            else if (inputLine[0].equalsIgnoreCase("removestudent")){
                studentDAO.deleteByID(Integer.parseInt(inputLine[1]));
            }
            else if (inputLine[0].equalsIgnoreCase("getstudent")){
                studentDAO.getByID(Integer.parseInt(inputLine[1]));
            }
            else if (inputLine[0].equalsIgnoreCase("liststudents")){
                studentDAO.getAll();
            }
            else if (inputLine[0].equalsIgnoreCase("createenrollment")){
                enrollmentDAO.add(new Enrollment(Integer.parseInt(inputLine[1]),Integer.parseInt(inputLine[2])));
            }
            else if (inputLine[0].equalsIgnoreCase("getenrollment")){
                enrollmentDAO.getByID(Integer.parseInt(inputLine[1]));
            }
            else if (inputLine[0].equalsIgnoreCase("addassessment")){
                int id = Integer.parseInt(inputLine[1]);
                IAssessment assessment = null;
                if(inputLine[2].equalsIgnoreCase("essaybased")){
                    assessment = new EssayBasedAssessment();
                    for (int i=3;i<inputLine.length;i++){
                        if(inputLine[i].equalsIgnoreCase("questionset")){
                            assessment = new QuestionSet(assessment);
                        }
                        else if(inputLine[i].equalsIgnoreCase("analysis")){
                            assessment = new Analysis(assessment);
                        }
                        else if(inputLine[i].equalsIgnoreCase("additionaltasks")){
                            assessment = new AdditionalTasks(assessment);
                        }
                        else if(inputLine[i].equalsIgnoreCase("literaturereview")){
                            assessment = new LiteratureReview(assessment);
                        }
                    }
                }
                else if(inputLine[2].equalsIgnoreCase("multiplechoice")){
                    assessment = new MultipleChoiceAssessment();
                    for (int i=3;i<inputLine.length;i++){
                        if(inputLine[i].equalsIgnoreCase("questionset")){
                            assessment = new QuestionSet(assessment);
                        }
                        else if(inputLine[i].equalsIgnoreCase("analysis")){
                            assessment = new Analysis(assessment);
                        }
                        else if(inputLine[i].equalsIgnoreCase("additionaltasks")){
                            assessment = new AdditionalTasks(assessment);
                        }
                        else if(inputLine[i].equalsIgnoreCase("literaturereview")){
                            assessment = new LiteratureReview(assessment);
                        }
                    }
                }
                enrollmentDAO.addAssessment(id,assessment);
            }
            else if (inputLine[0].equalsIgnoreCase("removeenrollment")){
                enrollmentDAO.deleteByID(Integer.parseInt(inputLine[1]));
            }
            else if (inputLine[0].equalsIgnoreCase("totalfee")){
                Enrollment enrollment = enrollmentDAO.getFromArrayListByID(Integer.parseInt(inputLine[1]));
                enrollmentDAO.getFee(enrollment);
            }
            else if (inputLine[0].equalsIgnoreCase("gettask")){
                enrollmentDAO.getTasks(enrollmentDAO.getFromArrayListByID(Integer.parseInt(inputLine[1])));
            }


        }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
