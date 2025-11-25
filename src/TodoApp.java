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
                    handleUpdateTask();
                    break;
                case "4":
                    handleDeleteTask();
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

        return switch (input) {
            case "1" -> Priority.LOW;
            case "2" -> Priority.MEDIUM;
            case "3" -> Priority.HIGH;
            case "4" -> Priority.URGENT;
            default -> null;
        };

    }

    private TaskStatus readStatus() {
        System.out.println("Choose Status:");
        System.out.println("1. PENDING");
        System.out.println("2. IN_PROGRESS");
        System.out.println("3. COMPLETED");
        System.out.println("4. CANCELLED");
        System.out.print("Choose: ");

        String input = scanner.nextLine();

        return switch (input) {
            case "1" -> TaskStatus.PENDING;
            case "2" -> TaskStatus.IN_PROGRESS;
            case "3" -> TaskStatus.COMPLETED;
            case "4" -> TaskStatus.CANCELLED;
            default -> null;
        };
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

    private void handleUpdateTask() {
        System.out.print("Enter task ID to update: ");
        String id = scanner.nextLine();

        Task task = service.getTask(id);
        if (task == null) {
            System.out.println("Task not found!");
            return;
        }

        System.out.println("\nCurrent Task:");
        displayTask(task);

        System.out.println("Leave field empty to skip updating it.");

        System.out.print("New title: ");
        String title = scanner.nextLine();
        if (title.isBlank()) title = null;

        System.out.print("New description: ");
        String description = scanner.nextLine();
        if (description.isBlank()) description = null;

        Priority priority = readPriorityOptional();

        LocalDate dueDate = readDateOptional();

        TaskStatus newStatus = readStatusOptional();

        boolean updated = service.updateTask(
                id,
                title,
                description,
                priority,
                dueDate,
                newStatus
        );

        if (updated)
            System.out.println("Task updated successfully!");
        else
            System.out.println("Update failed.");

    }

    private void handleDeleteTask() {
        System.out.print("Enter task ID to delete: ");
        String id = scanner.nextLine();

        boolean removed = service.deleteTask(id);
        if (removed)
            System.out.println("Task deleted.");
        else
            System.out.println("Task not found.");
    }

    private LocalDate readDateOptional() {
        System.out.print("New due date (yyyy-MM-dd) or press Enter to skip: ");
        String input = scanner.nextLine();
        if (input.isBlank()) return null;

        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date. Skipping update.");
            return null;
        }
    }

    private Priority readPriorityOptional() {
        System.out.print("New priority (Y or N): ");
        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("Y")) return readPriority();
        else if (input.equalsIgnoreCase("N")) return null;
        return readPriority();
    }

    private TaskStatus readStatusOptional() {
        System.out.print("New status (Y or N): ");
        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("Y")) return readStatus();
        else if (input.equalsIgnoreCase("N")) return null;
        return readStatus();
    }

}
