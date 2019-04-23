package Service;

import Domain.Nota;
import Domain.Student;
import Domain.Teme;
import Repository.NoteRepo;
import Repository.StudentRepo;
import Repository.TemeRepo;
import Validator.*;

import Validator.StudentValidator;
import com.google.common.collect.Iterables;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import static org.junit.Assert.*;


public class ServiceTopDown {

    private ServiceStudent srv;
    private ServiceTeme service;
    private ServiceNote serviceNote;

    private static final String RESOURCES_PATH = "src/test/resources";

    private static final String INITIAL_FILE_CONTENTS = "" +
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
            "<Teme>\n" +
            "    <Tema nr=\"1\">\n" +
            "        <descriere>Initial</descriere>\n" +
            "        <deadline>3</deadline>\n" +
            "        <sapt_primire>1</sapt_primire>\n" +
            "    </Tema>\n" +
            "</Teme>";

    @Before
    public void setUp(){
        StudentRepo rep = new StudentRepo(new StudentValidator(),"");
        srv = new ServiceStudent(rep);

        TemeRepo repo = new TemeRepo(new TemeValidator(), "");
        service = new ServiceTeme(repo);

        NoteRepo repo2 = new NoteRepo(new NotaValidator());
        serviceNote = new ServiceNote(repo2);
    }



    @Test
    public void addStudentTest() {
        Student std = new Student("1", "Ion", 931, "email@email.com", "Professor");

        try{
            srv.add(std);
            System.out.println("Test 1 - Success");
        }catch (ValidationException ignored) {
            throw new AssertionError("Bad student added with success");
        }
    }

    @Test
    public void addAssignmentIntegrationTest() {
        Student std = new Student("1", "Ion", 931, "email@email.com", "Professor");
        Teme teme = new Teme(4, "Test description", 2, 4);

        try {
            srv.add(std);
            service.add(teme);
        } catch (ValidationException ignored) {
            throw new AssertionError("Assignment not added despite being valid");
        }
    }

    @Test
    public void addGradeIntegration(){
        Student std = new Student("1", "Ion", 931, "email@email.com", "Professor");
        Teme teme = new Teme(4, "Test description", 2, 4);

        try{
            srv.add(std);
            service.add(teme);

            Nota n = new Nota(new HashMap.SimpleEntry<>("Ion", 1), srv.find("1"), service.find(4), 8, 2);
            serviceNote.add(n, "Text la panarama");
        }catch (Exception e){
            throw new AssertionError("Error");
        }
    }
}
