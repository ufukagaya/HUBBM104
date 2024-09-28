import java.util.ArrayList;

public interface IDAO<T> {
    T getByID(int id);
    void deleteByID(int id);
    void add(T t);
    ArrayList<T> getAll();
}
