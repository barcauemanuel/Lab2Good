package app.Validator;

import app.Domain.Student;

public class StudentValidator implements Validator<Student> {
    @Override
    public String validate(Student st) {
        String m = new String();
        m = "";
        if (st.getID() == null || st.getID().equals("") || !st.getID().matches("[0-9]+"))
            m = m + "\nID invalid";
        if (st.getGrupa() < 111 || st.getGrupa() > 937 || st.getGrupa() % 100 / 10 < 1 || st.getGrupa() % 100 / 10 > 3 || st.getGrupa() % 10 < 1 || st.getGrupa() % 10 > 7)
            m = m + "\nGrupa invalida";

        if(st.getMail() == null)
            m = m + "\nEmail invalid";
        else
        if (!st.getMail().contains("@") || !st.getMail().contains("."))
            m = m + "\nEmail invalid";

        if(st.getNume() == null)
            m = m + "\nNume invalid";
        else
        if(!st.getNume().matches("[A-Za-z ,.'-]+"))
            m=m+"\nNume invalid";

        if(st.getProfesor() == null)
            m = m + "\nNume profesor invalid";
        else
        if(!st.getProfesor().matches("[A-Za-z ,.'-]+"))
            m=m+"\nNume profesor invalid";
        return m;
    }
}
