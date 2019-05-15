package app.controller;


import app.Domain.Student;
import app.Repository.StudentRepo;
import app.Service.ServiceStudent;
import app.Validator.StudentValidator;
import app.Validator.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class StudentController {

    private ServiceStudent studentService;

    public StudentController(){
        StudentRepo rep=new StudentRepo(new StudentValidator(),"C:\\Users\\Bendic Radu\\Desktop\\vvss\\Lab2Good\\src\\main\\java\\studenti.xml");
        studentService = new ServiceStudent(rep);
    }

    @GetMapping("/page")
    public String page(){
        return "add-student";
    }

    @PostMapping("/add-student")
    public String addStudent(
            @RequestParam("id") String id,
            @RequestParam("name") String name,
            @RequestParam("gr") Integer gr,
            @RequestParam("email") String email,
            @RequestParam("prof") String prof, Model model){

         Student stud = new Student(id, name, gr, email, prof);

         try{
             studentService.add(stud);
             model.addAttribute("yes", "Added");
         }catch (Exception e){
             model.addAttribute("no", "Error");
         }

         return "add-student";
    }

}
