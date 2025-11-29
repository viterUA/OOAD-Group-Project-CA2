import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class TaskService {
    
    private ITaskRepository repository;
    
    public TaskService(ITaskRepository repository) {
        this.repository = repository;
    }
    
    public Task createTask(String title, String description, Priority priority, LocalDate dueDate) {
        Task task = new Task(title, description, priority, dueDate);
        repository.add(task);
        return task;
    }
    
    public boolean deleteTask(String id) {
        return repository.remove(id);
    }

    public boolean updateTask(
            String id,
            String title,
            String description,
            Priority priority,
            LocalDate dueDate,
            TaskStatus status
    ) {
        Task existing = repository.findById(id);
        if (existing == null) {
            return false;
        }

        if (title != null) existing.setTitle(title);
        if (description != null) existing.setDescription(description);
        if (priority != null) existing.setPriority(priority);
        if (dueDate != null) existing.setDueDate(dueDate);
        if (status != null) existing.setStatus(status);

        return repository.update(existing);
    }


    public Task getTask(String id) {
        return repository.findById(id);
    }
    
    public List<Task> getAllTasks() {
        return repository.getAll();
    }
    
    public List<Task> filterByStatus(TaskStatus status) {
        List<Task> allTasks = repository.getAll();
        List<Task> filteredTasks = new ArrayList<Task>();
        
        for (int i = 0; i < allTasks.size(); i++) {
            Task task = allTasks.get(i);
            if (task.getStatus() == status) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }
    
    public List<Task> filterByPriority(Priority priority) {
        List<Task> allTasks = repository.getAll();
        List<Task> filteredTasks = new ArrayList<Task>();
        
        for (int i = 0; i < allTasks.size(); i++) {
            Task task = allTasks.get(i);
            if (task.getPriority() == priority) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    public List<Task> applyFilter(ITaskFilter filter) {
        List<Task> allTasks = repository.getAll();
        return filter.filter(allTasks);
    }
    
    public String getStatistics() {
        List<Task> allTasks = repository.getAll();
        
        int pendingCount = 0;
        int inProgressCount = 0;
        int completedCount = 0;
        int cancelledCount = 0;
        
        int lowCount = 0;
        int mediumCount = 0;
        int highCount = 0;
        int urgentCount = 0;
        
        for (int i = 0; i < allTasks.size(); i++) {
            Task task = allTasks.get(i);
            switch (task.getStatus()) {
                case PENDING:
                    pendingCount++;
                    break;
                case IN_PROGRESS:
                    inProgressCount++;
                    break;
                case COMPLETED:
                    completedCount++;
                    break;
                case CANCELLED:
                    cancelledCount++;
                    break;
            }
            
            switch (task.getPriority()) {
                case LOW:
                    lowCount++;
                    break;
                case MEDIUM:
                    mediumCount++;
                    break;
                case HIGH:
                    highCount++;
                    break;
                case URGENT:
                    urgentCount++;
                    break;
            }
        }
        
        String stats = "Task Statistics:\n";
        stats += "Total Tasks: " + allTasks.size() + "\n";
        stats += "\nBy Status:\n";
        stats += "  Pending: " + pendingCount + "\n";
        stats += "  In Progress: " + inProgressCount + "\n";
        stats += "  Completed: " + completedCount + "\n";
        stats += "  Cancelled: " + cancelledCount + "\n";
        stats += "\nBy Priority:\n";
        stats += "  Low: " + lowCount + "\n";
        stats += "  Medium: " + mediumCount + "\n";
        stats += "  High: " + highCount + "\n";
        stats += "  Urgent: " + urgentCount;
        
        return stats;
    }
}