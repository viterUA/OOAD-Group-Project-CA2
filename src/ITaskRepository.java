import java.util.List;

public interface ITaskRepository {
    
    void add(Task task);
    
    boolean remove(String id);
    
    boolean update(Task task);
    
    Task findById(String id);
    
    List<Task> getAll();
}