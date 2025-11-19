import java.util.ArrayList;
import java.util.List;

public class StatusFilter implements ITaskFilter {

    private TaskStatus status;

    public StatusFilter(TaskStatus status) {
        this.status = status;
    }

    public List<Task> filter(List<Task> tasks) {
        List<Task> result = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getStatus() == status) {
                result.add(task);
            }
        }

        return result;
    }
}
