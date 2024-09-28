public class Analysis extends AssessmentDecorator{
    public Analysis(IAssessment tempAssessment) {
        super(tempAssessment);
    }

    @Override
    public int getFee() {
        return super.getFee() + 10;
    }

    @Override
    public String getTasks() {
        return super.getTasks() + "Analysis ";
    }
}
