import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ITaskRepository repository = new TaskRepository();
        TaskService taskService = new TaskService(repository);

        TodoApp app = new TodoApp(taskService);
        app.start();

//        Task task1 = taskService.createTask(
//                "assignment",
//                "Finish the task management system",
//                Priority.HIGH,
//                LocalDate.of(2025, 11, 25)
//        );
//
//        Task task2 = taskService.createTask(
//                "test",
//                "Finish the test",
//                Priority.LOW,
//                LocalDate.of(2025, 11, 26)
//        );
//
//        List<Task> allTasks = taskService.getAllTasks();
//        System.out.println("All priority tasks:");
//        for (int i = 0; i < allTasks.size(); i++) {
//            System.out.println(allTasks.get(i));
//        }
//
//        List<Task> highPriorityTasks = taskService.filterByPriority(Priority.HIGH);
//        System.out.println("====================================");
//        System.out.println("\nHigh priority tasks:");
//        for (int i = 0; i < highPriorityTasks.size(); i++) {
//            System.out.println(highPriorityTasks.get(i));
//        }
//
//        List<Task> lowPriorityTasks = taskService.filterByPriority(Priority.LOW);
//        System.out.println("====================================");
//        System.out.println("\nLow priority tasks:");
//        for (int i = 0; i < lowPriorityTasks.size(); i++) {
//            System.out.println(lowPriorityTasks.get(i));
//        }
    }
}
