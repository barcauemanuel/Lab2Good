package app.Validator;

public interface Validator<E> {
    public String validate(E el);
}
