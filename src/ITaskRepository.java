import java.util.List;

/**
 * Abstraction + DIP: Contract for storing and retrieving tasks; high-level code depends on this interface.
 * LSP: Any concrete repository can substitute this interface as long as it respects the contract.
 */
public interface ITaskRepository {
    
    void add(Task task);
    
    boolean remove(String id);
    
    boolean update(Task task);
    
    Task findById(String id);
    
    List<Task> getAll();
}