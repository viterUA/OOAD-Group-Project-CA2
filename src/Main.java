import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ITaskRepository repository = new TaskRepository();
        TaskService taskService = new TaskService(repository);

        Task task1 = taskService.createTask(
                "assignment",
                "Finish the task management system",
                Priority.HIGH,
                LocalDate.of(2025, 11, 25)
        );

        Task task2 = taskService.createTask(
                "test",
                "Finish the test",
                Priority.LOW,
                LocalDate.of(2025, 11, 26)
        );

        List<Task> allTasks = taskService.getAllTasks();
        for (int i = 0; i < allTasks.size(); i++) {
            System.out.println("\nAll: " + allTasks.get(i));
        }

        List<Task> highPriorityTasks = taskService.filterByPriority(Priority.HIGH);
        for (int i = 0; i < highPriorityTasks.size(); i++) {
            System.out.println("\nHigh: " + highPriorityTasks.get(i));
        }
        System.out.println();

        List<Task> lowPriorityTasks = taskService.filterByPriority(Priority.LOW);
        for (int i = 0; i < lowPriorityTasks.size(); i++) {
            System.out.println("\nLow: " + lowPriorityTasks.get(i));
        }
    }
}
