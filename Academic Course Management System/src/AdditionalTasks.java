public class AdditionalTasks extends AssessmentDecorator{
    public AdditionalTasks(IAssessment tempAssessment) {
        super(tempAssessment);
    }

    @Override
    public int getFee() {
        return super.getFee() + 5;
    }

    @Override
    public String getTasks() {
        return super.getTasks() + "AdditionalTasks ";
    }
}
