public abstract class AssessmentDecorator implements IAssessment{
    protected IAssessment tempAssessment;

    public AssessmentDecorator(IAssessment tempAssessment){
        this.tempAssessment = tempAssessment;
    }
    public int getFee(){
        return tempAssessment.getFee();
    }

    public String getTasks(){
        return tempAssessment.getTasks();
    }

}
