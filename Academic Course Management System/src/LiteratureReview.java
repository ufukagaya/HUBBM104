public class LiteratureReview extends AssessmentDecorator{
    public LiteratureReview(IAssessment tempAssessment) {
        super(tempAssessment);
    }
    @Override
    public int getFee() {
        return super.getFee() + 15;
    }

    @Override
    public String getTasks() {
        return super.getTasks() + "LiteratureReview ";
    }
}
