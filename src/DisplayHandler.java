import java.util.List;

/**
 * SRP: Handles output and formatting concerns only (no business logic).
 * Encapsulation: Central place for how tasks and messages are presented to the user.
 */
public class DisplayHandler {

    public void displayTask(Task task) {
        System.out.println(task);
        System.out.println("----------------------");
    }

    public void displayTasks(List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            System.out.println("No tasks.");
            return;
        }
        for (Task task : tasks) {
            displayTask(task);
        }
    }

    public void displayTaskCreated(Task task) {
        System.out.println("Task created:");
        displayTask(task);
    }

    public void displayCurrentTask(Task task) {
        System.out.println("\nCurrent Task:");
        displayTask(task);
    }

    public void displayUpdateInstructions() {
        System.out.println("Leave field empty to skip updating it.");
    }

    public void displaySearchResults(String searchTerm, List<Task> results) {
        System.out.println("\nSearch results for: " + searchTerm);
        displayTasks(results);
    }

    public void displayFilteredTasks(String filterType, Object filterValue, List<Task> tasks) {
        System.out.println("\nTasks with " + filterType + " " + filterValue + ":");
        displayTasks(tasks);
    }

    public void displayStatistics(String stats) {
        System.out.println("\n=== TASK STATISTICS ===");
        System.out.println(stats);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(String error) {
        System.out.println(error);
    }
}