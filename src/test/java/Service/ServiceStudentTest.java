package Service;

import Domain.Student;
import Repository.StudentRepo;
import Validator.StudentValidator;
import Validator.ValidationException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;


public class ServiceStudentTest {

    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addClass(ServiceStudent.class).addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void addStudentTestOne() {

        StudentRepo rep = new StudentRepo(new StudentValidator(),"c:\\temp\\studenti.xml");
        ServiceStudent srv = new ServiceStudent(rep);

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

        StudentRepo rep = new StudentRepo(new StudentValidator(),"c:\\temp\\studenti.xml");
        ServiceStudent srv = new ServiceStudent(rep);

        Student std2 = new Student("1", "Ion", 931, "email-email.com", "Professor");

        try{
            srv.add(std2);
            throw new AssertionError("Bad student added with success");
        }catch (ValidationException ignored){
        }
        System.out.println("Test 2 - Success");
    }
}
