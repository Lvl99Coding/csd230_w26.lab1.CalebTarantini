package csd230.lab1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity @DiscriminatorValue("DISCMAG")
public class DiscMagEntity extends MagazineEntity {
    private boolean hasDisc;
    public DiscMagEntity() {}
    public DiscMagEntity(String t, double p, int c, int o, LocalDateTime d, boolean h) { super(t, p, c, o, d); this.hasDisc = h; }
    public boolean getHasDisc() { return hasDisc; }
    public void setHasDisc(boolean h) { this.hasDisc = h; }
    @Override public String toString() { return "DiscMag{disc=" + hasDisc + ", " + super.toString() + "}"; }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DiscMagEntity that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getTitle(), that.getTitle());
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTitle());
    }
}
