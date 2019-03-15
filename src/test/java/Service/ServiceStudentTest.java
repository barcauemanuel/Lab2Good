package Service;

import Domain.Student;
import Repository.StudentRepo;
import Validator.StudentValidator;
import Validator.ValidationException;

import Validator.StudentValidator;
import com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;


public class ServiceStudentTest {

    private ServiceStudent srv;

    @Before
    public void setUp(){
        StudentRepo rep = new StudentRepo(new StudentValidator(),"c:\\temp\\studenti.xml");
        srv = new ServiceStudent(rep);
    }

    @Test
    public void addStudentTestOne() {
        Student std = new Student("1", "Ion", 1931, "email@email.com", "Professor");

        try{
            srv.add(std);
            throw new AssertionError("Bad student added with success");
        }catch (ValidationException ignored){
        }
        System.out.println("Test 1 - Success");
    }

    @Test
    public void addStudentTestTwo() {
        Student std2 = new Student("1", "Ion", 931, "email-email.com", "Professor");

        try{
            srv.add(std2);
            throw new AssertionError("Bad student added with success");
        }catch (ValidationException ignored){
        }
        System.out.println("Test 2 - Success");
    }
}
