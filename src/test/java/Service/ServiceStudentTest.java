package Service;

import app.Domain.Student;
import app.Repository.StudentRepo;
import app.Service.ServiceStudent;
import app.Validator.StudentValidator;
import app.Validator.ValidationException;
import com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;


public class ServiceStudentTest {

    private ServiceStudent srv;

    @Before
    public void setUp() {
        StudentRepo rep = new StudentRepo(new StudentValidator(), "");
        srv = new ServiceStudent(rep);
    }

    @Test
    public void addStudentTestOne() {
        Student std = new Student("1", "Ion", 1931, "email@email.com", "Professor");

        try {
            srv.add(std);
            throw new AssertionError("Bad student added with success");
        } catch (ValidationException ignored) {
        }
        System.out.println("Test 1 - Success");
    }

    @Test
    public void addStudentTestTwo() {
        Student std2 = new Student("1", "Ion", 931, "email-email.com", "Professor");

        try {
            srv.add(std2);
            throw new AssertionError("Bad student added with success");
        } catch (ValidationException ignored) {
        }
        System.out.println("Test 2 - Success");
    }


    @Test
    public void addStudentTestId() {
        Student student1 = new Student("102", "Ion Creanga", 931, "email@yahoo.com", "Mihai Eminescu");
        srv.add(student1);

        try {
            Student studentX = new Student("string", "Ion Creanga", 931, "email@yahoo.com", "Mihai Eminescu");
            srv.add(studentX);
            throw new AssertionError("Bad ID: " + studentX.getID());
        } catch (ValidationException ignored) {
        }

        try {
            Student studentX = new Student("", "Ion Creanga", 931, "email@yahoo.com", "Mihai Eminescu");
            srv.add(studentX);
            throw new AssertionError("Bad ID: " + studentX.getID());
        } catch (ValidationException ignored) {
        }

        try {
            Student studentX = new Student(null, "Ion Creanga", 931, "email@yahoo.com", "Mihai Eminescu");
            srv.add(studentX);
            throw new AssertionError("Bad ID: " + studentX.getID());
        } catch (ValidationException ignored) {
        }

        try {
            Student studentX = new Student("-1", "Ion Creanga", 931, "email@yahoo.com", "Mihai Eminescu");
            srv.add(studentX);
            throw new AssertionError("Bad ID: " + studentX.getID());
        } catch (ValidationException ignored) {
        }


        System.out.println("Test Student.ID - Success");
    }

    @Test
    public void addStudentTestGrupa() {
        Student student1 = new Student("102", "Ion Creanga", 931, "email@yahoo.com", "Mihai Eminescu");
        srv.add(student1);

        try {
            Student studentX = new Student("103", "Ion Creanga", 12, "email@yahoo.com", "Mihai Eminescu");
            srv.add(studentX);
            throw new AssertionError("Bad Grupa: " + studentX.getGrupa());
        } catch (ValidationException ignored) {
        }

        try {
            Student studentX = new Student("103", "Ion Creanga", 1234, "email@yahoo.com", "Mihai Eminescu");
            srv.add(studentX);
            throw new AssertionError("Bad Grupa: " + studentX.getGrupa());
        } catch (ValidationException ignored) {
        }

        try {
            Student studentX = new Student("103", "Ion Creanga", 141, "email@yahoo.com", "Mihai Eminescu");
            srv.add(studentX);
            throw new AssertionError("Bad Grupa: " + studentX.getGrupa());
        } catch (ValidationException ignored) {
        }

        try {
            Student studentX = new Student("103", "Ion Creanga", 918, "email@yahoo.com", "Mihai Eminescu");
            srv.add(studentX);
            throw new AssertionError("Bad Grupa: " + studentX.getGrupa());
        } catch (ValidationException ignored) {
        }


        System.out.println("Test Student.Grupa - Success");
    }

    @Test
    public void addStudentTestMail() {
        Student student1 = new Student("102", "Ion Creanga", 931, "email@yahoo.com", "Mihai Eminescu");
        srv.add(student1);

        try {
            Student studentX = new Student("103", "Ion Creanga", 12, "email+yahoo.com", "Mihai Eminescu");
            srv.add(studentX);
            throw new AssertionError("Bad Mail: " + studentX.getMail());
        } catch (ValidationException ignored) {
        }

        try {
            Student studentX = new Student("103", "Ion Creanga", 12, "email@yahoo!com", "Mihai Eminescu");
            srv.add(studentX);
            throw new AssertionError("Bad Mail: " + studentX.getMail());
        } catch (ValidationException ignored) {
        }

        try {
            Student studentX = new Student("103", "Ion Creanga", 12, "email*yahoo*com", "Mihai Eminescu");
            srv.add(studentX);
            throw new AssertionError("Bad Mail: " + studentX.getMail());
        } catch (ValidationException ignored) {
        }

        try {
            Student studentX = new Student("103", "Ion Creanga", 12, null, "Mihai Eminescu");
            srv.add(studentX);
            throw new AssertionError("Bad Mail: " + studentX.getMail());
        } catch (ValidationException ignored) {
        }

        System.out.println("Test Student.Mail - Success");
    }

    @Test
    public void addStudentTestNume() {
        Student student1 = new Student("102", "Ion Creanga", 931, "email@yahoo.com", "Mihai Eminescu");
        srv.add(student1);

        Student student3 = new Student("103", "Alex Barboi'ay", 931, "email@yahoo.com", "Mihai Eminescu");
        srv.add(student3);

        try {
            Student studentX = new Student("103", "Ion ! Creanga", 12, "email+yahoo.com", "Mihai Eminescu");
            srv.add(studentX);
            throw new AssertionError("Bad Nume: " + studentX.getNume());
        } catch (ValidationException ignored) {
        }

        try {
            Student studentX = new Student("103", "Alex =", 12, "email+yahoo.com", "Mihai Eminescu");
            srv.add(studentX);
            throw new AssertionError("Bad Nume: " + studentX.getNume());
        } catch (ValidationException ignored) {
        }

        System.out.println("Test Student.Nume - Success");
    }

    @Test
    public void addStudentTestProfesor() {
        Student student1 = new Student("102", "Ion Creanga", 931, "email@yahoo.com", "Ion Creanga");
        srv.add(student1);

        Student student3 = new Student("103", "Alex Barboi'ay", 931, "email@yahoo.com", "Alex Barboi'ay");
        srv.add(student3);

        try {
            Student studentX = new Student("103", "Ion Creanga", 12, "email+yahoo.com", "Ion ! Creanga");
            srv.add(studentX);
            throw new AssertionError("Bad Nume: " + studentX.getNume());
        } catch (ValidationException ignored) {
        }

        try {
            Student studentX = new Student("103", "Alex", 12, "email+yahoo.com", "Alex =");
            srv.add(studentX);
            throw new AssertionError("Bad Profesor: " + studentX.getProfesor());
        } catch (ValidationException ignored) {
        }

        System.out.println("Test Student.Profesor - Success");
    }
}
