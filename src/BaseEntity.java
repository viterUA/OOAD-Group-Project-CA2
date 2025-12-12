import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Inheritance + Abstraction: Base class for entities that need an id and createdDate.
 * Encapsulation: Provides common state and accessors shared by subclasses like Task.
 */
public abstract class BaseEntity {

    private static final AtomicInteger NEXT_ID = new AtomicInteger(1);

    protected String id;
    protected LocalDate createdDate;

    public BaseEntity() {
        this.id = String.valueOf(NEXT_ID.getAndIncrement());
        this.createdDate = LocalDate.now();
    }

    public String getId() {
        return id;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public abstract String getDisplayInfo();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BaseEntity that = (BaseEntity) obj;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}