package vvss.steps.serenity;

import app.Domain.Student;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import vvss.pages.AddStudentPage;

public class EndUserSteps {

    AddStudentPage addStudentPage;

    @Step
    public void opens_add_student_page() {
        addStudentPage.open();
    }

    @Step
    public void types_valid_data() {
        Student student = new Student("1", "Radu", 911, "email@s.c", "Prof");
        addStudentPage.populateInputs(student);
    }

    @Step
    public void types_invalid_data() {
        Student student = new Student("1", "1", 1, "1", "1");
        addStudentPage.populateInputs(student);
    }

    @Step
    public void clicks_submit_button(){
        addStudentPage.submit();
    }

    @Step
    public void should_see_added_message(){
        Assert.assertEquals("Added", addStudentPage.getResult());
    }


    @Step
    public void should_see_error_message(){
        Assert.assertEquals("Error", addStudentPage.getResult());
    }
}