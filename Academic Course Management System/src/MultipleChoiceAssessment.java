public class MultipleChoiceAssessment implements IAssessment{
    @Override
    public int getFee() {
        return 15;
    }

    @Override
    public String getTasks() {
        return "MultipleChoice\t";
    }

}
