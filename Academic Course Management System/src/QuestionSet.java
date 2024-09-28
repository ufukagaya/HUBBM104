public class QuestionSet extends AssessmentDecorator{
    public QuestionSet(IAssessment tempAssessment) {
        super(tempAssessment);
    }
    @Override
    public int getFee() {
        return super.getFee() + 7;
    }

    @Override
    public String getTasks() {
        return super.getTasks() + "QuestionSet ";
    }
}
