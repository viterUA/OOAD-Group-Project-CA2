import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public String readOptionalString(String prompt) {
        String input = readString(prompt);
        return input.isBlank() ? null : input;
    }

    public LocalDate readDate() {
        System.out.print("Due date (yyyy-MM-dd): ");
        String input = scanner.nextLine();
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date format.");
            return null;
        }
    }

    public LocalDate readDateOptional() {
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

    public Priority readPriority() {
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

    public Priority readPriorityOptional() {
        System.out.print("New priority (Y or N): ");
        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("Y")) return readPriority();
        else if (input.equalsIgnoreCase("N")) return null;
        return readPriority();
    }

    public TaskStatus readStatus() {
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

    public TaskStatus readStatusOptional() {
        System.out.print("New status (Y or N): ");
        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("Y")) return readStatus();
        else if (input.equalsIgnoreCase("N")) return null;
        return readStatus();
    }

    public String readChoice(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}