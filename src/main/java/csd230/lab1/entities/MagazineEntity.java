package csd230.lab1.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity @DiscriminatorValue("MAGAZINE")
public class MagazineEntity extends PublicationEntity {
    private int orderQty;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime currentIssue;
    public MagazineEntity() {}
    public MagazineEntity(String t, double p, int c, int o, LocalDateTime d) { super(t, p, c); this.orderQty = o; this.currentIssue = d; }
    public MagazineEntity(String t, double p, int c, int o) {
        super(t, p, c);
        this.orderQty = o;
        this.currentIssue = LocalDateTime.now(); // Default to current date and time
    }
    public int getOrderQty() { return orderQty; }
    public void setOrderQty(int o) { this.orderQty = o; }
    public void setCurrentIssue(LocalDateTime d) { this.currentIssue = d; }
    public LocalDateTime getCurrentIssue() { return currentIssue; }
    @Override public String toString() { return "Mag{issue=" + currentIssue + ", " + super.toString() + "}"; }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MagazineEntity that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getTitle(), that.getTitle());
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTitle());
    }
}
