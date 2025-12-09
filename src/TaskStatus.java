/**
 * SRP + Abstraction: Enumerates valid lifecycle states for a Task.
 * Encapsulation: Prevents use of arbitrary strings for task status.
 */
public enum TaskStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
