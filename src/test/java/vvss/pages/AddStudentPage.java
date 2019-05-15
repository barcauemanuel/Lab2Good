package vvss.pages;

import app.Domain.Student;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("http://localhost:8080/page")
public class AddStudentPage extends PageObject {

    @FindBy(id = "idInput")
    private WebElementFacade idInput;

    @FindBy(id = "nameInput")
    private WebElementFacade nameInput;

    @FindBy(id = "groupInput")
    private WebElementFacade groupInput;

    @FindBy(id = "emailInput")
    private WebElementFacade emailInput;

    @FindBy(id = "profInput")
    private WebElementFacade profInput;

    @FindBy(id = "submitBtn")
    private WebElementFacade submitBtn;


    public void submit(){
        submitBtn.click();
    }

    public void populateInputs(Student student){
        idInput.type(student.getID());
        nameInput.type(student.getNume());
        groupInput.type(String.valueOf(student.getGrupa()));
        emailInput.type(student.getMail());
        profInput.type(student.getProfesor());
    }

    public String getResult(){
        WebElementFacade elem = find(By.id("resultDiv"));
        return elem.getText();
    }

}