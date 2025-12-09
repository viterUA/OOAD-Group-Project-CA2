import java.time.LocalDate;

/**
 * SRP + Encapsulation: Represents a single task with its own data and validation logic only.
 * Inheritance: Extends BaseEntity to reuse common id/createdDate fields.
 * Abstraction: Exposes state via getters/setters instead of public fields.
 */
public class Task extends BaseEntity {

    private String title;
    private String description;
    private Priority priority;
    private LocalDate dueDate;
    private TaskStatus status;

    public Task(String title, String description, Priority priority, LocalDate dueDate) {
        super();
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        if (priority == null) {
            throw new IllegalArgumentException("Priority cannot be null");
        }
        if (dueDate == null) {
            throw new IllegalArgumentException("Due date cannot be null");
        }

        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.status = TaskStatus.PENDING;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        if (priority == null) {
            throw new IllegalArgumentException("Priority cannot be null");
        }
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        if (dueDate == null) {
            throw new IllegalArgumentException("Due date cannot be null");
        }
        this.dueDate = dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.status = status;
    }

    @Override
    public String getDisplayInfo() {
        return "Task: " + title + " [" + priority + "]";
    }

    @Override
    public String toString() {
        return  "\nID: " + getId() + "\n" +
                "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Priority: " + priority + "\n" +
                "DueDate: " + dueDate + "\n" +
                "Status:" + status + "\n" +
                "CreatedDate: " + getCreatedDate();
    }
}
