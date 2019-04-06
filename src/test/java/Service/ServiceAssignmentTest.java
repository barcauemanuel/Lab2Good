package Service;

import Domain.Teme;
import Repository.TemeRepo;
import Validator.TemeValidator;
import Validator.ValidationException;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ServiceAssignmentTest {

    private ServiceTeme service;
    private TemeRepo repo;

    @Before
    public void setUp(){
        TemeRepo rep = new TemeRepo(new TemeValidator(),"");
        service = new ServiceTeme(rep);
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
        Teme teme = new Teme(0, "Test description", 1, 15);

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
        Teme teme = new Teme(0, "Test description", 15, 15);

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
        Teme teme = new Teme(10, "Test description", 2, 3);
        Teme teme1 = new Teme(10, "Test description 1", 2, 3);

        Teme returned;

        try {
            returned = service.add(teme);
        }catch (ValidationException e){
            e.printStackTrace();
            throw new AssertionError("Adding an assignment failed despite beeing valid");
        }

        Assert.assertNull(returned);

        try{
            returned = service.add(teme1);
        }catch (ValidationException e){
            e.printStackTrace();
            throw new AssertionError("Adding an assignment failed despite beeing valid");
        }

        Assert.assertNotNull(returned);

    }

    @Test
    public void test9(){
        Teme teme = new Teme(4, "Test description", 0, 4);

        try {
            service.add(teme);
            throw new AssertionError("Assignment added despite invalid \"saptamana primire\"");
        }catch (ValidationException ignored){ }
    }


    @Test
    public void test10(){
        Teme teme = new Teme(4, "Test description", 0, 15);

        try {
            service.add(teme);
            throw new AssertionError("Assignment added despite invalid \"saptamana primire\"");
        }catch (ValidationException ignored){ }
    }

}
