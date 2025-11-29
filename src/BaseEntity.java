import java.time.LocalDate;
import java.util.UUID;

public abstract class BaseEntity {

    protected String id;
    protected LocalDate createdDate;

    public BaseEntity() {
        this.id = UUID.randomUUID().toString();
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