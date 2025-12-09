import java.util.List;

/**
 * ISP + Abstraction: Small, focused interface for filtering tasks.
 * Polymorphism: Different filter implementations can be applied through this single interface type.
 */
public interface ITaskFilter {
    List<Task> filter(List<Task> tasks);
    }
