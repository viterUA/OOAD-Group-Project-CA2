import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * SRP: Coordinates overall application flow and delegates work to handlers and TaskService.
 * Abstraction + Encapsulation: Uses high-level interfaces/services rather than dealing with low-level details.
 * OOP: Collaborates with other objects through their public APIs, not their internals.
 */
public class TodoApp {

    private final TaskService service;
    private final InputHandler inputHandler;
    private final MenuHandler menuHandler;
    private final DisplayHandler displayHandler;
    private boolean running;

    public TodoApp(TaskService service) {
        this.service = service;
        Scanner scanner = new Scanner(System.in);
        this.inputHandler = new InputHandler(scanner);
        this.menuHandler = new MenuHandler();
        this.displayHandler = new DisplayHandler();
        this.running = true;
    }

    public void start() {
        while (running) {
            menuHandler.showMainMenu();
            String choice = inputHandler.readChoice("");

            switch (choice) {
                case "1" -> handleAddTask();
                case "2" -> handleViewTasks();
                case "3" -> handleUpdateTask();
                case "4" -> handleDeleteTask();
                case "5" -> handleSearchTasks();
                case "6" -> handleFilterTasks();
                case "7" -> handleStatistics();
                case "8" -> {
                    menuHandler.showExitMessage();
                    running = false;
                }
                default -> menuHandler.showInvalidOptionMessage();
            }
        }
    }


    private void handleAddTask() {
        String title = inputHandler.readString("Title: ");
        String description = inputHandler.readString("Description: ");

        Priority priority = inputHandler.readPriority();
        if (priority == null) {
            displayHandler.displayError("Wrong priority.");
            return;
        }

        LocalDate dueDate = inputHandler.readDate();
        if (dueDate == null) {
            return;
        }

        Task task = service.createTask(title, description, priority, dueDate);
        displayHandler.displayTaskCreated(task);
    }

    private void handleViewTasks() {
        List<Task> tasks = service.getAllTasks();
        displayHandler.displayTasks(tasks);
    }


    private void handleUpdateTask() {
        String id = inputHandler.readString("Enter task ID to update: ");

        Task task = service.getTask(id);
        if (task == null) {
            displayHandler.displayError("Task not found!");
            return;
        }

        displayHandler.displayCurrentTask(task);
        displayHandler.displayUpdateInstructions();

        String title = inputHandler.readOptionalString("New title: ");
        String description = inputHandler.readOptionalString("New description: ");
        Priority priority = inputHandler.readPriorityOptional();
        LocalDate dueDate = inputHandler.readDateOptional();
        TaskStatus newStatus = inputHandler.readStatusOptional();

        boolean updated = service.updateTask(id, title, description, priority, dueDate, newStatus);

        if (updated) {
            displayHandler.displayMessage("Task updated successfully!");
        } else {
            displayHandler.displayError("Update failed.");
        }
    }

    private void handleDeleteTask() {
        String id = inputHandler.readString("Enter task ID to delete: ");

        boolean removed = service.deleteTask(id);
        if (removed) {
            displayHandler.displayMessage("Task deleted.");
        } else {
            displayHandler.displayError("Task not found.");
        }
    }


    private void handleSearchTasks() {
        String searchTerm = inputHandler.readString("Enter search term (title or description): ");

        if (searchTerm.isEmpty()) {
            displayHandler.displayError("Search term cannot be empty.");
            return;
        }

        List<Task> allTasks = service.getAllTasks();
        List<Task> searchResults = allTasks.stream()
                .filter(task -> task.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) ||
                        task.getDescription().toLowerCase().contains(searchTerm.toLowerCase()))
                .toList();

        displayHandler.displaySearchResults(searchTerm, searchResults);
    }

    private void handleFilterTasks() {
        menuHandler.showFilterMenu();
        String choice = inputHandler.readChoice("");

        switch (choice) {
            case "1" -> {
                Priority priority = inputHandler.readPriority();
                if (priority != null) {
                    ITaskFilter filter = new PriorityFilter(priority);
                    List<Task> filteredTasks = service.applyFilter(filter);
                    displayHandler.displayFilteredTasks("priority", priority, filteredTasks);
                } else {
                    displayHandler.displayError("Invalid priority selected.");
                }
            }
            case "2" -> {
                TaskStatus status = inputHandler.readStatus();
                if (status != null) {
                    ITaskFilter filter = new StatusFilter(status);
                    List<Task> filteredTasks = service.applyFilter(filter);
                    displayHandler.displayFilteredTasks("status", status, filteredTasks);
                } else {
                    displayHandler.displayError("Invalid status selected.");
                }
            }
            default -> displayHandler.displayError("Invalid filter option.");
        }
    }

    private void handleStatistics() {
        String stats = service.getStatistics();
        displayHandler.displayStatistics(stats);
    }

}
