package Service;


import app.Domain.Nota;
import app.Domain.Student;
import app.Domain.Teme;
import app.Repository.NoteRepo;
import app.Service.ServiceNote;
import app.Validator.NotaValidator;
import com.google.common.collect.Lists;
import javafx.util.Pair;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ServiceNoteTest {

    private ServiceNote service;

    @Before
    public void setUp(){
        NoteRepo repo = new NoteRepo(new NotaValidator());
        service = new ServiceNote(repo);
    }


    @Test
    public void testInClass1(){
        Teme teme = new Teme(1, "t", 1, 3);
        Student student1 = new Student("102", "Ion Creanga", 931, "email@yahoo.com","Ion Creanga");
        Nota n = new Nota(new HashMap.SimpleEntry<>("Ion", 1), student1, teme, 8, 2);


        try{
            service.add(n, "Text la panarama");
        }catch (Exception e){
            throw new AssertionError("Error");
        }
    }

    @Test
    public void runAllThree() {
        testInClass1();

        ServiceAssignmentTest sat = new ServiceAssignmentTest();
        sat.setUp();
        sat.test1();
        sat.tearDown();

        ServiceStudentTest sst = new ServiceStudentTest();
        sst.setUp();
        sst.addStudentTestGrupa();
    }





}
