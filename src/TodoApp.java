import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class TodoApp {

    private TaskService service;
    private Scanner scanner;
    private boolean running;

    public TodoApp(TaskService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
        this.running = true;
    }

    public void start() {
        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    handleAddTask();
                    break;
                case "2":
                    handleViewTasks();
                    break;
                case "3":
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
    }

    private void showMenu() {
        System.out.println("┌────────────────────┐");
        System.out.println("│ MAIN MENU          │");
        System.out.println("├────────────────────┤");
        System.out.println("│ 1. Add New Task    │");
        System.out.println("│ 2. View All Tasks  │");
        System.out.println("│ 3. Update Task     │");
        System.out.println("│ 4. Delete Task     │");
        System.out.println("│ 5. Search Tasks    │");
        System.out.println("│ 6. Filter Tasks    │");
        System.out.println("│ 7. View Statistics │");
        System.out.println("│ 8. Exit            │");
        System.out.println("└────────────────────┘");
        System.out.print("Choose option: ");
    }

    private void handleAddTask() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        Priority priority = readPriority();
        if (priority == null) {
            System.out.println("Wrong priority.");
            return;
        }

        LocalDate dueDate = readDate();
        if (dueDate == null) {
            return;
        }

        Task task = service.createTask(title, description, priority, dueDate);
        System.out.println("Task created:");
        displayTask(task);
    }

    private void handleViewTasks() {
        List<Task> tasks = service.getAllTasks();
        displayTasks(tasks);
    }

    private LocalDate readDate() {
        System.out.print("Due date (yyyy-MM-dd): ");
        String input = scanner.nextLine();
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date format.");
            return null;
        }
    }

    private Priority readPriority() {
        System.out.println("Priority:");
        System.out.println("1. LOW");
        System.out.println("2. MEDIUM");
        System.out.println("3. HIGH");
        System.out.println("4. URGENT");
        System.out.print("Choose: ");
        String input = scanner.nextLine().trim();

        if (input.equals("1")) return Priority.LOW;
        if (input.equals("2")) return Priority.MEDIUM;
        if (input.equals("3")) return Priority.HIGH;
        if (input.equals("4")) return Priority.URGENT;
        return null;
    }

    private void displayTask(Task task) {
        System.out.println(task);
        System.out.println("----------------------");
    }

    private void displayTasks(List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            System.out.println("No tasks.");
            return;
        }
        for (Task task : tasks) {
            displayTask(task);
        }
    }
}
