package Repository;

public interface CrudRepo <E,ID> {
    E save(E elem);
    E update(E elem);
    E delete(ID id);
    E findOne(ID id);
    Iterable<E> findAll();
    int size();

}
