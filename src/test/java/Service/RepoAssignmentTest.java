package Service;

import Domain.Teme;
import Repository.TemeRepo;
import Validator.TemeValidator;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

public class RepoAssignmentTest {

    private TemeRepo repo;

    @Before
    public void setUp(){
        repo = new TemeRepo(new TemeValidator(),"C:\\Users\\Bendic Radu\\Desktop\\vvss\\Lab2Good\\src\\main\\java\\teme.xml");
    }


    @Test
    public void test1(){

        List<Teme> lst = Lists.newArrayList(repo.findAll());

        int id = lst.stream()
                    .map(Teme::getID)
                    .max(Comparator.comparingInt(Integer::intValue))
                    .orElse(0) + 1;

        Teme teme = new Teme(id, "Test description", 1, 3);

        repo.loadFromFile();
        int assignmentCount = lst.size();

        repo.save(teme);
        lst = Lists.newArrayList(repo.findAll());

        Assert.assertEquals(assignmentCount + 1, lst.size());
    }

    @Test
    public void test2(){


        repo.loadFromFile();

        List<Teme> myList = Lists.newArrayList(repo.findAll());

        int assignmentCount = myList.size();

        Assert.assertEquals(assignmentCount + 1, myList.size());
    }

}
