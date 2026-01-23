package csd230.lab1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public abstract class PublicationEntity extends ProductEntity {
    private String title;
    @Column(name = "pub_price") private double price;
    private int copies;
    public PublicationEntity() {}
    public PublicationEntity(String t, double p, int c) { this.title = t; this.price = p; this.copies = c; }

    @Override public void sellItem() {
        if (copies > 0) { copies--; System.out.println("Sold '" + title + "'. Remaining copies: " + copies); }
        else { System.out.println("Cannot sell '" + title + "'. Out of stock."); }
    }
    @Override public double getPrice() { return price; }

    public String getTitle() { return title; }
    public void setTitle(String t) { this.title = t; }
    public void setPrice(double p) { this.price = p; }
    public int getCopies() { return copies; }
    public void setCopies(int c) { this.copies = c; }
    @Override public String toString() { return "Pub{title='" + title + "', price=" + price + ", copies=" + copies + "}"; }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PublicationEntity that)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(getPrice(), that.getPrice()) == 0
                && getCopies() == that.getCopies()
                && Objects.equals(getTitle(), that.getTitle())
                && Objects.equals(getPrice(), that.getPrice());
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTitle(), getPrice(), getCopies());
    }
}
