import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;



public class TaskRepository implements ITaskRepository {
    
    private Map<String, Task> tasks;
    
    public TaskRepository() {
        tasks = new HashMap<String, Task>();
    }
    
    public void add(Task task) {
        tasks.put(task.getId(), task);
    }
    
    public boolean remove(String id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
            return true;
        }
        return false;
    }
    
    public boolean update(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
            return true;
        }
        return false;
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