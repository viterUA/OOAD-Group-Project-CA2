import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.*;

/**
 * SRP: Concrete persistence for tasks using an in-memory collection.
 * DIP + LSP: Implements ITaskRepository so it can be substituted without changing TaskService.
 */
public class TaskRepository implements ITaskRepository {
    
    private Map<String, Task> tasks;
    
    public TaskRepository() {
        tasks = new HashMap<String, Task>();
    }
    
    public void add(Task task) {
        tasks.put(task.getId(), task);
    }

    public boolean update(Task updatedTask) {
        String id = updatedTask.getId();
        if (tasks.containsKey(id)) {
            tasks.put(id, updatedTask);
            return true;
        }
        return false;
    }

    public boolean remove(String id) {
        return tasks.remove(id) != null;
    }

    public Task findById(String id) {
        return tasks.get(id);
    }
    
    public List<Task> getAll() {
        List<Task> allTasks = new ArrayList<Task>();
        String[] keys = tasks.keySet().toArray(new String[0]);
        for (int i = 0; i < keys.length; i++) {
            allTasks.add(tasks.get(keys[i]));
        }
        return allTasks;
    }
}