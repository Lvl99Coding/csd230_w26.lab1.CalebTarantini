package csd230.lab1.pojos;

import csd230.lab1.entities.GuitarEntity;

import java.util.Objects;

/**
 * DTO for {@link GuitarEntity}
 */
public abstract class Guitar extends Product {
    private String brand = "";
    private String model = "";
    private int numberOfStrings = 0;
    private double price = 0.0;

    public Guitar() {
    }

    public Guitar(String brand, String model, int numberOfStrings, double price) {
        this.brand = brand;
        this.model = model;
        this.numberOfStrings = numberOfStrings;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getNumberOfStrings() {
        return numberOfStrings;
    }

    public void setNumberOfStrings(int numberOfStrings) {
        this.numberOfStrings = numberOfStrings;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Guitar)) return false;
        Guitar that = (Guitar) o;
        return Double.compare(that.price, price) == 0 &&
                numberOfStrings == that.numberOfStrings &&
                brand.equals(that.brand) &&
                model.equals(that.model);
    }

    @Override
    public void edit() {
        System.out.println("Edit Guitar Brand [" + this.brand + "]:");
        this.brand = getInput(this.brand);
        System.out.println("Edit Guitar Model [" + this.model + "]:");
        this.model = getInput(this.model);
        System.out.println("Edit Number of Strings [" + this.numberOfStrings + "]:");
        this.numberOfStrings = Integer.parseInt(getInput(Integer.toString(this.numberOfStrings)));
        System.out.println("Edit Guitar Price [" + this.price + "]:");
        this.price = Double.parseDouble(getInput(Double.toString(this.price)));
    }

    @Override
    public void initialize() {
        System.out.println("Enter Guitar Brand:");
        this.brand = getInput("Unknown Brand");
        System.out.println("Enter Guitar Model:");
        this.model = getInput("Unknown Model");
        System.out.println("Enter Number of Strings:");
        this.numberOfStrings = Integer.parseInt(getInput("6"));
        System.out.println("Enter Guitar Price:");
        this.price = Double.parseDouble(getInput("0.0"));
    }

    @Override
    public String toString(){
        return "Guitar{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", numberOfStrings=" + numberOfStrings +
                ", price=" + price +
                '}';
    }

    @Override
    public int hashCode() {return Objects.hash(brand, model, numberOfStrings, price);}



}
