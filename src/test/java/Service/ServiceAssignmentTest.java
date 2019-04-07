package Service;

import Domain.Teme;
import Repository.TemeRepo;
import Validator.TemeValidator;
import Validator.ValidationException;
import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ServiceAssignmentTest {

    private ServiceTeme service;

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
        TemeRepo repo = new TemeRepo(new TemeValidator(), "");
        service = new ServiceTeme(repo);
    }

    @After
    public void tearDown(){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(RESOURCES_PATH + "/teme/temeTest.xml");
            writer.print(INITIAL_FILE_CONTENTS);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test1(){

        Teme teme = new Teme(1, "Test description", 1, 0);

        try {
            service.add(teme);
            throw new AssertionError("Assignment added despite invalid deadline");
        }catch (ValidationException ignored){ }
    }

    @Test
    public void test2(){
        Teme teme = new Teme(null, "Test description", 1, 3);

        try {
            service.add(teme);
            throw new AssertionError("Assignment added despite null id");
        }catch (ValidationException ignored){ }
    }

    @Test
    public void test3(){
        Teme teme = new Teme(0, "Test description", 1, 3);

        try {
            service.add(teme);
            throw new AssertionError("Assignment added despite invalid id");
        }catch (ValidationException ignored){ }
    }

    @Test
    public void test4(){
        Teme teme = new Teme(1, "Test description", 1, 15);

        try {
            service.add(teme);
            throw new AssertionError("Assignment added despite invalid \"deadline\"");
        }catch (ValidationException ignored){ }
    }

    @Test
    public void test5(){
        Teme teme = new Teme(4, "Test description", 2, 4);

        try {
            service.add(teme);
        }catch (ValidationException ignored){
            throw new AssertionError("Assignment not added despite being valid");
        }
    }


    @Test
    public void test6(){
        Teme teme = new Teme(1, "Test description", 15, 16);

        try {
            service.add(teme);
            throw new AssertionError("Assignment added despite invalid \"sapt_primire\"");
        }catch (ValidationException ignored){ }
    }

    @Test
    public void test7(){
        Teme teme = new Teme(10, "Test description", 2, 3);
        Teme teme1 = new Teme(10, "Test description 1", 2, 3);

        try {
            service.add(teme);
        }catch (ValidationException e){
            e.printStackTrace();
            throw new AssertionError("Adding an assignment failed despite beeing valid");
        }

        try{
            service.add(teme1);
        }catch (ValidationException e){
            e.printStackTrace();
            throw new AssertionError("Adding an assignment failed despite beeing valid");
        }

        List<Teme> myList = Lists.newArrayList(service.all());

        Assert.assertEquals(1, myList.size());
    }


    @Test
    public void test8(){
        Teme teme = new Teme(4, "Test description", 0, 4);

        try {
            service.add(teme);
            throw new AssertionError("Assignment added despite invalid \"saptamana primire\"");
        }catch (ValidationException ignored){ }
    }


    @Test
    public void test9(){
        Teme teme = new Teme(4, "Test description", 0, 15);

        try {
            service.add(teme);
            throw new AssertionError("Assignment added despite invalid \"saptamana primire\"");
        }catch (ValidationException ignored){ }
    }


    @Test
    public void test10(){
        Teme teme = new Teme(0, "Test description", 0, 15);

        try {
            service.add(teme);
            throw new AssertionError("Assignment added despite everything being invalid");
        }catch (ValidationException ignored){ }
    }

    @Test
    public void test11(){
        Teme teme = new Teme(null, "Test description", 12, 11);

        try {
            service.add(teme);
            throw new AssertionError("Assignment added despite invalid \"id and deadline\"");
        }catch (ValidationException ignored){ }
    }


    @Test
    public void test12(){
        Teme teme = new Teme(null, "Test description", -1, 3);

        try {
            service.add(teme);
            throw new AssertionError("Assignment added despite invalid \"id and deadline\"");
        }catch (ValidationException ignored){ }
    }


    @Test
    public void test13() throws IOException {

        String filePath = RESOURCES_PATH + "/teme/temeTest.xml";

        ServiceTeme service = new ServiceTeme(new TemeRepo(new TemeValidator(), filePath));

        Teme teme = new Teme(2, "Test description", 1, 3);
        service.add(teme);

        List<Teme> lst = Lists.newArrayList(service.all());

        Assert.assertEquals(2, lst.size());

    }

    @Test
    public void test14() throws IOException {

        String filePath = RESOURCES_PATH + "/teme/temeTest.xml";

        ServiceTeme service = new ServiceTeme(new TemeRepo(new TemeValidator(), filePath));

        IntStream.range(2, 500)
                .mapToObj(i -> new Teme(i, "t", 1, 3))
                .forEach(service::add);

        List<Teme> lst = Lists.newArrayList(service.all());

        Assert.assertEquals(500 - 2 + 1, lst.size());

    }


    @Test
    public void test15() throws IOException {

        String filePath = RESOURCES_PATH + "/teme/temeTest.xml";

        ServiceTeme service = new ServiceTeme(new TemeRepo(new TemeValidator(), filePath));

        Teme teme = new Teme(1, "t", 1, 3);
        service.add(teme);

        List<Teme> lst = Lists.newArrayList(service.all());

        Assert.assertEquals(1, lst.size());

    }


    @Test
    public void test16() throws IOException {

        String filePath = RESOURCES_PATH + "/teme/temeTest.xml";

        ServiceTeme service = new ServiceTeme(new TemeRepo(new TemeValidator(), filePath));

        IntStream.range(2, 500)
                .mapToObj(i -> new Teme(1, "t", 1, 3))
                .forEach(service::add);

        List<Teme> lst = Lists.newArrayList(service.all());

        Assert.assertEquals(1, lst.size());

    }


}
