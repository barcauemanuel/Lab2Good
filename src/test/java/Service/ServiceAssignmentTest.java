package Service;

import Domain.Teme;
import Repository.TemeRepo;
import Validator.TemeValidator;
import Validator.ValidationException;
import org.junit.Before;
import org.junit.Test;

public class ServiceAssignmentTest {

    private ServiceTeme service;

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
        Teme teme = new Teme(null, "Test description", 1, 0);

        try {
            service.add(teme);
            throw new AssertionError("Assignment added despite null id");
        }catch (ValidationException ignored){ }
    }

    @Test
    public void test3(){
        Teme teme = new Teme(0, "Test description", 1, 0);

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
        Teme teme = new Teme(0, "Test description", 15, 15);

        try {
            service.add(teme);
            throw new AssertionError("Assignment added despite invalid \"sapt_primire\"");
        }catch (ValidationException ignored){ }
    }


}
