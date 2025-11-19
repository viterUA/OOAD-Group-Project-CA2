import java.util.ArrayList;
import java.util.List;

public class PriorityFilter implements ITaskFilter {

    private Priority priority;

    public PriorityFilter(Priority priority) {
        this.priority = priority;
    }

    public List<Task> filter(List<Task> tasks) {
        List<Task> result = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getPriority() == priority) {
                result.add(task);
            }
        }

        return result;
    }
}
