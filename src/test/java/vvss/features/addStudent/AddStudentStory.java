package vvss.features.addStudent;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import vvss.steps.serenity.EndUserSteps;

@RunWith(SerenityRunner.class)
public class AddStudentStory {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public EndUserSteps emuBot;

    @Test
    public void opens_add_student_page_types_valid_data_clicks_submit_button_should_see_added_message() {
        emuBot.opens_add_student_page();
        emuBot.types_valid_data();
        emuBot.clicks_submit_button();
        emuBot.should_see_added_message();
    }

    @Test
    public void opens_add_student_page_types_invalid_data_clicks_submit_button_should_see_error_message() {
        emuBot.opens_add_student_page();
        emuBot.types_invalid_data();
        emuBot.clicks_submit_button();
        emuBot.should_see_error_message();
   }

} 